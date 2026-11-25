package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID

private val Context.shieldDataStore: DataStore<Preferences> by preferencesDataStore(name = "touchshield_prefs")

class ShieldPreferences(private val context: Context) {

    private object Keys {
        val IS_ARMED = booleanPreferencesKey("is_armed")
        val UNLOCK_METHOD = stringPreferencesKey("unlock_method")
        val HOLD_DURATION_MS = longPreferencesKey("hold_duration_ms")
        val UNLOCK_PIN = stringPreferencesKey("unlock_pin")
        val BIOMETRIC_UNLOCK = booleanPreferencesKey("biometric_unlock")
        val EMERGENCY_HOLD_BYPASS = booleanPreferencesKey("emergency_hold_bypass")
        val LOCK_COUNTDOWN_SECONDS = intPreferencesKey("lock_countdown_seconds")
        val HUD_TIMEOUT_MS = longPreferencesKey("hud_timeout_ms")

        val OVERLAY_STYLE = stringPreferencesKey("overlay_style")
        val CUSTOM_DIM_PERCENT = intPreferencesKey("custom_dim_percent")
        val LOCK_BRIGHTNESS_PERCENT = intPreferencesKey("lock_brightness_percent")
        val BORDER_ENABLED = booleanPreferencesKey("border_enabled")
        val BORDER_COLOR = stringPreferencesKey("border_color")
        val BORDER_THICKNESS = stringPreferencesKey("border_thickness")
        val BORDER_STYLE = stringPreferencesKey("border_style")
        val BORDER_PULSE = booleanPreferencesKey("border_pulse")
        val BADGE_VISIBILITY_MODE = stringPreferencesKey("badge_visibility_mode")
        val OLED_CLOCK = booleanPreferencesKey("oled_clock")

        val FLOATING_BUBBLE_ENABLED = booleanPreferencesKey("floating_bubble_enabled")
        val FLOATING_BUBBLE_OPACITY = floatPreferencesKey("floating_bubble_opacity")
        val BUBBLE_SIZE = stringPreferencesKey("bubble_size")
        val BUBBLE_STYLE = stringPreferencesKey("bubble_style")
        val BUBBLE_AUTO_TUCK = booleanPreferencesKey("bubble_auto_tuck")
        val BUBBLE_DOUBLE_TAP_HIDE = booleanPreferencesKey("bubble_double_tap_hide")

        val POCKET_AUTO_LOCK = booleanPreferencesKey("pocket_auto_lock")
        val POCKET_AUTO_UNLOCK = booleanPreferencesKey("pocket_auto_unlock")
        val POCKET_DEBOUNCE_MS = longPreferencesKey("pocket_debounce_ms")
        val POCKET_ORIENTATION_FILTER = booleanPreferencesKey("pocket_orientation_filter")
        val PAUSE_POCKET_LANDSCAPE = booleanPreferencesKey("pause_pocket_landscape")
        val PAUSE_POCKET_CHARGING = booleanPreferencesKey("pause_pocket_charging")
        val FLIP_FACE_DOWN_LOCK = booleanPreferencesKey("flip_face_down_lock")
        val AUTO_UNLOCK_ON_CALL = booleanPreferencesKey("auto_unlock_on_call")
        val RELOCK_AFTER_CALL = booleanPreferencesKey("relock_after_call")
        val SESSION_TIMER_MINUTES = intPreferencesKey("session_timer_minutes")

        val KEEP_SCREEN_ON = booleanPreferencesKey("keep_screen_on")
        val HAPTIC_FEEDBACK = booleanPreferencesKey("haptic_feedback")
        val AUTO_START_ON_BOOT = booleanPreferencesKey("auto_start_on_boot")
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val DYNAMIC_COLOR = booleanPreferencesKey("dynamic_color")
        val APP_LANGUAGE = stringPreferencesKey("app_language")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val IS_ADVANCED_MODE = booleanPreferencesKey("is_advanced_mode")
        val CUSTOM_PROFILES_JSON = stringPreferencesKey("custom_profiles_json")

        val TOTAL_BLOCKED_TOUCHES = longPreferencesKey("total_blocked_touches")
        val TOTAL_LOCK_SESSIONS = longPreferencesKey("total_lock_sessions")
        val DAILY_BLOCKED_JSON = stringPreferencesKey("daily_blocked_json")
        val LOCK_SOURCE_COUNTS_JSON = stringPreferencesKey("lock_source_counts_json")

        // Kids Safety & Cooldown
        val KIDS_SAFETY_ENABLED = booleanPreferencesKey("kids_safety_enabled")
        val WRONG_ATTEMPT_COOLDOWN = booleanPreferencesKey("wrong_attempt_cooldown")
        val KIDS_LOCK_ON_TIMER_EXPIRY = booleanPreferencesKey("kids_lock_on_timer_expiry")

        // Partial Touch Zone & Privacy Veil
        val PARTIAL_ZONE_ENABLED = booleanPreferencesKey("partial_zone_enabled")
        val PARTIAL_ZONE_PRESET = stringPreferencesKey("partial_zone_preset")
        val PARTIAL_ZONE_LEFT = floatPreferencesKey("partial_zone_left")
        val PARTIAL_ZONE_TOP = floatPreferencesKey("partial_zone_top")
        val PARTIAL_ZONE_RIGHT = floatPreferencesKey("partial_zone_right")
        val PARTIAL_ZONE_BOTTOM = floatPreferencesKey("partial_zone_bottom")
        val PRIVACY_VEIL_ENABLED = booleanPreferencesKey("privacy_veil_enabled")
        val STEALTH_BLACKOUT_ENABLED = booleanPreferencesKey("stealth_blackout_enabled")

        // App-Watch, Shake/Volume & Power Guard
        val APP_WATCH_ENABLED = booleanPreferencesKey("app_watch_enabled")
        val WATCHED_APPS_JSON = stringPreferencesKey("watched_apps_json")
        val APP_WATCH_ACTION = stringPreferencesKey("app_watch_action")
        val APP_WATCH_AUTO_DISARM = booleanPreferencesKey("app_watch_auto_disarm")
        val SHAKE_TO_LOCK = booleanPreferencesKey("shake_to_lock")
        val SHAKE_TO_UNLOCK = booleanPreferencesKey("shake_to_unlock")
        val SHAKE_SENSITIVITY = stringPreferencesKey("shake_sensitivity")
        val VOLUME_DOUBLE_PRESS_ACTION = stringPreferencesKey("volume_double_press_action")
        val LOW_BATTERY_BLACKOUT_PERCENT = intPreferencesKey("low_battery_blackout_percent")
        val AUTO_LOCK_CHARGER_CONNECT = booleanPreferencesKey("auto_lock_charger_connect")
        val UNLOCK_CHARGER_DISCONNECT = booleanPreferencesKey("unlock_charger_disconnect")
    }

