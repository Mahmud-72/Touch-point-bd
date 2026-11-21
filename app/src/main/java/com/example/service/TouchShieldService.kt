package com.example.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Settings
import android.service.quicksettings.TileService
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.R
import com.example.data.LockSource
import com.example.data.ShieldPreferences
import com.example.data.ShieldSettings
import com.example.data.ShieldStateHolder
import com.example.sensor.PocketSensorManager
import com.example.ui.getAppStrings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TouchShieldService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val mainHandler = Handler(Looper.getMainLooper())

    private lateinit var preferences: ShieldPreferences
    private lateinit var overlayController: OverlayWindowController
    private lateinit var pocketSensorManager: PocketSensorManager
    private lateinit var notificationManager: NotificationManager

    private var latestSettings = ShieldSettings()
    private var settingsLoaded = false
    private var isForegroundStarted = false

    private var countdownJob: Job? = null
    private var sessionTimerRunnable: Runnable? = null

    private var telephonyManager: TelephonyManager? = null
    private var activeTelephonyCallback: Any? = null

    @Suppress("DEPRECATION")
    private var legacyPhoneListener: PhoneStateListener? = null

    override fun onCreate() {
        super.onCreate()
        preferences = ShieldPreferences(applicationContext)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
        createNotificationChannel()

        overlayController = OverlayWindowController(
            context = applicationContext,
            onBubbleLockClicked = { initiateLock(LockSource.FLOATING_BUBBLE) },
            onBubbleDoubleTapHidden = {
                ShieldStateHolder.setBubbleTemporarilyHidden(true)
                syncOverlayState()
                refreshNotification()
            },
            onCountdownCancelled = { cancelCountdown() },
            onTouchBlocked = {
                ShieldStateHolder.incrementSessionBlockedTouches()
                serviceScope.launch(Dispatchers.IO) {
                    preferences.incrementBlockedTouches(1L)
                }
            },
            onUnlockTriggered = { unlockScreen() }
        )

        pocketSensorManager = PocketSensorManager(
            context = applicationContext,
            onPocketLockTriggered = {
                if (!ShieldStateHolder.runtimeState.value.isScreenLocked &&
                    latestSettings.pocketAutoLockEnabled
                ) {
                    cancelCountdown()
                    lockScreenImmediately(LockSource.POCKET_SENSOR)
                }
            },
            onPocketUnlockTriggered = {
                val state = ShieldStateHolder.runtimeState.value
                if (state.isScreenLocked &&
                    state.lockSource == LockSource.POCKET_SENSOR &&
                    latestSettings.pocketAutoUnlockEnabled
                ) {
                    unlockScreen()
                }
            }
        )

        ShieldStateHolder.setServiceRunning(true)

        serviceScope.launch {
            preferences.settingsFlow.collectLatest { settings ->
                latestSettings = settings
                settingsLoaded = true
                pocketSensorManager.updateConfig(
                    autoLock = settings.pocketAutoLockEnabled,
                    autoUnlock = settings.pocketAutoUnlockEnabled,
                    debounceMs = settings.pocketDebounceMs,
                    orientationFilter = settings.pocketOrientationFilterEnabled
                )
                if (settings.pocketAutoLockEnabled) {
                    pocketSensorManager.startListening()
                } else {
                    pocketSensorManager.stopListening()
                }

                updateCallStateObserver(settings.autoUnlockOnCallEnabled)
                syncOverlayState()
                refreshNotification()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ensureForegroundStarted()

        when (intent?.action) {
            ACTION_LOCK_NOW -> {
                val sourceName = intent.getStringExtra(EXTRA_LOCK_SOURCE)
                val source = runCatching { LockSource.valueOf(sourceName ?: "") }
                    .getOrDefault(LockSource.MANUAL_APP)
                if (settingsLoaded) {
                    initiateLock(source)
                } else {
                    serviceScope.launch {
                        latestSettings = preferences.settingsFlow.first()
                        settingsLoaded = true
                        initiateLock(source)
                    }
                }
            }

            ACTION_UNLOCK -> {
                cancelCountdown()
                unlockScreen()
            }

            ACTION_TOGGLE_POCKET -> {
                serviceScope.launch {
                    if (!settingsLoaded) {
                        latestSettings = preferences.settingsFlow.first()
                        settingsLoaded = true
                    }
                    preferences.setPocketAutoLock(!latestSettings.pocketAutoLockEnabled)
                }
            }

            ACTION_SHOW_BUBBLE -> {
                ShieldStateHolder.setBubbleTemporarilyHidden(false)
                syncOverlayState()
                refreshNotification()
            }

            ACTION_STOP_SERVICE -> {
                stopShieldGracefully()
                return START_NOT_STICKY
            }

            else -> {
                syncOverlayState()
                refreshNotification()
            }
        }

        return START_STICKY
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        overlayController.onConfigurationChanged()
    }

    override fun onDestroy() {
        cancelCountdown()
        cancelSessionTimer()
        unregisterCallStateObserver()
        overlayController.removeAllOverlays()
        pocketSensorManager.stopListening()
        ShieldStateHolder.setServiceRunning(false)
        requestTileRefresh()
        serviceScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun initiateLock(source: LockSource) {
        if (!Settings.canDrawOverlays(this)) return
        if (ShieldStateHolder.runtimeState.value.isScreenLocked) return
        val delaySec = latestSettings.lockCountdownSeconds
        if (delaySec <= 0 || source == LockSource.POCKET_SENSOR) {
            lockScreenImmediately(source)
            return
        }

        cancelCountdown()
        countdownJob = serviceScope.launch {
            for (sec in delaySec downTo 1) {
                ShieldStateHolder.setCountdownRemaining(sec)
                overlayController.showCountdownOverlay(sec)
                delay(1000L)
            }
            ShieldStateHolder.setCountdownRemaining(0)
            overlayController.hideCountdownOverlay()
            lockScreenImmediately(source)
        }
    }

    private fun cancelCountdown() {
        countdownJob?.cancel()
        countdownJob = null
        ShieldStateHolder.setCountdownRemaining(0)
        overlayController.hideCountdownOverlay()
    }

    private fun lockScreenImmediately(source: LockSource) {
        if (!Settings.canDrawOverlays(this)) return
        if (ShieldStateHolder.runtimeState.value.isScreenLocked) return

        ShieldStateHolder.setScreenLocked(true, source)
        syncOverlayState()
        serviceScope.launch(Dispatchers.IO) {
            preferences.incrementLockSessions()
        }
        scheduleSessionTimerIfNeeded()
        refreshNotification()
        requestTileRefresh()
    }

    private fun unlockScreen() {
        cancelSessionTimer()
        ShieldStateHolder.setScreenLocked(false, LockSource.NONE)
        syncOverlayState()
        refreshNotification()
        requestTileRefresh()
    }

    private fun syncOverlayState() {
        val runtime = ShieldStateHolder.runtimeState.value
        overlayController.updateSettings(
            settings = latestSettings,
            isServiceArmed = runtime.isServiceRunning,
            isScreenLocked = runtime.isScreenLocked,
            isBubbleTemporarilyHidden = runtime.isBubbleTemporarilyHidden
        )
    }

    private fun scheduleSessionTimerIfNeeded() {
        cancelSessionTimer()
        val minutes = latestSettings.sessionTimerMinutes
        if (minutes <= 0) return
        val runnable = Runnable {
            if (ShieldStateHolder.runtimeState.value.isScreenLocked) {
                unlockScreen()
            }
        }
        sessionTimerRunnable = runnable
        mainHandler.postDelayed(runnable, minutes * 60_000L)
    }

    private fun cancelSessionTimer() {
        sessionTimerRunnable?.let { mainHandler.removeCallbacks(it) }
        sessionTimerRunnable = null
    }

    private fun updateCallStateObserver(enabled: Boolean) {
        if (!enabled) {
            unregisterCallStateObserver()
            return
        }
        val hasPhonePerm = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED
        if (!hasPhonePerm) {
            unregisterCallStateObserver()
            return
        }

        val tm = telephonyManager ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (activeTelephonyCallback != null) return
            val callback = object : TelephonyCallback(), TelephonyCallback.CallStateListener {
                override fun onCallStateChanged(state: Int) {
                    if (state == TelephonyManager.CALL_STATE_RINGING &&
                        ShieldStateHolder.runtimeState.value.isScreenLocked
                    ) {
                        unlockScreen()
                    }
                }
            }
            runCatching {
                tm.registerTelephonyCallback(mainExecutor, callback)
                activeTelephonyCallback = callback
            }
        } else {
            @Suppress("DEPRECATION")
            if (legacyPhoneListener != null) return
            @Suppress("DEPRECATION")
            val listener = object : PhoneStateListener() {
                @Deprecated("Deprecated in Java")
                override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                    if (state == TelephonyManager.CALL_STATE_RINGING &&
                        ShieldStateHolder.runtimeState.value.isScreenLocked
                    ) {
                        unlockScreen()
                    }
                }
            }
            runCatching {
                @Suppress("DEPRECATION")
                tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE)
                legacyPhoneListener = listener
            }
        }
    }

    private fun unregisterCallStateObserver() {
        val tm = telephonyManager ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val cb = activeTelephonyCallback as? TelephonyCallback
            if (cb != null) {
                runCatching { tm.unregisterTelephonyCallback(cb) }
            }
            activeTelephonyCallback = null
        } else {
            @Suppress("DEPRECATION")
            legacyPhoneListener?.let { listener ->
                runCatching { tm.listen(listener, PhoneStateListener.LISTEN_NONE) }
            }
            legacyPhoneListener = null
        }
    }

    private fun stopShieldGracefully() {
        cancelCountdown()
        cancelSessionTimer()
        unregisterCallStateObserver()
        serviceScope.launch {
            preferences.setArmed(false)
        }
        overlayController.removeAllOverlays()
        ShieldStateHolder.setServiceRunning(false)
        requestTileRefresh()
        ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun ensureForegroundStarted() {
        if (isForegroundStarted) return
        val notification = buildNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ServiceCompat.startForeground(
                this,
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
        isForegroundStarted = true
    }

    private fun refreshNotification() {
        if (!isForegroundStarted) return
        notificationManager.notify(NOTIFICATION_ID, buildNotification())
    }

    private fun buildNotification(): Notification {
        val strings = getAppStrings(latestSettings.appLanguage)
        val runtime = ShieldStateHolder.runtimeState.value
        val isLocked = runtime.isScreenLocked

        val openAppIntent = PendingIntent.getActivity(
            this,
            100,
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val toggleIntent = PendingIntent.getService(
            this,
            101,
            Intent(this, TouchShieldService::class.java).apply {
                action = if (isLocked) ACTION_UNLOCK else ACTION_LOCK_NOW
                putExtra(EXTRA_LOCK_SOURCE, LockSource.NOTIFICATION.name)
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val pocketToggleIntent = PendingIntent.getService(
            this,
            103,
            Intent(this, TouchShieldService::class.java).apply {
                action = ACTION_TOGGLE_POCKET
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val showBubbleIntent = PendingIntent.getService(
            this,
            104,
            Intent(this, TouchShieldService::class.java).apply {
                action = ACTION_SHOW_BUBBLE
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val stopIntent = PendingIntent.getService(
            this,
            102,
            Intent(this, TouchShieldService::class.java).apply {
                action = ACTION_STOP_SERVICE
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val title = if (isLocked) strings.notifTitleLocked else strings.notifTitleArmed
        val body = if (isLocked) strings.notifTextLocked else strings.notifTextArmed
        val primaryActionLabel = if (isLocked) strings.notifActionUnlock else strings.notifActionLock
        val pocketLabel = if (latestSettings.pocketAutoLockEnabled) {
            strings.notifActionPocketOn
        } else {
            strings.notifActionPocketOff
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_shield_lock)
            .setContentTitle(title)
            .setContentText(body)
            .setOngoing(true)
            .setSilent(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(openAppIntent)
            .addAction(R.drawable.ic_shield_lock, primaryActionLabel, toggleIntent)

        if (!isLocked && runtime.isBubbleTemporarilyHidden && latestSettings.floatingBubbleEnabled) {
            builder.addAction(0, strings.notifActionShowBubble, showBubbleIntent)
        } else {
            builder.addAction(0, pocketLabel, pocketToggleIntent)
        }

        builder.addAction(0, strings.notifActionStop, stopIntent)
        return builder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = getString(R.string.notification_channel_desc)
                setShowBadge(false)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun requestTileRefresh() {
        try {
            TileService.requestListeningState(
                applicationContext,
                ComponentName(applicationContext, TouchShieldTileService::class.java)
            )
        } catch (_: Exception) {
        }
    }

    companion object {
        private const val CHANNEL_ID = "touch_shield_channel"
        private const val NOTIFICATION_ID = 4041

        const val ACTION_ARM = "com.example.action.ARM"
        const val ACTION_LOCK_NOW = "com.example.action.LOCK_NOW"
        const val ACTION_UNLOCK = "com.example.action.UNLOCK"
        const val ACTION_TOGGLE_POCKET = "com.example.action.TOGGLE_POCKET"
        const val ACTION_SHOW_BUBBLE = "com.example.action.SHOW_BUBBLE"
        const val ACTION_STOP_SERVICE = "com.example.action.STOP_SERVICE"
        const val EXTRA_LOCK_SOURCE = "extra_lock_source"

        fun startArmed(context: Context) {
            val intent = Intent(context, TouchShieldService::class.java).apply {
                action = ACTION_ARM
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun lockScreenNow(context: Context, source: LockSource) {
            val intent = Intent(context, TouchShieldService::class.java).apply {
                action = ACTION_LOCK_NOW
                putExtra(EXTRA_LOCK_SOURCE, source.name)
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun unlockScreenNow(context: Context) {
            val intent = Intent(context, TouchShieldService::class.java).apply {
                action = ACTION_UNLOCK
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun stop(context: Context) {
            val intent = Intent(context, TouchShieldService::class.java).apply {
                action = ACTION_STOP_SERVICE
            }
            context.startService(intent)
        }
    }
}
