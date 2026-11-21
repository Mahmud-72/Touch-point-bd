package com.example.ui

import com.example.data.AppLanguage
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderColorOption
import com.example.data.BorderStyle
import com.example.data.BorderThickness
import com.example.data.BubbleSize
import com.example.data.BubbleStyle
import com.example.data.LockSource
import com.example.data.OverlayStyle
import com.example.data.SmartPreset
import com.example.data.ThemeMode
import com.example.data.UnlockMethod

data class UiGeneralStrings(
    val appTitle: String,
    val appTagline: String,
    val tabDashboard: String,
    val tabSettings: String,
    val tabGuide: String,
    val statusStandby: String,
    val statusArmed: String,
    val statusLocked: String,
    val statusPermissionNeeded: String,
    val statusStandbyDesc: String,
    val statusArmedDesc: String,
    val statusLockedDesc: String,
    val statusPermissionDesc: String,
    val btnArmShield: String,
    val btnStopShield: String,
    val btnLockScreenNow: String,
    val btnTestLockSandbox: String,
    val btnGrantPermission: String,
    val btnEnableNow: String,
    val btnBatterySettings: String,
    val permissionsCardTitle: String,
    val permOverlayTitle: String,
    val permOverlayDesc: String,
    val permNotificationTitle: String,
    val permNotificationDesc: String,
    val permGranted: String,
    val permRequired: String,
    val permOptional: String,
    val statBlockedTouches: String,
    val statLockSessions: String,
    val statActiveMode: String,
    val analyticsTitle: String,
    val analyticsEmptyHint: String,
    val analyticsSourcesTitle: String,
    val sensorCardTitle: String,
    val sensorNear: String,
    val sensorFar: String,
    val sensorUnavailable: String,
    val sensorTestHint: String,
    val sensorOrientationPocket: String,
    val sensorOrientationFlat: String,
    val sensorOrientationFaceDown: String,
    val sensorPausedLandscape: String,
    val sensorPausedCharging: String,
    val quickControlsTitle: String,
    val togglePocketAutoLock: String,
    val togglePocketAutoLockDesc: String,
    val togglePocketAutoUnlock: String,
    val togglePocketAutoUnlockDesc: String,
    val toggleFloatingBubble: String,
    val toggleFloatingBubbleDesc: String,
    val toggleKeepScreenOn: String,
    val toggleKeepScreenOnDesc: String,
    val presetsTitle: String,
    val presetPocketPhotoTitle: String,
    val presetPocketPhotoDesc: String,
    val presetKidsVideoTitle: String,
    val presetKidsVideoDesc: String,
    val presetBatteryTitle: String,
    val presetBatteryDesc: String,
    val presetNavMapTitle: String,
    val presetNavMapDesc: String,
    val customProfilesTitle: String,
    val btnSaveProfile: String,
    val saveProfileDialogTitle: String,
    val saveProfileHint: String,
    val renameProfileDialogTitle: String,
    val noCustomProfilesHint: String,
    val settingsSearchHint: String,
    val settingsNoSearchResults: String,
    val btnExpandAll: String,
    val btnCollapseAll: String,
    val btnTestGestureHere: String,
    val inlineTesterTitle: String,
    val inlineTesterSuccess: String,
    val settingsLivePreviewTitle: String,
    val settingsUnlockSection: String,
    val settingsOverlaySection: String,
    val settingsBubbleSection: String,
    val settingsPocketSection: String,
    val settingsSystemSection: String,
    val subheaderSecurity: String,
    val subheaderRecovery: String,
    val subheaderBrightness: String,
    val subheaderBorders: String,
    val subheaderOledHud: String,
    val subheaderSensorTuning: String,
    val subheaderContextRules: String,
    val subheaderCallTimer: String,
    val subheaderAppearance: String,
    val subheaderBackupReset: String,
    val subheaderDiagnostics: String
)

data class UiSettingsStrings(
    val holdDurationLabel: String,
    val hudTimeoutLabel: String,
    val lockCountdownLabel: String,
    val countdownOff: String,
    val pinCodeLabel: String,
    val btnChangePin: String,
    val pinDialogTitle: String,
    val pinDialogHint: String,
    val btnSave: String,
    val btnCancel: String,
    val overlayPinHint: String,
    val overlayPinWrong: String,
    val toggleBiometricUnlock: String,
    val toggleBiometricUnlockDesc: String,
    val overlayBtnBiometric: String,
    val toggleEmergencyBypass: String,
    val toggleEmergencyBypassDesc: String,
    val overlayEmergencyHoldLabel: String,
    val customDimLabel: String,
    val clearPercentLabel: String,
    val lockBrightnessLabel: String,
    val brightnessSystem: String,
    val brightnessNight: String,
    val brightnessOutdoor: String,
    val toggleBorderEnabled: String,
    val toggleBorderDesc: String,
    val borderColorLabel: String,
    val borderColorEmerald: String,
    val borderColorCyan: String,
    val borderColorAmber: String,
    val borderColorRose: String,
    val borderThicknessLabel: String,
    val borderStyleLabel: String,
    val borderStyleGlow: String,
    val borderStyleCorners: String,
    val toggleBorderPulse: String,
    val toggleBorderPulseDesc: String,
    val badgeModeLabel: String,
    val badgeAlways: String,
    val badgeOnTouch: String,
    val badgeHidden: String,
    val toggleOledClock: String,
    val toggleOledClockDesc: String,
    val thicknessThin: String,
    val thicknessMedium: String,
    val thicknessBold: String,
    val bubbleOpacityLabel: String,
    val bubbleSizeLabel: String,
    val bubbleSizeCompact: String,
    val bubbleSizeStandard: String,
    val bubbleSizeLarge: String,
    val bubbleStyleLabel: String,
    val bubbleStyleShield: String,
    val bubbleStyleDot: String,
    val bubbleStylePill: String,
    val bubblePillLabel: String,
    val toggleBubbleAutoTuck: String,
    val toggleBubbleAutoTuckDesc: String,
    val toggleBubbleDoubleTapHide: String,
    val toggleBubbleDoubleTapHideDesc: String,
    val pocketDebounceLabel: String,
    val toggleOrientationFilter: String,
    val toggleOrientationFilterDesc: String,
    val togglePauseLandscape: String,
    val togglePauseLandscapeDesc: String,
    val togglePauseCharging: String,
    val togglePauseChargingDesc: String,
    val toggleFlipFaceDownLock: String,
    val toggleFlipFaceDownLockDesc: String,
    val toggleAutoUnlockOnCall: String,
    val toggleAutoUnlockOnCallDesc: String,
    val toggleReLockAfterCall: String,
    val toggleReLockAfterCallDesc: String,
    val sessionTimerLabel: String,
    val timerOff: String,
    val toggleHaptics: String,
    val toggleHapticsDesc: String,
    val toggleAutoBoot: String,
    val toggleAutoBootDesc: String,
    val themeModeLabel: String,
    val themeSystem: String,
    val themeLight: String,
    val themeDark: String,
    val toggleDynamicColor: String,
    val toggleDynamicColorDesc: String,
    val languageLabel: String,
    val languageAutoSystem: String,
    val btnExportBackup: String,
    val btnImportBackup: String,
    val backupExportSuccess: String,
    val backupImportSuccess: String,
    val backupError: String,
    val privacyCardTitle: String,
    val privacyBadgeOffline: String,
    val privacyBadgeNoNet: String,
    val privacyBadgeZeroTrack: String,
    val privacyCardDesc: String,
    val batteryCardTitle: String,
    val batteryCardDesc: String,
    val diagProximity: String,
    val diagAccelerometer: String,
    val diagOverlay: String,
    val diagNotifications: String,
    val diagPhoneState: String,
    val diagBattery: String,
    val diagAvailable: String,
    val diagUnavailable: String,
    val diagUnrestricted: String,
    val diagOptimized: String,
    val btnResetStats: String,
    val btnRestoreDefaults: String,
    val confirmResetStatsTitle: String,
    val confirmResetStatsMsg: String,
    val confirmRestoreTitle: String,
    val confirmRestoreMsg: String,
    val btnConfirm: String,
    val statusOnLabel: String,
    val statusOffLabel: String
)

data class UiOverlayGuideStrings(
    val unlockHoldTitle: String,
    val unlockHoldDesc: String,
    val unlockSlideTitle: String,
    val unlockSlideDesc: String,
    val unlockDoubleTapTitle: String,
    val unlockDoubleTapDesc: String,
    val unlockPinTitle: String,
    val unlockPinDesc: String,
    val styleClearTitle: String,
    val styleClearDesc: String,
    val styleTintTitle: String,
    val styleTintDesc: String,
    val styleBlackTitle: String,
    val styleBlackDesc: String,
    val overlayTouchBlockedTitle: String,
    val overlayHoldHint: String,
    val overlaySlideHint: String,
    val overlayDoubleTapHint: String,
    val overlayCountdownLabel: String,
    val overlayCountdownCancel: String,
    val sandboxTitle: String,
    val sandboxSubtitle: String,
    val sandboxDummyPhotoTitle: String,
    val sandboxDummyPhotoDesc: String,
    val sandboxTapBlockedToast: String,
    val sandboxTryClicking: String,
    val sandboxLockTriggerBtn: String,
    val sandboxExitBtn: String,
    val notifTitleArmed: String,
    val notifTextArmed: String,
    val notifTitleLocked: String,
    val notifTextLocked: String,
    val notifActionLock: String,
    val notifActionUnlock: String,
    val notifActionPocketOn: String,
    val notifActionPocketOff: String,
    val notifActionShowBubble: String,
    val notifActionStop: String,
    val onboardingSkip: String,
    val onboardingNext: String,
    val onboardingGetStarted: String,
    val onboardingPage1Title: String,
    val onboardingPage1Desc: String,
    val onboardingPage2Title: String,
    val onboardingPage2Desc: String,
    val onboardingPage3Title: String,
    val onboardingPage3Desc: String,
    val btnReplayOnboarding: String,
    val guideTitle: String,
    val guideStep1Title: String,
    val guideStep1Desc: String,
    val guideStep2Title: String,
    val guideStep2Desc: String,
    val guideStep3Title: String,
    val guideStep3Desc: String,
    val guideStep4Title: String,
    val guideStep4Desc: String,
    val guideLimitsTitle: String,
    val guideLimitsDesc: String,
    val lockSourceManual: String,
    val lockSourceBubble: String,
    val lockSourceNotification: String,
    val lockSourceTile: String,
    val lockSourcePocket: String,
    val lockSourceFlip: String,
    val lockSourceWidget: String
)

