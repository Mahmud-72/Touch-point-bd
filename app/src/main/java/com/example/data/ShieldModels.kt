package com.example.data

import java.util.Locale

enum class UnlockMethod(val storageKey: String) {
    LONG_PRESS("long_press"),
    SLIDE("slide"),
    DOUBLE_TAP("double_tap"),
    PIN("pin");

    companion object {
        fun fromKey(key: String?): UnlockMethod =
            entries.firstOrNull { it.storageKey == key } ?: LONG_PRESS
    }
}

enum class OverlayStyle(val storageKey: String, val defaultDimPercent: Int) {
    CLEAR_GLASS("clear_glass", 0),
    SUBTLE_TINT("subtle_tint", 25),
    AMOLED_BLACK("amoled_black", 88);

    companion object {
        fun fromKey(key: String?): OverlayStyle =
            entries.firstOrNull { it.storageKey == key } ?: CLEAR_GLASS
    }
}

enum class BorderColorOption(val storageKey: String, val colorArgb: Int) {
    EMERALD("emerald", 0xFF10D99B.toInt()),
    CYAN("cyan", 0xFF38BDF8.toInt()),
    AMBER("amber", 0xFFF59E0B.toInt()),
    ROSE("rose", 0xFFFB7185.toInt());

    companion object {
        fun fromKey(key: String?): BorderColorOption =
            entries.firstOrNull { it.storageKey == key } ?: EMERALD
    }
}

enum class BorderThickness(val storageKey: String, val dpValue: Float) {
    THIN("thin", 1.5f),
    MEDIUM("medium", 3.0f),
    BOLD("bold", 4.5f);

    companion object {
        fun fromKey(key: String?): BorderThickness =
            entries.firstOrNull { it.storageKey == key } ?: MEDIUM
    }
}

enum class BorderStyle(val storageKey: String) {
    FULL_GLOW("full_glow"),
    CORNER_BRACKETS("corner_brackets");

    companion object {
        fun fromKey(key: String?): BorderStyle =
            entries.firstOrNull { it.storageKey == key } ?: FULL_GLOW
    }
}

enum class BadgeVisibilityMode(val storageKey: String) {
    ALWAYS("always"),
    ON_TOUCH("on_touch"),
    HIDDEN("hidden");

    companion object {
        fun fromKey(key: String?): BadgeVisibilityMode =
            entries.firstOrNull { it.storageKey == key } ?: ALWAYS
    }
}

enum class BubbleSize(val storageKey: String, val dpSize: Int) {
    COMPACT("compact", 44),
    STANDARD("standard", 54),
    LARGE("large", 66);

    companion object {
        fun fromKey(key: String?): BubbleSize =
            entries.firstOrNull { it.storageKey == key } ?: STANDARD
    }
}

enum class BubbleStyle(val storageKey: String) {
    SHIELD_CIRCLE("shield_circle"),
    MINIMAL_DOT("minimal_dot"),
    COMPACT_PILL("compact_pill");

    companion object {
        fun fromKey(key: String?): BubbleStyle =
            entries.firstOrNull { it.storageKey == key } ?: SHIELD_CIRCLE
    }
}

enum class SmartPreset {
    POCKET_PHOTO,
    KIDS_VIDEO,
    BATTERY_SAVER,
    NAVIGATION_MAP
}

enum class ThemeMode(val storageKey: String) {
    SYSTEM("system"),
    LIGHT("light"),
    DARK("dark");

    companion object {
        fun fromKey(key: String?): ThemeMode =
            entries.firstOrNull { it.storageKey == key } ?: SYSTEM
    }
}

enum class AppLanguage(val storageKey: String, val displayLabel: String) {
    SYSTEM_AUTO("auto", "Auto (System)"),
    ENGLISH("en", "English"),
    BENGALI("bn", "বাংলা"),
    HINDI("hi", "हिन्दी"),
    ARABIC("ar", "العربية"),
    SPANISH("es", "Español"),
    INDONESIAN("in", "Bahasa Indonesia"),
    PORTUGUESE("pt", "Português");