    val settingsFlow: Flow<ShieldSettings> = context.shieldDataStore.data.map { prefs ->
        val overlayStyle = OverlayStyle.fromKey(prefs[Keys.OVERLAY_STYLE])
        val legacyDark = prefs[Keys.DARK_THEME]
        val themeMode = prefs[Keys.THEME_MODE]?.let { ThemeMode.fromKey(it) }
            ?: if (legacyDark == true) ThemeMode.DARK else ThemeMode.SYSTEM

        ShieldSettings(
            isArmed = prefs[Keys.IS_ARMED] ?: false,
            unlockMethod = UnlockMethod.fromKey(prefs[Keys.UNLOCK_METHOD]),
            holdDurationMs = (prefs[Keys.HOLD_DURATION_MS] ?: 1800L).coerceIn(1000L, 3500L),
            unlockPin = (prefs[Keys.UNLOCK_PIN] ?: "1234").take(4).padEnd(4, '0'),
            biometricUnlockEnabled = prefs[Keys.BIOMETRIC_UNLOCK] ?: true,
            emergencyHoldBypassEnabled = prefs[Keys.EMERGENCY_HOLD_BYPASS] ?: true,
            lockCountdownSeconds = (prefs[Keys.LOCK_COUNTDOWN_SECONDS] ?: 0).coerceIn(0, 10),
            hudTimeoutMs = (prefs[Keys.HUD_TIMEOUT_MS] ?: 3000L).coerceIn(1500L, 6000L),
            overlayStyle = overlayStyle,
            customDimPercent = (prefs[Keys.CUSTOM_DIM_PERCENT] ?: overlayStyle.defaultDimPercent)
                .coerceIn(0, 95),
            lockBrightnessPercent = (prefs[Keys.LOCK_BRIGHTNESS_PERCENT] ?: -1).let {
                if (it < 0) -1 else it.coerceIn(1, 100)
            },
            borderEnabled = prefs[Keys.BORDER_ENABLED] ?: true,
            borderColor = BorderColorOption.fromKey(prefs[Keys.BORDER_COLOR]),
            borderThickness = BorderThickness.fromKey(prefs[Keys.BORDER_THICKNESS]),
            borderStyle = BorderStyle.fromKey(prefs[Keys.BORDER_STYLE]),
            borderPulseEnabled = prefs[Keys.BORDER_PULSE] ?: true,
            badgeVisibilityMode = BadgeVisibilityMode.fromKey(prefs[Keys.BADGE_VISIBILITY_MODE]),
            oledClockEnabled = prefs[Keys.OLED_CLOCK] ?: false,
            floatingBubbleEnabled = prefs[Keys.FLOATING_BUBBLE_ENABLED] ?: true,
            floatingBubbleOpacity = (prefs[Keys.FLOATING_BUBBLE_OPACITY] ?: 0.85f).coerceIn(0.35f, 1.0f),
            bubbleSize = BubbleSize.fromKey(prefs[Keys.BUBBLE_SIZE]),
            bubbleStyle = BubbleStyle.fromKey(prefs[Keys.BUBBLE_STYLE]),
            bubbleAutoTuckEnabled = prefs[Keys.BUBBLE_AUTO_TUCK] ?: true,
            bubbleDoubleTapHideEnabled = prefs[Keys.BUBBLE_DOUBLE_TAP_HIDE] ?: true,
            pocketAutoLockEnabled = prefs[Keys.POCKET_AUTO_LOCK] ?: true,
            pocketAutoUnlockEnabled = prefs[Keys.POCKET_AUTO_UNLOCK] ?: false,
            pocketDebounceMs = (prefs[Keys.POCKET_DEBOUNCE_MS] ?: 700L).coerceIn(400L, 2500L),
            pocketOrientationFilterEnabled = prefs[Keys.POCKET_ORIENTATION_FILTER] ?: true,
            pausePocketInLandscape = prefs[Keys.PAUSE_POCKET_LANDSCAPE] ?: true,
            pausePocketWhileCharging = prefs[Keys.PAUSE_POCKET_CHARGING] ?: false,
            flipFaceDownLockEnabled = prefs[Keys.FLIP_FACE_DOWN_LOCK] ?: false,
            autoUnlockOnCallEnabled = prefs[Keys.AUTO_UNLOCK_ON_CALL] ?: false,
            reLockAfterCallEnabled = prefs[Keys.RELOCK_AFTER_CALL] ?: false,
            sessionTimerMinutes = prefs[Keys.SESSION_TIMER_MINUTES] ?: 0,
            keepScreenOnEnabled = prefs[Keys.KEEP_SCREEN_ON] ?: true,
            hapticFeedbackEnabled = prefs[Keys.HAPTIC_FEEDBACK] ?: true,
            autoStartOnBootEnabled = prefs[Keys.AUTO_START_ON_BOOT] ?: false,
            themeMode = themeMode,
            dynamicColorEnabled = prefs[Keys.DYNAMIC_COLOR] ?: true,
            appLanguage = AppLanguage.fromKey(prefs[Keys.APP_LANGUAGE]),
            hasCompletedOnboarding = prefs[Keys.ONBOARDING_COMPLETED] ?: false,
            isAdvancedMode = prefs[Keys.IS_ADVANCED_MODE] ?: false,
            customProfiles = parseProfilesJson(prefs[Keys.CUSTOM_PROFILES_JSON]),
            totalBlockedTouches = prefs[Keys.TOTAL_BLOCKED_TOUCHES] ?: 0L,
            totalLockSessions = prefs[Keys.TOTAL_LOCK_SESSIONS] ?: 0L,
            dailyBlockedHistory = parseLongMapJson(prefs[Keys.DAILY_BLOCKED_JSON]),
            lockSourceCounts = parseLongMapJson(prefs[Keys.LOCK_SOURCE_COUNTS_JSON]),
            kidsSafetyEnabled = prefs[Keys.KIDS_SAFETY_ENABLED] ?: false,
            wrongAttemptCooldownEnabled = prefs[Keys.WRONG_ATTEMPT_COOLDOWN] ?: true,
            kidsLockOnTimerExpiry = prefs[Keys.KIDS_LOCK_ON_TIMER_EXPIRY] ?: false,
            partialZoneEnabled = prefs[Keys.PARTIAL_ZONE_ENABLED] ?: false,
            partialZonePreset = PartialZonePreset.fromKey(prefs[Keys.PARTIAL_ZONE_PRESET]),
            partialZoneRect = NormalizedRect(
                left = prefs[Keys.PARTIAL_ZONE_LEFT] ?: 0.08f,
                top = prefs[Keys.PARTIAL_ZONE_TOP] ?: 0.22f,
                right = prefs[Keys.PARTIAL_ZONE_RIGHT] ?: 0.92f,
                bottom = prefs[Keys.PARTIAL_ZONE_BOTTOM] ?: 0.56f
            ).clamped(),
            privacyVeilEnabled = prefs[Keys.PRIVACY_VEIL_ENABLED] ?: false,
            stealthBlackoutEnabled = prefs[Keys.STEALTH_BLACKOUT_ENABLED] ?: false,
            appWatchEnabled = prefs[Keys.APP_WATCH_ENABLED] ?: false,
            watchedAppPackages = parseStringSetJson(prefs[Keys.WATCHED_APPS_JSON])
                ?: ShieldSettings.DEFAULT_WATCHED_APPS,
            appWatchAction = AppWatchAction.fromKey(prefs[Keys.APP_WATCH_ACTION]),
            appWatchAutoDisarmOnExit = prefs[Keys.APP_WATCH_AUTO_DISARM] ?: true,
            shakeToLockEnabled = prefs[Keys.SHAKE_TO_LOCK] ?: false,
            shakeToUnlockEnabled = prefs[Keys.SHAKE_TO_UNLOCK] ?: false,
            shakeSensitivity = ShakeSensitivity.fromKey(prefs[Keys.SHAKE_SENSITIVITY]),
            volumeDoublePressAction = VolumeShortcutAction.fromKey(prefs[Keys.VOLUME_DOUBLE_PRESS_ACTION]),
            lowBatteryBlackoutPercent = (prefs[Keys.LOW_BATTERY_BLACKOUT_PERCENT] ?: 0).coerceIn(0, 30),
            autoLockOnChargerConnect = prefs[Keys.AUTO_LOCK_CHARGER_CONNECT] ?: false,
            unlockOnChargerDisconnect = prefs[Keys.UNLOCK_CHARGER_DISCONNECT] ?: false
        )
    }

