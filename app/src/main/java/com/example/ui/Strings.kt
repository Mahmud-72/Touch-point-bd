package com.example.ui

import com.example.data.AppLanguage
import com.example.data.AppWatchAction
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderColorOption
import com.example.data.BorderStyle
import com.example.data.BorderThickness
import com.example.data.BubbleSize
import com.example.data.BubbleStyle
import com.example.data.LockSource
import com.example.data.OverlayStyle
import com.example.data.PartialZonePreset
import com.example.data.SettingHelpTopic
import com.example.data.ShakeSensitivity
import com.example.data.SmartPreset
import com.example.data.ThemeMode
import com.example.data.UnlockMethod
import com.example.data.VolumeShortcutAction

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
    val subheaderDiagnostics: String,
    val btnCustomize: String,
    val statScreenClarity: String,
    val dashSectionOverview: String,
    val dashSectionQuickControls: String,
    val dashSectionProfiles: String,
    val dashSectionInsights: String,
    val systemReadyBadge: String,
    val systemReadinessTapHint: String,
    val dashQuickStyleLabel: String,
    val dashFlipRuleActiveBadge: String,
    val togglePartialZone: String,
    val togglePrivacyVeil: String,
    val toggleKidsSafety: String,
    val toggleAppWatch: String,
    val badgeBatteryGuard: String,
    val badgeShakeReady: String,
    val badgeAppWatchActive: String,
    val modeEasyLabel: String,
    val modeProLabel: String,
    val modeEasySubtitle: String,
    val modeProSubtitle: String,
    val dashUnlockProTilesHint: String,
    val proBadgeLabel: String,
    val helpActionDesc: String,
    val helpSectionWhat: String,
    val helpSectionExample: String,
    val helpSectionTip: String,
    val helpCloseBtn: String
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
    val statusOffLabel: String,
    val subheaderKidsSafety: String,
    val toggleKidsSafetyTitle: String,
    val toggleKidsSafetyDesc: String,
    val toggleWrongAttemptCooldown: String,
    val toggleWrongAttemptCooldownDesc: String,
    val toggleKidsLockOnTimerExpiry: String,
    val toggleKidsLockOnTimerExpiryDesc: String,
    val overlayPinCooldownFormat: String,
    val overlayKidsTimeUpTitle: String,
    val subheaderPartialZone: String,
    val togglePartialZoneTitle: String,
    val togglePartialZoneDesc: String,
    val partialZonePresetLabel: String,
    val partialZoneFull: String,
    val partialZoneTopHalf: String,
    val partialZoneBottomHalf: String,
    val partialZoneCenter: String,
    val partialZoneCustom: String,
    val partialZoneTopEdge: String,
    val partialZoneBottomEdge: String,
    val partialZoneLeftEdge: String,
    val partialZoneRightEdge: String,
    val partialZoneHint: String,
    val overlayPartialZoneBadge: String,
    val subheaderPrivacyVeil: String,
    val togglePrivacyVeilTitle: String,
    val togglePrivacyVeilDesc: String,
    val toggleStealthBlackoutTitle: String,
    val toggleStealthBlackoutDesc: String,
    val overlayBtnQuickPeek: String,
    val subheaderAppWatch: String,
    val toggleAppWatchTitle: String,
    val toggleAppWatchDesc: String,
    val appWatchActionLabel: String,
    val appWatchActionBubble: String,
    val appWatchActionLock: String,
    val toggleAppWatchAutoDisarm: String,
    val toggleAppWatchAutoDisarmDesc: String,
    val btnSelectWatchedApps: String,
    val watchedAppsCountFormat: String,
    val appWatchPickerTitle: String,
    val appWatchSearchHint: String,
    val usageAccessDisclosureTitle: String,
    val usageAccessDisclosureBody: String,
    val btnOpenUsageAccess: String,
    val usageAccessMissingHint: String,
    val subheaderShortcuts: String,
    val toggleShakeLock: String,
    val toggleShakeLockDesc: String,
    val toggleShakeUnlock: String,
    val toggleShakeUnlockDesc: String,
    val shakeSensitivityLabel: String,
    val shakeLow: String,
    val shakeMedium: String,
    val shakeHigh: String,
    val volumeShortcutLabel: String,
    val volumeShortcutOff: String,
    val volumeShortcutToggle: String,
    val volumeShortcutUnlockOnly: String,
    val subheaderPowerGuard: String,
    val lowBatteryBlackoutLabel: String,
    val lowBatteryOff: String,
    val toggleAutoLockChargerConnect: String,
    val toggleAutoLockChargerConnectDesc: String,
    val toggleUnlockChargerDisconnect: String,
    val toggleUnlockChargerDisconnectDesc: String,
    val overlayLowBatteryBlackoutBadge: String,
    val diagUsageAccess: String
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
    val lockSourceWidget: String,
    val lockSourceAppWatch: String,
    val lockSourceShake: String,
    val lockSourceVolume: String,
    val lockSourceCharger: String,
    val lockSourceKidsTimeout: String
)

