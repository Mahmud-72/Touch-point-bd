package com.example.data

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
    BATTERY_SAVER
}

enum class AppLanguage(val storageKey: String) {
    ENGLISH("en"),
    BENGALI("bn");

    companion object {
        fun fromKey(key: String?): AppLanguage =
            entries.firstOrNull { it.storageKey == key } ?: BENGALI
    }
}

enum class LockSource {
    NONE,
    MANUAL_APP,
    FLOATING_BUBBLE,
    NOTIFICATION,
    QUICK_TILE,
    POCKET_SENSOR
}

data class ShieldSettings(
    val isArmed: Boolean = false,
    // Unlock & Security
    val unlockMethod: UnlockMethod = UnlockMethod.LONG_PRESS,
    val holdDurationMs: Long = 1800L,
    val unlockPin: String = "1234",
    val lockCountdownSeconds: Int = 0,
    val hudTimeoutMs: Long = 3000L,
    // Overlay Visuals
    val overlayStyle: OverlayStyle = OverlayStyle.CLEAR_GLASS,
    val customDimPercent: Int = 0,
    val borderEnabled: Boolean = true,
    val borderColor: BorderColorOption = BorderColorOption.EMERALD,
    val borderThickness: BorderThickness = BorderThickness.MEDIUM,
    val badgeVisibilityMode: BadgeVisibilityMode = BadgeVisibilityMode.ALWAYS,
    // Floating Bubble
    val floatingBubbleEnabled: Boolean = true,
    val floatingBubbleOpacity: Float = 0.85f,
    val bubbleSize: BubbleSize = BubbleSize.STANDARD,
    val bubbleStyle: BubbleStyle = BubbleStyle.SHIELD_CIRCLE,
    val bubbleAutoTuckEnabled: Boolean = true,
    val bubbleDoubleTapHideEnabled: Boolean = true,
    // Pocket Sensor & Automation
    val pocketAutoLockEnabled: Boolean = true,
    val pocketAutoUnlockEnabled: Boolean = false,
    val pocketDebounceMs: Long = 700L,
    val pocketOrientationFilterEnabled: Boolean = true,
    val autoUnlockOnCallEnabled: Boolean = false,
    val sessionTimerMinutes: Int = 0,
    // System & App
    val keepScreenOnEnabled: Boolean = true,
    val hapticFeedbackEnabled: Boolean = true,
    val autoStartOnBootEnabled: Boolean = false,
    val darkThemeEnabled: Boolean = false,
    val appLanguage: AppLanguage = AppLanguage.BENGALI,
    val totalBlockedTouches: Long = 0L,
    val totalLockSessions: Long = 0L
)

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
    val isFlatFaceUp: Boolean = false
)
