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

enum class LockSource {
    NONE,
    MANUAL_APP,
    FLOATING_BUBBLE,
    NOTIFICATION,
    QUICK_TILE,
    POCKET_SENSOR,
    FLIP_GESTURE,
    WIDGET
}

enum class SettingsSection {
    UNLOCK,
    OVERLAY,
    BUBBLE,
    POCKET,
    SYSTEM
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
    // System, Appearance & Profiles
    val keepScreenOnEnabled: Boolean = true,
    val hapticFeedbackEnabled: Boolean = true,
    val autoStartOnBootEnabled: Boolean = false,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColorEnabled: Boolean = true,
    val appLanguage: AppLanguage = AppLanguage.SYSTEM_AUTO,
    val hasCompletedOnboarding: Boolean = false,
    val customProfiles: List<CustomProfile> = emptyList(),
    // Stats & Analytics
    val totalBlockedTouches: Long = 0L,
    val totalLockSessions: Long = 0L,
    val dailyBlockedHistory: Map<String, Long> = emptyMap(),
    val lockSourceCounts: Map<String, Long> = emptyMap()
) {
    val darkThemeEnabled: Boolean
        get() = themeMode == ThemeMode.DARK
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
    val batteryPercent: Int = 100
)