class AppStrings(
    val general: UiGeneralStrings,
    val cfg: UiSettingsStrings,
    val ovl: UiOverlayGuideStrings
) {
    val appTitle: String get() = general.appTitle
    val appTagline: String get() = general.appTagline
    val tabDashboard: String get() = general.tabDashboard
    val tabSettings: String get() = general.tabSettings
    val tabGuide: String get() = general.tabGuide
    val statusStandby: String get() = general.statusStandby
    val statusArmed: String get() = general.statusArmed
    val statusLocked: String get() = general.statusLocked
    val statusPermissionNeeded: String get() = general.statusPermissionNeeded
    val statusStandbyDesc: String get() = general.statusStandbyDesc
    val statusArmedDesc: String get() = general.statusArmedDesc
    val statusLockedDesc: String get() = general.statusLockedDesc
    val statusPermissionDesc: String get() = general.statusPermissionDesc
    val btnArmShield: String get() = general.btnArmShield
    val btnStopShield: String get() = general.btnStopShield
    val btnLockScreenNow: String get() = general.btnLockScreenNow
    val btnTestLockSandbox: String get() = general.btnTestLockSandbox
    val btnGrantPermission: String get() = general.btnGrantPermission
    val btnEnableNow: String get() = general.btnEnableNow
    val btnBatterySettings: String get() = general.btnBatterySettings
    val permissionsCardTitle: String get() = general.permissionsCardTitle
    val permOverlayTitle: String get() = general.permOverlayTitle
    val permOverlayDesc: String get() = general.permOverlayDesc
    val permNotificationTitle: String get() = general.permNotificationTitle
    val permNotificationDesc: String get() = general.permNotificationDesc
    val permGranted: String get() = general.permGranted
    val permRequired: String get() = general.permRequired
    val permOptional: String get() = general.permOptional
    val statBlockedTouches: String get() = general.statBlockedTouches
    val statLockSessions: String get() = general.statLockSessions
    val statActiveMode: String get() = general.statActiveMode
    val analyticsTitle: String get() = general.analyticsTitle
    val analyticsEmptyHint: String get() = general.analyticsEmptyHint
    val analyticsSourcesTitle: String get() = general.analyticsSourcesTitle
    val sensorCardTitle: String get() = general.sensorCardTitle
    val sensorNear: String get() = general.sensorNear
    val sensorFar: String get() = general.sensorFar
    val sensorUnavailable: String get() = general.sensorUnavailable
    val sensorTestHint: String get() = general.sensorTestHint
    val sensorOrientationPocket: String get() = general.sensorOrientationPocket
    val sensorOrientationFlat: String get() = general.sensorOrientationFlat
    val sensorOrientationFaceDown: String get() = general.sensorOrientationFaceDown
    val sensorPausedLandscape: String get() = general.sensorPausedLandscape
    val sensorPausedCharging: String get() = general.sensorPausedCharging
    val quickControlsTitle: String get() = general.quickControlsTitle
    val togglePocketAutoLock: String get() = general.togglePocketAutoLock
    val togglePocketAutoLockDesc: String get() = general.togglePocketAutoLockDesc
    val togglePocketAutoUnlock: String get() = general.togglePocketAutoUnlock
    val togglePocketAutoUnlockDesc: String get() = general.togglePocketAutoUnlockDesc
    val toggleFloatingBubble: String get() = general.toggleFloatingBubble
    val toggleFloatingBubbleDesc: String get() = general.toggleFloatingBubbleDesc
    val toggleKeepScreenOn: String get() = general.toggleKeepScreenOn
    val toggleKeepScreenOnDesc: String get() = general.toggleKeepScreenOnDesc
    val presetsTitle: String get() = general.presetsTitle
    val presetPocketPhotoTitle: String get() = general.presetPocketPhotoTitle
    val presetPocketPhotoDesc: String get() = general.presetPocketPhotoDesc
    val presetKidsVideoTitle: String get() = general.presetKidsVideoTitle
    val presetKidsVideoDesc: String get() = general.presetKidsVideoDesc
    val presetBatteryTitle: String get() = general.presetBatteryTitle
    val presetBatteryDesc: String get() = general.presetBatteryDesc
    val presetNavMapTitle: String get() = general.presetNavMapTitle
    val presetNavMapDesc: String get() = general.presetNavMapDesc
    val customProfilesTitle: String get() = general.customProfilesTitle
    val btnSaveProfile: String get() = general.btnSaveProfile
    val saveProfileDialogTitle: String get() = general.saveProfileDialogTitle
    val saveProfileHint: String get() = general.saveProfileHint
    val renameProfileDialogTitle: String get() = general.renameProfileDialogTitle
    val noCustomProfilesHint: String get() = general.noCustomProfilesHint
    val settingsSearchHint: String get() = general.settingsSearchHint
    val settingsNoSearchResults: String get() = general.settingsNoSearchResults
    val btnExpandAll: String get() = general.btnExpandAll
    val btnCollapseAll: String get() = general.btnCollapseAll
    val btnTestGestureHere: String get() = general.btnTestGestureHere
    val inlineTesterTitle: String get() = general.inlineTesterTitle
    val inlineTesterSuccess: String get() = general.inlineTesterSuccess
    val settingsLivePreviewTitle: String get() = general.settingsLivePreviewTitle
    val settingsUnlockSection: String get() = general.settingsUnlockSection
    val settingsOverlaySection: String get() = general.settingsOverlaySection
    val settingsBubbleSection: String get() = general.settingsBubbleSection
    val settingsPocketSection: String get() = general.settingsPocketSection
    val settingsSystemSection: String get() = general.settingsSystemSection
    val subheaderSecurity: String get() = general.subheaderSecurity
    val subheaderRecovery: String get() = general.subheaderRecovery
    val subheaderBrightness: String get() = general.subheaderBrightness
    val subheaderBorders: String get() = general.subheaderBorders
    val subheaderOledHud: String get() = general.subheaderOledHud
    val subheaderSensorTuning: String get() = general.subheaderSensorTuning
    val subheaderContextRules: String get() = general.subheaderContextRules
    val subheaderCallTimer: String get() = general.subheaderCallTimer
    val subheaderAppearance: String get() = general.subheaderAppearance
    val subheaderBackupReset: String get() = general.subheaderBackupReset
    val subheaderDiagnostics: String get() = general.subheaderDiagnostics

    val holdDurationLabel: String get() = cfg.holdDurationLabel
    val hudTimeoutLabel: String get() = cfg.hudTimeoutLabel
    val lockCountdownLabel: String get() = cfg.lockCountdownLabel
    val countdownOff: String get() = cfg.countdownOff
    val pinCodeLabel: String get() = cfg.pinCodeLabel
    val btnChangePin: String get() = cfg.btnChangePin
    val pinDialogTitle: String get() = cfg.pinDialogTitle
    val pinDialogHint: String get() = cfg.pinDialogHint
    val btnSave: String get() = cfg.btnSave
    val btnCancel: String get() = cfg.btnCancel
    val overlayPinHint: String get() = cfg.overlayPinHint
    val overlayPinWrong: String get() = cfg.overlayPinWrong
    val toggleBiometricUnlock: String get() = cfg.toggleBiometricUnlock
    val toggleBiometricUnlockDesc: String get() = cfg.toggleBiometricUnlockDesc
    val overlayBtnBiometric: String get() = cfg.overlayBtnBiometric
    val toggleEmergencyBypass: String get() = cfg.toggleEmergencyBypass
    val toggleEmergencyBypassDesc: String get() = cfg.toggleEmergencyBypassDesc
    val overlayEmergencyHoldLabel: String get() = cfg.overlayEmergencyHoldLabel
    val customDimLabel: String get() = cfg.customDimLabel
    val clearPercentLabel: String get() = cfg.clearPercentLabel
    val lockBrightnessLabel: String get() = cfg.lockBrightnessLabel
    val brightnessSystem: String get() = cfg.brightnessSystem
    val brightnessNight: String get() = cfg.brightnessNight
    val brightnessOutdoor: String get() = cfg.brightnessOutdoor
    val toggleBorderEnabled: String get() = cfg.toggleBorderEnabled
    val toggleBorderDesc: String get() = cfg.toggleBorderDesc
    val borderColorLabel: String get() = cfg.borderColorLabel
    val borderColorEmerald: String get() = cfg.borderColorEmerald
    val borderColorCyan: String get() = cfg.borderColorCyan
    val borderColorAmber: String get() = cfg.borderColorAmber
    val borderColorRose: String get() = cfg.borderColorRose
    val borderThicknessLabel: String get() = cfg.borderThicknessLabel
    val borderStyleLabel: String get() = cfg.borderStyleLabel
    val borderStyleGlow: String get() = cfg.borderStyleGlow
    val borderStyleCorners: String get() = cfg.borderStyleCorners
    val toggleBorderPulse: String get() = cfg.toggleBorderPulse
    val toggleBorderPulseDesc: String get() = cfg.toggleBorderPulseDesc
    val badgeModeLabel: String get() = cfg.badgeModeLabel
    val badgeAlways: String get() = cfg.badgeAlways
    val badgeOnTouch: String get() = cfg.badgeOnTouch
    val badgeHidden: String get() = cfg.badgeHidden
    val toggleOledClock: String get() = cfg.toggleOledClock
    val toggleOledClockDesc: String get() = cfg.toggleOledClockDesc
    val thicknessThin: String get() = cfg.thicknessThin
    val thicknessMedium: String get() = cfg.thicknessMedium
    val thicknessBold: String get() = cfg.thicknessBold
    val bubbleOpacityLabel: String get() = cfg.bubbleOpacityLabel
    val bubbleSizeLabel: String get() = cfg.bubbleSizeLabel
    val bubbleSizeCompact: String get() = cfg.bubbleSizeCompact
    val bubbleSizeStandard: String get() = cfg.bubbleSizeStandard
    val bubbleSizeLarge: String get() = cfg.bubbleSizeLarge
    val bubbleStyleLabel: String get() = cfg.bubbleStyleLabel
    val bubbleStyleShield: String get() = cfg.bubbleStyleShield
    val bubbleStyleDot: String get() = cfg.bubbleStyleDot
    val bubbleStylePill: String get() = cfg.bubbleStylePill
    val bubblePillLabel: String get() = cfg.bubblePillLabel
    val toggleBubbleAutoTuck: String get() = cfg.toggleBubbleAutoTuck
    val toggleBubbleAutoTuckDesc: String get() = cfg.toggleBubbleAutoTuckDesc
    val toggleBubbleDoubleTapHide: String get() = cfg.toggleBubbleDoubleTapHide
    val toggleBubbleDoubleTapHideDesc: String get() = cfg.toggleBubbleDoubleTapHideDesc
    val pocketDebounceLabel: String get() = cfg.pocketDebounceLabel
    val toggleOrientationFilter: String get() = cfg.toggleOrientationFilter
    val toggleOrientationFilterDesc: String get() = cfg.toggleOrientationFilterDesc
    val togglePauseLandscape: String get() = cfg.togglePauseLandscape
    val togglePauseLandscapeDesc: String get() = cfg.togglePauseLandscapeDesc
    val togglePauseCharging: String get() = cfg.togglePauseCharging
    val togglePauseChargingDesc: String get() = cfg.togglePauseChargingDesc
    val toggleFlipFaceDownLock: String get() = cfg.toggleFlipFaceDownLock
    val toggleFlipFaceDownLockDesc: String get() = cfg.toggleFlipFaceDownLockDesc
    val toggleAutoUnlockOnCall: String get() = cfg.toggleAutoUnlockOnCall
    val toggleAutoUnlockOnCallDesc: String get() = cfg.toggleAutoUnlockOnCallDesc
    val toggleReLockAfterCall: String get() = cfg.toggleReLockAfterCall
    val toggleReLockAfterCallDesc: String get() = cfg.toggleReLockAfterCallDesc
    val sessionTimerLabel: String get() = cfg.sessionTimerLabel
    val timerOff: String get() = cfg.timerOff
    val toggleHaptics: String get() = cfg.toggleHaptics
    val toggleHapticsDesc: String get() = cfg.toggleHapticsDesc
    val toggleAutoBoot: String get() = cfg.toggleAutoBoot
    val toggleAutoBootDesc: String get() = cfg.toggleAutoBootDesc
    val themeModeLabel: String get() = cfg.themeModeLabel
    val themeSystem: String get() = cfg.themeSystem
    val themeLight: String get() = cfg.themeLight
    val themeDark: String get() = cfg.themeDark
    val toggleDynamicColor: String get() = cfg.toggleDynamicColor
    val toggleDynamicColorDesc: String get() = cfg.toggleDynamicColorDesc
    val languageLabel: String get() = cfg.languageLabel
    val languageAutoSystem: String get() = cfg.languageAutoSystem
    val btnExportBackup: String get() = cfg.btnExportBackup
    val btnImportBackup: String get() = cfg.btnImportBackup
    val backupExportSuccess: String get() = cfg.backupExportSuccess
    val backupImportSuccess: String get() = cfg.backupImportSuccess
    val backupError: String get() = cfg.backupError
    val privacyCardTitle: String get() = cfg.privacyCardTitle
    val privacyBadgeOffline: String get() = cfg.privacyBadgeOffline
    val privacyBadgeNoNet: String get() = cfg.privacyBadgeNoNet
    val privacyBadgeZeroTrack: String get() = cfg.privacyBadgeZeroTrack
    val privacyCardDesc: String get() = cfg.privacyCardDesc
    val batteryCardTitle: String get() = cfg.batteryCardTitle
    val batteryCardDesc: String get() = cfg.batteryCardDesc
    val diagProximity: String get() = cfg.diagProximity
    val diagAccelerometer: String get() = cfg.diagAccelerometer
    val diagOverlay: String get() = cfg.diagOverlay
    val diagNotifications: String get() = cfg.diagNotifications
    val diagPhoneState: String get() = cfg.diagPhoneState
    val diagBattery: String get() = cfg.diagBattery
    val diagAvailable: String get() = cfg.diagAvailable
    val diagUnavailable: String get() = cfg.diagUnavailable
    val diagUnrestricted: String get() = cfg.diagUnrestricted
    val diagOptimized: String get() = cfg.diagOptimized
    val btnResetStats: String get() = cfg.btnResetStats
    val btnRestoreDefaults: String get() = cfg.btnRestoreDefaults
    val confirmResetStatsTitle: String get() = cfg.confirmResetStatsTitle
    val confirmResetStatsMsg: String get() = cfg.confirmResetStatsMsg
    val confirmRestoreTitle: String get() = cfg.confirmRestoreTitle
    val confirmRestoreMsg: String get() = cfg.confirmRestoreMsg
    val btnConfirm: String get() = cfg.btnConfirm
    val statusOnLabel: String get() = cfg.statusOnLabel
    val statusOffLabel: String get() = cfg.statusOffLabel

    val unlockHoldTitle: String get() = ovl.unlockHoldTitle
    val unlockHoldDesc: String get() = ovl.unlockHoldDesc
    val unlockSlideTitle: String get() = ovl.unlockSlideTitle
    val unlockSlideDesc: String get() = ovl.unlockSlideDesc
    val unlockDoubleTapTitle: String get() = ovl.unlockDoubleTapTitle
    val unlockDoubleTapDesc: String get() = ovl.unlockDoubleTapDesc
    val unlockPinTitle: String get() = ovl.unlockPinTitle
    val unlockPinDesc: String get() = ovl.unlockPinDesc
    val styleClearTitle: String get() = ovl.styleClearTitle
    val styleClearDesc: String get() = ovl.styleClearDesc
    val styleTintTitle: String get() = ovl.styleTintTitle
    val styleTintDesc: String get() = ovl.styleTintDesc
    val styleBlackTitle: String get() = ovl.styleBlackTitle
    val styleBlackDesc: String get() = ovl.styleBlackDesc
    val overlayTouchBlockedTitle: String get() = ovl.overlayTouchBlockedTitle
    val overlayHoldHint: String get() = ovl.overlayHoldHint
    val overlaySlideHint: String get() = ovl.overlaySlideHint
    val overlayDoubleTapHint: String get() = ovl.overlayDoubleTapHint
    val overlayCountdownLabel: String get() = ovl.overlayCountdownLabel
    val overlayCountdownCancel: String get() = ovl.overlayCountdownCancel
    val sandboxTitle: String get() = ovl.sandboxTitle
    val sandboxSubtitle: String get() = ovl.sandboxSubtitle
    val sandboxDummyPhotoTitle: String get() = ovl.sandboxDummyPhotoTitle
    val sandboxDummyPhotoDesc: String get() = ovl.sandboxDummyPhotoDesc
    val sandboxTapBlockedToast: String get() = ovl.sandboxTapBlockedToast
    val sandboxTryClicking: String get() = ovl.sandboxTryClicking
    val sandboxLockTriggerBtn: String get() = ovl.sandboxLockTriggerBtn
    val sandboxExitBtn: String get() = ovl.sandboxExitBtn
    val notifTitleArmed: String get() = ovl.notifTitleArmed
    val notifTextArmed: String get() = ovl.notifTextArmed
    val notifTitleLocked: String get() = ovl.notifTitleLocked
    val notifTextLocked: String get() = ovl.notifTextLocked
    val notifActionLock: String get() = ovl.notifActionLock
    val notifActionUnlock: String get() = ovl.notifActionUnlock
    val notifActionPocketOn: String get() = ovl.notifActionPocketOn
    val notifActionPocketOff: String get() = ovl.notifActionPocketOff
    val notifActionShowBubble: String get() = ovl.notifActionShowBubble
    val notifActionStop: String get() = ovl.notifActionStop
    val onboardingSkip: String get() = ovl.onboardingSkip
    val onboardingNext: String get() = ovl.onboardingNext
    val onboardingGetStarted: String get() = ovl.onboardingGetStarted
    val onboardingPage1Title: String get() = ovl.onboardingPage1Title
    val onboardingPage1Desc: String get() = ovl.onboardingPage1Desc
    val onboardingPage2Title: String get() = ovl.onboardingPage2Title
    val onboardingPage2Desc: String get() = ovl.onboardingPage2Desc
    val onboardingPage3Title: String get() = ovl.onboardingPage3Title
    val onboardingPage3Desc: String get() = ovl.onboardingPage3Desc
    val btnReplayOnboarding: String get() = ovl.btnReplayOnboarding
    val guideTitle: String get() = ovl.guideTitle
    val guideStep1Title: String get() = ovl.guideStep1Title
    val guideStep1Desc: String get() = ovl.guideStep1Desc
    val guideStep2Title: String get() = ovl.guideStep2Title
    val guideStep2Desc: String get() = ovl.guideStep2Desc
    val guideStep3Title: String get() = ovl.guideStep3Title
    val guideStep3Desc: String get() = ovl.guideStep3Desc
    val guideStep4Title: String get() = ovl.guideStep4Title
    val guideStep4Desc: String get() = ovl.guideStep4Desc
    val guideLimitsTitle: String get() = ovl.guideLimitsTitle
    val guideLimitsDesc: String get() = ovl.guideLimitsDesc
    val lockSourceManual: String get() = ovl.lockSourceManual
    val lockSourceBubble: String get() = ovl.lockSourceBubble
    val lockSourceNotification: String get() = ovl.lockSourceNotification
    val lockSourceTile: String get() = ovl.lockSourceTile
    val lockSourcePocket: String get() = ovl.lockSourcePocket
    val lockSourceFlip: String get() = ovl.lockSourceFlip
    val lockSourceWidget: String get() = ovl.lockSourceWidget

    fun unlockMethodTitle(method: UnlockMethod): String = when (method) {
        UnlockMethod.LONG_PRESS -> unlockHoldTitle
        UnlockMethod.SLIDE -> unlockSlideTitle
        UnlockMethod.DOUBLE_TAP -> unlockDoubleTapTitle
        UnlockMethod.PIN -> unlockPinTitle
    }

    fun unlockMethodDesc(method: UnlockMethod): String = when (method) {
        UnlockMethod.LONG_PRESS -> unlockHoldDesc
        UnlockMethod.SLIDE -> unlockSlideDesc
        UnlockMethod.DOUBLE_TAP -> unlockDoubleTapDesc
        UnlockMethod.PIN -> unlockPinDesc
    }

    fun overlayStyleTitle(style: OverlayStyle): String = when (style) {
        OverlayStyle.CLEAR_GLASS -> styleClearTitle
        OverlayStyle.SUBTLE_TINT -> styleTintTitle
        OverlayStyle.AMOLED_BLACK -> styleBlackTitle
    }

    fun overlayStyleDesc(style: OverlayStyle): String = when (style) {
        OverlayStyle.CLEAR_GLASS -> styleClearDesc
        OverlayStyle.SUBTLE_TINT -> styleTintDesc
        OverlayStyle.AMOLED_BLACK -> styleBlackDesc
    }

    fun overlayUnlockHint(method: UnlockMethod, holdDurationMs: Long = 1800L): String = when (method) {
        UnlockMethod.LONG_PRESS -> overlayHoldHint.replace("1.8", String.format("%.1f", holdDurationMs / 1000f))
        UnlockMethod.SLIDE -> overlaySlideHint
        UnlockMethod.DOUBLE_TAP -> overlayDoubleTapHint
        UnlockMethod.PIN -> overlayPinHint
    }

    fun presetTitle(preset: SmartPreset): String = when (preset) {
        SmartPreset.POCKET_PHOTO -> presetPocketPhotoTitle
        SmartPreset.KIDS_VIDEO -> presetKidsVideoTitle
        SmartPreset.BATTERY_SAVER -> presetBatteryTitle
        SmartPreset.NAVIGATION_MAP -> presetNavMapTitle
    }

    fun presetDesc(preset: SmartPreset): String = when (preset) {
        SmartPreset.POCKET_PHOTO -> presetPocketPhotoDesc
        SmartPreset.KIDS_VIDEO -> presetKidsVideoDesc
        SmartPreset.BATTERY_SAVER -> presetBatteryDesc
        SmartPreset.NAVIGATION_MAP -> presetNavMapDesc
    }

    fun borderThicknessLabel(t: BorderThickness): String = when (t) {
        BorderThickness.THIN -> thicknessThin
        BorderThickness.MEDIUM -> thicknessMedium
        BorderThickness.BOLD -> thicknessBold
    }

    fun borderStyleLabel(s: BorderStyle): String = when (s) {
        BorderStyle.FULL_GLOW -> borderStyleGlow
        BorderStyle.CORNER_BRACKETS -> borderStyleCorners
    }

    fun badgeModeLabel(mode: BadgeVisibilityMode): String = when (mode) {
        BadgeVisibilityMode.ALWAYS -> badgeAlways
        BadgeVisibilityMode.ON_TOUCH -> badgeOnTouch
        BadgeVisibilityMode.HIDDEN -> badgeHidden
    }

    fun bubbleSizeLabel(size: BubbleSize): String = when (size) {
        BubbleSize.COMPACT -> bubbleSizeCompact
        BubbleSize.STANDARD -> bubbleSizeStandard
        BubbleSize.LARGE -> bubbleSizeLarge
    }

    fun bubbleStyleLabel(style: BubbleStyle): String = when (style) {
        BubbleStyle.SHIELD_CIRCLE -> bubbleStyleShield
        BubbleStyle.MINIMAL_DOT -> bubbleStyleDot
        BubbleStyle.COMPACT_PILL -> bubbleStylePill
    }

    fun borderColorName(c: BorderColorOption): String = when (c) {
        BorderColorOption.EMERALD -> borderColorEmerald
        BorderColorOption.CYAN -> borderColorCyan
        BorderColorOption.AMBER -> borderColorAmber
        BorderColorOption.ROSE -> borderColorRose
    }

    fun themeModeLabel(mode: ThemeMode): String = when (mode) {
        ThemeMode.SYSTEM -> themeSystem
        ThemeMode.LIGHT -> themeLight
        ThemeMode.DARK -> themeDark
    }

    fun languageDisplayName(lang: AppLanguage): String = when (lang) {
        AppLanguage.SYSTEM_AUTO -> languageAutoSystem
        else -> lang.displayLabel
    }

    fun lockSourceLabel(source: LockSource): String = when (source) {
        LockSource.MANUAL_APP -> lockSourceManual
        LockSource.FLOATING_BUBBLE -> lockSourceBubble
        LockSource.NOTIFICATION -> lockSourceNotification
        LockSource.QUICK_TILE -> lockSourceTile
        LockSource.POCKET_SENSOR -> lockSourcePocket
        LockSource.FLIP_GESTURE -> lockSourceFlip
        LockSource.WIDGET -> lockSourceWidget
        LockSource.NONE -> ""
    }
}

