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
import kotlinx.coroutines.flow.map

private val Context.shieldDataStore: DataStore<Preferences> by preferencesDataStore(name = "touchshield_prefs")

class ShieldPreferences(private val context: Context) {

    private object Keys {
        val IS_ARMED = booleanPreferencesKey("is_armed")
        val UNLOCK_METHOD = stringPreferencesKey("unlock_method")
        val HOLD_DURATION_MS = longPreferencesKey("hold_duration_ms")
        val UNLOCK_PIN = stringPreferencesKey("unlock_pin")
        val LOCK_COUNTDOWN_SECONDS = intPreferencesKey("lock_countdown_seconds")
        val HUD_TIMEOUT_MS = longPreferencesKey("hud_timeout_ms")

        val OVERLAY_STYLE = stringPreferencesKey("overlay_style")
        val CUSTOM_DIM_PERCENT = intPreferencesKey("custom_dim_percent")
        val BORDER_ENABLED = booleanPreferencesKey("border_enabled")
        val BORDER_COLOR = stringPreferencesKey("border_color")
        val BORDER_THICKNESS = stringPreferencesKey("border_thickness")
        val BADGE_VISIBILITY_MODE = stringPreferencesKey("badge_visibility_mode")

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
        val AUTO_UNLOCK_ON_CALL = booleanPreferencesKey("auto_unlock_on_call")
        val SESSION_TIMER_MINUTES = intPreferencesKey("session_timer_minutes")

        val KEEP_SCREEN_ON = booleanPreferencesKey("keep_screen_on")
        val HAPTIC_FEEDBACK = booleanPreferencesKey("haptic_feedback")
        val AUTO_START_ON_BOOT = booleanPreferencesKey("auto_start_on_boot")
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val APP_LANGUAGE = stringPreferencesKey("app_language")
        val TOTAL_BLOCKED_TOUCHES = longPreferencesKey("total_blocked_touches")
        val TOTAL_LOCK_SESSIONS = longPreferencesKey("total_lock_sessions")
    }

    val settingsFlow: Flow<ShieldSettings> = context.shieldDataStore.data.map { prefs ->
        val overlayStyle = OverlayStyle.fromKey(prefs[Keys.OVERLAY_STYLE])
        ShieldSettings(
            isArmed = prefs[Keys.IS_ARMED] ?: false,
            unlockMethod = UnlockMethod.fromKey(prefs[Keys.UNLOCK_METHOD]),
            holdDurationMs = (prefs[Keys.HOLD_DURATION_MS] ?: 1800L).coerceIn(1000L, 3500L),
            unlockPin = (prefs[Keys.UNLOCK_PIN] ?: "1234").take(4).padEnd(4, '0'),
            lockCountdownSeconds = (prefs[Keys.LOCK_COUNTDOWN_SECONDS] ?: 0).coerceIn(0, 10),
            hudTimeoutMs = (prefs[Keys.HUD_TIMEOUT_MS] ?: 3000L).coerceIn(1500L, 6000L),
            overlayStyle = overlayStyle,
            customDimPercent = (prefs[Keys.CUSTOM_DIM_PERCENT] ?: overlayStyle.defaultDimPercent)
                .coerceIn(0, 95),
            borderEnabled = prefs[Keys.BORDER_ENABLED] ?: true,
            borderColor = BorderColorOption.fromKey(prefs[Keys.BORDER_COLOR]),
            borderThickness = BorderThickness.fromKey(prefs[Keys.BORDER_THICKNESS]),
            badgeVisibilityMode = BadgeVisibilityMode.fromKey(prefs[Keys.BADGE_VISIBILITY_MODE]),
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
            autoUnlockOnCallEnabled = prefs[Keys.AUTO_UNLOCK_ON_CALL] ?: false,
            sessionTimerMinutes = prefs[Keys.SESSION_TIMER_MINUTES] ?: 0,
            keepScreenOnEnabled = prefs[Keys.KEEP_SCREEN_ON] ?: true,
            hapticFeedbackEnabled = prefs[Keys.HAPTIC_FEEDBACK] ?: true,
            autoStartOnBootEnabled = prefs[Keys.AUTO_START_ON_BOOT] ?: false,
            darkThemeEnabled = prefs[Keys.DARK_THEME] ?: false,
            appLanguage = AppLanguage.fromKey(prefs[Keys.APP_LANGUAGE]),
            totalBlockedTouches = prefs[Keys.TOTAL_BLOCKED_TOUCHES] ?: 0L,
            totalLockSessions = prefs[Keys.TOTAL_LOCK_SESSIONS] ?: 0L
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

    suspend fun setBorderEnabled(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.BORDER_ENABLED] = enabled }
    }

    suspend fun setBorderColor(color: BorderColorOption) {
        context.shieldDataStore.edit { it[Keys.BORDER_COLOR] = color.storageKey }
    }

    suspend fun setBorderThickness(thickness: BorderThickness) {
        context.shieldDataStore.edit { it[Keys.BORDER_THICKNESS] = thickness.storageKey }
    }

