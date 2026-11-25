package com.example.service

import android.app.AppOpsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.os.Process

data class WatchedAppInfo(
    val packageName: String,
    val label: String,
    val isPopularPreset: Boolean = false
)

class AppWatchMonitor(
    private val context: Context,
    private val onWatchedAppEntered: (packageName: String) -> Unit,
    private val onWatchedAppExited: () -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())
    private val usageStatsManager =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as? UsageStatsManager
    private val powerManager =
        context.getSystemService(Context.POWER_SERVICE) as? PowerManager

    private var isMonitoring = false
    private var watchedPackages: Set<String> = emptySet()
    private var lastForegroundPkg: String? = null
    private var currentlyInWatchedApp: String? = null

    private val pollRunnable = object : Runnable {
        override fun run() {
            if (!isMonitoring) return
            pollOnce()
            handler.postDelayed(this, POLL_INTERVAL_MS)
        }
    }

    fun updateConfig(enabled: Boolean, packages: Set<String>) {
        watchedPackages = packages
        val shouldRun = enabled && packages.isNotEmpty() && hasUsageStatsPermission(context)
        if (shouldRun && !isMonitoring) {
            isMonitoring = true
            handler.removeCallbacks(pollRunnable)
            handler.post(pollRunnable)
        } else if (!shouldRun && isMonitoring) {
            stop()
        } else if (isMonitoring) {
            val active = currentlyInWatchedApp
            if (active != null && active !in watchedPackages) {
                currentlyInWatchedApp = null
                onWatchedAppExited()
            }
        }
    }

    fun stop() {
        isMonitoring = false
        handler.removeCallbacks(pollRunnable)
        if (currentlyInWatchedApp != null) {
            currentlyInWatchedApp = null
            onWatchedAppExited()
        }
        lastForegroundPkg = null
    }

    private fun pollOnce() {
        val usm = usageStatsManager ?: return
        if (powerManager?.isInteractive == false) return
        if (!hasUsageStatsPermission(context)) return

        val now = System.currentTimeMillis()
        val events = runCatching { usm.queryEvents(now - LOOKBACK_WINDOW_MS, now) }.getOrNull()
            ?: return

        val event = UsageEvents.Event()
        var latestPkg: String? = null
        while (events.hasNextEvent()) {
            events.getNextEvent(event)
            val isForeground = event.eventType == UsageEvents.Event.ACTIVITY_RESUMED ||
                @Suppress("DEPRECATION")
                (event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND)
            if (isForeground && !event.packageName.isNullOrBlank()) {
                latestPkg = event.packageName
            }
        }

        val resolvedPkg = latestPkg ?: lastForegroundPkg ?: return
        if (resolvedPkg == context.packageName) return
        lastForegroundPkg = resolvedPkg

        val isWatched = resolvedPkg in watchedPackages
        if (isWatched && currentlyInWatchedApp != resolvedPkg) {
            currentlyInWatchedApp = resolvedPkg
            onWatchedAppEntered(resolvedPkg)
        } else if (!isWatched && currentlyInWatchedApp != null) {
            currentlyInWatchedApp = null
            onWatchedAppExited()
        }
    }

    companion object {
        private const val POLL_INTERVAL_MS = 1200L
        private const val LOOKBACK_WINDOW_MS = 7000L

        val POPULAR_PRESET_APPS = listOf(
            WatchedAppInfo("com.google.android.youtube", "YouTube", isPopularPreset = true),
            WatchedAppInfo("com.google.android.apps.youtube.kids", "YouTube Kids", isPopularPreset = true),
            WatchedAppInfo("com.google.android.apps.maps", "Google Maps", isPopularPreset = true),
            WatchedAppInfo("com.google.android.GoogleCamera", "Camera", isPopularPreset = true),
            WatchedAppInfo("com.netflix.mediaclient", "Netflix", isPopularPreset = true),
            WatchedAppInfo("com.spotify.music", "Spotify", isPopularPreset = true),
            WatchedAppInfo("com.android.chrome", "Chrome", isPopularPreset = true),
            WatchedAppInfo("com.whatsapp", "WhatsApp", isPopularPreset = true)
        )

        fun hasUsageStatsPermission(context: Context): Boolean {
            return try {
                val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
                val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    appOps.unsafeCheckOpNoThrow(
                        AppOpsManager.OPSTR_GET_USAGE_STATS,
                        Process.myUid(),
                        context.packageName
                    )
                } else {
                    @Suppress("DEPRECATION")
                    appOps.checkOpNoThrow(
                        AppOpsManager.OPSTR_GET_USAGE_STATS,
                        Process.myUid(),
                        context.packageName
                    )
                }
                mode == AppOpsManager.MODE_ALLOWED
            } catch (_: Exception) {
                false
            }
        }

        fun loadSelectableApps(context: Context): List<WatchedAppInfo> {
            val pm = context.packageManager
            val installedMap = mutableMapOf<String, String>()
            runCatching {
                val launcherIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
                val resolved = pm.queryIntentActivities(launcherIntent, 0)
                resolved.forEach { info ->
                    val pkg = info.activityInfo?.packageName ?: return@forEach
                    if (pkg != context.packageName) {
                        val label = info.loadLabel(pm)?.toString()?.trim().orEmpty()
                        if (label.isNotEmpty()) {
                            installedMap[pkg] = label
                        }
                    }
                }
            }

            val result = mutableListOf<WatchedAppInfo>()
            val seen = mutableSetOf<String>()

            POPULAR_PRESET_APPS.forEach { preset ->
                val resolvedLabel = installedMap[preset.packageName] ?: preset.label
                result.add(preset.copy(label = resolvedLabel))
                seen.add(preset.packageName)
            }

            installedMap.entries
                .filter { it.key !in seen }
                .sortedBy { it.value.lowercase() }
                .forEach { (pkg, label) ->
                    result.add(WatchedAppInfo(pkg, label, isPopularPreset = false))
                }

            return result
        }
    }
}