class AppStrings(
    val general: UiGeneralStrings,
    val cfg: UiSettingsStrings,
    val ovl: UiOverlayGuideStrings,
    val helpMap: Map<SettingHelpTopic, FeatureHelpContent> = EnglishHelpMap
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
    val btnCustomize: String get() = general.btnCustomize
    val statScreenClarity: String get() = general.statScreenClarity
    val dashSectionOverview: String get() = general.dashSectionOverview
    val dashSectionQuickControls: String get() = general.dashSectionQuickControls
    val dashSectionProfiles: String get() = general.dashSectionProfiles
    val dashSectionInsights: String get() = general.dashSectionInsights
    val systemReadyBadge: String get() = general.systemReadyBadge
    val systemReadinessTapHint: String get() = general.systemReadinessTapHint
    val dashQuickStyleLabel: String get() = general.dashQuickStyleLabel
    val dashFlipRuleActiveBadge: String get() = general.dashFlipRuleActiveBadge
    val togglePartialZone: String get() = general.togglePartialZone
    val togglePrivacyVeil: String get() = general.togglePrivacyVeil
    val toggleKidsSafety: String get() = general.toggleKidsSafety
    val toggleAppWatch: String get() = general.toggleAppWatch
    val badgeBatteryGuard: String get() = general.badgeBatteryGuard
    val badgeShakeReady: String get() = general.badgeShakeReady
    val badgeAppWatchActive: String get() = general.badgeAppWatchActive
    val modeEasyLabel: String get() = general.modeEasyLabel
    val modeProLabel: String get() = general.modeProLabel
    val modeEasySubtitle: String get() = general.modeEasySubtitle
    val modeProSubtitle: String get() = general.modeProSubtitle
    val dashUnlockProTilesHint: String get() = general.dashUnlockProTilesHint
    val proBadgeLabel: String get() = general.proBadgeLabel
    val helpActionDesc: String get() = general.helpActionDesc
    val helpSectionWhat: String get() = general.helpSectionWhat
    val helpSectionExample: String get() = general.helpSectionExample
    val helpSectionTip: String get() = general.helpSectionTip
    val helpCloseBtn: String get() = general.helpCloseBtn

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
    val subheaderKidsSafety: String get() = cfg.subheaderKidsSafety
    val toggleKidsSafetyTitle: String get() = cfg.toggleKidsSafetyTitle
    val toggleKidsSafetyDesc: String get() = cfg.toggleKidsSafetyDesc
    val toggleWrongAttemptCooldown: String get() = cfg.toggleWrongAttemptCooldown
    val toggleWrongAttemptCooldownDesc: String get() = cfg.toggleWrongAttemptCooldownDesc
    val toggleKidsLockOnTimerExpiry: String get() = cfg.toggleKidsLockOnTimerExpiry
    val toggleKidsLockOnTimerExpiryDesc: String get() = cfg.toggleKidsLockOnTimerExpiryDesc
    val overlayKidsTimeUpTitle: String get() = cfg.overlayKidsTimeUpTitle
    val subheaderPartialZone: String get() = cfg.subheaderPartialZone
    val togglePartialZoneTitle: String get() = cfg.togglePartialZoneTitle
    val togglePartialZoneDesc: String get() = cfg.togglePartialZoneDesc
    val partialZonePresetLabel: String get() = cfg.partialZonePresetLabel
    val partialZoneFull: String get() = cfg.partialZoneFull
    val partialZoneTopHalf: String get() = cfg.partialZoneTopHalf
    val partialZoneBottomHalf: String get() = cfg.partialZoneBottomHalf
    val partialZoneCenter: String get() = cfg.partialZoneCenter
    val partialZoneCustom: String get() = cfg.partialZoneCustom
    val partialZoneTopEdge: String get() = cfg.partialZoneTopEdge
    val partialZoneBottomEdge: String get() = cfg.partialZoneBottomEdge
    val partialZoneLeftEdge: String get() = cfg.partialZoneLeftEdge
    val partialZoneRightEdge: String get() = cfg.partialZoneRightEdge
    val partialZoneHint: String get() = cfg.partialZoneHint
    val overlayPartialZoneBadge: String get() = cfg.overlayPartialZoneBadge
    val subheaderPrivacyVeil: String get() = cfg.subheaderPrivacyVeil
    val togglePrivacyVeilTitle: String get() = cfg.togglePrivacyVeilTitle
    val togglePrivacyVeilDesc: String get() = cfg.togglePrivacyVeilDesc
    val toggleStealthBlackoutTitle: String get() = cfg.toggleStealthBlackoutTitle
    val toggleStealthBlackoutDesc: String get() = cfg.toggleStealthBlackoutDesc
    val overlayBtnQuickPeek: String get() = cfg.overlayBtnQuickPeek
    val subheaderAppWatch: String get() = cfg.subheaderAppWatch
    val toggleAppWatchTitle: String get() = cfg.toggleAppWatchTitle
    val toggleAppWatchDesc: String get() = cfg.toggleAppWatchDesc
    val appWatchActionLabel: String get() = cfg.appWatchActionLabel
    val appWatchActionBubble: String get() = cfg.appWatchActionBubble
    val appWatchActionLock: String get() = cfg.appWatchActionLock
    val toggleAppWatchAutoDisarm: String get() = cfg.toggleAppWatchAutoDisarm
    val toggleAppWatchAutoDisarmDesc: String get() = cfg.toggleAppWatchAutoDisarmDesc
    val btnSelectWatchedApps: String get() = cfg.btnSelectWatchedApps
    val appWatchPickerTitle: String get() = cfg.appWatchPickerTitle
    val appWatchSearchHint: String get() = cfg.appWatchSearchHint
    val usageAccessDisclosureTitle: String get() = cfg.usageAccessDisclosureTitle
    val usageAccessDisclosureBody: String get() = cfg.usageAccessDisclosureBody
    val btnOpenUsageAccess: String get() = cfg.btnOpenUsageAccess
    val usageAccessMissingHint: String get() = cfg.usageAccessMissingHint
    val subheaderShortcuts: String get() = cfg.subheaderShortcuts
    val toggleShakeLock: String get() = cfg.toggleShakeLock
    val toggleShakeLockDesc: String get() = cfg.toggleShakeLockDesc
    val toggleShakeUnlock: String get() = cfg.toggleShakeUnlock
    val toggleShakeUnlockDesc: String get() = cfg.toggleShakeUnlockDesc
    val shakeSensitivityLabel: String get() = cfg.shakeSensitivityLabel
    val shakeLow: String get() = cfg.shakeLow
    val shakeMedium: String get() = cfg.shakeMedium
    val shakeHigh: String get() = cfg.shakeHigh
    val volumeShortcutLabel: String get() = cfg.volumeShortcutLabel
    val volumeShortcutOff: String get() = cfg.volumeShortcutOff
    val volumeShortcutToggle: String get() = cfg.volumeShortcutToggle
    val volumeShortcutUnlockOnly: String get() = cfg.volumeShortcutUnlockOnly
    val subheaderPowerGuard: String get() = cfg.subheaderPowerGuard
    val lowBatteryBlackoutLabel: String get() = cfg.lowBatteryBlackoutLabel
    val lowBatteryOff: String get() = cfg.lowBatteryOff
    val toggleAutoLockChargerConnect: String get() = cfg.toggleAutoLockChargerConnect
    val toggleAutoLockChargerConnectDesc: String get() = cfg.toggleAutoLockChargerConnectDesc
    val toggleUnlockChargerDisconnect: String get() = cfg.toggleUnlockChargerDisconnect
    val toggleUnlockChargerDisconnectDesc: String get() = cfg.toggleUnlockChargerDisconnectDesc
    val overlayLowBatteryBlackoutBadge: String get() = cfg.overlayLowBatteryBlackoutBadge
    val diagUsageAccess: String get() = cfg.diagUsageAccess

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
    val lockSourceAppWatch: String get() = ovl.lockSourceAppWatch
    val lockSourceShake: String get() = ovl.lockSourceShake
    val lockSourceVolume: String get() = ovl.lockSourceVolume
    val lockSourceCharger: String get() = ovl.lockSourceCharger
    val lockSourceKidsTimeout: String get() = ovl.lockSourceKidsTimeout

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
        LockSource.APP_WATCH -> lockSourceAppWatch
        LockSource.SHAKE_GESTURE -> lockSourceShake
        LockSource.VOLUME_SHORTCUT -> lockSourceVolume
        LockSource.CHARGER_RULE -> lockSourceCharger
        LockSource.KIDS_TIMEOUT -> lockSourceKidsTimeout
        LockSource.NONE -> ""
    }

    fun overlayPinCooldownWait(seconds: Int): String =
        cfg.overlayPinCooldownFormat.replace("%d", seconds.toString())

    fun watchedAppsSummary(count: Int): String =
        cfg.watchedAppsCountFormat.replace("%d", count.toString())

    fun partialZonePresetLabel(preset: PartialZonePreset): String = when (preset) {
        PartialZonePreset.FULL_LOCK -> partialZoneFull
        PartialZonePreset.TOP_HALF -> partialZoneTopHalf
        PartialZonePreset.BOTTOM_HALF -> partialZoneBottomHalf
        PartialZonePreset.CENTER_WINDOW -> partialZoneCenter
        PartialZonePreset.CUSTOM -> partialZoneCustom
    }

    fun appWatchActionLabel(action: AppWatchAction): String = when (action) {
        AppWatchAction.SHOW_BUBBLE -> appWatchActionBubble
        AppWatchAction.AUTO_LOCK -> appWatchActionLock
    }

    fun shakeSensitivityLabel(sens: ShakeSensitivity): String = when (sens) {
        ShakeSensitivity.LOW -> shakeLow
        ShakeSensitivity.MEDIUM -> shakeMedium
        ShakeSensitivity.HIGH -> shakeHigh
    }

    fun volumeShortcutLabel(action: VolumeShortcutAction): String = when (action) {
        VolumeShortcutAction.OFF -> volumeShortcutOff
        VolumeShortcutAction.TOGGLE_LOCK -> volumeShortcutToggle
        VolumeShortcutAction.UNLOCK_ONLY -> volumeShortcutUnlockOnly
    }

    fun helpContent(topic: SettingHelpTopic): FeatureHelpContent =
        helpMap[topic] ?: EnglishHelpMap.getValue(topic)
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
        appTagline = "Keep your screen visible without accidental pocket or kid taps",
        tabDashboard = "Home",
        tabSettings = "Settings",
        tabGuide = "Guide",
        statusStandby = "Off",
        statusArmed = "Ready to Lock",
        statusLocked = "Touch Locked",
        statusPermissionNeeded = "Needs Permission",
        statusStandbyDesc = "Turn on Guard so you can lock touches anytime or automatically in your pocket.",
        statusArmedDesc = "Lock instantly via the floating button, home widget, Quick Tile, or pocket sensor.",
        statusLockedDesc = "Your screen stays visible while accidental taps and swipes are blocked.",
        statusPermissionDesc = "Allow 'Display over other apps' so TouchShield can protect any open screen.",
        btnArmShield = "Turn On Guard",
        btnStopShield = "Turn Off Guard",
        btnLockScreenNow = "Lock Screen Now",
        btnTestLockSandbox = "Try Safely Here",
        btnGrantPermission = "Allow Access",
        btnEnableNow = "Allow",
        btnBatterySettings = "Open Battery Settings",
        permissionsCardTitle = "App Permissions",
        permOverlayTitle = "Display Over Other Apps",
        permOverlayDesc = "Needed to block accidental taps over photos, videos, or any app.",
        permNotificationTitle = "Background Notification",
        permNotificationDesc = "Keeps protection running and gives quick Lock / Unlock buttons.",
        permGranted = "Allowed",
        permRequired = "Needed",
        permOptional = "Helpful",
        statBlockedTouches = "Blocked Taps",
        statLockSessions = "Times Locked",
        statActiveMode = "Screen Dim",
        analyticsTitle = "7-Day Blocked Taps",
        analyticsEmptyHint = "No accidental taps blocked yet—lock your screen or try the safe test to see stats.",
        analyticsSourcesTitle = "How You Locked Most",
        sensorCardTitle = "Live Pocket Sensor & Position",
        sensorNear = "Covered / Inside Pocket",
        sensorFar = "Open / In Clear Light",
        sensorUnavailable = "No pocket proximity sensor on this phone",
        sensorTestHint = "Cover the top speaker area with your hand to test pocket detection",
        sensorOrientationPocket = "Held Upright / Pocket Ready",
        sensorOrientationFlat = "Flat on Table (Auto-lock paused)",
        sensorOrientationFaceDown = "Placed Face-Down",
        sensorPausedLandscape = "Paused (Sideways Video)",
        sensorPausedCharging = "Paused (Charging)",
        quickControlsTitle = "Everyday Controls",
        togglePocketAutoLock = "Lock Inside Pocket",
        togglePocketAutoLockDesc = "Automatically locks touch when your phone goes into your pocket",
        togglePocketAutoUnlock = "Unlock Out of Pocket",
        togglePocketAutoUnlockDesc = "Unlocks automatically when taken back out (if pocket-locked)",
        toggleFloatingBubble = "Floating Lock Button",
        toggleFloatingBubbleDesc = "Shows a small movable Lock button over any app",
        toggleKeepScreenOn = "Keep Screen Awake",
        toggleKeepScreenOnDesc = "Stops the screen from turning off while locked",
        presetsTitle = "Quick 1-Tap Modes",
        presetPocketPhotoTitle = "Pocket Photo",
        presetPocketPhotoDesc = "0% Clear • Stay Awake • Pocket Lock",
        presetKidsVideoTitle = "Kids / Video",
        presetKidsVideoDesc = "Hold 3s • Corner Frame • No Button",
        presetBatteryTitle = "Battery Saver",
        presetBatteryDesc = "88% Dark • Dim Clock • 5% Bright",
        presetNavMapTitle = "Nav / Bike Map",
        presetNavMapDesc = "100% Bright • Pill • No Pocket Lock",
        customProfilesTitle = "My Saved Modes",
        btnSaveProfile = "Save Current",
        saveProfileDialogTitle = "Save Mode Name",
        saveProfileHint = "Mode name (e.g. YouTube Bedtime)",
        renameProfileDialogTitle = "Rename Mode",
        noCustomProfilesHint = "Save your favorite setup here as a 1-tap shortcut.",
        settingsSearchHint = "Search any setting (e.g. PIN, Kids, Pocket, Black...)",
        settingsNoSearchResults = "No settings match your search.",
        btnExpandAll = "Open All",
        btnCollapseAll = "Close All",
        btnTestGestureHere = "Practice Unlock",
        inlineTesterTitle = "Try Unlocking Here",
        inlineTesterSuccess = "Unlocked!",
        settingsLivePreviewTitle = "Live Screen Preview",
        settingsUnlockSection = "How to Unlock & Kids Lock",
        settingsOverlaySection = "Screen Look, Privacy & Touch Window",
        settingsBubbleSection = "Floating Lock Button",
        settingsPocketSection = "Auto-Lock Rules, Sensors & Battery",
        settingsSystemSection = "Vibration, Theme, Language & Backup",
        subheaderSecurity = "UNLOCK METHOD & KIDS PROTECTION",
        subheaderRecovery = "FORGOT-PIN RECOVERY",
        subheaderBrightness = "CLARITY & SCREEN BRIGHTNESS",
        subheaderBorders = "LOCK EDGE BORDER",
        subheaderOledHud = "TOP BADGE & DARK CLOCK",
        subheaderSensorTuning = "POCKET SENSOR",
        subheaderContextRules = "WHEN TO PAUSE OR LOCK",
        subheaderCallTimer = "PHONE CALLS & AUTO-TIMER",
        subheaderAppearance = "THEME & LANGUAGE",
        subheaderBackupReset = "BACKUP FILE, PRIVACY & RESET",
        subheaderDiagnostics = "PHONE CHECKUP",
        btnCustomize = "More Options",
        statScreenClarity = "Clarity",
        dashSectionOverview = "PROTECTION STATUS",
        dashSectionQuickControls = "EVERYDAY CONTROLS & SCREEN",
        dashSectionProfiles = "READY MODES",
        dashSectionInsights = "ACTIVITY & LIVE SENSOR",
        systemReadyBadge = "Ready • Permissions Allowed",
        systemReadinessTapHint = "Details",
        dashQuickStyleLabel = "Locked Screen Look",
        dashFlipRuleActiveBadge = "Face-Down Lock ON",
        togglePartialZone = "Touch Only 1 Area",
        togglePrivacyVeil = "Hide Screen (Black)",
        toggleKidsSafety = "Kids Safe-Lock",
        toggleAppWatch = "Auto-Lock Apps",
        badgeBatteryGuard = "Low-Battery Save",
        badgeShakeReady = "Shake Ready",
        badgeAppWatchActive = "App-Watch On",
        modeEasyLabel = "Easy Mode",
        modeProLabel = "Pro Mode",
        modeEasySubtitle = "Simple everyday options only",
        modeProSubtitle = "All fine controls, sensors & tools",
        dashUnlockProTilesHint = "Need Partial Area, Auto-Lock Apps or Pocket Unlock? Switch to Pro Mode →",
        proBadgeLabel = "PRO",
        helpActionDesc = "What does this do?",
        helpSectionWhat = "What does this do?",
        helpSectionExample = "Real-life example",
        helpSectionTip = "Who should use this?",
        helpCloseBtn = "Got it"
    ),
    cfg = UiSettingsStrings(
        holdDurationLabel = "How Long to Hold",
        hudTimeoutLabel = "Hide Unlock Bar After",
        lockCountdownLabel = "Wait Before Locking",
        countdownOff = "Instant (0s)",
        pinCodeLabel = "Current 4-Digit PIN",
        btnChangePin = "Change PIN",
        pinDialogTitle = "Set 4-Digit PIN",
        pinDialogHint = "Enter 4 digits (e.g. 1234)",
        btnSave = "Save",
        btnCancel = "Cancel",
        overlayPinHint = "Enter 4-digit PIN to unlock",
        overlayPinWrong = "Wrong PIN! Try again",
        toggleBiometricUnlock = "Fingerprint / Face Unlock",
        toggleBiometricUnlockDesc = "Show fingerprint/face button next to the PIN keypad",
        overlayBtnBiometric = "Biometric",
        toggleEmergencyBypass = "5s Emergency Unlock",
        toggleEmergencyBypassDesc = "Hold an emergency button for 5s if you ever forget your PIN",
        overlayEmergencyHoldLabel = "Forgot PIN? Hold 5s",
        customDimLabel = "Screen Darkness (Dim)",
        clearPercentLabel = "Clear",
        lockBrightnessLabel = "Brightness While Locked",
        brightnessSystem = "Normal",
        brightnessNight = "Night 5%",
        brightnessOutdoor = "Sun 100%",
        toggleBorderEnabled = "Show Colored Edge Frame",
        toggleBorderDesc = "Shows a thin frame around the screen so you know it's locked",
        borderColorLabel = "Frame Color",
        borderColorEmerald = "Green",
        borderColorCyan = "Blue",
        borderColorAmber = "Amber",
        borderColorRose = "Rose",
        borderThicknessLabel = "Frame Thickness",
        borderStyleLabel = "Frame Style",
        borderStyleGlow = "Full Border",
        borderStyleCorners = "4 Corners",
        toggleBorderPulse = "Gentle Breathing Glow",
        toggleBorderPulseDesc = "Slowly pulses the edge border while touch is locked",
        badgeModeLabel = "Top Lock Pill",
        badgeAlways = "Always",
        badgeOnTouch = "When Tapped",
        badgeHidden = "Hidden",
        toggleOledClock = "Show Clock & Battery on Dark Screen",
        toggleOledClockDesc = "Shows subtle time and battery % when the screen is dimmed",
        thicknessThin = "Thin",
        thicknessMedium = "Medium",
        thicknessBold = "Thick",
        bubbleOpacityLabel = "Button Visibility",
        bubbleSizeLabel = "Button Size",
        bubbleSizeCompact = "Small",
        bubbleSizeStandard = "Medium",
        bubbleSizeLarge = "Large",
        bubbleStyleLabel = "Button Shape",
        bubbleStyleShield = "Shield",
        bubbleStyleDot = "Small Dot",
        bubbleStylePill = "Pill",
        bubblePillLabel = "LOCK",
        toggleBubbleAutoTuck = "Slide to Edge After 3s",
        toggleBubbleAutoTuckDesc = "Tucks halfway into the screen edge so nothing stays covered",
        toggleBubbleDoubleTapHide = "Double-Tap Button to Hide",
        toggleBubbleDoubleTapHideDesc = "Tap twice to hide temporarily (restore from notification)",
        pocketDebounceLabel = "Pocket Detection Delay",
        toggleOrientationFilter = "Ignore When Flat on Table",
        toggleOrientationFilterDesc = "Won't auto-lock from hand shadows while resting face-up on a desk",
        togglePauseLandscape = "Pause During Sideways Video / Games",
        togglePauseLandscapeDesc = "Stops pocket lock while watching fullscreen videos or gaming",
        togglePauseCharging = "Pause Pocket Lock While Charging",
        togglePauseChargingDesc = "Turns off pocket auto-lock whenever a charger is plugged in",
        toggleFlipFaceDownLock = "Place Face-Down to Lock",
        toggleFlipFaceDownLockDesc = "Automatically locks touch when you lay your phone screen-down",
        toggleAutoUnlockOnCall = "Auto-Unlock for Phone Calls",
        toggleAutoUnlockOnCallDesc = "Unlocks automatically when ringing so you can answer calls easily",
        toggleReLockAfterCall = "Re-Lock When Call Ends",
        toggleReLockAfterCallDesc = "Locks touch again automatically once you hang up",
        sessionTimerLabel = "Auto-Unlock Timer",
        timerOff = "Off",
        toggleHaptics = "Gentle Vibration",
        toggleHapticsDesc = "Light buzz when touch locks and unlocks",
        toggleAutoBoot = "Auto-Ready After Restart",
        toggleAutoBootDesc = "Prepares TouchShield automatically whenever your phone restarts",
        themeModeLabel = "App Theme",
        themeSystem = "System",
        themeLight = "Light",
        themeDark = "Dark",
        toggleDynamicColor = "Match Wallpaper Colors",
        toggleDynamicColorDesc = "Uses your phone's Material You wallpaper colors (Android 12+)",
        languageLabel = "Language",
        languageAutoSystem = "Auto (Phone Language)",
        btnExportBackup = "Save Backup (.json)",
        btnImportBackup = "Load Backup",
        backupExportSuccess = "Settings backup saved successfully.",
        backupImportSuccess = "Settings restored from backup!",
        backupError = "Could not read or save backup file.",
        privacyCardTitle = "100% Offline & Private",
        privacyBadgeOffline = "Offline Only",
        privacyBadgeNoNet = "No INTERNET Access",
        privacyBadgeZeroTrack = "No Ads or Tracking",
        privacyCardDesc = "TouchShield does not use Internet permission. Your PIN, sensors, and modes stay strictly on your device.",
        batteryCardTitle = "Keep Running in Background",
        batteryCardDesc = "On Xiaomi, Samsung, Oppo, or Vivo phones, set Battery to 'Unrestricted' so Android doesn't close protection.",
        diagProximity = "Pocket Sensor",
        diagAccelerometer = "Motion Sensor",
        diagOverlay = "Display Over Apps",
        diagNotifications = "Notifications",
        diagPhoneState = "Call Detection",
        diagBattery = "Battery Mode",
        diagAvailable = "Ready",
        diagUnavailable = "Not Found",
        diagUnrestricted = "Unrestricted",
        diagOptimized = "Limited (Optimized)",
        btnResetStats = "Clear Stats",
        btnRestoreDefaults = "Reset Defaults",
        confirmResetStatsTitle = "Clear All Stats?",
        confirmResetStatsMsg = "Blocked tap counters and 7-day history will reset to 0.",
        confirmRestoreTitle = "Reset All Settings?",
        confirmRestoreMsg = "All custom settings will return to recommended defaults.",
        btnConfirm = "Confirm",
        statusOnLabel = "ON",
        statusOffLabel = "OFF",
        subheaderKidsSafety = "KIDS SAFETY & PIN LOCKOUT",
        toggleKidsSafetyTitle = "Kids Safe-Lock Mode",
        toggleKidsSafetyDesc = "Hides 5s emergency unlock & disables shake unlock so kids stay inside",
        toggleWrongAttemptCooldown = "Wait Timer on Wrong PIN",
        toggleWrongAttemptCooldownDesc = "Pauses PIN pad 15s after 5 wrong tries, 30s after 8+",
        toggleKidsLockOnTimerExpiry = "Require Parent PIN When Timer Ends",
        toggleKidsLockOnTimerExpiryDesc = "Locks with your PIN when the timer finishes instead of unlocking",
        overlayPinCooldownFormat = "Too many wrong tries • Wait %ds",
        overlayKidsTimeUpTitle = "Time's Up! Parent PIN Needed",
        subheaderPartialZone = "KEEP ONLY 1 AREA TOUCHABLE",
        togglePartialZoneTitle = "Keep One Area Touchable",
        togglePartialZoneDesc = "Leaves one box (like video or map) touchable while locking the rest",
        partialZonePresetLabel = "Open Area Shape",
        partialZoneFull = "Lock Full Screen",
        partialZoneTopHalf = "Top Half Open",
        partialZoneBottomHalf = "Bottom Half Open",
        partialZoneCenter = "Middle Box",
        partialZoneCustom = "Custom Size",
        partialZoneTopEdge = "Top",
        partialZoneBottomEdge = "Bottom",
        partialZoneLeftEdge = "Left",
        partialZoneRightEdge = "Right",
        partialZoneHint = "Highlighted area stays clickable; dark area blocks accidental taps.",
        overlayPartialZoneBadge = "Open Touch Window",
        subheaderPrivacyVeil = "PRIVACY BLACK SCREEN",
        togglePrivacyVeilTitle = "Hide Screen in Public (99% Black)",
        togglePrivacyVeilDesc = "Turns screen nearly pitch black for commute privacy (has 3s Peek button)",
        toggleStealthBlackoutTitle = "Totally Hidden Lock (No Lights)",
        toggleStealthBlackoutDesc = "Hides border, badge & clock—tap anywhere to show unlock bar",
        overlayBtnQuickPeek = "Peek 3s",
        subheaderAppWatch = "AUTO-PROTECT CHOSEN APPS",
        toggleAppWatchTitle = "Auto-Protect Chosen Apps",
        toggleAppWatchDesc = "Shows lock button or locks automatically when YouTube, Maps, etc. open",
        appWatchActionLabel = "When App Opens",
        appWatchActionBubble = "Show Button",
        appWatchActionLock = "Lock Right Away",
        toggleAppWatchAutoDisarm = "Turn Off When Leaving App",
        toggleAppWatchAutoDisarmDesc = "Hides button or unlocks automatically when you exit that app",
        btnSelectWatchedApps = "Choose Apps",
        watchedAppsCountFormat = "%d chosen",
        appWatchPickerTitle = "Pick Apps to Protect",
        appWatchSearchHint = "Search installed apps...",
        usageAccessDisclosureTitle = "App Usage Access (Private & Offline)",
        usageAccessDisclosureBody = "TouchShield checks Android Usage Stats locally only to know when your chosen apps open—no Accessibility service and no Internet.",
        btnOpenUsageAccess = "Allow Usage Access",
        usageAccessMissingHint = "Allow Usage Access so TouchShield knows when chosen apps open.",
        subheaderShortcuts = "SHAKE & VOLUME KEY SHORTCUTS",
        toggleShakeLock = "Shake Twice to Lock",
        toggleShakeLockDesc = "Give your phone two quick shakes anytime to lock touch",
        toggleShakeUnlock = "Shake Twice to Unlock",
        toggleShakeUnlockDesc = "Automatically stays off whenever Kids Safe-Lock is on",
        shakeSensitivityLabel = "Shake Force Needed",
        shakeLow = "Strong",
        shakeMedium = "Normal",
        shakeHigh = "Light",
        volumeShortcutLabel = "Double-Press Volume Key",
        volumeShortcutOff = "Off",
        volumeShortcutToggle = "Lock / Unlock",
        volumeShortcutUnlockOnly = "Unlock Only",
        subheaderPowerGuard = "CHARGER & LOW-BATTERY POWER GUARD",
        lowBatteryBlackoutLabel = "Auto Dark-Save When Battery Drops Below",
        lowBatteryOff = "Off",
        toggleAutoLockChargerConnect = "Auto-Lock When Charger Plugged",
        toggleAutoLockChargerConnectDesc = "Locks touch automatically as soon as charging starts",
        toggleUnlockChargerDisconnect = "Auto-Unlock When Unplugged",
        toggleUnlockChargerDisconnectDesc = "Unlocks automatically when you unplug the charging cable",
        overlayLowBatteryBlackoutBadge = "Low-Battery Dark Save",
        diagUsageAccess = "App Detection Access"
    ),
    ovl = UiOverlayGuideStrings(
        unlockHoldTitle = "Hold to Unlock",
        unlockHoldDesc = "Press and hold the unlock shield. Almost impossible to open in a pocket.",
        unlockSlideTitle = "Slide to Unlock",
        unlockSlideDesc = "Drag the unlock button from left to right.",
        unlockDoubleTapTitle = "Double-Tap Target",
        unlockDoubleTapDesc = "Quickly tap twice on the unlock icon.",
        unlockPinTitle = "4-Digit PIN Lock",
        unlockPinDesc = "Needs your 4-digit PIN (or Fingerprint) to unlock.",
        styleClearTitle = "Clear (0% Dim)",
        styleClearDesc = "Photos and videos stay 100% bright and clear underneath.",
        styleTintTitle = "Light Shade (25%)",
        styleTintDesc = "Light tint so you can immediately tell touch is locked.",
        styleBlackTitle = "Dark Black (88%)",
        styleBlackDesc = "Darkens screen to save battery while audio/app keeps running.",
        overlayTouchBlockedTitle = "Screen Touch Locked",
        overlayHoldHint = "Hold shield for 1.8s to unlock",
        overlaySlideHint = "Slide right to unlock",
        overlayDoubleTapHint = "Double-tap shield to unlock",
        overlayCountdownLabel = "Locking in",
        overlayCountdownCancel = "Tap to cancel",
        sandboxTitle = "Try Safely Inside App",
        sandboxSubtitle = "Test lock look, brightness, and unlocking right here with zero risk.",
        sandboxDummyPhotoTitle = "Sample Screen",
        sandboxDummyPhotoDesc = "Imagine your photo or video is open here. Tap below to lock and test!",
        sandboxTapBlockedToast = "Accidental tap blocked!",
        sandboxTryClicking = "Try tapping me (blocked while locked)",
        sandboxLockTriggerBtn = "Lock Test Screen",
        sandboxExitBtn = "Close",
        notifTitleArmed = "TouchShield Ready",
        notifTextArmed = "Tap the floating button or 'Lock Screen' below anytime",
        notifTitleLocked = "Screen Touch Locked",
        notifTextLocked = "Accidental taps blocked • Tap 'Unlock' if needed",
        notifActionLock = "Lock Screen",
        notifActionUnlock = "Unlock",
        notifActionPocketOn = "Pocket: ON",
        notifActionPocketOff = "Pocket: OFF",
        notifActionShowBubble = "Show Button",
        notifActionStop = "Turn Off",
        onboardingSkip = "Skip",
        onboardingNext = "Next",
        onboardingGetStarted = "Get Started",
        onboardingPage1Title = "Clear Screen, Zero Accidental Taps",
        onboardingPage1Desc = "Keep photos, maps, or videos visible in your pocket or hand without accidental clicks, calls, or deletes.",
        onboardingPage2Title = "Smart Pocket & Call Rules",
        onboardingPage2Desc = "Locks automatically in your pocket, pauses during sideways videos, and unlocks for phone calls.",
        onboardingPage3Title = "100% Offline & Private",
        onboardingPage3Desc = "No Internet access and no ads. Allow Display Over Other Apps next to start protecting any app.",
        btnReplayOnboarding = "Watch Intro Guide Again",
        guideTitle = "How to Use TouchShield",
        guideStep1Title = "1. Allow Overlay & Notification Access",
        guideStep1Desc = "Turn on 'Display over other apps' from Home so TouchShield works over Gallery, YouTube, or Maps.",
        guideStep2Title = "2. Pick a Mode & Turn On Guard",
        guideStep2Desc = "Choose a quick mode like Pocket Photo or Kids Video, then tap 'Turn On Guard'.",
        guideStep3Title = "3. Lock From Anywhere (5 Ways)",
        guideStep3Desc = "Lock using the Floating Button, 1x1 Home Widget, App Icon Shortcuts, Quick Tile, or Pocket Sensor.",
        guideStep4Title = "4. Add Home Widget & Quick Tile",
        guideStep4Desc = "Add the 1x1 widget or Quick Settings tile for 1-tap locking without opening the app.",
        guideLimitsTitle = "Good to Know (Android Rule)",
        guideLimitsDesc = "For security, Android always keeps the top status bar and system password popups touchable while locking your entire app screen.",
        lockSourceManual = "Manual Lock",
        lockSourceBubble = "Floating Button",
        lockSourceNotification = "Notification",
        lockSourceTile = "Quick Tile",
        lockSourcePocket = "Pocket Sensor",
        lockSourceFlip = "Face-Down",
        lockSourceWidget = "Home Widget",
        lockSourceAppWatch = "App-Watch",
        lockSourceShake = "Shake",
        lockSourceVolume = "Volume Keys",
        lockSourceCharger = "Charger",
        lockSourceKidsTimeout = "Kids Time's Up"
    ),
    helpMap = EnglishHelpMap
)