    fun resolve(systemLangCode: String = Locale.getDefault().language): AppLanguage {
        if (this != SYSTEM_AUTO) return this
        return when (systemLangCode.lowercase(Locale.US)) {
            "bn" -> BENGALI
            "hi" -> HINDI
            "ar" -> ARABIC
            "es" -> SPANISH
            "in", "id" -> INDONESIAN
            "pt" -> PORTUGUESE
            else -> ENGLISH
        }
    }

    fun isRtl(systemLangCode: String = Locale.getDefault().language): Boolean =
        resolve(systemLangCode) == ARABIC

    companion object {
        fun fromKey(key: String?): AppLanguage =
            entries.firstOrNull { it.storageKey == key } ?: SYSTEM_AUTO
    }
}

enum class AppWatchAction(val storageKey: String) {
    SHOW_BUBBLE("show_bubble"),
    AUTO_LOCK("auto_lock");

    companion object {
        fun fromKey(key: String?): AppWatchAction =
            entries.firstOrNull { it.storageKey == key } ?: SHOW_BUBBLE
    }
}

enum class ShakeSensitivity(val storageKey: String, val gThreshold: Float) {
    LOW("low", 21.5f),
    MEDIUM("medium", 17.5f),
    HIGH("high", 14.5f);

    companion object {
        fun fromKey(key: String?): ShakeSensitivity =
            entries.firstOrNull { it.storageKey == key } ?: MEDIUM
    }
}

enum class VolumeShortcutAction(val storageKey: String) {
    OFF("off"),
    TOGGLE_LOCK("toggle_lock"),
    UNLOCK_ONLY("unlock_only");

    companion object {
        fun fromKey(key: String?): VolumeShortcutAction =
            entries.firstOrNull { it.storageKey == key } ?: OFF
    }
}

enum class PartialZonePreset(val storageKey: String) {
    FULL_LOCK("full_lock"),
    TOP_HALF("top_half"),
    BOTTOM_HALF("bottom_half"),
    CENTER_WINDOW("center_window"),
    CUSTOM("custom");

    companion object {
        fun fromKey(key: String?): PartialZonePreset =
            entries.firstOrNull { it.storageKey == key } ?: CENTER_WINDOW
    }
}

data class NormalizedRect(
    val left: Float = 0.08f,
    val top: Float = 0.22f,
    val right: Float = 0.92f,
    val bottom: Float = 0.56f
) {
    fun clamped(): NormalizedRect {
        val l = left.coerceIn(0.0f, 0.80f)
        val t = top.coerceIn(0.05f, 0.80f)
        val r = right.coerceIn(l + 0.18f, 1.0f)
        val b = bottom.coerceIn(t + 0.14f, 0.95f)
        return NormalizedRect(l, t, r, b)
    }
}

enum class LockSource {
    NONE,
    MANUAL_APP,
    FLOATING_BUBBLE,
    NOTIFICATION,
    QUICK_TILE,
    POCKET_SENSOR,
    FLIP_GESTURE,
    WIDGET,
    APP_WATCH,
    SHAKE_GESTURE,
    VOLUME_SHORTCUT,
    CHARGER_RULE,
    KIDS_TIMEOUT
}

enum class SettingsSection {
    UNLOCK,
    OVERLAY,
    BUBBLE,
    POCKET,
    SYSTEM
}