    suspend fun setBadgeVisibilityMode(mode: BadgeVisibilityMode) {
        context.shieldDataStore.edit { it[Keys.BADGE_VISIBILITY_MODE] = mode.storageKey }
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

    suspend fun setAutoUnlockOnCall(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.AUTO_UNLOCK_ON_CALL] = enabled }
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

    suspend fun setDarkTheme(enabled: Boolean) {
        context.shieldDataStore.edit { it[Keys.DARK_THEME] = enabled }
    }

    suspend fun setAppLanguage(language: AppLanguage) {
        context.shieldDataStore.edit { it[Keys.APP_LANGUAGE] = language.storageKey }
    }

    suspend fun applySmartPreset(preset: SmartPreset) {
        context.shieldDataStore.edit { prefs ->
            when (preset) {
                SmartPreset.POCKET_PHOTO -> {
                    prefs[Keys.OVERLAY_STYLE] = OverlayStyle.CLEAR_GLASS.storageKey
                    prefs[Keys.CUSTOM_DIM_PERCENT] = 0
                    prefs[Keys.KEEP_SCREEN_ON] = true
                    prefs[Keys.POCKET_AUTO_LOCK] = true
                    prefs[Keys.POCKET_ORIENTATION_FILTER] = true
                    prefs[Keys.UNLOCK_METHOD] = UnlockMethod.LONG_PRESS.storageKey
                    prefs[Keys.HOLD_DURATION_MS] = 1800L
                    prefs[Keys.BORDER_ENABLED] = true
                    prefs[Keys.BORDER_COLOR] = BorderColorOption.EMERALD.storageKey
                    prefs[Keys.BORDER_THICKNESS] = BorderThickness.MEDIUM.storageKey
                    prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.ALWAYS.storageKey
                    prefs[Keys.FLOATING_BUBBLE_ENABLED] = true
                }

                SmartPreset.KIDS_VIDEO -> {
                    prefs[Keys.OVERLAY_STYLE] = OverlayStyle.CLEAR_GLASS.storageKey
                    prefs[Keys.CUSTOM_DIM_PERCENT] = 0
                    prefs[Keys.KEEP_SCREEN_ON] = true
                    prefs[Keys.POCKET_AUTO_LOCK] = false
                    prefs[Keys.UNLOCK_METHOD] = UnlockMethod.LONG_PRESS.storageKey
                    prefs[Keys.HOLD_DURATION_MS] = 3000L
                    prefs[Keys.BORDER_ENABLED] = true
                    prefs[Keys.BORDER_COLOR] = BorderColorOption.AMBER.storageKey
                    prefs[Keys.BORDER_THICKNESS] = BorderThickness.THIN.storageKey
                    prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.HIDDEN.storageKey
                    prefs[Keys.FLOATING_BUBBLE_ENABLED] = false
                }

                SmartPreset.BATTERY_SAVER -> {
                    prefs[Keys.OVERLAY_STYLE] = OverlayStyle.AMOLED_BLACK.storageKey
                    prefs[Keys.CUSTOM_DIM_PERCENT] = 88
                    prefs[Keys.KEEP_SCREEN_ON] = false
                    prefs[Keys.POCKET_AUTO_LOCK] = true
                    prefs[Keys.POCKET_ORIENTATION_FILTER] = true
                    prefs[Keys.UNLOCK_METHOD] = UnlockMethod.SLIDE.storageKey
                    prefs[Keys.BORDER_ENABLED] = true
                    prefs[Keys.BORDER_COLOR] = BorderColorOption.CYAN.storageKey
                    prefs[Keys.BORDER_THICKNESS] = BorderThickness.THIN.storageKey
                    prefs[Keys.BADGE_VISIBILITY_MODE] = BadgeVisibilityMode.ON_TOUCH.storageKey
                    prefs[Keys.FLOATING_BUBBLE_ENABLED] = true
                }
            }
        }
    }

    suspend fun resetStats() {
        context.shieldDataStore.edit {
            it[Keys.TOTAL_BLOCKED_TOUCHES] = 0L
            it[Keys.TOTAL_LOCK_SESSIONS] = 0L
        }
    }

    suspend fun restoreDefaults() {
        context.shieldDataStore.edit { prefs ->
            val currentLang = prefs[Keys.APP_LANGUAGE]
            val currentArmed = prefs[Keys.IS_ARMED] ?: false
            prefs.clear()
            if (currentLang != null) {
                prefs[Keys.APP_LANGUAGE] = currentLang
            }
            prefs[Keys.IS_ARMED] = currentArmed
        }
    }

    suspend fun incrementBlockedTouches(count: Long = 1L) {
        context.shieldDataStore.edit { prefs ->
            val current = prefs[Keys.TOTAL_BLOCKED_TOUCHES] ?: 0L
            prefs[Keys.TOTAL_BLOCKED_TOUCHES] = current + count
        }
    }

    suspend fun incrementLockSessions() {
        context.shieldDataStore.edit { prefs ->
            val current = prefs[Keys.TOTAL_LOCK_SESSIONS] ?: 0L
            prefs[Keys.TOTAL_LOCK_SESSIONS] = current + 1L
        }
    }
}