val BengaliStrings = AppStrings(
    general = UiGeneralStrings(
        appTitle = "TouchShield",
        appTagline = "পর্দা পরিষ্কার রেখে পকেট ও বাচ্চাদের ভুল টাচ থেকে সুরক্ষা",
        tabDashboard = "হোম",
        tabSettings = "সেটিংস",
        tabGuide = "ব্যবহারবিধি",
        statusStandby = "বন্ধ আছে",
        statusArmed = "সুরক্ষা চালু",
        statusLocked = "স্ক্রিন লক আছে",
        statusPermissionNeeded = "অনুমতি দিন",
        statusStandbyDesc = "ছবি, ভিডিও বা ম্যাপ খোলা রেখে ভুল চাপ এড়াতে সুরক্ষা চালু করুন।",
        statusArmedDesc = "ভাসমান বোতাম, হোম উইজেট, কুইক টাইল বা পকেট সেন্সর দিয়ে লক করুন।",
        statusLockedDesc = "পর্দা স্পষ্ট দেখা যাচ্ছে, কিন্তু সব ভুল ছোঁয়া আটকে দেওয়া হয়েছে।",
        statusPermissionDesc = "অন্য অ্যাপের ওপরে টাচ আটকাতে এই অনুমতিটি দিন।",
        btnArmShield = "সুরক্ষা চালু করুন",
        btnStopShield = "সুরক্ষা বন্ধ করুন",
        btnLockScreenNow = "এখনই স্ক্রিন লক করুন",
        btnTestLockSandbox = "বাইরে না গিয়ে টেস্ট",
        btnGrantPermission = "অনুমতি দিন",
        btnEnableNow = "চালু করুন",
        btnBatterySettings = "ব্যাটারি সেটিংস খুলুন",
        permissionsCardTitle = "অ্যাপ চলার অনুমতি",
        permOverlayTitle = "সব অ্যাপের ওপরে থাকার অনুমতি",
        permOverlayDesc = "ছবি, ভিডিও বা ম্যাপ খোলা রেখে টাচ আটকাতে এটি প্রয়োজন।",
        permNotificationTitle = "চলমান নোটিফিকেশন",
        permNotificationDesc = "পেছনে সুরক্ষা সচল রাখে ও সহজে লক/খোলার বাটন দেয়।",
        permGranted = "চালু আছে",
        permRequired = "জরুরি",
        permOptional = "সহায়ক",
        statBlockedTouches = "আটকানো চাপ",
        statLockSessions = "মোট লক",
        statActiveMode = "পর্দার ছায়া",
        analyticsTitle = "৭ দিনের কাজের হিসাব",
        analyticsEmptyHint = "এখনও কোনো ভুল চাপ আটকানো হয়নি—লক বা টেস্ট করে দেখুন।",
        analyticsSourcesTitle = "বেশি ব্যবহার হওয়া লক",
        sensorCardTitle = "পকেট সেন্সর ও অবস্থান",
        sensorNear = "ঢাকা / পকেটে আছে",
        sensorFar = "খোলা / পরিষ্কার আলো",
        sensorUnavailable = "এই ফোনে পকেট সেন্সর নেই",
        sensorTestHint = "পরীক্ষা করতে ফোনের ওপরের স্পিকারের কাছে হাত দিয়ে ঢাকুন",
        sensorOrientationPocket = "পকেটের মতো সোজা অবস্থায়",
        sensorOrientationFlat = "টেবিলে চিৎ হয়ে আছে (অটো-লক থামানো)",
        sensorOrientationFaceDown = "স্ক্রিন নিচে উল্টো করা",
        sensorPausedLandscape = "আড়াআড়ি ভিডিওতে থামানো",
        sensorPausedCharging = "চার্জ দেওয়ার সময় থামানো",
        quickControlsTitle = "দরকারি কন্ট্রোল",
        togglePocketAutoLock = "পকেটে অটো-লক",
        togglePocketAutoLockDesc = "পকেটে রাখলে নিজে থেকেই স্ক্রিন লক হয়ে যাবে",
        togglePocketAutoUnlock = "পকেট থেকে বের করলেই খুলবে",
        togglePocketAutoUnlockDesc = "পকেটে লক হওয়া ফোন বের করলে নিজে থেকেই খুলবে",
        toggleFloatingBubble = "ভাসমান লক বোতাম",
        toggleFloatingBubbleDesc = "যেকোনো অ্যাপের পাশে ছোট লক বোতাম দেখাবে",
        toggleKeepScreenOn = "পর্দা জ্বালিয়ে রাখুন",
        toggleKeepScreenOnDesc = "লক থাকা অবস্থায় পর্দা সময় হয়ে বন্ধ হতে দেবে না",
        presetsTitle = "১-চাপে রেডি মোড",
        presetPocketPhotoTitle = "পকেট ফটো",
        presetPocketPhotoDesc = "০% পরিষ্কার • পর্দা অন • পকেট লক",
        presetKidsVideoTitle = "শিশু / ভিডিও",
        presetKidsVideoDesc = "৩s চেপে খোলা • কোণায় দাগ • বাটন ছাড়া",
        presetBatteryTitle = "ব্যাটারি বাঁচাও",
        presetBatteryDesc = "৮৮% কালো • ঘড়ি • ৫% আলো",
        presetNavMapTitle = "ম্যাপ / বাইক",
        presetNavMapDesc = "১০০% আলো • পিল • পকেট লক বন্ধ",
        customProfilesTitle = "আমার সেভ করা মোড",
        btnSaveProfile = "নতুন সেভ",
        saveProfileDialogTitle = "মোডের নাম লিখুন",
        saveProfileHint = "মোডের নাম (যেমন: রাতের ইউটিউব)",
        renameProfileDialogTitle = "নাম বদলান",
        noCustomProfilesHint = "আপনার পছন্দের সেটিংস ১-চাপের শর্টকাট হিসেবে সেভ করুন।",
        settingsSearchHint = "যেকোনো সেটিংস খুঁজুন (যেমন: পিন, শিশু, পকেট, কালো...)",
        settingsNoSearchResults = "খোঁজা শব্দটির সাথে কোনো সেটিংস মেলেনি।",
        btnExpandAll = "সব খুলুন",
        btnCollapseAll = "সব গুটিয়ে রাখুন",
        btnTestGestureHere = "লক খোলার প্র্যাকটিস",
        inlineTesterTitle = "এখানেই লক খোলা অনুশীলন করুন",
        inlineTesterSuccess = "খুলে গেছে!",
        settingsLivePreviewTitle = "লক করলে কেমন দেখাবে",
        settingsUnlockSection = "লক খোলা ও শিশু সুরক্ষা",
        settingsOverlaySection = "পর্দার রূপ, গোপনীয়তা ও খোলা অংশ",
        settingsBubbleSection = "ভাসমান লক বোতাম",
        settingsPocketSection = "অটো-লক নিয়ম, সেন্সর ও ব্যাটারি",
        settingsSystemSection = "ভাইব্রেশন, থিম, ভাষা ও ব্যাকআপ",
        subheaderSecurity = "লক খোলার ধরন ও সময়",
        subheaderRecovery = "পিন ভুলে গেলে উদ্ধার",
        subheaderBrightness = "স্বচ্ছতা ও পর্দার আলো",
        subheaderBorders = "চারপাশের লক দাগ",
        subheaderOledHud = "উপরের ব্যাজ ও কালো পর্দার ঘড়ি",
        subheaderSensorTuning = "পকেট সেন্সর",
        subheaderContextRules = "কখন থামবে বা লক হবে",
        subheaderCallTimer = "ফোন কল ও সময় শেষের নিয়ম",
        subheaderAppearance = "থিম ও ভাষা",
        subheaderBackupReset = "ফাইল ব্যাকআপ, প্রাইভেসি ও রিসেট",
        subheaderDiagnostics = "ফোন পরীক্ষা",
        btnCustomize = "আরো সেটিংস",
        statScreenClarity = "পর্দার স্বচ্ছতা",
        dashSectionOverview = "সুরক্ষা অবস্থা",
        dashSectionQuickControls = "দরকারি কন্ট্রোল ও পর্দা",
        dashSectionProfiles = "রেডি মোড",
        dashSectionInsights = "কাজের হিসাব ও লাইভ সেন্সর",
        systemReadyBadge = "সব ঠিক আছে • মূল অনুমতি চালু",
        systemReadinessTapHint = "বিস্তারিত",
        dashQuickStyleLabel = "লক থাকলে পর্দার রূপ",
        dashFlipRuleActiveBadge = "উল্টালে লক চালু",
        togglePartialZone = "শুধু এক অংশ খোলা",
        togglePrivacyVeil = "পর্দা কালো লুকান",
        toggleKidsSafety = "শিশু সুরক্ষা লক",
        toggleAppWatch = "নির্দিষ্ট অ্যাপে অটো লক",
        badgeBatteryGuard = "ব্যাটারি বাঁচাও",
        badgeShakeReady = "ঝাঁকিতে লক চালু",
        badgeAppWatchActive = "অ্যাপ লক চালু",
        modeEasyLabel = "সহজ মোড",
        modeProLabel = "প্রো মোড",
        modeEasySubtitle = "শুধু প্রতিদিনের দরকারি ও সহজ অপশন",
        modeProSubtitle = "সব সূক্ষ্ম সেন্সর ও অ্যাডভান্সড কন্ট্রোল",
        dashUnlockProTilesHint = "নির্দিষ্ট অংশ খোলা বা অ্যাপ-ওয়াচ চান? প্রো মোড চালু করুন →",
        proBadgeLabel = "প্রো",
        helpActionDesc = "এটার কাজ কী?",
        helpSectionWhat = "এটা কী কাজ করে?",
        helpSectionExample = "বাস্তব উদাহরণ",
        helpSectionTip = "কারা ব্যবহার করবেন?",
        helpCloseBtn = "বুঝেছি"
    ),
    cfg = UiSettingsStrings(
        holdDurationLabel = "কতক্ষণ চেপে ধরলে খুলবে",
        hudTimeoutLabel = "খোলার বাটন কতক্ষণ পর লুকাবে",
        lockCountdownLabel = "লক হওয়ার আগে অপেক্ষা",
        countdownOff = "সাথে সাথে (0s)",
        pinCodeLabel = "বর্তমান ৪-সংখ্যার পিন",
        btnChangePin = "পিন বদলান",
        pinDialogTitle = "৪-সংখ্যার পিন দিন",
        pinDialogHint = "৪টি সংখ্যা লিখুন (যেমন 1234)",
        btnSave = "সেভ করুন",
        btnCancel = "বাতিল",
        overlayPinHint = "খুলতে ৪-সংখ্যার পিন লিখুন",
        overlayPinWrong = "ভুল পিন! আবার চেষ্টা করুন",
        toggleBiometricUnlock = "আঙুলের ছাপ / ফেস আনলক",
        toggleBiometricUnlockDesc = "পিন বোতামের পাশে দ্রুত আঙুলের ছাপ বাটন দেখাবে",
        overlayBtnBiometric = "আঙুলের ছাপ",
        toggleEmergencyBypass = "পিন ভুলে গেলে জরুরি আনলক (৫ সেকেন্ড)",
        toggleEmergencyBypassDesc = "পিন ভুলে গেলে ৫ সেকেন্ড চেপে ধরে খোলার উপায় রাখবে",
        overlayEmergencyHoldLabel = "পিন ভুলেছেন? ৫s ধরুন",
        customDimLabel = "পর্দা কতটুকু অন্ধকার হবে",
        clearPercentLabel = "পরিষ্কার",
        lockBrightnessLabel = "লক অবস্থায় পর্দার আলো",
        brightnessSystem = "সাধারণ",
        brightnessNight = "রাত ৫%",
        brightnessOutdoor = "রোদ ১০০%",
        toggleBorderEnabled = "চারপাশে রঙিন লক দাগ দেখান",
        toggleBorderDesc = "স্ক্রিনের ধারে হালকা দাগ দেখিয়ে বুঝিয়ে দেয় লক চালু আছে",
        borderColorLabel = "দাগের রঙ",
        borderColorEmerald = "সবুজ",
        borderColorCyan = "নীল",
        borderColorAmber = "সোনালী",
        borderColorRose = "গোলাপি",
        borderThicknessLabel = "দাগের চওড়া",
        borderStyleLabel = "দাগের ধরন",
        borderStyleGlow = "পুরো চারপাশে",
        borderStyleCorners = "চার কোণায়",
        toggleBorderPulse = "মৃদু জ্বলা-নেভা ইফেক্ট",
        toggleBorderPulseDesc = "লক চালু থাকলে রঙিন দাগটি ধীরে ধীরে জ্বলবে-নিভবে",
        badgeModeLabel = "উপরের লক চিহ্ন",
        badgeAlways = "সবসময়",
        badgeOnTouch = "ছুঁলে দেখাবে",
        badgeHidden = "লুকানো",
        toggleOledClock = "অন্ধকার পর্দায় সময় ও চার্জ দেখান",
        toggleOledClockDesc = "পর্দা কালো বা অন্ধকার থাকলে মাঝখানে ঘড়ি ও চার্জ % দেখাবে",
        thicknessThin = "চিকন",
        thicknessMedium = "মাঝারি",
        thicknessBold = "মোটা",
        bubbleOpacityLabel = "বোতাম কতটা স্পষ্ট থাকবে",
        bubbleSizeLabel = "বোতামের আকার",
        bubbleSizeCompact = "ছোট",
        bubbleSizeStandard = "মাঝারি",
        bubbleSizeLarge = "বড়",
        bubbleStyleLabel = "বোতামের রূপ",
        bubbleStyleShield = "শিল্ড",
        bubbleStyleDot = "ছোট বিন্দু",
        bubbleStylePill = "পিল",
        bubblePillLabel = "লক",
        toggleBubbleAutoTuck = "৩ সেকেন্ড পর ধারে সরে লুকাবে",
        toggleBubbleAutoTuckDesc = "কাজ না থাকলে স্ক্রিনের কিনারায় সরে যাবে যাতে লেখা না ঢাকে",
        toggleBubbleDoubleTapHide = "২ বার চাপলে সাময়িক লুকাবে",
        toggleBubbleDoubleTapHideDesc = "ভাসমান বোতামে ২ বার ট্যাপে সরান (নোটিফিকেশন থেকে আনুন)",
        pocketDebounceLabel = "পকেটে ধরার গতি (সময়)",
        toggleOrientationFilter = "টেবিলে রাখা থাকলে ভুল লক আটকাবে",
        toggleOrientationFilterDesc = "টেবিলে চিৎ হয়ে থাকলে হাতের ছায়ায় ভুল করে লক হবে না",
        togglePauseLandscape = "আড়াআড়ি ভিডিও বা গেমে অটো-লক থামাও",
        togglePauseLandscapeDesc = "সিনেমা বা গেমের সময় হাত স্পিকারে লাগলে লক হবে না",
        togglePauseCharging = "চার্জে লাগানো থাকলে পকেট লক বন্ধ",
        togglePauseChargingDesc = "চার্জার লাগানো থাকলে পকেট সেন্সর লক করবে না",
        toggleFlipFaceDownLock = "ফোন উল্টো করে রাখলে অটো-লক",
        toggleFlipFaceDownLockDesc = "স্ক্রিন নিচের দিকে দিয়ে উপুড় করে রাখলেই লক হবে",
        toggleAutoUnlockOnCall = "ফোন কল আসলে নিজেই খুলে যাবে",
        toggleAutoUnlockOnCallDesc = "কল আসলে সাথে সাথে খুলবে যাতে রিসিভ করতে অসুবিধা না হয়",
        toggleReLockAfterCall = "কথা বলা শেষে আবার লক হবে",
        toggleReLockAfterCallDesc = "ফোন কেটে দিলে নিজে থেকেই আবার টাচ লক চালু হবে",
        sessionTimerLabel = "নির্দিষ্ট সময় পর অটো-আনলক",
        timerOff = "বন্ধ",
        toggleHaptics = "হালকা ভাইব্রেশন",
        toggleHapticsDesc = "লক ও আনলক হলে হাতের ছোঁয়ায় হালকা কাঁপবে",
        toggleAutoBoot = "ফোন রিস্টার্টের পর নিজে তৈরি থাকবে",
        toggleAutoBootDesc = "ফোন বন্ধ করে চালু করলে অ্যাপ নিজে থেকেই প্রস্তুত হবে",
        themeModeLabel = "অ্যাপের রঙ (থিম)",
        themeSystem = "ফোনের মতো",
        themeLight = "সাদা (লাইট)",
        themeDark = "কালো (ডার্ক)",
        toggleDynamicColor = "ওয়ালপেপারের সাথে রঙ মেলান",
        toggleDynamicColorDesc = "আপনার ওয়ালপেপারের রঙের সাথে অ্যাপ মানিয়ে নেবে (Android 12+)",
        languageLabel = "ভাষা / Language",
        languageAutoSystem = "অটো (ফোনের ভাষা)",
        btnExportBackup = "সেটিংস সেভ (.json)",
        btnImportBackup = "সেভ থেকে আনুন",
        backupExportSuccess = "সেটিংস ফাইল সফলভাবে সেভ হয়েছে।",
        backupImportSuccess = "সেভ করা ফাইল থেকে সব সেটিংস ফিরে এসেছে!",
        backupError = "ফাইল পড়া বা সেভ করা যায়নি।",
        privacyCardTitle = "১০০% অফলাইন ও সম্পূর্ণ প্রাইভেট",
        privacyBadgeOffline = "ইন্টারনেট ছাড়াই চলে",
        privacyBadgeNoNet = "ইন্টারনেট অনুমতি নেই",
        privacyBadgeZeroTrack = "বিজ্ঞাপন বা ট্র্যাকিং নেই",
        privacyCardDesc = "TouchShield কোনো ইন্টারনেট ব্যবহার করে না। আপনার পিন ও সব তথ্য শুধু আপনার ফোনেই নিরাপদ থাকে।",
        batteryCardTitle = "সুরক্ষা বন্ধ হওয়া আটকাতে টিপস",
        batteryCardDesc = "Xiaomi, Samsung, Oppo বা Vivo ফোনে সুরক্ষা স্থায়ী রাখতে ব্যাটারি সেটিংস থেকে 'Unrestricted' দিন।",
        diagProximity = "পকেট সেন্সর",
        diagAccelerometer = "নড়াচড়া সেন্সর",
        diagOverlay = "অন্য অ্যাপের ওপর থাকা",
        diagNotifications = "নোটিফিকেশন",
        diagPhoneState = "কল বোঝার অনুমতি",
        diagBattery = "ব্যাটারি সেটিং",
        diagAvailable = "চালু আছে",
        diagUnavailable = "নেই",
        diagUnrestricted = "Unrestricted (সেরা)",
        diagOptimized = "সীমিত (Optimized)",
        btnResetStats = "হিসাব মুছুন",
        btnRestoreDefaults = "আগের মতো করুন",
        confirmResetStatsTitle = "সব হিসাব মুছে ফেলবেন?",
        confirmResetStatsMsg = "আটকানো চাপের সংখ্যা এবং ৭ দিনের হিসাব ০ হয়ে যাবে।",
        confirmRestoreTitle = "সব সেটিংস আগের মতো করবেন?",
        confirmRestoreMsg = "সব পরিবর্তন বাদ হয়ে শুরুর সহজ অবস্থায় ফিরে যাবে।",
        btnConfirm = "হ্যাঁ, করুন",
        statusOnLabel = "চালু",
        statusOffLabel = "বন্ধ",
        subheaderKidsSafety = "বাচ্চাদের কড়া লক ও পিন বিরতি",
        toggleKidsSafetyTitle = "শিশু সুরক্ষা লক",
        toggleKidsSafetyDesc = "৫ সেকেন্ডের জরুরি বাটন লুকায় ও ঝাঁকিয়ে খোলা বন্ধ রাখে",
        toggleWrongAttemptCooldown = "ভুল পিনে কিছুক্ষণ বিরতি",
        toggleWrongAttemptCooldownDesc = "৫ বার ভুল পিনে ১৫ সেকেন্ড ও ৮+ বারে ৩০ সেকেন্ড পিন থামবে",
        toggleKidsLockOnTimerExpiry = "সময় শেষ হলে পিন ছাড়া খুলবে না",
        toggleKidsLockOnTimerExpiryDesc = "টাইমার শেষ হলে আনলক না হয়ে প্যারেন্ট পিন লক বসবে",
        overlayPinCooldownFormat = "অনেক ভুল হয়েছে • %ds অপেক্ষা করুন",
        overlayKidsTimeUpTitle = "সময় শেষ! অভিভাবকের পিন দিন",
        subheaderPartialZone = "নির্দিষ্ট অংশ খোলা রেখে বাকিটা লক",
        togglePartialZoneTitle = "শুধু এক অংশে টাচ চালু রাখুন",
        togglePartialZoneDesc = "বেছে নেওয়া অংশে (ভিডিও/ম্যাপ) টাচ চলবে, বাকি পর্দা লক থাকবে",
        partialZonePresetLabel = "খোলা অংশের মাপ",
        partialZoneFull = "পুরো পর্দা লক",
        partialZoneTopHalf = "ওপরের অর্ধেক",
        partialZoneBottomHalf = "নিচের অর্ধেক",
        partialZoneCenter = "মাঝের বক্স",
        partialZoneCustom = "নিজের মাপে",
        partialZoneTopEdge = "উপর",
        partialZoneBottomEdge = "নিচ",
        partialZoneLeftEdge = "বাম",
        partialZoneRightEdge = "ডান",
        partialZoneHint = "রঙিন অংশে টাচ কাজ করবে; কালো অংশের ভুল চাপ আটকে যাবে।",
        overlayPartialZoneBadge = "শুধু এই অংশ খোলা",
        subheaderPrivacyVeil = "পাবলিকে পর্দা কালো রাখা",
        togglePrivacyVeilTitle = "পর্দা কালো করে লুকান (৯৯%)",
        togglePrivacyVeilDesc = "বাসে-ট্রেনে পাশের কেউ যেন না দেখে সেজন্য পর্দা কালো করে (৩s দেখুন বাটনসহ)",
        toggleStealthBlackoutTitle = "একদম বন্ধের মতো কালো পর্দা",
        toggleStealthBlackoutDesc = "চারপাশের দাগ, লেখা ও ঘড়ি লুকিয়ে রাখে—ছুঁলেই খোলার বাটন আসবে",
        overlayBtnQuickPeek = "৩s দেখুন",
        subheaderAppWatch = "নির্দিষ্ট অ্যাপ খুললে অটো সুরক্ষা",
        toggleAppWatchTitle = "পছন্দের অ্যাপে অটো সুরক্ষা",
        toggleAppWatchDesc = "YouTube, Maps বা Camera খুললেই নিজে থেকে বাটন বা লক চালু হবে",
        appWatchActionLabel = "অ্যাপ খুললে কী হবে",
        appWatchActionBubble = "ভাসমান বাটন দেখাও",
        appWatchActionLock = "সাথে সাথে লক করো",
        toggleAppWatchAutoDisarm = "অ্যাপ বন্ধ করলে থেমে যাবে",
        toggleAppWatchAutoDisarmDesc = "ওই অ্যাপ থেকে বের হলেই নিজে থেকে লক বা বাটন সরে যাবে",
        btnSelectWatchedApps = "অ্যাপ বেছে নিন",
        watchedAppsCountFormat = "%dটি বাছা",
        appWatchPickerTitle = "কোন অ্যাপগুলোতে চলবে?",
        appWatchSearchHint = "ফোনের অ্যাপ খুঁজুন...",
        usageAccessDisclosureTitle = "অ্যাপ চেনার অনুমতি (সম্পূর্ণ অফলাইন)",
        usageAccessDisclosureBody = "কোনো জটিল Accessibility ছাড়াই শুধু আপনার বাছা অ্যাপ খোলা হয়েছে কি না বুঝতে আপনার ফোনের ভেতরেই Usage Stats ব্যবহার হয়।",
        btnOpenUsageAccess = "অনুমতি দিন",
        usageAccessMissingHint = "পছন্দের অ্যাপ চিনতে Usage Access অনুমতি চালু করুন।",
        subheaderShortcuts = "ফোন ঝাঁকানো ও ভলিউম বাটন শর্টকাট",
        toggleShakeLock = "২ বার ঝাঁকিয়ে লক করুন",
        toggleShakeLockDesc = "দ্রুত দুইবার ফোন ঝাঁকালেই যেকোনো সময় টাচ লক হবে",
        toggleShakeUnlock = "২ বার ঝাঁকিয়ে খুলুন",
        toggleShakeUnlockDesc = "শিশু সুরক্ষা লক চালু থাকলে এটি নিজে থেকেই বন্ধ থাকে",
        shakeSensitivityLabel = "কতটুকু ঝাঁকাতে হবে",
        shakeLow = "জোরে",
        shakeMedium = "মাঝারি",
        shakeHigh = "হালকা",
        volumeShortcutLabel = "ভলিউম বাটন ২ বার চাপলে",
        volumeShortcutOff = "বন্ধ",
        volumeShortcutToggle = "লক / আনলক",
        volumeShortcutUnlockOnly = "শুধু আনলক",
        subheaderPowerGuard = "চার্জার ও কম ব্যাটারি সুরক্ষা",
        lowBatteryBlackoutLabel = "চার্জ এত % এর কম হলে অটো কালো পর্দা",
        lowBatteryOff = "বন্ধ",
        toggleAutoLockChargerConnect = "চার্জার লাগালে অটো লক",
        toggleAutoLockChargerConnectDesc = "চার্জার লাগালে নিজে থেকেই পর্দা লক হয়ে যাবে",
        toggleUnlockChargerDisconnect = "চার্জার খুললে অটো আনলক",
        toggleUnlockChargerDisconnectDesc = "চার্জার খুলে নিলেই নিজে থেকে লক খুলে যাবে",
        overlayLowBatteryBlackoutBadge = "কম চার্জ সেভার চালু",
        diagUsageAccess = "অ্যাপ চেনার অনুমতি"
    ),
    ovl = UiOverlayGuideStrings(
        unlockHoldTitle = "চেপে ধরে আনলক",
        unlockHoldDesc = "আনলক বাটনে কিছুক্ষণ চেপে ধরুন। পকেটে ভুল করে খোলার ভয় নেই।",
        unlockSlideTitle = "টেনে (স্লাইড) আনলক",
        unlockSlideDesc = "বাটনটি বাম থেকে ডানে টেনে নিয়ে আনলক করুন।",
        unlockDoubleTapTitle = "২ বার ট্যাপে আনলক",
        unlockDoubleTapDesc = "শুধু খোলার বাটনের ওপর পরপর ২ বার দ্রুত ট্যাপ করুন।",
        unlockPinTitle = "৪-সংখ্যার পিন লক",
        unlockPinDesc = "খুলতে ৪ সংখ্যার গোপন পিন বা আঙুলের ছাপ লাগবে।",
        styleClearTitle = "একদম পরিষ্কার (০%)",
        styleClearDesc = "ছবি বা ভিডিও ১০০% স্পষ্ট থাকবে, কোনো কালো ছায়া পড়বে না।",
        styleTintTitle = "হালকা ছায়া (২৫%)",
        styleTintDesc = "হালকা ছায়া যাতে এক নজরে বোঝা যায় পর্দা লক আছে।",
        styleBlackTitle = "গাঢ় কালো (৮৮%)",
        styleBlackDesc = "পর্দা কালো রেখে ব্যাটারি বাঁচাবে, কিন্তু গান বা অ্যাপ চলতে থাকবে।",
        overlayTouchBlockedTitle = "পর্দার টাচ লক আছে",
        overlayHoldHint = "খুলতে 1.8 সেকেন্ড চেপে ধরুন",
        overlaySlideHint = "খুলতে ডানে টানুন",
        overlayDoubleTapHint = "খুলতে ২ বার ট্যাপ করুন",
        overlayCountdownLabel = "লক হচ্ছে",
        overlayCountdownCancel = "বাতিল করতে ট্যাপ করুন",
        sandboxTitle = "বাইরে না গিয়ে নিরাপদ টেস্ট",
        sandboxSubtitle = "কোনো ঝুঁকি ছাড়াই লকের ধরন, আলো আর খোলা এখানেই পরীক্ষা করুন।",
        sandboxDummyPhotoTitle = "নমুনা পর্দা",
        sandboxDummyPhotoDesc = "ধরুন এখানে আপনার ছবি বা ভিডিও খোলা। নিচে লক চালু করে ছুঁয়ে পরীক্ষা করুন!",
        sandboxTapBlockedToast = "ভুল চাপ আটকে দেওয়া হয়েছে!",
        sandboxTryClicking = "এখানে চাপ দিয়ে দেখুন (লক থাকলে কাজ করবে না)",
        sandboxLockTriggerBtn = "টেস্ট পর্দা লক করুন",
        sandboxExitBtn = "বন্ধ করুন",
        notifTitleArmed = "TouchShield প্রস্তুত",
        notifTextArmed = "ভাসমান বাটন বা নিচের 'স্ক্রিন লক'-এ ট্যাপ করুন",
        notifTitleLocked = "পর্দার টাচ লক আছে",
        notifTextLocked = "ভুল চাপ আটকানো হয়েছে • প্রয়োজনে 'আনলক' চাপুন",
        notifActionLock = "স্ক্রিন লক",
        notifActionUnlock = "আনলক",
        notifActionPocketOn = "পকেট: চালু",
        notifActionPocketOff = "পকেট: বন্ধ",
        notifActionShowBubble = "বোতাম দেখান",
        notifActionStop = "বন্ধ",
        onboardingSkip = "বাদ দিন",
        onboardingNext = "পরবর্তী",
        onboardingGetStarted = "শুরু করুন",
        onboardingPage1Title = "স্বচ্ছ পর্দা, ভুল চাপ থেকে সম্পূর্ণ সুরক্ষা",
        onboardingPage1Desc = "ছবি, ম্যাপ বা ভিডিও পরিষ্কার রেখেই পকেটে বা বাচ্চাদের হাতে সব ভুল চাপ, কল বা ডিলিট আটকে দিন।",
        onboardingPage2Title = "বুদ্ধিমান পকেট ও কল সুরক্ষা",
        onboardingPage2Desc = "পকেটে অটো-লক, আড়াআড়ি ভিডিওতে বিরতি, কল এলে অটো-খোলা—সব নিজে থেকেই হবে।",
        onboardingPage3Title = "১০০% অফলাইন ও সম্পূর্ণ প্রাইভেট",
        onboardingPage3Desc = "ইন্টারনেট বা বিজ্ঞাপন ছাড়াই চলে। শুরু করতে পরের ধাপে ওপরের অনুমতিটি দিন।",
        btnReplayOnboarding = "শুরুর গাইড আবার দেখুন",
        guideTitle = "সহজ ব্যবহারবিধি",
        guideStep1Title = "১. ওপরের অনুমতি ও নোটিফিকেশন দিন",
        guideStep1Desc = "হোম পেজ থেকে 'সব অ্যাপের ওপরে থাকার অনুমতি' চালু করুন, যাতে গ্যালারি বা ইউটিউবের ওপরও কাজ করে।",
        guideStep2Title = "২. একটি রেডি মোড বেছে সুরক্ষা চালু করুন",
        guideStep2Desc = "পকেট ফটো বা শিশু/ভিডিও মোড বেছে 'সুরক্ষা চালু করুন' বাটনে ট্যাপ করুন।",
        guideStep3Title = "৩. ৫টি সহজ উপায়ে লক করুন",
        guideStep3Desc = "ভাসমান বোতাম, হোম উইজেট, অ্যাপ আইকন শর্টকাট, কুইক টাইল অথবা পকেট/উল্টো সেন্সর দিয়ে।",
        guideStep4Title = "৪. হোম উইজেট ও কুইক টাইল যোগ করুন",
        guideStep4Desc = "হোম স্ক্রিনে ১×১ উইজেট বা কুইক সেটিংস টাইল যোগ করলে অ্যাপ না খুলেই ১ ট্যাপে লক করা যাবে।",
        guideLimitsTitle = "Android-এর জরুরি নিয়ম",
        guideLimitsDesc = "নিরাপত্তার জন্য Android ফোনে ওপরের নোটিফিকেশন বার এবং পাসওয়ার্ড পপআপ সবসময় সচল থাকে—বাকি পুরো অ্যাপ স্ক্রিন লক থাকে।",
        lockSourceManual = "নিজে লক",
        lockSourceBubble = "ভাসমান বোতাম",
        lockSourceNotification = "নোটিফিকেশন",
        lockSourceTile = "কুইক টাইল",
        lockSourcePocket = "পকেট সেন্সর",
        lockSourceFlip = "উল্টো করা",
        lockSourceWidget = "হোম উইজেট",
        lockSourceAppWatch = "অ্যাপ অটো-লক",
        lockSourceShake = "ঝাঁকানো",
        lockSourceVolume = "ভলিউম বাটন",
        lockSourceCharger = "চার্জার",
        lockSourceKidsTimeout = "শিশু সময় শেষ"
    ),
    helpMap = BengaliHelpMap
)