    suspend fun setArmed(armed: Boolean) {
        context.shieldDataStore.edit { it[Keys.IS_ARMED] = armed }
    }

    suspend fun setUnlockMethod(method: UnlockMethod) {
        context.shieldDataStore.edit { it[Keys.UNLOCK_METHOD] = method.storageKey }
    }

    suspend fun setHoldDurationMs(durationMs: Long) {
        context.shieldDataStore.edit { it[Keys.HOLD_DURATION_MS] = durationMs.coerceIn(1000L, 3500L) }
    }

    suspend fun setUnlockPin(pin: String) {
        val sanitized = pin.filter { it.isDigit() }.take(4)
        if (sanitized.length == 4) {
            context.shieldDataStore.edit { it[Keys.UNLOCK_PIN] = sanitized }
        }
    }

    suspend fun setBiometricUnlock(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.BIOMETRIC_UNLOCK] = enabled }
    }

    suspend fun setEmergencyHoldBypass(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.EMERGENCY_HOLD_BYPASS] = enabled }
    }

    suspend fun setLockCountdownSeconds(seconds: Int) {
        context.shieldDataStore.edit { it[Keys.LOCK_COUNTDOWN_SECONDS] = seconds.coerceIn(0, 10) }
    }

    suspend fun setHudTimeoutMs(timeoutMs: Long) {
        context.shieldDataStore.edit { it[Keys.HUD_TIMEOUT_MS] = timeoutMs.coerceIn(1500L, 6000L) }
    }

    suspend fun setOverlayStyle(style: OverlayStyle) {
        context.shieldDataStore.edit {
            it[Keys.OVERLAY_STYLE] = style.storageKey
            it[Keys.CUSTOM_DIM_PERCENT] = style.defaultDimPercent
        }
    }

    suspend fun setCustomDimPercent(percent: Int) {
        val clamped = percent.coerceIn(0, 95)
        val matchedStyle = when {
            clamped == 0 -> OverlayStyle.CLEAR_GLASS
            clamped < 55 -> OverlayStyle.SUBTLE_TINT
            else -> OverlayStyle.AMOLED_BLACK
        }
        context.shieldDataStore.edit {
            it[Keys.CUSTOM_DIM_PERCENT] = clamped
            it[Keys.OVERLAY_STYLE] = matchedStyle.storageKey
        }
    }

    suspend fun setLockBrightnessPercent(percent: Int) {
        val normalized = if (percent < 0) -1 else percent.coerceIn(1, 100)
        context.shieldDataStore.edit { it[Keys.LOCK_BRIGHTNESS_PERCENT] = normalized }
    }

    suspend fun setBorderEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.BORDER_ENABLED] = enabled }
    }

    suspend fun setBorderColor(color: BorderColorOption) {
        context.shieldDataStore.edit { it[Keys.BORDER_COLOR] = color.storageKey }
    }

    suspend fun setBorderThickness(thickness: BorderThickness) {
        context.shieldDataStore.edit { it[Keys.BORDER_THICKNESS] = thickness.storageKey }
    }

    suspend fun setBorderStyle(style: BorderStyle) {
        context.shieldDataStore.edit { it[Keys.BORDER_STYLE] = style.storageKey }
    }

    suspend fun setBorderPulse(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.BORDER_PULSE] = enabled }
    }

    suspend fun setBadgeVisibilityMode(mode: BadgeVisibilityMode) {
        context.shieldDataStore.edit { it[Keys.BADGE_VISIBILITY_MODE] = mode.storageKey }
    }

    suspend fun setOledClockEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.OLED_CLOCK] = enabled }
    }

    suspend fun setFloatingBubbleEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.FLOATING_BUBBLE_ENABLED] = enabled }
    }

    suspend fun setFloatingBubbleOpacity(opacity: Float) {
        context.shieldDataStore.edit { it[Keys.FLOATING_BUBBLE_OPACITY] = opacity.coerceIn(0.35f, 1.0f) }
    }

    suspend fun setBubbleSize(size: BubbleSize) {
        context.shieldDataStore.edit { it[Keys.BUBBLE_SIZE] = size.storageKey }
    }

    suspend fun setBubbleStyle(style: BubbleStyle) {
        context.shieldDataStore.edit { it[Keys.BUBBLE_STYLE] = style.storageKey }
    }

    suspend fun setBubbleAutoTuck(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.BUBBLE_AUTO_TUCK] = enabled }
    }

    suspend fun setBubbleDoubleTapHide(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.BUBBLE_DOUBLE_TAP_HIDE] = enabled }
    }

    suspend fun setPocketAutoLock(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.POCKET_AUTO_LOCK] = enabled }
    }

    suspend fun setPocketAutoUnlock(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.POCKET_AUTO_UNLOCK] = enabled }
    }

    suspend fun setPocketDebounceMs(ms: Long) {
        context.shieldDataStore.edit { it[Keys.POCKET_DEBOUNCE_MS] = ms.coerceIn(400L, 2500L) }
    }

    suspend fun setPocketOrientationFilter(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.POCKET_ORIENTATION_FILTER] = enabled }
    }

    suspend fun setPausePocketInLandscape(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.PAUSE_POCKET_LANDSCAPE] = enabled }
    }

    suspend fun setPausePocketWhileCharging(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.PAUSE_POCKET_CHARGING] = enabled }
    }

    suspend fun setFlipFaceDownLock(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.FLIP_FACE_DOWN_LOCK] = enabled }
    }

    suspend fun setAutoUnlockOnCall(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.AUTO_UNLOCK_ON_CALL] = enabled }
    }

    suspend fun setReLockAfterCall(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.RELOCK_AFTER_CALL] = enabled }
    }

    suspend fun setSessionTimerMinutes(minutes: Int) {
        context.shieldDataStore.edit { it[Keys.SESSION_TIMER_MINUTES] = minutes.coerceAtLeast(0) }
    }

    suspend fun setKeepScreenOn(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.KEEP_SCREEN_ON] = enabled }
    }

    suspend fun setHapticFeedback(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.HAPTIC_FEEDBACK] = enabled }
    }

    suspend fun setAutoStartOnBoot(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.AUTO_START_ON_BOOT] = enabled }
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        context.shieldDataStore.edit {
            it[Keys.THEME_MODE] = mode.storageKey
            it[Keys.DARK_THEME] = (mode == ThemeMode.DARK)
        }
    }

    suspend fun setDynamicColorEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.DYNAMIC_COLOR] = enabled }
    }

    suspend fun setAppLanguage(language: AppLanguage) {
        context.shieldDataStore.edit { it[Keys.APP_LANGUAGE] = language.storageKey }
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.shieldDataStore.edit { it[Keys.ONBOARDING_COMPLETED] = completed }
    }

    suspend fun setAdvancedMode(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.IS_ADVANCED_MODE] = enabled }
    }

    // Kids Safety & Cooldown
    suspend fun setKidsSafetyEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.KIDS_SAFETY_ENABLED] = enabled }
    }

    suspend fun setWrongAttemptCooldownEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.WRONG_ATTEMPT_COOLDOWN] = enabled }
    }

    suspend fun setKidsLockOnTimerExpiry(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.KIDS_LOCK_ON_TIMER_EXPIRY] = enabled }
    }

    // Partial Touch Zone & Privacy Veil
    suspend fun setPartialZoneEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { prefs ->
            prefs[Keys.PARTIAL_ZONE_ENABLED] = enabled
            if (enabled && prefs[Keys.PARTIAL_ZONE_PRESET] == PartialZonePreset.FULL_LOCK.storageKey) {
                prefs[Keys.PARTIAL_ZONE_PRESET] = PartialZonePreset.CENTER_WINDOW.storageKey
            }
        }
    }

    suspend fun setPartialZonePreset(preset: PartialZonePreset) {
        context.shieldDataStore.edit { prefs ->
            prefs[Keys.PARTIAL_ZONE_PRESET] = preset.storageKey
            prefs[Keys.PARTIAL_ZONE_ENABLED] = (preset != PartialZonePreset.FULL_LOCK)
        }
    }

    suspend fun setPartialZoneRect(rect: NormalizedRect) {
        val clamped = rect.clamped()
        context.shieldDataStore.edit { prefs ->
            prefs[Keys.PARTIAL_ZONE_LEFT] = clamped.left
            prefs[Keys.PARTIAL_ZONE_TOP] = clamped.top
            prefs[Keys.PARTIAL_ZONE_RIGHT] = clamped.right
            prefs[Keys.PARTIAL_ZONE_BOTTOM] = clamped.bottom
            prefs[Keys.PARTIAL_ZONE_PRESET] = PartialZonePreset.CUSTOM.storageKey
            prefs[Keys.PARTIAL_ZONE_ENABLED] = true
        }
    }

    suspend fun setPrivacyVeilEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.PRIVACY_VEIL_ENABLED] = enabled }
    }

    suspend fun setStealthBlackoutEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.STEALTH_BLACKOUT_ENABLED] = enabled }
    }

    // App-Watch, Shake/Volume & Power Guard
    suspend fun setAppWatchEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.APP_WATCH_ENABLED] = enabled }
    }

    suspend fun setWatchedAppPackages(packages: Set<String>) {
        val cleaned = packages.map { it.trim() }.filter { it.isNotEmpty() }.toSet()
        context.shieldDataStore.edit { it[Keys.WATCHED_APPS_JSON] = encodeStringSetJson(cleaned) }
    }

    suspend fun toggleWatchedAppPackage(packageName: String) {
        val current = settingsFlow.first().watchedAppPackages.toMutableSet()
        if (!current.add(packageName)) {
            current.remove(packageName)
        }
        setWatchedAppPackages(current)
    }

    suspend fun setAppWatchAction(action: AppWatchAction) {
        context.shieldDataStore.edit { it[Keys.APP_WATCH_ACTION] = action.storageKey }
    }

    suspend fun setAppWatchAutoDisarmOnExit(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.APP_WATCH_AUTO_DISARM] = enabled }
    }

    suspend fun setShakeToLockEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.SHAKE_TO_LOCK] = enabled }
    }

    suspend fun setShakeToUnlockEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.SHAKE_TO_UNLOCK] = enabled }
    }

    suspend fun setShakeSensitivity(sensitivity: ShakeSensitivity) {
        context.shieldDataStore.edit { it[Keys.SHAKE_SENSITIVITY] = sensitivity.storageKey }
    }

    suspend fun setVolumeDoublePressAction(action: VolumeShortcutAction) {
        context.shieldDataStore.edit { it[Keys.VOLUME_DOUBLE_PRESS_ACTION] = action.storageKey }
    }

    suspend fun setLowBatteryBlackoutPercent(percent: Int) {
        context.shieldDataStore.edit { it[Keys.LOW_BATTERY_BLACKOUT_PERCENT] = percent.coerceIn(0, 30) }
    }

    suspend fun setAutoLockOnChargerConnect(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.AUTO_LOCK_CHARGER_CONNECT] = enabled }
    }

    suspend fun setUnlockOnChargerDisconnect(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.UNLOCK_CHARGER_DISCONNECT] = enabled }
    }

    suspend fun applySmartPreset(preset: SmartPreset) {
        context.shieldDataStore.edit { prefs ->
            when (preset) {
                SmartPreset.POCKET_PHOTO -> {
                    prefs[Keys.OVERLAY_STYLE] = OverlayStyle.CLEAR_GLASS.storageKey
                    prefs[Keys.CUSTOM_DIM_PERCENT] = 0
                    prefs[Keys.LOCK_BRIGHTNESS_PERCENT] = -1
                    prefs[Keys.KEEP_SCREEN_ON] = true
                    prefs[Keys.POCKET_AUTO_LOCK] = true
                    prefs[Keys.POCKET_ORIENTATION_FILTER] = true
                    prefs[Keys.PAUSE_POCKET_LANDSCAPE] = true
                    prefs[Keys.UNLOCK_METHOD] = UnlockMethod.LONG_PRESS.storageKey
                    prefs[Keys.HOLD_DURATION_MS] = 1800L
                    prefs[Keys.BORDER_ENABLED] = true
                    prefs[Keys.BORDER_COLOR] = BorderColorOption.EMERALD.storageKey
                    prefs[Keys.BORDER_THICKNESS] = BorderThickness.MEDIUM.storageKey
                    prefs[Keys.BORDER_STYLE] = BorderStyle.FULL_GLOW.storageKey
                    prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.ALWAYS.storageKey
                    prefs[Keys.OLED_CLOCK] = false
                    prefs[Keys.FLOATING_BUBBLE_ENABLED] = true
                }

                SmartPreset.KIDS_VIDEO -> {
                    prefs[Keys.OVERLAY_STYLE] = OverlayStyle.CLEAR_GLASS.storageKey
                    prefs[Keys.CUSTOM_DIM_PERCENT] = 0
                    prefs[Keys.LOCK_BRIGHTNESS_PERCENT] = -1
                    prefs[Keys.KEEP_SCREEN_ON] = true
                    prefs[Keys.POCKET_AUTO_LOCK] = false
                    prefs[Keys.PAUSE_POCKET_LANDSCAPE] = true
                    prefs[Keys.UNLOCK_METHOD] = UnlockMethod.LONG_PRESS.storageKey
                    prefs[Keys.HOLD_DURATION_MS] = 3000L
                    prefs[Keys.BORDER_ENABLED] = true
                    prefs[Keys.BORDER_COLOR] = BorderColorOption.AMBER.storageKey
                    prefs[Keys.BORDER_THICKNESS] = BorderThickness.THIN.storageKey
                    prefs[Keys.BORDER_STYLE] = BorderStyle.CORNER_BRACKETS.storageKey
                    prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.HIDDEN.storageKey
                    prefs[Keys.OLED_CLOCK] = false
                    prefs[Keys.FLOATING_BUBBLE_ENABLED] = false
                }

                SmartPreset.BATTERY_SAVER -> {
                    prefs[Keys.OVERLAY_STYLE] = OverlayStyle.AMOLED_BLACK.storageKey
                    prefs[Keys.CUSTOM_DIM_PERCENT] = 88
                    prefs[Keys.LOCK_BRIGHTNESS_PERCENT] = 5
                    prefs[Keys.KEEP_SCREEN_ON] = false
                    prefs[Keys.POCKET_AUTO_LOCK] = true
                    prefs[Keys.POCKET_ORIENTATION_FILTER] = true
                    prefs[Keys.UNLOCK_METHOD] = UnlockMethod.SLIDE.storageKey
                    prefs[Keys.BORDER_ENABLED] = true
                    prefs[Keys.BORDER_COLOR] = BorderColorOption.CYAN.storageKey
                    prefs[Keys.BORDER_THICKNESS] = BorderThickness.THIN.storageKey
                    prefs[Keys.BORDER_STYLE] = BorderStyle.FULL_GLOW.storageKey
                    prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.ON_TOUCH.storageKey
                    prefs[Keys.OLED_CLOCK] = true
                    prefs[Keys.FLOATING_BUBBLE_ENABLED] = true
                }

                SmartPreset.NAVIGATION_MAP -> {
                    prefs[Keys.OVERLAY_STYLE] = OverlayStyle.CLEAR_GLASS.storageKey
                    prefs[Keys.CUSTOM_DIM_PERCENT] = 0
                    prefs[Keys.LOCK_BRIGHTNESS_PERCENT] = 100
                    prefs[Keys.KEEP_SCREEN_ON] = true
                    prefs[Keys.POCKET_AUTO_LOCK] = false
                    prefs[Keys.PAUSE_POCKET_LANDSCAPE] = true
                    prefs[Keys.UNLOCK_METHOD] = UnlockMethod.SLIDE.storageKey
                    prefs[Keys.BORDER_ENABLED] = true
                    prefs[Keys.BORDER_COLOR] = BorderColorOption.CYAN.storageKey
                    prefs[Keys.BORDER_THICKNESS] = BorderThickness.MEDIUM.storageKey
                    prefs[Keys.BORDER_STYLE] = BorderStyle.CORNER_BRACKETS.storageKey
                    prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.ON_TOUCH.storageKey
                    prefs[Keys.OLED_CLOCK] = false
                    prefs[Keys.FLOATING_BUBBLE_ENABLED] = true
                    prefs[Keys.BUBBLE_STYLE] = BubbleStyle.COMPACT_PILL.storageKey
                }
            }
        }
    }

    suspend fun saveCurrentAsProfile(name: String) {
        val trimmed = name.trim().take(24)
        if (trimmed.isEmpty()) return
        val current = settingsFlow.first()
        val newProfile = CustomProfile(
            id = UUID.randomUUID().toString(),
            name = trimmed,
            unlockMethod = current.unlockMethod,
            holdDurationMs = current.holdDurationMs,
            customDimPercent = current.customDimPercent,
            lockBrightnessPercent = current.lockBrightnessPercent,
            borderEnabled = current.borderEnabled,
            borderColor = current.borderColor,
            borderStyle = current.borderStyle,
            badgeVisibilityMode = current.badgeVisibilityMode,
            floatingBubbleEnabled = current.floatingBubbleEnabled,
            pocketAutoLockEnabled = current.pocketAutoLockEnabled,
            keepScreenOnEnabled = current.keepScreenOnEnabled,
            oledClockEnabled = current.oledClockEnabled
        )
        val updated = (current.customProfiles + newProfile).takeLast(8)
        context.shieldDataStore.edit {
            it[Keys.CUSTOM_PROFILES_JSON] = encodeProfilesJson(updated)
        }
    }

    suspend fun applyCustomProfile(profileId: String) {
        val current = settingsFlow.first()
        val profile = current.customProfiles.firstOrNull { it.id == profileId } ?: return
        context.shieldDataStore.edit { prefs ->
            prefs[Keys.UNLOCK_METHOD] = profile.unlockMethod.storageKey
            prefs[Keys.HOLD_DURATION_MS] = profile.holdDurationMs.coerceIn(1000L, 3500L)
            prefs[Keys.CUSTOM_DIM_PERCENT] = profile.customDimPercent.coerceIn(0, 95)
            prefs[Keys.OVERLAY_STYLE] = when {
                profile.customDimPercent == 0 -> OverlayStyle.CLEAR_GLASS.storageKey
                profile.customDimPercent < 55 -> OverlayStyle.SUBTLE_TINT.storageKey
                else -> OverlayStyle.AMOLED_BLACK.storageKey
            }
            prefs[Keys.LOCK_BRIGHTNESS_PERCENT] = profile.lockBrightnessPercent
            prefs[Keys.BORDER_ENABLED] = profile.borderEnabled
            prefs[Keys.BORDER_COLOR] = profile.borderColor.storageKey
            prefs[Keys.BORDER_STYLE] = profile.borderStyle.storageKey
            prefs[Keys.BADGE_VISIBILITY_MODE] = profile.badgeVisibilityMode.storageKey
            prefs[Keys.FLOATING_BUBBLE_ENABLED] = profile.floatingBubbleEnabled
            prefs[Keys.POCKET_AUTO_LOCK] = profile.pocketAutoLockEnabled
            prefs[Keys.KEEP_SCREEN_ON] = profile.keepScreenOnEnabled
            prefs[Keys.OLED_CLOCK] = profile.oledClockEnabled
        }
    }

    suspend fun renameCustomProfile(profileId: String, newName: String) {
        val trimmed = newName.trim().take(24)
        if (trimmed.isEmpty()) return
        val current = settingsFlow.first()
        val updated = current.customProfiles.map {
            if (it.id == profileId) it.copy(name = trimmed) else it
        }
        context.shieldDataStore.edit {
            it[Keys.CUSTOM_PROFILES_JSON] = encodeProfilesJson(updated)
        }
    }

    suspend fun deleteCustomProfile(profileId: String) {
        val current = settingsFlow.first()
        val updated = current.customProfiles.filterNot { it.id == profileId }
        context.shieldDataStore.edit {
            it[Keys.CUSTOM_PROFILES_JSON] = encodeProfilesJson(updated)
        }
    }

    suspend fun resetStats() {
        context.shieldDataStore.edit {
            it[Keys.TOTAL_BLOCKED_TOUCHES] = 0L
            it[Keys.TOTAL_LOCK_SESSIONS] = 0L
            it.remove(Keys.DAILY_BLOCKED_JSON)
            it.remove(Keys.LOCK_SOURCE_COUNTS_JSON)
        }
    }

    suspend fun restoreDefaults() {
        context.shieldDataStore.edit { prefs ->
            val currentLang = prefs[Keys.APP_LANGUAGE]
            val currentArmed = prefs[Keys.IS_ARMED] ?: false
            val onboardingDone = prefs[Keys.ONBOARDING_COMPLETED] ?: true
            prefs.clear()
            if (currentLang != null) {
                prefs[Keys.APP_LANGUAGE] = currentLang
            }
            prefs[Keys.IS_ARMED] = currentArmed
            prefs[Keys.ONBOARDING_COMPLETED] = onboardingDone
        }
    }

    suspend fun incrementBlockedTouches(count: Long = 1L) {
        val todayKey = todayDateKey()
        val allowedKeys = last7DayKeys().toSet()
        context.shieldDataStore.edit { prefs ->
            val current = prefs[Keys.TOTAL_BLOCKED_TOUCHES] ?: 0L
            prefs[Keys.TOTAL_BLOCKED_TOUCHES] = current + count

            val map = parseLongMapJson(prefs[Keys.DAILY_BLOCKED_JSON]).toMutableMap()
            map[todayKey] = (map[todayKey] ?: 0L) + count
            val pruned = map.filterKeys { it in allowedKeys }
            prefs[Keys.DAILY_BLOCKED_JSON] = encodeLongMapJson(pruned)
        }
    }

    suspend fun incrementLockSessions(source: LockSource = LockSource.MANUAL_APP) {
        context.shieldDataStore.edit { prefs ->
            val current = prefs[Keys.TOTAL_LOCK_SESSIONS] ?: 0L
            prefs[Keys.TOTAL_LOCK_SESSIONS] = current + 1L

            if (source != LockSource.NONE) {
                val map = parseLongMapJson(prefs[Keys.LOCK_SOURCE_COUNTS_JSON]).toMutableMap()
                map[source.name] = (map[source.name] ?: 0L) + 1L
                prefs[Keys.LOCK_SOURCE_COUNTS_JSON] = encodeLongMapJson(map)
            }
        }
    }

    suspend fun exportSettingsJson(): String {
        val s = settingsFlow.first()
        return JSONObject().apply {
            put("format", "touchshield_backup_v1")
            put("unlockMethod", s.unlockMethod.storageKey)
            put("holdDurationMs", s.holdDurationMs)
            put("unlockPin", s.unlockPin)
            put("biometricUnlockEnabled", s.biometricUnlockEnabled)
            put("emergencyHoldBypassEnabled", s.emergencyHoldBypassEnabled)
            put("lockCountdownSeconds", s.lockCountdownSeconds)
            put("hudTimeoutMs", s.hudTimeoutMs)
            put("overlayStyle", s.overlayStyle.storageKey)
            put("customDimPercent", s.customDimPercent)
            put("lockBrightnessPercent", s.lockBrightnessPercent)
            put("borderEnabled", s.borderEnabled)
            put("borderColor", s.borderColor.storageKey)
            put("borderThickness", s.borderThickness.storageKey)
            put("borderStyle", s.borderStyle.storageKey)
            put("borderPulseEnabled", s.borderPulseEnabled)
            put("badgeVisibilityMode", s.badgeVisibilityMode.storageKey)
            put("oledClockEnabled", s.oledClockEnabled)
            put("floatingBubbleEnabled", s.floatingBubbleEnabled)
            put("floatingBubbleOpacity", s.floatingBubbleOpacity.toDouble())
            put("bubbleSize", s.bubbleSize.storageKey)
            put("bubbleStyle", s.bubbleStyle.storageKey)
            put("bubbleAutoTuckEnabled", s.bubbleAutoTuckEnabled)
            put("bubbleDoubleTapHideEnabled", s.bubbleDoubleTapHideEnabled)
            put("pocketAutoLockEnabled", s.pocketAutoLockEnabled)
            put("pocketAutoUnlockEnabled", s.pocketAutoUnlockEnabled)
            put("pocketDebounceMs", s.pocketDebounceMs)
            put("pocketOrientationFilterEnabled", s.pocketOrientationFilterEnabled)
            put("pausePocketInLandscape", s.pausePocketInLandscape)
            put("pausePocketWhileCharging", s.pausePocketWhileCharging)
            put("flipFaceDownLockEnabled", s.flipFaceDownLockEnabled)
            put("autoUnlockOnCallEnabled", s.autoUnlockOnCallEnabled)
            put("reLockAfterCallEnabled", s.reLockAfterCallEnabled)
            put("sessionTimerMinutes", s.sessionTimerMinutes)
            put("keepScreenOnEnabled", s.keepScreenOnEnabled)
            put("hapticFeedbackEnabled", s.hapticFeedbackEnabled)
            put("autoStartOnBootEnabled", s.autoStartOnBootEnabled)
            put("themeMode", s.themeMode.storageKey)
            put("dynamicColorEnabled", s.dynamicColorEnabled)
            put("appLanguage", s.appLanguage.storageKey)
            put("customProfilesJson", encodeProfilesJson(s.customProfiles))
            put("kidsSafetyEnabled", s.kidsSafetyEnabled)
            put("wrongAttemptCooldownEnabled", s.wrongAttemptCooldownEnabled)
            put("kidsLockOnTimerExpiry", s.kidsLockOnTimerExpiry)
            put("partialZoneEnabled", s.partialZoneEnabled)
            put("partialZonePreset", s.partialZonePreset.storageKey)
            put("partialZoneLeft", s.partialZoneRect.left.toDouble())
            put("partialZoneTop", s.partialZoneRect.top.toDouble())
            put("partialZoneRight", s.partialZoneRect.right.toDouble())
            put("partialZoneBottom", s.partialZoneRect.bottom.toDouble())
            put("privacyVeilEnabled", s.privacyVeilEnabled)
            put("stealthBlackoutEnabled", s.stealthBlackoutEnabled)
            put("appWatchEnabled", s.appWatchEnabled)
            put("watchedAppPackagesJson", encodeStringSetJson(s.watchedAppPackages))
            put("appWatchAction", s.appWatchAction.storageKey)
            put("appWatchAutoDisarmOnExit", s.appWatchAutoDisarmOnExit)
            put("shakeToLockEnabled", s.shakeToLockEnabled)
            put("shakeToUnlockEnabled", s.shakeToUnlockEnabled)
            put("shakeSensitivity", s.shakeSensitivity.storageKey)
            put("volumeDoublePressAction", s.volumeDoublePressAction.storageKey)
            put("lowBatteryBlackoutPercent", s.lowBatteryBlackoutPercent)
            put("autoLockOnChargerConnect", s.autoLockOnChargerConnect)
            put("unlockOnChargerDisconnect", s.unlockOnChargerDisconnect)
            put("isAdvancedMode", s.isAdvancedMode)
        }.toString(2)
    }

    suspend fun importSettingsJson(rawJson: String): Boolean {
        return try {
            val obj = JSONObject(rawJson)
            if (obj.optString("format") != "touchshield_backup_v1") {
                return false
            }
            context.shieldDataStore.edit { prefs ->
                if (obj.has("unlockMethod")) prefs[Keys.UNLOCK_METHOD] = UnlockMethod.fromKey(obj.optString("unlockMethod")).storageKey
                if (obj.has("holdDurationMs")) prefs[Keys.HOLD_DURATION_MS] = obj.optLong("holdDurationMs", 1800L).coerceIn(1000L, 3500L)
                if (obj.has("unlockPin")) {
                    val pin = obj.optString("unlockPin").filter { it.isDigit() }.take(4)
                    if (pin.length == 4) prefs[Keys.UNLOCK_PIN] = pin
                }
                if (obj.has("biometricUnlockEnabled")) prefs[Keys.BIOMETRIC_UNLOCK] = obj.optBoolean("biometricUnlockEnabled", true)
                if (obj.has("emergencyHoldBypassEnabled")) prefs[Keys.EMERGENCY_HOLD_BYPASS] = obj.optBoolean("emergencyHoldBypassEnabled", true)
                if (obj.has("lockCountdownSeconds")) prefs[Keys.LOCK_COUNTDOWN_SECONDS] = obj.optInt("lockCountdownSeconds", 0).coerceIn(0, 10)
                if (obj.has("hudTimeoutMs")) prefs[Keys.HUD_TIMEOUT_MS] = obj.optLong("hudTimeoutMs", 3000L).coerceIn(1500L, 6000L)
                if (obj.has("customDimPercent")) {
                    val dim = obj.optInt("customDimPercent", 0).coerceIn(0, 95)
                    prefs[Keys.CUSTOM_DIM_PERCENT] = dim
                    prefs[Keys.OVERLAY_STYLE] = when {
                        dim == 0 -> OverlayStyle.CLEAR_GLASS.storageKey
                        dim < 55 -> OverlayStyle.SUBTLE_TINT.storageKey
                        else -> OverlayStyle.AMOLED_BLACK.storageKey
                    }
                }
                if (obj.has("lockBrightnessPercent")) {
                    val br = obj.optInt("lockBrightnessPercent", -1)
                    prefs[Keys.LOCK_BRIGHTNESS_PERCENT] = if (br < 0) -1 else br.coerceIn(1, 100)
                }
                if (obj.has("borderEnabled")) prefs[Keys.BORDER_ENABLED] = obj.optBoolean("borderEnabled", true)
                if (obj.has("borderColor")) prefs[Keys.BORDER_COLOR] = BorderColorOption.fromKey(obj.optString("borderColor")).storageKey
                if (obj.has("borderThickness")) prefs[Keys.BORDER_THICKNESS] = BorderThickness.fromKey(obj.optString("borderThickness")).storageKey
                if (obj.has("borderStyle")) prefs[Keys.BORDER_STYLE] = BorderStyle.fromKey(obj.optString("borderStyle")).storageKey
                if (obj.has("borderPulseEnabled")) prefs[Keys.BORDER_PULSE] = obj.optBoolean("borderPulseEnabled", true)
                if (obj.has("badgeVisibilityMode")) prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.fromKey(obj.optString("badgeVisibilityMode")).storageKey
                if (obj.has("oledClockEnabled")) prefs[Keys.OLED_CLOCK] = obj.optBoolean("oledClockEnabled", false)
                if (obj.has("floatingBubbleEnabled")) prefs[Keys.FLOATING_BUBBLE_ENABLED] = obj.optBoolean("floatingBubbleEnabled", true)
                if (obj.has("floatingBubbleOpacity")) prefs[Keys.FLOATING_BUBBLE_OPACITY] = obj.optDouble("floatingBubbleOpacity", 0.85).toFloat().coerceIn(0.35f, 1.0f)
                if (obj.has("bubbleSize")) prefs[Keys.BUBBLE_SIZE] = BubbleSize.fromKey(obj.optString("bubbleSize")).storageKey
                if (obj.has("bubbleStyle")) prefs[Keys.BUBBLE_STYLE] = BubbleStyle.fromKey(obj.optString("bubbleStyle")).storageKey
                if (obj.has("bubbleAutoTuckEnabled")) prefs[Keys.BUBBLE_AUTO_TUCK] = obj.optBoolean("bubbleAutoTuckEnabled", true)
                if (obj.has("bubbleDoubleTapHideEnabled")) prefs[Keys.BUBBLE_DOUBLE_TAP_HIDE] = obj.optBoolean("bubbleDoubleTapHideEnabled", true)
                if (obj.has("pocketAutoLockEnabled")) prefs[Keys.POCKET_AUTO_LOCK] = obj.optBoolean("pocketAutoLockEnabled", true)
                if (obj.has("pocketAutoUnlockEnabled")) prefs[Keys.POCKET_AUTO_UNLOCK] = obj.optBoolean("pocketAutoUnlockEnabled", false)
                if (obj.has("pocketDebounceMs")) prefs[Keys.POCKET_DEBOUNCE_MS] = obj.optLong("pocketDebounceMs", 700L).coerceIn(400L, 2500L)
                if (obj.has("pocketOrientationFilterEnabled")) prefs[Keys.POCKET_ORIENTATION_FILTER] = obj.optBoolean("pocketOrientationFilterEnabled", true)
                if (obj.has("pausePocketInLandscape")) prefs[Keys.PAUSE_POCKET_LANDSCAPE] = obj.optBoolean("pausePocketInLandscape", true)
                if (obj.has("pausePocketWhileCharging")) prefs[Keys.PAUSE_POCKET_CHARGING] = obj.optBoolean("pausePocketWhileCharging", false)
                if (obj.has("flipFaceDownLockEnabled")) prefs[Keys.FLIP_FACE_DOWN_LOCK] = obj.optBoolean("flipFaceDownLockEnabled", false)
                if (obj.has("autoUnlockOnCallEnabled")) prefs[Keys.AUTO_UNLOCK_ON_CALL] = obj.optBoolean("autoUnlockOnCallEnabled", false)
                if (obj.has("reLockAfterCallEnabled")) prefs[Keys.RELOCK_AFTER_CALL] = obj.optBoolean("reLockAfterCallEnabled", false)
                if (obj.has("sessionTimerMinutes")) prefs[Keys.SESSION_TIMER_MINUTES] = obj.optInt("sessionTimerMinutes", 0).coerceAtLeast(0)
                if (obj.has("keepScreenOnEnabled")) prefs[Keys.KEEP_SCREEN_ON] = obj.optBoolean("keepScreenOnEnabled", true)
                if (obj.has("hapticFeedbackEnabled")) prefs[Keys.HAPTIC_FEEDBACK] = obj.optBoolean("hapticFeedbackEnabled", true)
                if (obj.has("autoStartOnBootEnabled")) prefs[Keys.AUTO_START_ON_BOOT] = obj.optBoolean("autoStartOnBootEnabled", false)
                if (obj.has("themeMode")) {
                    val mode = ThemeMode.fromKey(obj.optString("themeMode"))
                    prefs[Keys.THEME_MODE] = mode.storageKey
                    prefs[Keys.DARK_THEME] = (mode == ThemeMode.DARK)
                }
                if (obj.has("dynamicColorEnabled")) prefs[Keys.DYNAMIC_COLOR] = obj.optBoolean("dynamicColorEnabled", true)
                if (obj.has("appLanguage")) prefs[Keys.APP_LANGUAGE] = AppLanguage.fromKey(obj.optString("appLanguage")).storageKey
                if (obj.has("customProfilesJson")) {
                    val profiles = parseProfilesJson(obj.optString("customProfilesJson"))
                    prefs[Keys.CUSTOM_PROFILES_JSON] = encodeProfilesJson(profiles)
                }
                if (obj.has("kidsSafetyEnabled")) prefs[Keys.KIDS_SAFETY_ENABLED] = obj.optBoolean("kidsSafetyEnabled", false)
                if (obj.has("wrongAttemptCooldownEnabled")) prefs[Keys.WRONG_ATTEMPT_COOLDOWN] = obj.optBoolean("wrongAttemptCooldownEnabled", true)
                if (obj.has("kidsLockOnTimerExpiry")) prefs[Keys.KIDS_LOCK_ON_TIMER_EXPIRY] = obj.optBoolean("kidsLockOnTimerExpiry", false)
                if (obj.has("partialZoneEnabled")) prefs[Keys.PARTIAL_ZONE_ENABLED] = obj.optBoolean("partialZoneEnabled", false)
                if (obj.has("partialZonePreset")) prefs[Keys.PARTIAL_ZONE_PRESET] = PartialZonePreset.fromKey(obj.optString("partialZonePreset")).storageKey
                if (obj.has("partialZoneLeft") && obj.has("partialZoneTop") && obj.has("partialZoneRight") && obj.has("partialZoneBottom")) {
                    val rect = NormalizedRect(
                        left = obj.optDouble("partialZoneLeft", 0.08).toFloat(),
                        top = obj.optDouble("partialZoneTop", 0.22).toFloat(),
                        right = obj.optDouble("partialZoneRight", 0.92).toFloat(),
                        bottom = obj.optDouble("partialZoneBottom", 0.56).toFloat()
                    ).clamped()
                    prefs[Keys.PARTIAL_ZONE_LEFT] = rect.left
                    prefs[Keys.PARTIAL_ZONE_TOP] = rect.top
                    prefs[Keys.PARTIAL_ZONE_RIGHT] = rect.right
                    prefs[Keys.PARTIAL_ZONE_BOTTOM] = rect.bottom
                }
                if (obj.has("privacyVeilEnabled")) prefs[Keys.PRIVACY_VEIL_ENABLED] = obj.optBoolean("privacyVeilEnabled", false)
                if (obj.has("stealthBlackoutEnabled")) prefs[Keys.STEALTH_BLACKOUT_ENABLED] = obj.optBoolean("stealthBlackoutEnabled", false)
                if (obj.has("appWatchEnabled")) prefs[Keys.APP_WATCH_ENABLED] = obj.optBoolean("appWatchEnabled", false)
                if (obj.has("watchedAppPackagesJson")) {
                    parseStringSetJson(obj.optString("watchedAppPackagesJson"))?.let {
                        prefs[Keys.WATCHED_APPS_JSON] = encodeStringSetJson(it)
                    }
                }
                if (obj.has("appWatchAction")) prefs[Keys.APP_WATCH_ACTION] = AppWatchAction.fromKey(obj.optString("appWatchAction")).storageKey
                if (obj.has("appWatchAutoDisarmOnExit")) prefs[Keys.APP_WATCH_AUTO_DISARM] = obj.optBoolean("appWatchAutoDisarmOnExit", true)
                if (obj.has("shakeToLockEnabled")) prefs[Keys.SHAKE_TO_LOCK] = obj.optBoolean("shakeToLockEnabled", false)
                if (obj.has("shakeToUnlockEnabled")) prefs[Keys.SHAKE_TO_UNLOCK] = obj.optBoolean("shakeToUnlockEnabled", false)
                if (obj.has("shakeSensitivity")) prefs[Keys.SHAKE_SENSITIVITY] = ShakeSensitivity.fromKey(obj.optString("shakeSensitivity")).storageKey
                if (obj.has("volumeDoublePressAction")) prefs[Keys.VOLUME_DOUBLE_PRESS_ACTION] = VolumeShortcutAction.fromKey(obj.optString("volumeDoublePressAction")).storageKey
                if (obj.has("lowBatteryBlackoutPercent")) prefs[Keys.LOW_BATTERY_BLACKOUT_PERCENT] = obj.optInt("lowBatteryBlackoutPercent", 0).coerceIn(0, 30)
                if (obj.has("autoLockOnChargerConnect")) prefs[Keys.AUTO_LOCK_CHARGER_CONNECT] = obj.optBoolean("autoLockOnChargerConnect", false)
                if (obj.has("unlockOnChargerDisconnect")) prefs[Keys.UNLOCK_CHARGER_DISCONNECT] = obj.optBoolean("unlockOnChargerDisconnect", false)
                if (obj.has("isAdvancedMode")) prefs[Keys.IS_ADVANCED_MODE] = obj.optBoolean("isAdvancedMode", false)
            }
            true
        } catch (_: Exception) {
            false
        }
    }

    private fun parseProfilesJson(raw: String?): List<CustomProfile> {
        if (raw.isNullOrBlank()) return emptyList()
        return try {
            val arr = JSONArray(raw)
            buildList {
                for (i in 0 until arr.length()) {
                    val o = arr.getJSONObject(i)
                    add(
                        CustomProfile(
                            id = o.optString("id", UUID.randomUUID().toString()),
                            name = o.optString("name", "Profile ${i + 1}"),
                            unlockMethod = UnlockMethod.fromKey(o.optString("unlockMethod")),
                            holdDurationMs = o.optLong("holdDurationMs", 1800L).coerceIn(1000L, 3500L),
                            customDimPercent = o.optInt("customDimPercent", 0).coerceIn(0, 95),
                            lockBrightnessPercent = o.optInt("lockBrightnessPercent", -1),
                            borderEnabled = o.optBoolean("borderEnabled", true),
                            borderColor = BorderColorOption.fromKey(o.optString("borderColor")),
                            borderStyle = BorderStyle.fromKey(o.optString("borderStyle")),
                            badgeVisibilityMode = BadgeVisibilityMode.fromKey(o.optString("badgeVisibilityMode")),
                            floatingBubbleEnabled = o.optBoolean("floatingBubbleEnabled", true),
                            pocketAutoLockEnabled = o.optBoolean("pocketAutoLockEnabled", true),
                            keepScreenOnEnabled = o.optBoolean("keepScreenOnEnabled", true),
                            oledClockEnabled = o.optBoolean("oledClockEnabled", false)
                        )
                    )
                }
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun encodeProfilesJson(profiles: List<CustomProfile>): String {
        val arr = JSONArray()
        profiles.forEach { p ->
            arr.put(
                JSONObject().apply {
                    put("id", p.id)
                    put("name", p.name)
                    put("unlockMethod", p.unlockMethod.storageKey)
                    put("holdDurationMs", p.holdDurationMs)
                    put("customDimPercent", p.customDimPercent)
                    put("lockBrightnessPercent", p.lockBrightnessPercent)
                    put("borderEnabled", p.borderEnabled)
                    put("borderColor", p.borderColor.storageKey)
                    put("borderStyle", p.borderStyle.storageKey)
                    put("badgeVisibilityMode", p.badgeVisibilityMode.storageKey)
                    put("floatingBubbleEnabled", p.floatingBubbleEnabled)
                    put("pocketAutoLockEnabled", p.pocketAutoLockEnabled)
                    put("keepScreenOnEnabled", p.keepScreenOnEnabled)
                    put("oledClockEnabled", p.oledClockEnabled)
                }
            )
        }
        return arr.toString()
    }

    private fun parseLongMapJson(raw: String?): Map<String, Long> {
        if (raw.isNullOrBlank()) return emptyMap()
        return try {
            val obj = JSONObject(raw)
            buildMap {
                val keys = obj.keys()
                while (keys.hasNext()) {
                    val k = keys.next()
                    put(k, obj.optLong(k, 0L))
                }
            }
        } catch (_: Exception) {
            emptyMap()
        }
    }

    private fun encodeLongMapJson(map: Map<String, Long>): String {
        val obj = JSONObject()
        map.forEach { (k, v) -> obj.put(k, v) }
        return obj.toString()
    }

    private fun parseStringSetJson(raw: String?): Set<String>? {
        if (raw == null) return null
        return try {
            val arr = JSONArray(raw)
            buildSet {
                for (i in 0 until arr.length()) {
                    val v = arr.optString(i, "").trim()
                    if (v.isNotEmpty()) add(v)
                }
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun encodeStringSetJson(values: Set<String>): String {
        val arr = JSONArray()
        values.forEach { arr.put(it) }
        return arr.toString()
    }

    companion object {
        fun todayDateKey(nowMs: Long = System.currentTimeMillis()): String {
            val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            return fmt.format(Date(nowMs))
        }

        fun last7DayKeys(nowMs: Long = System.currentTimeMillis()): List<String> {
            val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val cal = Calendar.getInstance().apply { timeInMillis = nowMs }
            return (6 downTo 0).map { offset ->
                val dayCal = cal.clone() as Calendar
                dayCal.add(Calendar.DAY_OF_YEAR, -offset)
                fmt.format(dayCal.time)
            }
        }

        fun buildLast7DayEntries(
            history: Map<String, Long>,
            nowMs: Long = System.currentTimeMillis(),
            locale: Locale = Locale.getDefault()
        ): List<DailyStatEntry> {
            val keyFmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val labelFmt = SimpleDateFormat("EEEEE", locale)
            val cal = Calendar.getInstance().apply { timeInMillis = nowMs }
            return (6 downTo 0).map { offset ->
                val dayCal = cal.clone() as Calendar
                dayCal.add(Calendar.DAY_OF_YEAR, -offset)
                val key = keyFmt.format(dayCal.time)
                val label = labelFmt.format(dayCal.time).ifBlank { key.takeLast(2) }
                DailyStatEntry(
                    dateKey = key,
                    label = label,
                    blockedTouches = history[key] ?: 0L
                )
            }
        }
    }
}