enum class SettingHelpTopic {
    MODE_EASY_PRO,
    UNLOCK_METHOD,
    BIOMETRIC_UNLOCK,
    EMERGENCY_HOLD_BYPASS,
    LOCK_COUNTDOWN,
    HUD_TIMEOUT,
    KIDS_SAFETY,
    WRONG_ATTEMPT_COOLDOWN,
    KIDS_TIMER_EXPIRY,
    OVERLAY_STYLE,
    LOCK_BRIGHTNESS,
    EDGE_BORDER,
    OLED_CLOCK,
    PARTIAL_TOUCH_ZONE,
    PRIVACY_VEIL,
    STEALTH_BLACKOUT,
    FLOATING_BUBBLE,
    BUBBLE_AUTO_TUCK,
    BUBBLE_DOUBLE_TAP_HIDE,
    POCKET_AUTO_LOCK,
    POCKET_AUTO_UNLOCK,
    POCKET_SENSITIVITY,
    ORIENTATION_FILTER,
    PAUSE_LANDSCAPE,
    PAUSE_CHARGING,
    FLIP_FACE_DOWN,
    CALL_AUTO_UNLOCK,
    SESSION_TIMER,
    APP_WATCH,
    SHAKE_SHORTCUT,
    VOLUME_SHORTCUT,
    LOW_BATTERY_BLACKOUT,
    CHARGER_AUTO_RULE,
    KEEP_SCREEN_ON,
    HAPTICS_AND_BOOT,
    BACKUP_RESTORE
}

data class CustomProfile(
    val id: String,
    val name: String,
    val unlockMethod: UnlockMethod,
    val holdDurationMs: Long,
    val customDimPercent: Int,
    val lockBrightnessPercent: Int,
    val borderEnabled: Boolean,
    val borderColor: BorderColorOption,
    val borderStyle: BorderStyle,
    val badgeVisibilityMode: BadgeVisibilityMode,
    val floatingBubbleEnabled: Boolean,
    val pocketAutoLockEnabled: Boolean,
    val keepScreenOnEnabled: Boolean,
    val oledClockEnabled: Boolean
)

data class DailyStatEntry(
    val dateKey: String,
    val label: String,
    val blockedTouches: Long
)

