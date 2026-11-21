package com.example.service

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.example.MainActivity
import com.example.data.LockSource
import com.example.data.ShieldStateHolder

class TouchShieldTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        syncTileState()
    }

    override fun onClick() {
        super.onClick()
        if (!Settings.canDrawOverlays(this)) {
            openMainApp()
            return
        }

        val state = ShieldStateHolder.runtimeState.value
        if (state.isScreenLocked) {
            TouchShieldService.unlockScreenNow(this)
        } else {
            TouchShieldService.lockScreenNow(this, LockSource.QUICK_TILE)
        }
        syncTileState()
    }

    private fun syncTileState() {
        val tile = qsTile ?: return
        val runtime = ShieldStateHolder.runtimeState.value

        tile.state = when {
            runtime.isScreenLocked -> Tile.STATE_ACTIVE
            runtime.isServiceRunning -> Tile.STATE_ACTIVE
            else -> Tile.STATE_INACTIVE
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tile.subtitle = when {
                runtime.isScreenLocked -> "Locked"
                runtime.isServiceRunning -> "Armed"
                else -> "Tap to Lock"
            }
        }
        tile.updateTile()
    }

    private fun openMainApp() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            val pendingIntent = PendingIntent.getActivity(
                this,
                200,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            startActivityAndCollapse(pendingIntent)
        } else {
            @Suppress("DEPRECATION")
            startActivityAndCollapse(intent)
        }
    }
}
