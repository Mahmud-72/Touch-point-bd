package com.example.ui

import android.Manifest
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppLanguage
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderColorOption
import com.example.data.BorderStyle
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
import com.example.data.ThemeMode
import com.example.data.UnlockMethod
import com.example.sensor.PocketSensorManager
import com.example.service.TouchShieldService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private val _snackbarEvents = MutableSharedFlow<String>(extraBufferCapacity = 4)
    val snackbarEvents: SharedFlow<String> = _snackbarEvents.asSharedFlow()

    private var lastLandscape = false
    private var lastCharging = false

    private val previewSensorManager = PocketSensorManager(
        context = appContext,
        onPocketLockTriggered = {},
        onPocketUnlockTriggered = {}
    )

    private val batteryPreviewReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateBatteryFromIntent(intent)
        }
    }

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
        refreshContextSnapshot()
        runCatching {
            val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val sticky = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                appContext.registerReceiver(batteryPreviewReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
            } else {
                appContext.registerReceiver(batteryPreviewReceiver, filter)
            }
            updateBatteryFromIntent(sticky)
        }
        viewModelScope.launch {
            preferences.settingsFlow.collectLatest { settings ->
                previewSensorManager.updateConfig(
                    autoLock = false,
                    autoUnlock = false,
                    debounceMs = settings.pocketDebounceMs,
                    orientationFilter = settings.pocketOrientationFilterEnabled,
                    pauseLandscape = settings.pausePocketInLandscape,
                    pauseCharging = settings.pausePocketWhileCharging,
                    flipFaceDown = false
                )
            }
        }
    }

    override fun onCleared() {
        previewSensorManager.stopListening()
        runCatching { appContext.unregisterReceiver(batteryPreviewReceiver) }
        super.onCleared()
    }

    fun refreshPermissions() {
        permissionFlow.value = readPermissions()
        refreshContextSnapshot()
    }

    fun refreshContextSnapshot() {
        val landscape = appContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        lastLandscape = landscape
        ShieldStateHolder.updateDeviceContext(isLandscape = landscape)
        previewSensorManager.updateContextState(lastLandscape, lastCharging)
    }

    private fun updateBatteryFromIntent(intent: Intent?) {
        if (intent == null) return
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)
        val charging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
            status == BatteryManager.BATTERY_STATUS_FULL ||
            plugged != 0
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100).coerceAtLeast(1)
        val pct = if (level >= 0) ((level * 100f) / scale).toInt().coerceIn(0, 100) else 100
        lastCharging = charging
        ShieldStateHolder.updateDeviceContext(
            isLandscape = lastLandscape,
            isCharging = charging,
            batteryPercent = pct
        )
        previewSensorManager.updateContextState(lastLandscape, lastCharging)
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

    // Security & Unlock
    fun setUnlockMethod(method: UnlockMethod) {
        viewModelScope.launch { preferences.setUnlockMethod(method) }
    }

    fun setHoldDurationMs(ms: Long) {
        viewModelScope.launch { preferences.setHoldDurationMs(ms) }
    }

    fun setUnlockPin(pin: String) {
        viewModelScope.launch { preferences.setUnlockPin(pin) }
    }

    fun setBiometricUnlock(enabled: Boolean) {
        viewModelScope.launch { preferences.setBiometricUnlock(enabled) }
    }

    fun setEmergencyHoldBypass(enabled: Boolean) {
        viewModelScope.launch { preferences.setEmergencyHoldBypass(enabled) }
    }

    fun setLockCountdownSeconds(seconds: Int) {
        viewModelScope.launch { preferences.setLockCountdownSeconds(seconds) }
    }

    fun setHudTimeoutMs(ms: Long) {
        viewModelScope.launch { preferences.setHudTimeoutMs(ms) }
    }

    // Overlay Visuals & Display
    fun setOverlayStyle(style: OverlayStyle) {
        viewModelScope.launch { preferences.setOverlayStyle(style) }
    }

    fun setCustomDimPercent(percent: Int) {
        viewModelScope.launch { preferences.setCustomDimPercent(percent) }
    }

    fun setLockBrightnessPercent(percent: Int) {
        viewModelScope.launch { preferences.setLockBrightnessPercent(percent) }
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

    fun setBorderStyle(style: BorderStyle) {
        viewModelScope.launch { preferences.setBorderStyle(style) }
    }

    fun setBorderPulse(enabled: Boolean) {
        viewModelScope.launch { preferences.setBorderPulse(enabled) }
    }

    fun setBadgeVisibilityMode(mode: BadgeVisibilityMode) {
        viewModelScope.launch { preferences.setBadgeVisibilityMode(mode) }
    }

    fun setOledClockEnabled(enabled: Boolean) {
        viewModelScope.launch { preferences.setOledClockEnabled(enabled) }
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

    // Pocket Sensor & Context Automation
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

    fun setPausePocketInLandscape(enabled: Boolean) {
        viewModelScope.launch { preferences.setPausePocketInLandscape(enabled) }
    }

    fun setPausePocketWhileCharging(enabled: Boolean) {
        viewModelScope.launch { preferences.setPausePocketWhileCharging(enabled) }
    }

    fun setFlipFaceDownLock(enabled: Boolean) {
        viewModelScope.launch { preferences.setFlipFaceDownLock(enabled) }
    }

    fun setAutoUnlockOnCall(enabled: Boolean) {
        viewModelScope.launch { preferences.setAutoUnlockOnCall(enabled) }
    }

    fun setReLockAfterCall(enabled: Boolean) {
        viewModelScope.launch { preferences.setReLockAfterCall(enabled) }
    }

    fun setSessionTimerMinutes(minutes: Int) {
        viewModelScope.launch { preferences.setSessionTimerMinutes(minutes) }
    }

    // System, Appearance, Onboarding & Profiles
    fun setKeepScreenOn(enabled: Boolean) {
        viewModelScope.launch { preferences.setKeepScreenOn(enabled) }
    }

    fun setHapticFeedback(enabled: Boolean) {
        viewModelScope.launch { preferences.setHapticFeedback(enabled) }
    }

    fun setAutoStartOnBoot(enabled: Boolean) {
        viewModelScope.launch { preferences.setAutoStartOnBoot(enabled) }
    }

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch { preferences.setThemeMode(mode) }
    }

    fun setDynamicColorEnabled(enabled: Boolean) {
        viewModelScope.launch { preferences.setDynamicColorEnabled(enabled) }
    }

    fun setAppLanguage(language: AppLanguage) {
        viewModelScope.launch { preferences.setAppLanguage(language) }
    }

    fun completeOnboarding() {
        viewModelScope.launch { preferences.setOnboardingCompleted(true) }
    }

    fun replayOnboarding() {
        viewModelScope.launch { preferences.setOnboardingCompleted(false) }
    }

    fun applySmartPreset(preset: SmartPreset) {
        viewModelScope.launch { preferences.applySmartPreset(preset) }
    }

    fun saveCurrentAsProfile(name: String) {
        viewModelScope.launch { preferences.saveCurrentAsProfile(name) }
    }

    fun applyCustomProfile(profileId: String) {
        viewModelScope.launch { preferences.applyCustomProfile(profileId) }
    }

    fun renameCustomProfile(profileId: String, newName: String) {
        viewModelScope.launch { preferences.renameCustomProfile(profileId, newName) }
    }

    fun deleteCustomProfile(profileId: String) {
        viewModelScope.launch { preferences.deleteCustomProfile(profileId) }
    }

    fun exportBackupToUri(uri: Uri) {
        viewModelScope.launch {
            val strings = getAppStrings(uiState.value.settings.appLanguage)
            val success = withContext(Dispatchers.IO) {
                runCatching {
                    val json = preferences.exportSettingsJson()
                    appContext.contentResolver.openOutputStream(uri)?.use { out ->
                        out.write(json.toByteArray(Charsets.UTF_8))
                    } ?: error("Null output stream")
                }.isSuccess
            }
            _snackbarEvents.tryEmit(if (success) strings.backupExportSuccess else strings.backupError)
        }
    }

    fun importBackupFromUri(uri: Uri) {
        viewModelScope.launch {
            val strings = getAppStrings(uiState.value.settings.appLanguage)
            val raw = withContext(Dispatchers.IO) {
                runCatching {
                    appContext.contentResolver.openInputStream(uri)?.bufferedReader(Charsets.UTF_8)?.use {
                        it.readText()
                    }
                }.getOrNull()
            }
            val imported = if (!raw.isNullOrBlank()) preferences.importSettingsJson(raw) else false
            val updatedStrings = getAppStrings(uiState.value.settings.appLanguage)
            _snackbarEvents.tryEmit(
                if (imported) updatedStrings.backupImportSuccess else strings.backupError
            )
        }
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
