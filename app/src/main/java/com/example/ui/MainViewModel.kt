package com.example.ui

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppLanguage
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderColorOption
import com.example.data.BorderThickness
import com.example.data.BubbleSize
import com.example.data.BubbleStyle
import com.example.data.LockSource
import com.example.data.OverlayStyle
import com.example.data.ShieldPreferences
import com.example.data.ShieldRuntimeState
import com.example.data.ShieldSettings
import com.example.data.ShieldStateHolder
import com.example.data.SmartPreset
import com.example.data.UnlockMethod
import com.example.sensor.PocketSensorManager
import com.example.service.TouchShieldService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class PermissionStatus(
    val hasOverlayPermission: Boolean = false,
    val hasNotificationPermission: Boolean = true,
    val hasPhoneStatePermission: Boolean = false,
    val isIgnoringBatteryOptimizations: Boolean = false
)

data class MainUiState(
    val settings: ShieldSettings = ShieldSettings(),
    val runtime: ShieldRuntimeState = ShieldRuntimeState(),
    val permissions: PermissionStatus = PermissionStatus(),
    val isSandboxOpen: Boolean = false
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val appContext = application.applicationContext
    private val preferences = ShieldPreferences(appContext)
    private val permissionFlow = MutableStateFlow(readPermissions())
    private val sandboxOpenFlow = MutableStateFlow(false)

    private val previewSensorManager = PocketSensorManager(
        context = appContext,
        onPocketLockTriggered = {},
        onPocketUnlockTriggered = {}
    )

    val uiState: StateFlow<MainUiState> = combine(
        preferences.settingsFlow,
        ShieldStateHolder.runtimeState,
        permissionFlow,
        sandboxOpenFlow
    ) { settings, runtime, permissions, sandboxOpen ->
        MainUiState(
            settings = settings,
            runtime = runtime,
            permissions = permissions,
            isSandboxOpen = sandboxOpen
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainUiState(permissions = readPermissions())
    )

    init {
        previewSensorManager.startListening()
        viewModelScope.launch {
            preferences.settingsFlow.collectLatest { settings ->
                previewSensorManager.updateConfig(
                    autoLock = false,
                    autoUnlock = false,
                    debounceMs = settings.pocketDebounceMs,
                    orientationFilter = settings.pocketOrientationFilterEnabled
                )
            }
        }
    }

    override fun onCleared() {
        previewSensorManager.stopListening()
        super.onCleared()
    }

    fun refreshPermissions() {
        permissionFlow.value = readPermissions()
    }

    fun toggleServiceArmed() {
        val current = uiState.value
        if (!current.permissions.hasOverlayPermission) {
            openOverlaySettings()
            return
        }
        if (current.runtime.isServiceRunning) {
            viewModelScope.launch { preferences.setArmed(false) }
            TouchShieldService.stop(appContext)
        } else {
            viewModelScope.launch { preferences.setArmed(true) }
            TouchShieldService.startArmed(appContext)
        }
    }

    fun lockScreenNow() {
        if (!uiState.value.permissions.hasOverlayPermission) {
            openOverlaySettings()
            return
        }
        viewModelScope.launch { preferences.setArmed(true) }
        TouchShieldService.lockScreenNow(appContext, LockSource.MANUAL_APP)
    }

    // Unlock & Security
    fun setUnlockMethod(method: UnlockMethod) {
        viewModelScope.launch { preferences.setUnlockMethod(method) }
    }

    fun setHoldDurationMs(ms: Long) {
        viewModelScope.launch { preferences.setHoldDurationMs(ms) }
    }

    fun setUnlockPin(pin: String) {
        viewModelScope.launch { preferences.setUnlockPin(pin) }
    }

    fun setLockCountdownSeconds(seconds: Int) {
        viewModelScope.launch { preferences.setLockCountdownSeconds(seconds) }
    }

    fun setHudTimeoutMs(ms: Long) {
        viewModelScope.launch { preferences.setHudTimeoutMs(ms) }
    }

    // Overlay Visuals
    fun setOverlayStyle(style: OverlayStyle) {
        viewModelScope.launch { preferences.setOverlayStyle(style) }
    }

    fun setCustomDimPercent(percent: Int) {
        viewModelScope.launch { preferences.setCustomDimPercent(percent) }
    }

    fun setBorderEnabled(enabled: Boolean) {
        viewModelScope.launch { preferences.setBorderEnabled(enabled) }
    }

    fun setBorderColor(color: BorderColorOption) {
        viewModelScope.launch { preferences.setBorderColor(color) }
    }

    fun setBorderThickness(thickness: BorderThickness) {
        viewModelScope.launch { preferences.setBorderThickness(thickness) }
    }

    fun setBadgeVisibilityMode(mode: BadgeVisibilityMode) {
        viewModelScope.launch { preferences.setBadgeVisibilityMode(mode) }
    }

    // Floating Bubble
    fun setFloatingBubbleEnabled(enabled: Boolean) {
        viewModelScope.launch { preferences.setFloatingBubbleEnabled(enabled) }
    }

    fun setFloatingBubbleOpacity(opacity: Float) {
        viewModelScope.launch { preferences.setFloatingBubbleOpacity(opacity) }
    }

    fun setBubbleSize(size: BubbleSize) {
        viewModelScope.launch { preferences.setBubbleSize(size) }
    }

    fun setBubbleStyle(style: BubbleStyle) {
        viewModelScope.launch { preferences.setBubbleStyle(style) }
    }

    fun setBubbleAutoTuck(enabled: Boolean) {
        viewModelScope.launch { preferences.setBubbleAutoTuck(enabled) }
    }

    fun setBubbleDoubleTapHide(enabled: Boolean) {
        viewModelScope.launch { preferences.setBubbleDoubleTapHide(enabled) }
    }

    // Pocket Sensor & Automation
    fun setPocketAutoLock(enabled: Boolean) {
        viewModelScope.launch { preferences.setPocketAutoLock(enabled) }
    }

    fun setPocketAutoUnlock(enabled: Boolean) {
        viewModelScope.launch { preferences.setPocketAutoUnlock(enabled) }
    }

    fun setPocketDebounceMs(ms: Long) {
        viewModelScope.launch { preferences.setPocketDebounceMs(ms) }
    }

    fun setPocketOrientationFilter(enabled: Boolean) {
        viewModelScope.launch { preferences.setPocketOrientationFilter(enabled) }
    }

    fun setAutoUnlockOnCall(enabled: Boolean) {
        viewModelScope.launch { preferences.setAutoUnlockOnCall(enabled) }
    }

    fun setSessionTimerMinutes(minutes: Int) {
        viewModelScope.launch { preferences.setSessionTimerMinutes(minutes) }
    }

    // System & Presets
    fun setKeepScreenOn(enabled: Boolean) {
        viewModelScope.launch { preferences.setKeepScreenOn(enabled) }
    }

    fun setHapticFeedback(enabled: Boolean) {
        viewModelScope.launch { preferences.setHapticFeedback(enabled) }
    }

    fun setAutoStartOnBoot(enabled: Boolean) {
        viewModelScope.launch { preferences.setAutoStartOnBoot(enabled) }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch { preferences.setDarkTheme(enabled) }
    }

    fun toggleLanguage() {
        val next = if (uiState.value.settings.appLanguage == AppLanguage.BENGALI) {
            AppLanguage.ENGLISH
        } else {
            AppLanguage.BENGALI
        }
        viewModelScope.launch { preferences.setAppLanguage(next) }
    }

    fun applySmartPreset(preset: SmartPreset) {
        viewModelScope.launch { preferences.applySmartPreset(preset) }
    }

    fun resetStats() {
        viewModelScope.launch { preferences.resetStats() }
    }

    fun restoreDefaults() {
        viewModelScope.launch { preferences.restoreDefaults() }
    }

    fun openSandbox() {
        sandboxOpenFlow.value = true
    }

    fun closeSandbox() {
        sandboxOpenFlow.value = false
    }

    fun recordSandboxBlockedTouch() {
        viewModelScope.launch { preferences.incrementBlockedTouches(1L) }
    }

    fun openOverlaySettings() {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:${appContext.packageName}")
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { appContext.startActivity(intent) }
    }

    fun openBatterySettings() {
        val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { appContext.startActivity(intent) }
    }

    private fun readPermissions(): PermissionStatus {
        val overlayGranted = Settings.canDrawOverlays(appContext)
        val notifGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                appContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
        val phoneGranted = ContextCompat.checkSelfPermission(
            appContext,
            Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED
        val powerManager = appContext.getSystemService(Context.POWER_SERVICE) as? PowerManager
        val ignoringBattery = powerManager?.isIgnoringBatteryOptimizations(appContext.packageName) ?: false
        return PermissionStatus(
            hasOverlayPermission = overlayGranted,
            hasNotificationPermission = notifGranted,
            hasPhoneStatePermission = phoneGranted,
            isIgnoringBatteryOptimizations = ignoringBattery
        )
    }
}