data class ShieldSettings(
    val isArmed: Boolean = false,
    // Security & Unlock
    val unlockMethod: UnlockMethod = UnlockMethod.LONG_PRESS,
    val holdDurationMs: Long = 1800L,
    val unlockPin: String = "1234",
    val biometricUnlockEnabled: Boolean = true,
    val emergencyHoldBypassEnabled: Boolean = true,
    val lockCountdownSeconds: Int = 0,
    val hudTimeoutMs: Long = 3000L,
    // Kids Safety & Cooldown
    val kidsSafetyEnabled: Boolean = false,
    val wrongAttemptCooldownEnabled: Boolean = true,
    val kidsLockOnTimerExpiry: Boolean = false,
    // Overlay Visuals & Display
    val overlayStyle: OverlayStyle = OverlayStyle.CLEAR_GLASS,
    val customDimPercent: Int = 0,
    val lockBrightnessPercent: Int = -1,
    val borderEnabled: Boolean = true,
    val borderColor: BorderColorOption = BorderColorOption.EMERALD,
    val borderThickness: BorderThickness = BorderThickness.MEDIUM,
    val borderStyle: BorderStyle = BorderStyle.FULL_GLOW,
    val borderPulseEnabled: Boolean = true,
    val badgeVisibilityMode: BadgeVisibilityMode = BadgeVisibilityMode.ALWAYS,
    val oledClockEnabled: Boolean = false,
    // Partial Touch Zone & Privacy Veil
    val partialZoneEnabled: Boolean = false,
    val partialZonePreset: PartialZonePreset = PartialZonePreset.CENTER_WINDOW,
    val partialZoneRect: NormalizedRect = NormalizedRect(),
    val privacyVeilEnabled: Boolean = false,
    val stealthBlackoutEnabled: Boolean = false,
    // Floating Bubble
    val floatingBubbleEnabled: Boolean = true,
    val floatingBubbleOpacity: Float = 0.85f,
    val bubbleSize: BubbleSize = BubbleSize.STANDARD,
    val bubbleStyle: BubbleStyle = BubbleStyle.SHIELD_CIRCLE,
    val bubbleAutoTuckEnabled: Boolean = true,
    val bubbleDoubleTapHideEnabled: Boolean = true,
    // Pocket Sensor & Context Automation
    val pocketAutoLockEnabled: Boolean = true,
    val pocketAutoUnlockEnabled: Boolean = false,
    val pocketDebounceMs: Long = 700L,
    val pocketOrientationFilterEnabled: Boolean = true,
    val pausePocketInLandscape: Boolean = true,
    val pausePocketWhileCharging: Boolean = false,
    val flipFaceDownLockEnabled: Boolean = false,
    val autoUnlockOnCallEnabled: Boolean = false,
    val reLockAfterCallEnabled: Boolean = false,
    val sessionTimerMinutes: Int = 0,
    // App-Watch, Shake/Volume Shortcuts & Power Guard
    val appWatchEnabled: Boolean = false,
    val watchedAppPackages: Set<String> = DEFAULT_WATCHED_APPS,
    val appWatchAction: AppWatchAction = AppWatchAction.SHOW_BUBBLE,
    val appWatchAutoDisarmOnExit: Boolean = true,
    val shakeToLockEnabled: Boolean = false,
    val shakeToUnlockEnabled: Boolean = false,
    val shakeSensitivity: ShakeSensitivity = ShakeSensitivity.MEDIUM,
    val volumeDoublePressAction: VolumeShortcutAction = VolumeShortcutAction.OFF,
    val lowBatteryBlackoutPercent: Int = 0,
    val autoLockOnChargerConnect: Boolean = false,
    val unlockOnChargerDisconnect: Boolean = false,
    // System, Appearance & Profiles
    val keepScreenOnEnabled: Boolean = true,
    val hapticFeedbackEnabled: Boolean = true,
    val autoStartOnBootEnabled: Boolean = false,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColorEnabled: Boolean = true,
    val appLanguage: AppLanguage = AppLanguage.SYSTEM_AUTO,
    val hasCompletedOnboarding: Boolean = false,
    val isAdvancedMode: Boolean = false,
    val customProfiles: List<CustomProfile> = emptyList(),
    // Stats & Analytics
    val totalBlockedTouches: Long = 0L,
    val totalLockSessions: Long = 0L,
    val dailyBlockedHistory: Map<String, Long> = emptyMap(),
    val lockSourceCounts: Map<String, Long> = emptyMap()
) {
    val darkThemeEnabled: Boolean
        get() = themeMode == ThemeMode.DARK

    companion object {
        val DEFAULT_WATCHED_APPS = setOf(
            "com.google.android.youtube",
            "com.google.android.apps.maps"
        )
    }
}

data class ShieldRuntimeState(
    val isServiceRunning: Boolean = false,
    val isScreenLocked: Boolean = false,
    val lockSource: LockSource = LockSource.NONE,
    val countdownRemainingSec: Int = 0,
    val isBubbleTemporarilyHidden: Boolean = false,
    val sessionBlockedTouches: Int = 0,
    val lockStartTimeMs: Long = 0L,
    val hasProximitySensor: Boolean = false,
    val sensorName: String = "",
    val proximityIsNear: Boolean = false,
    val proximityDistanceCm: Float = 5f,
    val proximityMaxRangeCm: Float = 5f,
    val hasAccelerometer: Boolean = false,
    val accelerometerName: String = "",
    val isPocketOrientationValid: Boolean = true,
    val isFlatFaceUp: Boolean = false,
    val isFaceDown: Boolean = false,
    val isLandscape: Boolean = false,
    val isCharging: Boolean = false,
    val batteryPercent: Int = 100,
    val activeWatchedApp: String? = null,
    val isLowBatteryBlackoutActive: Boolean = false,
    val isKidsTimeUpLocked: Boolean = false
)
