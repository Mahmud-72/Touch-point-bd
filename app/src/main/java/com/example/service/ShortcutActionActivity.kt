package com.example.service

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import com.example.MainActivity
import com.example.data.LockSource
import com.example.data.ShieldPreferences
import com.example.data.ShieldStateHolder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ShortcutActionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (intent?.action) {
            ACTION_SHORTCUT_LOCK, ACTION_WIDGET_LOCK -> {
                if (!Settings.canDrawOverlays(this)) {
                    startActivity(
                        Intent(this, MainActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        }
                    )
                } else if (ShieldStateHolder.runtimeState.value.isScreenLocked) {
                    TouchShieldService.unlockScreenNow(this)
                } else {
                    val source = if (intent?.action == ACTION_WIDGET_LOCK) {
                        LockSource.WIDGET
                    } else {
                        LockSource.MANUAL_APP
                    }
                    TouchShieldService.lockScreenNow(this, source)
                }
            }

            ACTION_SHORTCUT_TOGGLE_POCKET -> {
                val prefs = ShieldPreferences(applicationContext)
                runBlocking {
                    val current = prefs.settingsFlow.first()
                    prefs.setPocketAutoLock(!current.pocketAutoLockEnabled)
                }
            }
        }
        finish()
    }

    companion object {
        const val ACTION_SHORTCUT_LOCK = "com.example.action.SHORTCUT_LOCK"
        const val ACTION_SHORTCUT_TOGGLE_POCKET = "com.example.action.SHORTCUT_TOGGLE_POCKET"
        const val ACTION_WIDGET_LOCK = "com.example.action.WIDGET_LOCK"
    }
}