fun getAppStrings(language: AppLanguage): AppStrings = when (language.resolve()) {
    AppLanguage.BENGALI -> BengaliStrings
    AppLanguage.HINDI -> HindiStrings
    AppLanguage.ARABIC -> ArabicStrings
    AppLanguage.SPANISH -> SpanishStrings
    AppLanguage.INDONESIAN -> IndonesianStrings
    AppLanguage.PORTUGUESE -> PortugueseStrings
    else -> EnglishStrings
}

val EnglishStrings = AppStrings(
    general = UiGeneralStrings(
        appTitle = "TouchShield",
        appTagline = "Crystal-clear pocket mistouch guard while keeping your screen visible",
        tabDashboard = "Dashboard",
        tabSettings = "Settings",
        tabGuide = "Guide",
        statusStandby = "Standby",
        statusArmed = "Shield Armed",
        statusLocked = "Touch Locked",
        statusPermissionNeeded = "Setup Needed",
        statusStandbyDesc = "Arm the shield to protect open photos, videos, or apps in your pocket.",
        statusArmedDesc = "Use the floating bubble, widget, Quick Tile, or pocket sensor to lock touch.",
        statusLockedDesc = "Screen stays crystal clear while all accidental touches are blocked.",
        statusPermissionDesc = "Grant Overlay permission so TouchShield can block touches over other apps.",
        btnArmShield = "Arm Shield Service",
        btnStopShield = "Stop Shield",
        btnLockScreenNow = "Lock Screen Now",
        btnTestLockSandbox = "Test Inside App",
        btnGrantPermission = "Grant Access",
        btnEnableNow = "Allow",
        btnBatterySettings = "Open Battery Settings",
        permissionsCardTitle = "System Permissions",
        permOverlayTitle = "Display Over Other Apps",
        permOverlayDesc = "Required to intercept accidental touches over photos, videos, or any app.",
        permNotificationTitle = "Persistent Notification",
        permNotificationDesc = "Keeps the service alive and gives instant lock/unlock controls.",
        permGranted = "Granted",
        permRequired = "Required",
        permOptional = "Recommended",
        statBlockedTouches = "Blocked Touches",
        statLockSessions = "Lock Sessions",
        statActiveMode = "Dim Level",
        analyticsTitle = "7-Day Mistouch Protection",
        analyticsEmptyHint = "No mistouches blocked yet—lock your screen or test in Sandbox to see trends.",
        analyticsSourcesTitle = "Top Lock Triggers",
        sensorCardTitle = "Live Pocket Sensor & Context",
        sensorNear = "Covered / Pocket (Near)",
        sensorFar = "Open / Clear (Far)",
        sensorUnavailable = "No hardware proximity sensor detected on this device",
        sensorTestHint = "Cover the top bezel of your phone to test live pocket detection",
        sensorOrientationPocket = "Pocket Orientation (Valid)",
        sensorOrientationFlat = "Flat on Desk (Auto-lock paused)",
        sensorOrientationFaceDown = "Face-Down Detected",
        sensorPausedLandscape = "Paused in Landscape",
        sensorPausedCharging = "Paused While Charging",
        quickControlsTitle = "Quick Controls",
        togglePocketAutoLock = "Auto Pocket Lock",
        togglePocketAutoLockDesc = "Automatically locks touch when placed inside a pocket",
        togglePocketAutoUnlock = "Auto Unlock Out of Pocket",
        togglePocketAutoUnlockDesc = "Unlocks automatically when removed (only if pocket-locked)",
        toggleFloatingBubble = "Floating Lock Bubble",
        toggleFloatingBubbleDesc = "Shows a subtle draggable lock button over any app",
        toggleKeepScreenOn = "Keep Screen Awake",
        toggleKeepScreenOnDesc = "Prevents screen timeout while your photo or app is touch-locked",
        presetsTitle = "Smart Profiles & Presets",
        presetPocketPhotoTitle = "Pocket Photo",
        presetPocketPhotoDesc = "0% Clear • Keep Awake • Pocket Lock",
        presetKidsVideoTitle = "Kids / Video",
        presetKidsVideoDesc = "3s Hold • Brackets • No Bubble",
        presetBatteryTitle = "Battery Saver",
        presetBatteryDesc = "88% Dim • OLED Clock • 5% Bright",
        presetNavMapTitle = "Nav / Bike Map",
        presetNavMapDesc = "100% Bright • Pill • No Auto-Lock",
        customProfilesTitle = "My Custom Profiles",
        btnSaveProfile = "Save Current",
        saveProfileDialogTitle = "Save Custom Profile",
        saveProfileHint = "Profile name (e.g. YouTube Bedtime)",
        renameProfileDialogTitle = "Rename Profile",
        noCustomProfilesHint = "Save your favorite configuration as a 1-tap profile.",
        settingsSearchHint = "Search settings (e.g. PIN, Dim, Bubble, Call...)",
        settingsNoSearchResults = "No settings match your search.",
        btnExpandAll = "Expand All",
        btnCollapseAll = "Collapse All",
        btnTestGestureHere = "Test Gesture",
        inlineTesterTitle = "Quick Gesture Calibration",
        inlineTesterSuccess = "Unlocked!",
        settingsLivePreviewTitle = "Live Overlay & Bubble Preview",
        settingsUnlockSection = "Security & Unlock",
        settingsOverlaySection = "Overlay Visuals & Display",
        settingsBubbleSection = "Floating Bubble",
        settingsPocketSection = "Pocket Sensor & Context Rules",
        settingsSystemSection = "System, Backup & Diagnostics",
        subheaderSecurity = "UNLOCK METHOD & TIMING",
        subheaderRecovery = "LOCKOUT RECOVERY",
        subheaderBrightness = "TRANSPARENCY & HARDWARE BRIGHTNESS",
        subheaderBorders = "BORDER STYLE & ANIMATION",
        subheaderOledHud = "STATUS BADGE & OLED CLOCK",
        subheaderSensorTuning = "SENSOR TUNING",
        subheaderContextRules = "SMART CONTEXT FILTERS",
        subheaderCallTimer = "CALL & SESSION AUTOMATION",
        subheaderAppearance = "APPEARANCE & LANGUAGE",
        subheaderBackupReset = "BACKUP, PRIVACY & RESET",
        subheaderDiagnostics = "DEVICE HEALTH & DIAGNOSTICS"
    ),
    cfg = UiSettingsStrings(
        holdDurationLabel = "Hold-to-Unlock Duration",
        hudTimeoutLabel = "Unlock HUD Auto-Hide Delay",
        lockCountdownLabel = "Lock Countdown Delay",
        countdownOff = "Instant (0s)",
        pinCodeLabel = "Current 4-Digit PIN",
        btnChangePin = "Change PIN",
        pinDialogTitle = "Set 4-Digit PIN",
        pinDialogHint = "Enter 4 digits (e.g. 1234)",
        btnSave = "Save",
        btnCancel = "Cancel",
        overlayPinHint = "Enter 4-digit PIN to unlock",
        overlayPinWrong = "Wrong PIN! Try again",
        toggleBiometricUnlock = "Biometric / Fingerprint Unlock",
        toggleBiometricUnlockDesc = "Show Fingerprint / Face prompt button alongside PIN keypad",
        overlayBtnBiometric = "Biometric",
        toggleEmergencyBypass = "5s Emergency Hold Bypass",
        toggleEmergencyBypassDesc = "Adds a 5-second emergency hold recovery option if you forget your PIN",
        overlayEmergencyHoldLabel = "Forgot PIN? Hold 5s",
        customDimLabel = "Custom Screen Dim Level",
        clearPercentLabel = "Clear",
        lockBrightnessLabel = "Lock Screen Brightness",
        brightnessSystem = "System",
        brightnessNight = "Night 5%",
        brightnessOutdoor = "Outdoor 100%",
        toggleBorderEnabled = "Show Edge Glow Border",
        toggleBorderDesc = "Draws a crisp perimeter indicator so lock status is unmistakable",
        borderColorLabel = "Border Color",
        borderColorEmerald = "Emerald",
        borderColorCyan = "Cyan",
        borderColorAmber = "Amber",
        borderColorRose = "Rose",
        borderThicknessLabel = "Border Thickness",
        borderStyleLabel = "Border Style",
        borderStyleGlow = "Full Glow",
        borderStyleCorners = "Corner Frame",
        toggleBorderPulse = "Breathing Pulse Animation",
        toggleBorderPulseDesc = "Gently pulses the lock border to indicate active protection",
        badgeModeLabel = "Top Status Badge",
        badgeAlways = "Always",
        badgeOnTouch = "On Touch",
        badgeHidden = "Hidden",
        toggleOledClock = "OLED Clock & Battery HUD",
        toggleOledClockDesc = "Displays a subtle clock & battery level when screen is dimmed",
        thicknessThin = "Thin",
        thicknessMedium = "Medium",
        thicknessBold = "Bold",
        bubbleOpacityLabel = "Bubble Opacity",
        bubbleSizeLabel = "Bubble Size",
        bubbleSizeCompact = "Small",
        bubbleSizeStandard = "Medium",
        bubbleSizeLarge = "Large",
        bubbleStyleLabel = "Bubble Style",
        bubbleStyleShield = "Shield",
        bubbleStyleDot = "Dot",
        bubbleStylePill = "Pill",
        bubblePillLabel = "LOCK",
        toggleBubbleAutoTuck = "Auto-Tuck After 3s Idle",
        toggleBubbleAutoTuckDesc = "Tucks halfway into the screen edge so photos stay unobstructed",
        toggleBubbleDoubleTapHide = "Double-Tap Bubble to Hide",
        toggleBubbleDoubleTapHideDesc = "Double-tap bubble to hide temporarily (restore from notification)",
        pocketDebounceLabel = "Pocket Detection Delay",
        toggleOrientationFilter = "Smart Orientation Filter",
        toggleOrientationFilterDesc = "Ignores accidental hand waves when flat face-up on a desk",
        togglePauseLandscape = "Pause Auto-Lock in Landscape",
        togglePauseLandscapeDesc = "Prevents pocket lock while watching fullscreen videos or gaming",
        togglePauseCharging = "Pause Auto-Lock While Charging",
        togglePauseChargingDesc = "Disables pocket auto-lock when the charger cable is plugged in",
        toggleFlipFaceDownLock = "Flip Face-Down to Lock",
        toggleFlipFaceDownLockDesc = "Automatically locks touch when phone is placed face-down",
        toggleAutoUnlockOnCall = "Auto-Unlock on Incoming Call",
        toggleAutoUnlockOnCallDesc = "Unlocks automatically when ringing so you can answer immediately",
        toggleReLockAfterCall = "Re-Lock When Call Ends",
        toggleReLockAfterCallDesc = "Automatically restores touch lock once your phone call finishes",
        sessionTimerLabel = "Auto-Unlock Session Timer",
        timerOff = "Off",
        toggleHaptics = "Haptic Feedback",
        toggleHapticsDesc = "Subtle tactile vibration when locking and unlocking",
        toggleAutoBoot = "Auto-Arm After Reboot",
        toggleAutoBootDesc = "Automatically re-arms TouchShield after restarting your phone",
        themeModeLabel = "App Theme",
        themeSystem = "System",
        themeLight = "Light",
        themeDark = "Dark",
        toggleDynamicColor = "Material You Dynamic Color",
        toggleDynamicColorDesc = "Adapts accent colors from your device wallpaper (Android 12+)",
        languageLabel = "Language",
        languageAutoSystem = "Auto (Follow System)",
        btnExportBackup = "Export Backup (.json)",
        btnImportBackup = "Import Backup",
        backupExportSuccess = "Settings backup exported successfully.",
        backupImportSuccess = "Settings restored from backup!",
        backupError = "Could not read or write backup file.",
        privacyCardTitle = "100% Offline & Zero-Tracking Trust",
        privacyBadgeOffline = "Offline Only",
        privacyBadgeNoNet = "No INTERNET Permission",
        privacyBadgeZeroTrack = "Zero Analytics / Ads",
        privacyCardDesc = "TouchShield does not request Internet permission. All sensor processing, PINs, and profiles stay 100% private on your device.",
        batteryCardTitle = "Background Reliability",
        batteryCardDesc = "On Xiaomi, Samsung, Oppo, or Vivo devices, set Battery usage to 'Unrestricted' so the OS never kills the lock service.",
        diagProximity = "Proximity Sensor",
        diagAccelerometer = "Accelerometer Sensor",
        diagOverlay = "Overlay Permission",
        diagNotifications = "Notification Permission",
        diagPhoneState = "Call State (Auto-Unlock)",
        diagBattery = "Battery Optimization",
        diagAvailable = "Available",
        diagUnavailable = "Unavailable",
        diagUnrestricted = "Unrestricted",
        diagOptimized = "Optimized",
        btnResetStats = "Reset Analytics",
        btnRestoreDefaults = "Restore Defaults",
        confirmResetStatsTitle = "Reset Statistics?",
        confirmResetStatsMsg = "Blocked touches, 7-day history, and lock session counters will reset to 0.",
        confirmRestoreTitle = "Restore Default Settings?",
        confirmRestoreMsg = "All customized preferences will return to their recommended defaults.",
        btnConfirm = "Confirm",
        statusOnLabel = "ON",
        statusOffLabel = "OFF"
    ),
    ovl = UiOverlayGuideStrings(
        unlockHoldTitle = "Hold to Unlock",
        unlockHoldDesc = "Press and hold the unlock shield. Virtually impossible to trigger in a pocket.",
        unlockSlideTitle = "Slide to Unlock",
        unlockSlideDesc = "Drag the unlock handle smoothly from left to right.",
        unlockDoubleTapTitle = "Double-Tap Target",
        unlockDoubleTapDesc = "Quickly double-tap directly inside the floating unlock badge.",
        unlockPinTitle = "4-Digit PIN Unlock",
        unlockPinDesc = "Requires entering your 4-digit PIN (or Biometric) inside the lock keypad.",
        styleClearTitle = "Clear Glass (0% Dim)",
        styleClearDesc = "Keeps your photo or video 100% bright and crystal clear underneath.",
        styleTintTitle = "Subtle Tint (25% Dim)",
        styleTintDesc = "Light translucent tint so you can tell at a glance that touch is locked.",
        styleBlackTitle = "AMOLED Dim (88% Dim)",
        styleBlackDesc = "Darkens the display to save battery while keeping the app active.",
        overlayTouchBlockedTitle = "Touch Input Locked",
        overlayHoldHint = "Hold shield for 1.8s to unlock",
        overlaySlideHint = "Slide right to unlock",
        overlayDoubleTapHint = "Double-tap shield to unlock",
        overlayCountdownLabel = "Locking in",
        overlayCountdownCancel = "Tap to cancel",
        sandboxTitle = "Interactive Lock Sandbox",
        sandboxSubtitle = "Practice every lock style, border, brightness, and unlock gesture right here.",
        sandboxDummyPhotoTitle = "Sample Open Viewer",
        sandboxDummyPhotoDesc = "Pretend this is your open photo or app. Activate the sandbox lock and test tapping below!",
        sandboxTapBlockedToast = "Mistouch blocked!",
        sandboxTryClicking = "Try tapping me (blocked while locked)",
        sandboxLockTriggerBtn = "Activate Sandbox Lock",
        sandboxExitBtn = "Close",
        notifTitleArmed = "TouchShield Armed",
        notifTextArmed = "Tap the floating bubble or 'Lock' below anytime",
        notifTitleLocked = "Screen Touch Locked",
        notifTextLocked = "Accidental touches blocked • Tap 'Unlock' if needed",
        notifActionLock = "Lock Screen",
        notifActionUnlock = "Unlock",
        notifActionPocketOn = "Pocket: ON",
        notifActionPocketOff = "Pocket: OFF",
        notifActionShowBubble = "Show Bubble",
        notifActionStop = "Stop",
        onboardingSkip = "Skip",
        onboardingNext = "Next",
        onboardingGetStarted = "Get Started",
        onboardingPage1Title = "Crystal-Clear Touch Protection",
        onboardingPage1Desc = "Keep photos, maps, or videos 100% visible in your pocket or hand without accidental taps, deletes, or dials.",
        onboardingPage2Title = "Context-Aware Smart Sensors",
        onboardingPage2Desc = "Proximity + Accelerometer filters lock automatically in your pocket, pause in landscape videos, and unlock on incoming calls.",
        onboardingPage3Title = "100% Offline Privacy Guarantee",
        onboardingPage3Desc = "Zero Internet permission, zero ads, and full backup/biometric support. Grant Overlay access next to activate global locking.",
        btnReplayOnboarding = "Replay Welcome Guide",
        guideTitle = "How to Use TouchShield",
        guideStep1Title = "1. Grant Overlay & Notification Access",
        guideStep1Desc = "Enable 'Display over other apps' from the Dashboard so TouchShield can protect any open gallery, map, or video app.",
        guideStep2Title = "2. Pick a Profile & Arm Shield",
        guideStep2Desc = "Choose a preset or custom profile, then tap 'Arm Shield Service' to activate the bubble and pocket guard.",
        guideStep3Title = "3. Lock From Anywhere (5 Ways)",
        guideStep3Desc = "Lock via Floating Bubble, 1x1 Home Screen Widget, App Icon Shortcuts, Quick Settings Tile, or Pocket/Flip Sensor.",
        guideStep4Title = "4. Add Quick Tile & 1x1 Widget",
        guideStep4Desc = "Add the 'Touch Lock' tile to Quick Settings or place the 1x1 TouchShield widget on your home screen for instant 1-tap locking.",
        guideLimitsTitle = "Android Security Behavior",
        guideLimitsDesc = "By Android system design, standard overlays protect the entire app canvas while system lock screens, permission dialogs, and the top status bar shade remain OS-protected.",
        lockSourceManual = "Manual Lock",
        lockSourceBubble = "Floating Bubble",
        lockSourceNotification = "Notification",
        lockSourceTile = "Quick Tile",
        lockSourcePocket = "Pocket Sensor",
        lockSourceFlip = "Flip Face-Down",
        lockSourceWidget = "Home Widget"
    )
)

