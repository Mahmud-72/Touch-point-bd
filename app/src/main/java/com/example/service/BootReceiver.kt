package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.example.data.ShieldPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return
        if (action != Intent.ACTION_BOOT_COMPLETED && action != Intent.ACTION_MY_PACKAGE_REPLACED) {
            return
        }

        val pendingResult: PendingResult? = goAsync()
        val appContext = context.applicationContext
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val settings = ShieldPreferences(appContext).settingsFlow.first()
                if (settings.autoStartOnBootEnabled &&
                    settings.isArmed &&
                    Settings.canDrawOverlays(appContext)
                ) {
                    TouchShieldService.startArmed(appContext)
                }
            } catch (_: Exception) {
            } finally {
                pendingResult?.finish()
            }
        }
    }
}
