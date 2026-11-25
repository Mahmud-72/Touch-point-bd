package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import androidx.core.content.ContextCompat

class VolumeShortcutMonitor(
    private val context: Context,
    private val onVolumeDoublePress: () -> Unit
) {
    private var isRegistered = false
    private var enabled = false
    private var lastPressMs = 0L
    private var lastCooldownFireMs = 0L
    private var lastStreamType = -1

    private val volumeReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            if (!enabled) return
            if (intent?.action != VOLUME_CHANGED_ACTION) return
            val stream = intent.getIntExtra(EXTRA_VOLUME_STREAM_TYPE, -1)
            val newVal = intent.getIntExtra(EXTRA_VOLUME_STREAM_VALUE, -1)
            val prevVal = intent.getIntExtra(EXTRA_PREV_VOLUME_STREAM_VALUE, -1)
            if (newVal == prevVal && newVal != -1) return

            if (stream == AudioManager.STREAM_MUSIC ||
                stream == AudioManager.STREAM_RING ||
                stream == AudioManager.STREAM_NOTIFICATION ||
                stream == AudioManager.STREAM_SYSTEM
            ) {
                registerVolumeChangeEvent(stream, System.currentTimeMillis())
            }
        }
    }

    fun updateEnabled(shouldEnable: Boolean) {
        enabled = shouldEnable
        if (shouldEnable && !isRegistered) {
            runCatching {
                ContextCompat.registerReceiver(
                    context,
                    volumeReceiver,
                    IntentFilter(VOLUME_CHANGED_ACTION),
                    ContextCompat.RECEIVER_NOT_EXPORTED
                )
                isRegistered = true
            }
        } else if (!shouldEnable && isRegistered) {
            stop()
        }
    }

    fun stop() {
        enabled = false
        if (isRegistered) {
            runCatching { context.unregisterReceiver(volumeReceiver) }
            isRegistered = false
        }
        lastPressMs = 0L
        lastStreamType = -1
    }

    internal fun registerVolumeChangeEvent(stream: Int, nowMs: Long): Boolean {
        if (lastCooldownFireMs != 0L && nowMs - lastCooldownFireMs < FIRE_COOLDOWN_MS) return false
        val withinWindow = (nowMs - lastPressMs) in MIN_TAP_GAP_MS..DOUBLE_PRESS_WINDOW_MS
        if (withinWindow && (lastStreamType == -1 || lastStreamType == stream)) {
            lastPressMs = 0L
            lastStreamType = -1
            lastCooldownFireMs = nowMs
            onVolumeDoublePress()
            return true
        } else {
            lastPressMs = nowMs
            lastStreamType = stream
            return false
        }
    }

    companion object {
        const val VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION"
        private const val EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE"
        private const val EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE"
        private const val EXTRA_PREV_VOLUME_STREAM_VALUE = "android.media.EXTRA_PREV_VOLUME_STREAM_VALUE"

        const val MIN_TAP_GAP_MS = 60L
        const val DOUBLE_PRESS_WINDOW_MS = 480L
        const val FIRE_COOLDOWN_MS = 1200L
    }
}