val BengaliStrings = AppStrings(
    general = UiGeneralStrings(
        appTitle = "TouchShield",
        appTagline = "স্ক্রিন অন ও স্পষ্ট রেখেই পকেট মিসটাচ থেকে সুরক্ষা",
        tabDashboard = "ড্যাশবোর্ড",
        tabSettings = "সেটিংস",
        tabGuide = "গাইড",
        statusStandby = "নিষ্ক্রিয়",
        statusArmed = "সুরক্ষা প্রস্তুত",
        statusLocked = "টাচ লক চালু",
        statusPermissionNeeded = "অনুমতি প্রয়োজন",
        statusStandbyDesc = "ছবি বা অ্যাপ খোলা রেখে পকেটে নিশ্চিন্তে রাখতে শিল্ড চালু করুন।",
        statusArmedDesc = "ফ্লোটিং বাবল, হোম উইজেট, কুইক টাইল বা পকেট সেন্সর দিয়ে লক করুন।",
        statusLockedDesc = "স্ক্রিন পরিষ্কার ও দৃশ্যমান আছে, কিন্তু সব মিসটাচ ব্লক করা হয়েছে।",
        statusPermissionDesc = "অন্য অ্যাপের ওপর টাচ ব্লক করতে Overlay পারমিশন দিন।",
        btnArmShield = "শিল্ড সার্ভিস চালু করুন",
        btnStopShield = "শিল্ড বন্ধ করুন",
        btnLockScreenNow = "এখনই স্ক্রিন লক করুন",
        btnTestLockSandbox = "অ্যাপের ভেতরে টেস্ট",
        btnGrantPermission = "অনুমতি দিন",
        btnEnableNow = "চালু করুন",
        btnBatterySettings = "ব্যাটারি সেটিংস খুলুন",
        permissionsCardTitle = "প্রয়োজনীয় সিস্টেম অনুমতি",
        permOverlayTitle = "অন্য অ্যাপের ওপর প্রদর্শন (Overlay)",
        permOverlayDesc = "ছবি বা ভিডিও খোলা রেখে স্ক্রিনের টাচ আটকাতে এটি আবশ্যক।",
        permNotificationTitle = "সার্ভিস নোটিফিকেশন",
        permNotificationDesc = "ব্যাকগ্রাউন্ডে শিল্ড সচল রাখতে এবং দ্রুত লক/আনলক করতে সাহায্য করে।",
        permGranted = "অনুমোদিত",
        permRequired = "জরুরি",
        permOptional = "সহায়ক",
        statBlockedTouches = "ব্লক করা মিসটাচ",
        statLockSessions = "মোট লক সেশন",
        statActiveMode = "ডিম লেভেল",
        analyticsTitle = "৭ দিনের মিসটাচ সুরক্ষা বিশ্লেষণ",
        analyticsEmptyHint = "এখনও কোনো মিসটাচ ব্লক হয়নি—স্ক্রিন লক বা স্যান্ডবক্স টেস্ট করলে এখানে গ্রাফ দেখাবে।",
        analyticsSourcesTitle = "প্রধান লক সোর্স",
        sensorCardTitle = "লাইভ পকেট সেন্সর ও কনটেক্সট",
        sensorNear = "ঢাকা / পকেটে (Near)",
        sensorFar = "খোলা / পরিষ্কার (Far)",
        sensorUnavailable = "এই ডিভাইসে Proximity Sensor পাওয়া যায়নি",
        sensorTestHint = "পরীক্ষা করতে ফোনের ওপরের দিকে হাত দিয়ে ঢেকে দেখুন",
        sensorOrientationPocket = "পকেট পজিশন (উপযোগী)",
        sensorOrientationFlat = "টেবিলে ফ্ল্যাট (অটো-লক বিরত)",
        sensorOrientationFaceDown = "উল্টো (Face-Down) অবস্থায় আছে",
        sensorPausedLandscape = "ল্যান্ডস্কেপ মোডে বিরত",
        sensorPausedCharging = "চার্জিং অবস্থায় বিরত",
        quickControlsTitle = "দ্রুত কন্ট্রোল",
        togglePocketAutoLock = "অটো পকেট লক",
        togglePocketAutoLockDesc = "পকেটে রাখলে সেন্সর দিয়ে অটোমেটিক টাচ লক হবে",
        togglePocketAutoUnlock = "পকেট থেকে বের করলে অটো আনলক",
        togglePocketAutoUnlockDesc = "অটো-লক হওয়া স্ক্রিন পকেট থেকে বের করলেই খুলে যাবে",
        toggleFloatingBubble = "ফ্লোটিং লক বাটন",
        toggleFloatingBubbleDesc = "যেকোনো অ্যাপের পাশে ছোট ভাসমান লক বাটন দেখাবে",
        toggleKeepScreenOn = "স্ক্রিন সবসময় অন রাখুন",
        toggleKeepScreenOnDesc = "লক অবস্থায় ছবি বা অ্যাপ অটো-অফ হতে দেবে না",
        presetsTitle = "স্মার্ট প্রোফাইল ও প্রিসেট",
        presetPocketPhotoTitle = "পকেট ফটো",
        presetPocketPhotoDesc = "০% স্বচ্ছ • স্ক্রিন অন • পকেট লক",
        presetKidsVideoTitle = "শিশু / ভিডিও",
        presetKidsVideoDesc = "৩s হোল্ড • কর্নার ফ্রেম • বাবল বন্ধ",
        presetBatteryTitle = "ব্যাটারি সাশ্রয়",
        presetBatteryDesc = "৮৮% ডিম • OLED ঘড়ি • ৫% আলো",
        presetNavMapTitle = "ম্যাপ / বাইক",
        presetNavMapDesc = "১০০% আলো • পিল • অটো-লক বন্ধ",
        customProfilesTitle = "আমার কাস্টম প্রোফাইল",
        btnSaveProfile = "সেভ করুন",
        saveProfileDialogTitle = "নতুন প্রোফাইল সেভ করুন",
        saveProfileHint = "প্রোফাইলের নাম (যেমন: রাতের ইউটিউব)",
        renameProfileDialogTitle = "প্রোফাইলের নাম বদলান",
        noCustomProfilesHint = "আপনার পছন্দের সেটিংস ১-ট্যাপ প্রোফাইল হিসেবে সেভ করে রাখুন।",
        settingsSearchHint = "সেটিংস খুঁজুন (যেমন: PIN, ডিম, বাবল, কল...)",
        settingsNoSearchResults = "খোঁজা শব্দটির সাথে কোনো সেটিংস মেলেনি।",
        btnExpandAll = "সব খুলুন",
        btnCollapseAll = "সব বন্ধ করুন",
        btnTestGestureHere = "জেসচার টেস্ট",
        inlineTesterTitle = "তাৎক্ষণিক জেসচার ক্যালিব্রেশন",
        inlineTesterSuccess = "আনলক সফল!",
        settingsLivePreviewTitle = "লাইভ ওভারলে ও বাবল প্রিভিউ",
        settingsUnlockSection = "সিকিউরিটি ও আনলক",
        settingsOverlaySection = "ওভারলে, ব্রাইটনেস ও ডিসপ্লে",
        settingsBubbleSection = "ফ্লোটিং বাবল প্রো-টিউনিং",
        settingsPocketSection = "পকেট সেন্সর ও স্মার্ট রুলস",
        settingsSystemSection = "সিস্টেম, ব্যাকআপ ও ডায়াগনস্টিকস",
        subheaderSecurity = "আনলক পদ্ধতি ও সময়",
        subheaderRecovery = "লকআউট সুরক্ষা ও রিকভারি",
        subheaderBrightness = "স্বচ্ছতা ও হার্ডওয়্যার ব্রাইটনেস",
        subheaderBorders = "বর্ডার স্টাইল ও অ্যানিমেশন",
        subheaderOledHud = "স্ট্যাটাস ব্যাজ ও OLED ঘড়ি",
        subheaderSensorTuning = "সেন্সর টিউনিং",
        subheaderContextRules = "কনটেক্সট-ভিত্তিক স্মার্ট ফিল্টার",
        subheaderCallTimer = "কল ও সেশন অটোমেশন",
        subheaderAppearance = "থিম ও ভাষা",
        subheaderBackupReset = "ব্যাকআপ, প্রাইভেসি ও রিসেট",
        subheaderDiagnostics = "ডিভাইস হেলথ ও ডায়াগনস্টিকস"
    ),
    cfg = UiSettingsStrings(
        holdDurationLabel = "হোল্ড আনলক সময়",
        hudTimeoutLabel = "আনলক প্যানেল অটো-হাইড সময়",
        lockCountdownLabel = "লক হওয়ার আগে কাউন্টডাউন বিলম্ব",
        countdownOff = "তাৎক্ষণিক (0s)",
        pinCodeLabel = "বর্তমান ৪-সংখ্যার পিন",
        btnChangePin = "পিন বদলান",
        pinDialogTitle = "৪-সংখ্যার পিন সেট করুন",
        pinDialogHint = "৪টি সংখ্যা লিখুন (যেমন 1234)",
        btnSave = "সেভ করুন",
        btnCancel = "বাতিল",
        overlayPinHint = "আনলক করতে ৪-সংখ্যার পিন লিখুন",
        overlayPinWrong = "ভুল পিন! আবার চেষ্টা করুন",
        toggleBiometricUnlock = "ফিঙ্গারপ্রিন্ট / ফেস আনলক",
        toggleBiometricUnlockDesc = "পিন কিপ্যাডের সাথে ফোনের বায়োমেট্রিক আনলক বাটন দেখাবে",
        overlayBtnBiometric = "বায়োমেট্রিক",
        toggleEmergencyBypass = "৫ সেকেন্ড ইমার্জেন্সি বাইপাস",
        toggleEmergencyBypassDesc = "পিন ভুলে গেলে ৫ সেকেন্ড চেপে ধরে জরুরি আনলকের সুযোগ রাখবে",
        overlayEmergencyHoldLabel = "পিন ভুলেছেন? ৫s ধরুন",
        customDimLabel = "স্ক্রিন ডিম / অন্ধকার লেভেল",
        clearPercentLabel = "স্পষ্ট",
        lockBrightnessLabel = "লক অবস্থায় স্ক্রিনের আলো (Brightness)",
        brightnessSystem = "সিস্টেম",
        brightnessNight = "রাত ৫%",
        brightnessOutdoor = "রোদ ১০০%",
        toggleBorderEnabled = "এজ গ্লো বর্ডার দেখান",
        toggleBorderDesc = "স্ক্রিনের চারপাশে স্পষ্ট বর্ডার দেখিয়ে লক অবস্থা বোঝাবে",
        borderColorLabel = "বর্ডারের রঙ",
        borderColorEmerald = "পান্না সবুজ",
        borderColorCyan = "নীলচে সায়ান",
        borderColorAmber = "সোনালী অ্যাম্বার",
        borderColorRose = "গোলাপি রোজ",
        borderThicknessLabel = "বর্ডারের পুরুত্ব",
        borderStyleLabel = "বর্ডারের ধরন",
        borderStyleGlow = "সম্পূর্ণ বর্ডার",
        borderStyleCorners = "কর্নার ফ্রেম",
        toggleBorderPulse = "পালস অ্যানিমেশন",
        toggleBorderPulseDesc = "লক সক্রিয় থাকলে বর্ডারে মৃদু পালস ইফেক্ট দেখাবে",
        badgeModeLabel = "ওপরের স্ট্যাটাস ব্যাজ",
        badgeAlways = "সবসময়",
        badgeOnTouch = "টাচ করলে",
        badgeHidden = "লুকানো",
        toggleOledClock = "OLED ঘড়ি ও ব্যাটারি HUD",
        toggleOledClockDesc = "ডিম মোডে স্ক্রিনের মাঝে হালকা সময় ও ব্যাটারি শতাংশ দেখাবে",
        thicknessThin = "চিকন",
        thicknessMedium = "মাঝারি",
        thicknessBold = "মোটা",
        bubbleOpacityLabel = "বাবলের স্বচ্ছতা (Opacity)",
        bubbleSizeLabel = "বাবলের সাইজ",
        bubbleSizeCompact = "ছোট",
        bubbleSizeStandard = "মাঝারি",
        bubbleSizeLarge = "বড়",
        bubbleStyleLabel = "বাবলের ধরন",
        bubbleStyleShield = "শিল্ড",
        bubbleStyleDot = "মিনিমাল ডট",
        bubbleStylePill = "পিল",
        bubblePillLabel = "লক",
        toggleBubbleAutoTuck = "৩ সেকেন্ড পর কিনারায় লুকাবে",
        toggleBubbleAutoTuckDesc = "ব্যবহার না করলে বাবল স্ক্রিনের ধারে অর্ধেক সরে হালকা হবে",
        toggleBubbleDoubleTapHide = "ডাবল-ট্যাপে সাময়িক হাইড",
        toggleBubbleDoubleTapHideDesc = "বাবলে ডাবল-ট্যাপ করলে হাইড হবে (নোটিফিকেশন থেকে আনুন)",
        pocketDebounceLabel = "পকেট ডিটেকশন সেন্সিটিভিটি",
        toggleOrientationFilter = "স্মার্ট পজিশন ফিল্টার (Accelerometer)",
        toggleOrientationFilterDesc = "টেবিলে চিৎ হয়ে থাকলে ভুল হাতের ছোঁয়ায় অযথা লক হবে না",
        togglePauseLandscape = "ল্যান্ডস্কেপ মোডে অটো-লক বিরত",
        togglePauseLandscapeDesc = "আড়াআড়ি ভিডিও দেখা বা গেম খেলার সময় পকেট লক হবে না",
        togglePauseCharging = "চার্জিং অবস্থায় অটো-লক বিরত",
        togglePauseChargingDesc = "ফোন চার্জে লাগানো থাকলে পকেট সেন্সর দিয়ে লক হবে না",
        toggleFlipFaceDownLock = "উল্টো করে রাখলে অটো লক",
        toggleFlipFaceDownLockDesc = "ফোন স্ক্রিন নিচে দিয়ে উপুড় করে রাখলেই টাচ লক হবে",
        toggleAutoUnlockOnCall = "ফোন কল আসলে অটো আনলক",
        toggleAutoUnlockOnCallDesc = "কল আসলে নিজে থেকে লক খুলবে যাতে রিসিভ করতে সমস্যা না হয়",
        toggleReLockAfterCall = "কল শেষে পুনরায় অটো লক",
        toggleReLockAfterCallDesc = "কলের জন্য আনলক হলে কথা বলা শেষে আবার লক চালু হবে",
        sessionTimerLabel = "অটো-আনলক সেশন টাইমার",
        timerOff = "বন্ধ",
        toggleHaptics = "ভাইব্রেশন ফিডব্যাক",
        toggleHapticsDesc = "লক ও আনলক করার সময় হালকা ভাইব্রেশন দেবে",
        toggleAutoBoot = "ফোন রিস্টার্টের পর অটো-আর্ম",
        toggleAutoBootDesc = "ডিভাইস রিবুট হলে শিল্ড সার্ভিস নিজে থেকেই প্রস্তুত হবে",
        themeModeLabel = "অ্যাপ থিম",
        themeSystem = "সিস্টেম",
        themeLight = "লাইট",
        themeDark = "ডার্ক",
        toggleDynamicColor = "Material You ডায়নামিক রঙ",
        toggleDynamicColorDesc = "ফোনের ওয়ালপেপারের রঙের সাথে থিম মানিয়ে নেবে (Android 12+)",
        languageLabel = "ভাষা / Language",
        languageAutoSystem = "অটো (সিস্টেম ভাষা)",
        btnExportBackup = "ব্যাকআপ সেভ (.json)",
        btnImportBackup = "ব্যাকআপ রিস্টোর",
        backupExportSuccess = "সেটিংস ব্যাকআপ সফলভাবে সেভ হয়েছে।",
        backupImportSuccess = "ব্যাকআপ থেকে সব সেটিংস রিস্টোর হয়েছে!",
        backupError = "ব্যাকআপ ফাইল পড়া বা সেভ করা যায়নি।",
        privacyCardTitle = "১০০% অফলাইন ও প্রাইভেসি গ্যারান্টি",
        privacyBadgeOffline = "সম্পূর্ণ অফলাইন",
        privacyBadgeNoNet = "ইন্টারনেট পারমিশন নেই",
        privacyBadgeZeroTrack = "কোনো বিজ্ঞাপন/ট্র্যাকিং নেই",
        privacyCardDesc = "TouchShield কোনো Internet পারমিশন ব্যবহার করে না। আপনার পিন, সেন্সর ডেটা ও প্রোফাইল ১০০% নিরাপদে শুধু আপনার ফোনেই থাকে।",
        batteryCardTitle = "ব্যাকগ্রাউন্ড সুরক্ষা টিপস",
        batteryCardDesc = "Xiaomi, Samsung, Oppo বা Vivo ফোনে ব্যাকগ্রাউন্ডে সার্ভিস বন্ধ হওয়া আটকাতে ব্যাটারি সেটিংস থেকে 'Unrestricted' সিলেক্ট করুন।",
        diagProximity = "Proximity সেন্সর",
        diagAccelerometer = "Accelerometer সেন্সর",
        diagOverlay = "Overlay পারমিশন",
        diagNotifications = "Notification পারমিশন",
        diagPhoneState = "Call State (অটো-আনলক)",
        diagBattery = "ব্যাটারি অপ্টিমাইজেশন",
        diagAvailable = "সক্রিয়",
        diagUnavailable = "নেই",
        diagUnrestricted = "Unrestricted",
        diagOptimized = "Optimized (সীমিত)",
        btnResetStats = "অ্যানালিটিক্স রিসেট",
        btnRestoreDefaults = "সব ডিফল্ট করুন",
        confirmResetStatsTitle = "পরিসংখ্যান মুছবেন?",
        confirmResetStatsMsg = "ব্লক করা মিসটাচ, ৭ দিনের চার্ট ও লক সেশন সংখ্যা ০ হয়ে যাবে।",
        confirmRestoreTitle = "ডিফল্ট সেটিংস ফিরিয়ে আনবেন?",
        confirmRestoreMsg = "আপনার কাস্টমাইজ করা সব সেটিংস প্রাথমিক অবস্থায় ফিরে যাবে।",
        btnConfirm = "নিশ্চিত করুন",
        statusOnLabel = "চালু",
        statusOffLabel = "বন্ধ"
    ),
    ovl = UiOverlayGuideStrings(
        unlockHoldTitle = "চেপে ধরে আনলক (Hold)",
        unlockHoldDesc = "আনলক বাটনে নির্দিষ্ট সময় চেপে ধরুন। পকেটে ভুল করে খোলার সম্ভাবনা শূন্য।",
        unlockSlideTitle = "স্লাইড করে আনলক (Slide)",
        unlockSlideDesc = "স্লাইডারটি বাম থেকে ডানে টেনে আনলক করুন।",
        unlockDoubleTapTitle = "ডাবল ট্যাপ (Double Tap)",
        unlockDoubleTapDesc = "শুধু আনলক আইকনের ওপর পরপর ২ বার দ্রুত ট্যাপ করুন।",
        unlockPinTitle = "৪-সংখ্যার পিন (PIN)",
        unlockPinDesc = "৪ সংখ্যার গোপন পিন বা বায়োমেট্রিক দিয়ে আনলক করুন।",
        styleClearTitle = "সম্পূর্ণ স্বচ্ছ (Clear 0%)",
        styleClearDesc = "ছবি বা ভিডিও ১০০% পরিষ্কার দেখাবে, কোনো কালো ছায়া থাকবে না।",
        styleTintTitle = "হালকা শেড (25% Dim)",
        styleTintDesc = "হালকা আস্তরণ, যাতে সহজে বোঝা যায় স্ক্রিন লক আছে।",
        styleBlackTitle = "AMOLED ডিম (88% Dim)",
        styleBlackDesc = "স্ক্রিন অন্ধকার করে রাখবে কিন্তু অ্যাপ চালু থাকবে—ব্যাটারি সাশ্রয়ী।",
        overlayTouchBlockedTitle = "টাচ লক চালু আছে",
        overlayHoldHint = "আনলক করতে 1.8 সেকেন্ড চেপে ধরুন",
        overlaySlideHint = "আনলক করতে ডানে স্লাইড করুন",
        overlayDoubleTapHint = "আনলক করতে বাটনে ডাবল-ট্যাপ করুন",
        overlayCountdownLabel = "লক হচ্ছে",
        overlayCountdownCancel = "বাতিল করতে ট্যাপ করুন",
        sandboxTitle = "ইন্টারঅ্যাক্টিভ লক স্যান্ডবক্স",
        sandboxSubtitle = "সিস্টেম পারমিশন ছাড়াই সব স্টাইল, ব্রাইটনেস ও আনলক এখানে পরীক্ষা করুন।",
        sandboxDummyPhotoTitle = "স্যাম্পল গ্যালারি প্রিভিউ",
        sandboxDummyPhotoDesc = "মনে করুন আপনার পছন্দের ছবি বা অ্যাপ খোলা আছে। লক চালু করে নিচের বাটনে টাচ করে পরীক্ষা করুন!",
        sandboxTapBlockedToast = "মিসটাচ আটকে দেওয়া হয়েছে!",
        sandboxTryClicking = "টেস্ট বাটনে ক্লিক করুন (লক থাকলে কাজ করবে না)",
        sandboxLockTriggerBtn = "স্যান্ডবক্স লক চালু করুন",
        sandboxExitBtn = "বন্ধ করুন",
        notifTitleArmed = "TouchShield প্রস্তুত",
        notifTextArmed = "ফ্লোটিং বাবল বা নিচের বাটনে ট্যাপ করে স্ক্রিন লক করুন",
        notifTitleLocked = "স্ক্রিন টাচ লক করা আছে",
        notifTextLocked = "মিসটাচ সুরক্ষা সক্রিয় • আনলক করতে ট্যাপ করুন",
        notifActionLock = "স্ক্রিন লক",
        notifActionUnlock = "আনলক",
        notifActionPocketOn = "পকেট: চালু",
        notifActionPocketOff = "পকেট: বন্ধ",
        notifActionShowBubble = "বাবল দেখান",
        notifActionStop = "বন্ধ",
        onboardingSkip = "এড়িয়ে যান",
        onboardingNext = "পরবর্তী",
        onboardingGetStarted = "শুরু করুন",
        onboardingPage1Title = "স্বচ্ছ স্ক্রিন, শতভাগ পকেট সুরক্ষা",
        onboardingPage1Desc = "ছবি, ম্যাপ বা ভিডিও ১০০% পরিষ্কার রেখেই পকেটে বা হাতে থাকা অবস্থায় সব ভুল টাচ, কল বা ডিলিট আটকে দিন।",
        onboardingPage2Title = "বুদ্ধিমান সেন্সর ও কল অটোমেশন",
        onboardingPage2Desc = "পকেটে রাখলে অটো-লক, আড়াআড়ি ভিডিওতে বিরতি, কল এলে অটো-আনলক এবং কল শেষে পুনরায় লক—সবই স্বয়ংক্রিয়।",
        onboardingPage3Title = "১০০% অফলাইন ও সম্পূর্ণ প্রাইভেট",
        onboardingPage3Desc = "ইন্টারনেট পারমিশন ছাড়াই চলে—কোনো বিজ্ঞাপন বা ট্র্যাকিং নেই। শুরু করতে শুধু Overlay অনুমতি দিন।",
        btnReplayOnboarding = "ওয়েলকাম গাইড আবার দেখুন",
        guideTitle = "ব্যবহারের সম্পূর্ণ নিয়মাবলী",
        guideStep1Title = "১. Overlay ও Notification অনুমতি দিন",
        guideStep1Desc = "ড্যাশবোর্ড থেকে 'Display over other apps' পারমিশন চালু করুন, যাতে গ্যালারি বা ইউটিউবের ওপরও টাচ ব্লক করা যায়।",
        guideStep2Title = "২. স্মার্ট প্রোফাইল বেছে শিল্ড চালু করুন",
        guideStep2Desc = "পছন্দের প্রিসেট বা কাস্টম প্রোফাইল সিলেক্ট করে 'শিল্ড সার্ভিস চালু করুন' চাপুন।",
        guideStep3Title = "৩. ৫টি উপায়ে তাৎক্ষণিক লক করুন",
        guideStep3Desc = "ফ্লোটিং বাবল, ১×১ হোম স্ক্রিন উইজেট, অ্যাপ আইকন শর্টকাট, কুইক টাইল অথবা পকেট/ফ্লিপ সেন্সর দিয়ে লক করুন।",
        guideStep4Title = "৪. হোম উইজেট ও কুইক টাইল যুক্ত করুন",
        guideStep4Desc = "হোম স্ক্রিনে TouchShield উইজেট বা Quick Settings-এ 'টাচ লক' টাইল যোগ করলে অ্যাপ না খুলেও ১ ট্যাপে লক করা যাবে।",
        guideLimitsTitle = "Android-এর সিকিউরিটি নিয়ম (জেনে রাখুন)",
        guideLimitsDesc = "Android সিস্টেমের নিরাপত্তার কারণে ওপরের স্ট্যাটাস বার টেনে নামানো বা পাসওয়ার্ড/পারমিশন স্ক্রিন ওভারলে দিয়ে ব্লক হয় না—তবে মূল অ্যাপ স্ক্রিনের সব টাচ সম্পূর্ণ নিরাপদ ও লক থাকে।",
        lockSourceManual = "ম্যানুয়াল লক",
        lockSourceBubble = "ফ্লোটিং বাটন",
        lockSourceNotification = "নোটিফিকেশন",
        lockSourceTile = "কুইক টাইল",
        lockSourcePocket = "পকেট সেন্সর",
        lockSourceFlip = "ফ্লিপ ফেস-ডাউন",
        lockSourceWidget = "হোম উইজেট"
    )
)
