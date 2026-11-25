package com.example.ui.screens

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Animation
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.BatteryChargingFull
import androidx.compose.material.icons.rounded.BatterySaver
import androidx.compose.material.icons.rounded.BorderAll
import androidx.compose.material.icons.rounded.BubbleChart
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ChildCare
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.CropFree
import androidx.compose.material.icons.rounded.DeleteSweep
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.NightsStay
import androidx.compose.material.icons.rounded.PhoneCallback
import androidx.compose.material.icons.rounded.PhonelinkLock
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material.icons.rounded.Vibration
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.AppLanguage
import com.example.data.AppWatchAction
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderColorOption
import com.example.data.BorderStyle
import com.example.data.BorderThickness
import com.example.data.BubbleSize
import com.example.data.BubbleStyle
import com.example.data.CustomProfile
import com.example.data.NormalizedRect
import com.example.data.OverlayStyle
import com.example.data.PartialZonePreset
import com.example.data.SettingHelpTopic
import com.example.data.SettingsSection
import com.example.data.ShakeSensitivity
import com.example.data.ShieldRuntimeState
import com.example.data.ShieldSettings
import com.example.data.SmartPreset
import com.example.data.ThemeMode
import com.example.data.UnlockMethod
import com.example.data.VolumeShortcutAction
import com.example.ui.AppStrings
import com.example.ui.PermissionStatus
import com.example.ui.components.ChangePinDialog
import com.example.ui.components.CollapsibleSettingsCard
import com.example.ui.components.CompactTextInputBox
import com.example.ui.components.ConfirmationDialog
import com.example.ui.components.DiagnosticsCard
import com.example.ui.components.EasyProModeBanner
import com.example.ui.components.FeatureHelpDialog
import com.example.ui.components.HelpInfoIconButton
import com.example.ui.components.InlineGestureTesterBox
import com.example.ui.components.LiveMiniPreviewCard
import com.example.ui.components.PartialZoneEditorCard
import com.example.ui.components.PrivacyTrustCard
import com.example.ui.components.ProFeatureBadge
import com.example.ui.components.SaveProfileDialog
import com.example.ui.components.SettingToggleRow
import com.example.ui.components.SettingsSubHeader
import com.example.ui.components.SmartPresetsRow
import com.example.ui.components.TightFilterChip
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    settings: ShieldSettings,
    runtime: ShieldRuntimeState,
    permissions: PermissionStatus,
    strings: AppStrings,
    onAdvancedModeChanged: (Boolean) -> Unit,
    onSelectPreset: (SmartPreset) -> Unit,
    onSaveCustomProfile: (String) -> Unit,
    onApplyCustomProfile: (CustomProfile) -> Unit,
    onRenameCustomProfile: (CustomProfile, String) -> Unit,
    onDeleteCustomProfile: (CustomProfile) -> Unit,
    onUnlockMethodSelected: (UnlockMethod) -> Unit,
    onHoldDurationChanged: (Long) -> Unit,
    onSaveUnlockPin: (String) -> Unit,
    onBiometricUnlockChanged: (Boolean) -> Unit,
    onEmergencyBypassChanged: (Boolean) -> Unit,
    onCountdownSelected: (Int) -> Unit,
    onHudTimeoutSelected: (Long) -> Unit,
    onKidsSafetyChanged: (Boolean) -> Unit,
    onWrongAttemptCooldownChanged: (Boolean) -> Unit,
    onKidsLockOnTimerExpiryChanged: (Boolean) -> Unit,
    onOverlayStyleSelected: (OverlayStyle) -> Unit,
    onCustomDimChanged: (Int) -> Unit,
    onLockBrightnessSelected: (Int) -> Unit,
    onBorderEnabledChanged: (Boolean) -> Unit,
    onBorderColorSelected: (BorderColorOption) -> Unit,
    onBorderThicknessSelected: (BorderThickness) -> Unit,
    onBorderStyleSelected: (BorderStyle) -> Unit,
    onBorderPulseChanged: (Boolean) -> Unit,
    onBadgeModeSelected: (BadgeVisibilityMode) -> Unit,
    onOledClockChanged: (Boolean) -> Unit,
    onPartialZoneEnabledChanged: (Boolean) -> Unit,
    onPartialZonePresetSelected: (PartialZonePreset) -> Unit,
    onPartialZoneRectChanged: (NormalizedRect) -> Unit,
    onPrivacyVeilChanged: (Boolean) -> Unit,
    onStealthBlackoutChanged: (Boolean) -> Unit,
    onFloatingBubbleEnabledChanged: (Boolean) -> Unit,
    onFloatingBubbleOpacityChanged: (Float) -> Unit,
    onBubbleSizeSelected: (BubbleSize) -> Unit,
    onBubbleStyleSelected: (BubbleStyle) -> Unit,
    onBubbleAutoTuckChanged: (Boolean) -> Unit,
    onBubbleDoubleTapHideChanged: (Boolean) -> Unit,
    onPocketAutoLockChanged: (Boolean) -> Unit,
    onPocketAutoUnlockChanged: (Boolean) -> Unit,
    onPocketDebounceChanged: (Long) -> Unit,
    onOrientationFilterChanged: (Boolean) -> Unit,
    onPauseLandscapeChanged: (Boolean) -> Unit,
    onPauseChargingChanged: (Boolean) -> Unit,
    onFlipFaceDownChanged: (Boolean) -> Unit,
    onAutoUnlockOnCallChanged: (Boolean) -> Unit,
    onReLockAfterCallChanged: (Boolean) -> Unit,
    onSessionTimerSelected: (Int) -> Unit,
    onAppWatchEnabledChanged: (Boolean) -> Unit,
    onAppWatchActionSelected: (AppWatchAction) -> Unit,
    onAppWatchAutoDisarmChanged: (Boolean) -> Unit,
    onOpenWatchedAppsPicker: () -> Unit,
    onOpenUsageAccessDisclosure: () -> Unit,
    onShakeToLockChanged: (Boolean) -> Unit,
    onShakeToUnlockChanged: (Boolean) -> Unit,
    onShakeSensitivitySelected: (ShakeSensitivity) -> Unit,
    onVolumeDoublePressSelected: (VolumeShortcutAction) -> Unit,
    onLowBatteryBlackoutSelected: (Int) -> Unit,
    onAutoLockChargerConnectChanged: (Boolean) -> Unit,
    onUnlockChargerDisconnectChanged: (Boolean) -> Unit,
    onHapticsChanged: (Boolean) -> Unit,
    onAutoBootChanged: (Boolean) -> Unit,
    onThemeModeSelected: (ThemeMode) -> Unit,
    onDynamicColorChanged: (Boolean) -> Unit,
    onLanguageSelected: (AppLanguage) -> Unit,
    onExportBackup: () -> Unit,
    onImportBackup: () -> Unit,
    onRequestOverlayPermission: () -> Unit,
    onRequestNotificationPermission: () -> Unit,
    onRequestPhoneStatePermission: () -> Unit,
    onOpenBatterySettings: () -> Unit,
    onResetStats: () -> Unit,
    onRestoreDefaults: () -> Unit,
    focusSection: SettingsSection? = null,
    focusTicket: Int = 0,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showGestureTester by rememberSaveable { mutableStateOf(false) }

    var expandUnlock by rememberSaveable { mutableStateOf(true) }
    var expandOverlay by rememberSaveable { mutableStateOf(false) }
    var expandBubble by rememberSaveable { mutableStateOf(false) }
    var expandPocket by rememberSaveable { mutableStateOf(false) }
    var expandSystem by rememberSaveable { mutableStateOf(false) }
    val listState = rememberLazyListState()

    LaunchedEffect(focusTicket, focusSection) {
        if (focusTicket > 0 && focusSection != null) {
            searchQuery = ""
            when (focusSection) {
                SettingsSection.UNLOCK -> expandUnlock = true
                SettingsSection.OVERLAY -> expandOverlay = true
                SettingsSection.BUBBLE -> expandBubble = true
                SettingsSection.POCKET -> expandPocket = true
                SettingsSection.SYSTEM -> expandSystem = true
            }
            val baseOffset = if (showGestureTester) 5 else 4
            val targetIdx = baseOffset + when (focusSection) {
                SettingsSection.UNLOCK -> 0
                SettingsSection.OVERLAY -> 1
                SettingsSection.BUBBLE -> 2
                SettingsSection.POCKET -> 3
                SettingsSection.SYSTEM -> 4
            }
            listState.animateScrollToItem(targetIdx)
        }
    }

    var showPinDialog by rememberSaveable { mutableStateOf(false) }
    var showCreateProfileDialog by rememberSaveable { mutableStateOf(false) }
    var renameTargetProfile by remember { mutableStateOf<CustomProfile?>(null) }
    var showLanguageDialog by rememberSaveable { mutableStateOf(false) }
    var showResetStatsDialog by rememberSaveable { mutableStateOf(false) }
    var showRestoreDefaultsDialog by rememberSaveable { mutableStateOf(false) }
    var activeHelpTopic by remember { mutableStateOf<SettingHelpTopic?>(null) }

    val q = searchQuery.trim().lowercase()
    val isSearching = q.isNotEmpty()
    val showPro = settings.isAdvancedMode || isSearching

    fun matchesKeywords(vararg terms: String): Boolean {
        if (!isSearching) return true
        return terms.any { it.lowercase().contains(q) }
    }

    val matchUnlock = matchesKeywords(
        strings.settingsUnlockSection, strings.unlockHoldTitle, strings.unlockSlideTitle,
        strings.unlockDoubleTapTitle, strings.unlockPinTitle, strings.pinCodeLabel,
        strings.toggleBiometricUnlock, strings.toggleEmergencyBypass, strings.lockCountdownLabel,
        strings.hudTimeoutLabel, strings.subheaderKidsSafety, strings.toggleKidsSafetyTitle,
        strings.toggleWrongAttemptCooldown, strings.toggleKidsLockOnTimerExpiry,
        "pin", "biometric", "fingerprint", "hold", "slide", "countdown", "bypass", "gesture", "kids", "cooldown",
        "পিন", "বাচ্চা", "শিশু", "আঙুল", "লক", "খোলা", "জরুরি"
    )
    val matchOverlay = matchesKeywords(
        strings.settingsOverlaySection, strings.styleClearTitle, strings.styleTintTitle,
        strings.styleBlackTitle, strings.customDimLabel, strings.lockBrightnessLabel,
        strings.toggleBorderEnabled, strings.borderStyleLabel, strings.toggleBorderPulse,
        strings.badgeModeLabel, strings.toggleOledClock, strings.subheaderPartialZone,
        strings.togglePartialZoneTitle, strings.subheaderPrivacyVeil, strings.togglePrivacyVeilTitle,
        strings.toggleStealthBlackoutTitle,
        "dim", "clear", "amoled", "brightness", "border", "corner", "pulse", "clock", "oled", "badge",
        "partial", "zone", "cutout", "privacy", "veil", "stealth", "peek",
        "কালো", "আলো", "ঘড়ি", "বর্ডার", "পর্দা", "অংশ", "প্রাইভেসি", "আড়াল"
    )
    val matchBubble = matchesKeywords(
        strings.settingsBubbleSection, strings.toggleFloatingBubble, strings.bubbleOpacityLabel,
        strings.bubbleSizeLabel, strings.bubbleStyleLabel, strings.toggleBubbleAutoTuck,
        strings.toggleBubbleDoubleTapHide, "bubble", "opacity", "pill", "dot", "tuck",
        "ভাসমান", "বাটন", "গোল", "বোতাম"
    )
    val matchPocket = matchesKeywords(
        strings.settingsPocketSection, strings.togglePocketAutoLock, strings.togglePocketAutoUnlock,
        strings.pocketDebounceLabel, strings.toggleOrientationFilter, strings.togglePauseLandscape,
        strings.togglePauseCharging, strings.toggleFlipFaceDownLock, strings.toggleAutoUnlockOnCall,
        strings.toggleReLockAfterCall, strings.sessionTimerLabel, strings.subheaderAppWatch,
        strings.toggleAppWatchTitle, strings.subheaderShortcuts, strings.toggleShakeLock,
        strings.volumeShortcutLabel, strings.subheaderPowerGuard, strings.lowBatteryBlackoutLabel,
        strings.toggleAutoLockChargerConnect,
        "pocket", "sensor", "proximity", "landscape", "charging", "flip", "face", "call", "timer",
        "app", "watch", "usage", "shake", "volume", "battery", "charger",
        "পকেট", "সেন্সর", "কল", "ফোন", "ঝাঁকুনি", "ভলিউম", "ব্যাটারি", "চার্জ", "অ্যাপ", "টাইমার", "উল্টে"
    )
    val matchSystem = matchesKeywords(
        strings.settingsSystemSection, strings.toggleHaptics, strings.toggleAutoBoot,
        strings.themeModeLabel, strings.toggleDynamicColor, strings.languageLabel,
        strings.btnExportBackup, strings.btnImportBackup, strings.privacyCardTitle,
        strings.batteryCardTitle, strings.btnResetStats, strings.btnRestoreDefaults, strings.diagUsageAccess,
        "theme", "dark", "light", "dynamic", "language", "backup", "export", "import", "json",
        "privacy", "offline", "diagnostic", "battery", "haptic", "boot", "reset", "usage",
        "ভাষা", "থিম", "ব্যাকআপ", "রিসেট", "ভাইব্রেশন", "অফলাইন", "ব্যাটারি"
    )

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Instant Settings Search + Expand/Collapse Controls
        item {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                CompactTextInputBox(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = strings.settingsSearchHint,
                    leadingIcon = Icons.Rounded.Search,
                    trailingContent = if (searchQuery.isNotEmpty()) {
                        {
                            IconButton(
                                onClick = { searchQuery = "" },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = strings.btnCancel,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        }
                    } else null,
                    modifier = Modifier.testTag("settings_search_field")
                )

                if (!isSearching) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val allExpanded = expandUnlock && expandOverlay && expandBubble && expandPocket && expandSystem
                        TextButton(
                            onClick = {
                                val next = !allExpanded
                                expandUnlock = next
                                expandOverlay = next
                                expandBubble = next
                                expandPocket = next
                                expandSystem = next
                            },
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                            modifier = Modifier
                                .height(28.dp)
                                .testTag("settings_expand_collapse_all")
                        ) {
                            Text(
                                text = if (allExpanded) strings.btnCollapseAll else strings.btnExpandAll,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }

        // Easy/Pro Mode Switch + Live Mini Preview Card + Presets when not searching
        if (!isSearching) {
            item {
                EasyProModeBanner(
                    isAdvancedMode = settings.isAdvancedMode,
                    strings = strings,
                    onModeSelected = onAdvancedModeChanged,
                    onHelpClick = { activeHelpTopic = SettingHelpTopic.MODE_EASY_PRO }
                )
            }

            item {
                LiveMiniPreviewCard(
                    settings = settings,
                    strings = strings,
                    onTestGesture = {
                        showGestureTester = !showGestureTester
                        expandUnlock = true
                    }
                )
            }

            if (showGestureTester) {
                item {
                    InlineGestureTesterBox(
                        settings = settings,
                        strings = strings
                    )
                }
            }

            item {
                SmartPresetsRow(
                    settings = settings,
                    strings = strings,
                    onSelectPreset = onSelectPreset,
                    onSaveCurrentProfile = { showCreateProfileDialog = true },
                    onApplyCustomProfile = onApplyCustomProfile,
                    onRenameCustomProfile = { renameTargetProfile = it },
                    onDeleteCustomProfile = onDeleteCustomProfile
                )
            }
        }

        if (isSearching && !matchUnlock && !matchOverlay && !matchBubble && !matchPocket && !matchSystem) {
            item {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = strings.settingsNoSearchResults,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(9.dp)
                    )
                }
            }
        }

        // 1. Security & Unlock
        if (matchUnlock) {
            item {
                CollapsibleSettingsCard(
                    icon = Icons.Rounded.Lock,
                    title = strings.settingsUnlockSection,
                    summary = strings.unlockMethodTitle(settings.unlockMethod),
                    expanded = if (isSearching) true else expandUnlock,
                    onToggleExpand = { expandUnlock = !expandUnlock },
                    testTag = "section_unlock_security"
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SettingsSubHeader(
                            title = strings.subheaderSecurity,
                            modifier = Modifier.weight(1f)
                        )
                        HelpInfoIconButton(
                            contentDescription = strings.helpActionDesc,
                            onClick = { activeHelpTopic = SettingHelpTopic.UNLOCK_METHOD },
                            modifier = Modifier.testTag("help_unlock_method")
                        )
                    }

                    UnlockMethod.entries.forEachIndexed { index, method ->
                        SelectableOptionRow(
                            title = strings.unlockMethodTitle(method),
                            description = strings.unlockMethodDesc(method),
                            selected = settings.unlockMethod == method,
                            testTag = "unlock_method_${method.storageKey}",
                            onClick = { onUnlockMethodSelected(method) }
                        )
                        if (index < UnlockMethod.entries.lastIndex) {
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        }
                    }

                    AnimatedVisibility(visible = settings.unlockMethod == UnlockMethod.LONG_PRESS) {
                        Column {
                            Spacer(modifier = Modifier.height(6.dp))
                            SliderHeader(
                                label = strings.holdDurationLabel,
                                valueText = "${String.format("%.1f", settings.holdDurationMs / 1000f)}s"
                            )
                            CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 32.dp) {
                                Slider(
                                    value = settings.holdDurationMs.toFloat(),
                                    onValueChange = { onHoldDurationChanged(it.roundToInt().toLong()) },
                                    valueRange = 1000f..3500f,
                                    steps = 4,
                                    modifier = Modifier.testTag("hold_duration_slider")
                                )
                            }
                        }
                    }

                    AnimatedVisibility(visible = settings.unlockMethod == UnlockMethod.PIN) {
                        Column {
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${strings.pinCodeLabel}: ••••",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                OutlinedButton(
                                    onClick = { showPinDialog = true },
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                                    modifier = Modifier
                                        .height(34.dp)
                                        .testTag("change_pin_button")
                                ) {
                                    Text(strings.btnChangePin)
                                }
                            }

                            SettingsSubHeader(strings.subheaderRecovery)
                            SettingToggleRow(
                                icon = Icons.Rounded.Fingerprint,
                                title = strings.toggleBiometricUnlock,
                                subtitle = strings.toggleBiometricUnlockDesc,
                                checked = settings.biometricUnlockEnabled,
                                onCheckedChange = onBiometricUnlockChanged,
                                helpTopic = SettingHelpTopic.BIOMETRIC_UNLOCK,
                                helpContentDesc = strings.helpActionDesc,
                                onHelpClick = { activeHelpTopic = it },
                                testTag = "switch_biometric_unlock"
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                            SettingToggleRow(
                                icon = Icons.Rounded.WarningAmber,
                                title = strings.toggleEmergencyBypass,
                                subtitle = strings.toggleEmergencyBypassDesc,
                                checked = settings.emergencyHoldBypassEnabled,
                                onCheckedChange = onEmergencyBypassChanged,
                                helpTopic = SettingHelpTopic.EMERGENCY_HOLD_BYPASS,
                                helpContentDesc = strings.helpActionDesc,
                                onHelpClick = { activeHelpTopic = it },
                                testTag = "switch_emergency_bypass"
                            )
                        }
                    }

                    if (showPro) {
                        Spacer(modifier = Modifier.height(6.dp))
                        TopicHeaderWithHelp(
                            label = strings.lockCountdownLabel,
                            topic = SettingHelpTopic.LOCK_COUNTDOWN,
                            strings = strings,
                            isPro = true,
                            onHelpClick = { activeHelpTopic = it }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf(0, 3, 5).forEach { sec ->
                                TightFilterChip(
                                    selected = settings.lockCountdownSeconds == sec,
                                    onClick = { onCountdownSelected(sec) },
                                    label = if (sec == 0) strings.countdownOff else "${sec}s",
                                    modifier = Modifier.testTag("countdown_chip_$sec")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        TopicHeaderWithHelp(
                            label = strings.hudTimeoutLabel,
                            topic = SettingHelpTopic.HUD_TIMEOUT,
                            strings = strings,
                            isPro = true,
                            onHelpClick = { activeHelpTopic = it }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf(1500L, 3000L, 5000L).forEach { ms ->
                                TightFilterChip(
                                    selected = settings.hudTimeoutMs == ms,
                                    onClick = { onHudTimeoutSelected(ms) },
                                    label = "${String.format("%.1f", ms / 1000f)}s",
                                    modifier = Modifier.testTag("hud_timeout_chip_$ms")
                                )
                            }
                        }
                    }

                    SettingsSubHeader(strings.subheaderKidsSafety)
                    SettingToggleRow(
                        icon = Icons.Rounded.ChildCare,
                        title = strings.toggleKidsSafetyTitle,
                        subtitle = strings.toggleKidsSafetyDesc,
                        checked = settings.kidsSafetyEnabled,
                        onCheckedChange = onKidsSafetyChanged,
                        helpTopic = SettingHelpTopic.KIDS_SAFETY,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "settings_switch_kids_safety"
                    )
                    if (showPro) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.Security,
                            title = strings.toggleWrongAttemptCooldown,
                            subtitle = strings.toggleWrongAttemptCooldownDesc,
                            checked = settings.wrongAttemptCooldownEnabled,
                            onCheckedChange = onWrongAttemptCooldownChanged,
                            helpTopic = SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_wrong_attempt_cooldown"
                        )
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.Timer,
                            title = strings.toggleKidsLockOnTimerExpiry,
                            subtitle = strings.toggleKidsLockOnTimerExpiryDesc,
                            checked = settings.kidsLockOnTimerExpiry,
                            onCheckedChange = onKidsLockOnTimerExpiryChanged,
                            helpTopic = SettingHelpTopic.KIDS_TIMER_EXPIRY,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_kids_timer_lock"
                        )
                    }
                }
            }
        }

        // 2. Overlay Visuals & Display
        if (matchOverlay) {
            item {
                CollapsibleSettingsCard(
                    icon = Icons.Rounded.Visibility,
                    title = strings.settingsOverlaySection,
                    summary = "${settings.customDimPercent}% Dim • ${strings.borderColorName(settings.borderColor)}",
                    expanded = if (isSearching) true else expandOverlay,
                    onToggleExpand = { expandOverlay = !expandOverlay },
                    testTag = "section_overlay_visuals"
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SettingsSubHeader(
                            title = strings.subheaderBrightness,
                            modifier = Modifier.weight(1f)
                        )
                        HelpInfoIconButton(
                            contentDescription = strings.helpActionDesc,
                            onClick = { activeHelpTopic = SettingHelpTopic.OVERLAY_STYLE },
                            modifier = Modifier.testTag("help_overlay_style")
                        )
                    }

                    OverlayStyle.entries.forEachIndexed { index, style ->
                        SelectableOptionRow(
                            title = strings.overlayStyleTitle(style),
                            description = strings.overlayStyleDesc(style),
                            selected = settings.overlayStyle == style,
                            testTag = "overlay_style_${style.storageKey}",
                            onClick = { onOverlayStyleSelected(style) }
                        )
                        if (index < OverlayStyle.entries.lastIndex) {
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    SliderHeader(
                        label = strings.customDimLabel,
                        valueText = "${settings.customDimPercent}%"
                    )
                    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 32.dp) {
                        Slider(
                            value = settings.customDimPercent.toFloat(),
                            onValueChange = { onCustomDimChanged(it.roundToInt()) },
                            valueRange = 0f..95f,
                            modifier = Modifier.testTag("custom_dim_slider")
                        )
                    }

                    if (showPro) {
                        Spacer(modifier = Modifier.height(6.dp))
                        TopicHeaderWithHelp(
                            label = strings.lockBrightnessLabel,
                            topic = SettingHelpTopic.LOCK_BRIGHTNESS,
                            strings = strings,
                            isPro = true,
                            onHelpClick = { activeHelpTopic = it }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf(
                                -1 to strings.brightnessSystem,
                                5 to strings.brightnessNight,
                                40 to "40%",
                                100 to strings.brightnessOutdoor
                            ).forEach { (value, label) ->
                                TightFilterChip(
                                    selected = settings.lockBrightnessPercent == value,
                                    onClick = { onLockBrightnessSelected(value) },
                                    label = label,
                                    modifier = Modifier.testTag("brightness_chip_$value")
                                )
                            }
                        }

                        SettingsSubHeader(strings.subheaderBorders)

                        SettingToggleRow(
                            icon = Icons.Rounded.BorderAll,
                            title = strings.toggleBorderEnabled,
                            subtitle = strings.toggleBorderDesc,
                            checked = settings.borderEnabled,
                            onCheckedChange = onBorderEnabledChanged,
                            helpTopic = SettingHelpTopic.EDGE_BORDER,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "switch_border_enabled"
                        )

                        AnimatedVisibility(visible = settings.borderEnabled) {
                            Column {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = strings.borderColorLabel,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    BorderColorOption.entries.forEach { option ->
                                        val selected = settings.borderColor == option
                                        Box(
                                            modifier = Modifier
                                                .size(38.dp)
                                                .clip(CircleShape)
                                                .clickable { onBorderColorSelected(option) }
                                                .testTag("border_color_${option.storageKey}"),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(30.dp)
                                                    .clip(CircleShape)
                                                    .background(Color(option.colorArgb))
                                                    .border(
                                                        width = if (selected) 2.5.dp else 1.dp,
                                                        color = if (selected) {
                                                            MaterialTheme.colorScheme.onSurface
                                                        } else {
                                                            Color.Transparent
                                                        },
                                                        shape = CircleShape
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                if (selected) {
                                                    Icon(
                                                        imageVector = Icons.Rounded.Check,
                                                        contentDescription = option.name,
                                                        tint = Color.White,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = strings.borderStyleLabel,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    BorderStyle.entries.forEach { style ->
                                        TightFilterChip(
                                            selected = settings.borderStyle == style,
                                            onClick = { onBorderStyleSelected(style) },
                                            label = strings.borderStyleLabel(style),
                                            modifier = Modifier.testTag("border_style_${style.storageKey}")
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = strings.borderThicknessLabel,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    BorderThickness.entries.forEach { thickness ->
                                        TightFilterChip(
                                            selected = settings.borderThickness == thickness,
                                            onClick = { onBorderThicknessSelected(thickness) },
                                            label = strings.borderThicknessLabel(thickness),
                                            modifier = Modifier.testTag("border_thickness_${thickness.storageKey}")
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))
                                SettingToggleRow(
                                    icon = Icons.Rounded.Animation,
                                    title = strings.toggleBorderPulse,
                                    subtitle = strings.toggleBorderPulseDesc,
                                    checked = settings.borderPulseEnabled,
                                    onCheckedChange = onBorderPulseChanged,
                                    testTag = "switch_border_pulse"
                                )
                            }
                        }

                        SettingsSubHeader(strings.subheaderOledHud)
                        TopicHeaderWithHelp(
                            label = strings.badgeModeLabel,
                            topic = SettingHelpTopic.OLED_CLOCK,
                            strings = strings,
                            isPro = true,
                            onHelpClick = { activeHelpTopic = it }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            BadgeVisibilityMode.entries.forEach { mode ->
                                TightFilterChip(
                                    selected = settings.badgeVisibilityMode == mode,
                                    onClick = { onBadgeModeSelected(mode) },
                                    label = strings.badgeModeLabel(mode),
                                    modifier = Modifier.testTag("badge_mode_${mode.storageKey}")
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        SettingToggleRow(
                            icon = Icons.Rounded.AccessTime,
                            title = strings.toggleOledClock,
                            subtitle = strings.toggleOledClockDesc,
                            checked = settings.oledClockEnabled,
                            onCheckedChange = onOledClockChanged,
                            helpTopic = SettingHelpTopic.OLED_CLOCK,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "switch_oled_clock"
                        )
                    }

                    SettingsSubHeader(strings.subheaderPartialZone)
                    SettingToggleRow(
                        icon = Icons.Rounded.CropFree,
                        title = strings.togglePartialZoneTitle,
                        subtitle = strings.togglePartialZoneDesc,
                        checked = settings.partialZoneEnabled,
                        onCheckedChange = onPartialZoneEnabledChanged,
                        helpTopic = SettingHelpTopic.PARTIAL_TOUCH_ZONE,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "settings_switch_partial_zone"
                    )
                    AnimatedVisibility(visible = settings.partialZoneEnabled) {
                        PartialZoneEditorCard(
                            preset = settings.partialZonePreset,
                            customRect = settings.partialZoneRect,
                            accentColor = Color(settings.borderColor.colorArgb),
                            strings = strings,
                            onPresetSelected = onPartialZonePresetSelected,
                            onCustomRectChanged = onPartialZoneRectChanged,
                            onHelpClick = { activeHelpTopic = SettingHelpTopic.PARTIAL_TOUCH_ZONE },
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                        )
                    }

                    SettingsSubHeader(strings.subheaderPrivacyVeil)
                    SettingToggleRow(
                        icon = Icons.Rounded.NightsStay,
                        title = strings.togglePrivacyVeilTitle,
                        subtitle = strings.togglePrivacyVeilDesc,
                        checked = settings.privacyVeilEnabled,
                        onCheckedChange = onPrivacyVeilChanged,
                        helpTopic = SettingHelpTopic.PRIVACY_VEIL,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "settings_switch_privacy_veil"
                    )
                    if (showPro) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.VisibilityOff,
                            title = strings.toggleStealthBlackoutTitle,
                            subtitle = strings.toggleStealthBlackoutDesc,
                            checked = settings.stealthBlackoutEnabled,
                            onCheckedChange = onStealthBlackoutChanged,
                            helpTopic = SettingHelpTopic.STEALTH_BLACKOUT,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_stealth_blackout"
                        )
                    }
                }
            }
        }

        // 3. Floating Bubble Pro Tuning
        if (matchBubble) {
            item {
                CollapsibleSettingsCard(
                    icon = Icons.Rounded.BubbleChart,
                    title = strings.settingsBubbleSection,
                    summary = if (settings.floatingBubbleEnabled) {
                        "${strings.bubbleSizeLabel(settings.bubbleSize)} • ${(settings.floatingBubbleOpacity * 100).roundToInt()}%"
                    } else {
                        strings.statusOffLabel
                    },
                    expanded = if (isSearching) true else expandBubble,
                    onToggleExpand = { expandBubble = !expandBubble },
                    testTag = "section_floating_bubble"
                ) {
                    SettingToggleRow(
                        icon = Icons.Rounded.BubbleChart,
                        title = strings.toggleFloatingBubble,
                        subtitle = strings.toggleFloatingBubbleDesc,
                        checked = settings.floatingBubbleEnabled,
                        onCheckedChange = onFloatingBubbleEnabledChanged,
                        helpTopic = SettingHelpTopic.FLOATING_BUBBLE,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "settings_switch_bubble"
                    )

                    AnimatedVisibility(visible = settings.floatingBubbleEnabled && showPro) {
                        Column {
                            Spacer(modifier = Modifier.height(4.dp))
                            SliderHeader(
                                label = strings.bubbleOpacityLabel,
                                valueText = "${(settings.floatingBubbleOpacity * 100).roundToInt()}%"
                            )
                            CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 32.dp) {
                                Slider(
                                    value = settings.floatingBubbleOpacity,
                                    onValueChange = onFloatingBubbleOpacityChanged,
                                    valueRange = 0.35f..1.0f,
                                    modifier = Modifier.testTag("bubble_opacity_slider")
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = strings.bubbleSizeLabel,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                BubbleSize.entries.forEach { size ->
                                    TightFilterChip(
                                        selected = settings.bubbleSize == size,
                                        onClick = { onBubbleSizeSelected(size) },
                                        label = strings.bubbleSizeLabel(size),
                                        modifier = Modifier.testTag("bubble_size_${size.storageKey}")
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = strings.bubbleStyleLabel,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                BubbleStyle.entries.forEach { style ->
                                    TightFilterChip(
                                        selected = settings.bubbleStyle == style,
                                        onClick = { onBubbleStyleSelected(style) },
                                        label = strings.bubbleStyleLabel(style),
                                        modifier = Modifier.testTag("bubble_style_${style.storageKey}")
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                            SettingToggleRow(
                                icon = Icons.Rounded.Tune,
                                title = strings.toggleBubbleAutoTuck,
                                subtitle = strings.toggleBubbleAutoTuckDesc,
                                checked = settings.bubbleAutoTuckEnabled,
                                onCheckedChange = onBubbleAutoTuckChanged,
                                helpTopic = SettingHelpTopic.BUBBLE_AUTO_TUCK,
                                isPro = true,
                                proBadgeLabel = strings.proBadgeLabel,
                                helpContentDesc = strings.helpActionDesc,
                                onHelpClick = { activeHelpTopic = it },
                                testTag = "switch_bubble_auto_tuck"
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                            SettingToggleRow(
                                icon = Icons.Rounded.TouchApp,
                                title = strings.toggleBubbleDoubleTapHide,
                                subtitle = strings.toggleBubbleDoubleTapHideDesc,
                                checked = settings.bubbleDoubleTapHideEnabled,
                                onCheckedChange = onBubbleDoubleTapHideChanged,
                                helpTopic = SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE,
                                isPro = true,
                                proBadgeLabel = strings.proBadgeLabel,
                                helpContentDesc = strings.helpActionDesc,
                                onHelpClick = { activeHelpTopic = it },
                                testTag = "switch_bubble_double_tap_hide"
                            )
                        }
                    }
                }
            }
        }

        // 4. Pocket Sensor & Context Automation
        if (matchPocket) {
            item {
                CollapsibleSettingsCard(
                    icon = Icons.Rounded.Sensors,
                    title = strings.settingsPocketSection,
                    summary = "${String.format("%.1f", settings.pocketDebounceMs / 1000f)}s • ${
                        if (settings.pocketAutoLockEnabled) strings.statusOnLabel else strings.statusOffLabel
                    }",
                    expanded = if (isSearching) true else expandPocket,
                    onToggleExpand = { expandPocket = !expandPocket },
                    testTag = "section_pocket_automation"
                ) {
                    SettingsSubHeader(strings.subheaderSensorTuning)
                    SettingToggleRow(
                        icon = Icons.Rounded.PhonelinkLock,
                        title = strings.togglePocketAutoLock,
                        subtitle = strings.togglePocketAutoLockDesc,
                        checked = settings.pocketAutoLockEnabled,
                        onCheckedChange = onPocketAutoLockChanged,
                        helpTopic = SettingHelpTopic.POCKET_AUTO_LOCK,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "settings_switch_pocket_lock"
                    )
                    if (showPro) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.Sensors,
                            title = strings.togglePocketAutoUnlock,
                            subtitle = strings.togglePocketAutoUnlockDesc,
                            checked = settings.pocketAutoUnlockEnabled,
                            onCheckedChange = onPocketAutoUnlockChanged,
                            helpTopic = SettingHelpTopic.POCKET_AUTO_UNLOCK,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_pocket_unlock"
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                        TopicHeaderWithHelp(
                            label = strings.pocketDebounceLabel,
                            topic = SettingHelpTopic.POCKET_SENSITIVITY,
                            strings = strings,
                            isPro = true,
                            trailingText = "${String.format("%.1f", settings.pocketDebounceMs / 1000f)}s",
                            onHelpClick = { activeHelpTopic = it }
                        )
                        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 32.dp) {
                            Slider(
                                value = settings.pocketDebounceMs.toFloat(),
                                onValueChange = { onPocketDebounceChanged(it.roundToInt().toLong()) },
                                valueRange = 400f..2500f,
                                modifier = Modifier.testTag("pocket_debounce_slider")
                            )
                        }
                    }

                    SettingsSubHeader(strings.subheaderContextRules)
                    SettingToggleRow(
                        icon = Icons.Rounded.PhonelinkLock,
                        title = strings.toggleFlipFaceDownLock,
                        subtitle = strings.toggleFlipFaceDownLockDesc,
                        checked = settings.flipFaceDownLockEnabled,
                        onCheckedChange = onFlipFaceDownChanged,
                        helpTopic = SettingHelpTopic.FLIP_FACE_DOWN,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "switch_flip_face_down"
                    )
                    if (showPro) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.Explore,
                            title = strings.toggleOrientationFilter,
                            subtitle = strings.toggleOrientationFilterDesc,
                            checked = settings.pocketOrientationFilterEnabled,
                            onCheckedChange = onOrientationFilterChanged,
                            helpTopic = SettingHelpTopic.ORIENTATION_FILTER,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "switch_orientation_filter"
                        )
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.ScreenRotation,
                            title = strings.togglePauseLandscape,
                            subtitle = strings.togglePauseLandscapeDesc,
                            checked = settings.pausePocketInLandscape,
                            onCheckedChange = onPauseLandscapeChanged,
                            helpTopic = SettingHelpTopic.PAUSE_LANDSCAPE,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "switch_pause_landscape"
                        )
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.BatteryChargingFull,
                            title = strings.togglePauseCharging,
                            subtitle = strings.togglePauseChargingDesc,
                            checked = settings.pausePocketWhileCharging,
                            onCheckedChange = onPauseChargingChanged,
                            helpTopic = SettingHelpTopic.PAUSE_CHARGING,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "switch_pause_charging"
                        )
                    }

                    SettingsSubHeader(strings.subheaderCallTimer)
                    SettingToggleRow(
                        icon = Icons.Rounded.Call,
                        title = strings.toggleAutoUnlockOnCall,
                        subtitle = strings.toggleAutoUnlockOnCallDesc,
                        checked = settings.autoUnlockOnCallEnabled,
                        onCheckedChange = { wantEnable ->
                            if (wantEnable && !permissions.hasPhoneStatePermission) {
                                onRequestPhoneStatePermission()
                            } else {
                                onAutoUnlockOnCallChanged(wantEnable)
                            }
                        },
                        helpTopic = SettingHelpTopic.CALL_AUTO_UNLOCK,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "switch_auto_unlock_call"
                    )

                    AnimatedVisibility(visible = settings.autoUnlockOnCallEnabled) {
                        Column {
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                            SettingToggleRow(
                                icon = Icons.Rounded.PhoneCallback,
                                title = strings.toggleReLockAfterCall,
                                subtitle = strings.toggleReLockAfterCallDesc,
                                checked = settings.reLockAfterCallEnabled,
                                onCheckedChange = onReLockAfterCallChanged,
                                helpTopic = SettingHelpTopic.CALL_AUTO_UNLOCK,
                                helpContentDesc = strings.helpActionDesc,
                                onHelpClick = { activeHelpTopic = it },
                                testTag = "switch_relock_after_call"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    TopicHeaderWithHelp(
                        label = strings.sessionTimerLabel,
                        topic = SettingHelpTopic.SESSION_TIMER,
                        strings = strings,
                        onHelpClick = { activeHelpTopic = it }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        listOf(0, 15, 30, 60).forEach { mins ->
                            TightFilterChip(
                                selected = settings.sessionTimerMinutes == mins,
                                onClick = { onSessionTimerSelected(mins) },
                                label = if (mins == 0) strings.timerOff else "${mins}m",
                                modifier = Modifier.testTag("session_timer_$mins")
                            )
                        }
                    }

                    if (showPro) {
                        SettingsSubHeader(strings.subheaderAppWatch)
                        SettingToggleRow(
                            icon = Icons.Rounded.Apps,
                            title = strings.toggleAppWatchTitle,
                            subtitle = strings.toggleAppWatchDesc,
                            checked = settings.appWatchEnabled,
                            onCheckedChange = { wantEnable ->
                                onAppWatchEnabledChanged(wantEnable)
                                if (wantEnable && !permissions.hasUsageAccessPermission) {
                                    onOpenUsageAccessDisclosure()
                                }
                            },
                            helpTopic = SettingHelpTopic.APP_WATCH,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_app_watch"
                        )
                        AnimatedVisibility(visible = settings.appWatchEnabled) {
                            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                if (!permissions.hasUsageAccessPermission) {
                                    Surface(
                                        shape = MaterialTheme.shapes.small,
                                        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(MaterialTheme.shapes.small)
                                            .clickable { onOpenUsageAccessDisclosure() }
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = strings.usageAccessMissingHint,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                                modifier = Modifier.weight(1f)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = strings.btnOpenUsageAccess,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                }

                                Text(
                                    text = strings.appWatchActionLabel,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    AppWatchAction.entries.forEach { action ->
                                        TightFilterChip(
                                            selected = settings.appWatchAction == action,
                                            onClick = { onAppWatchActionSelected(action) },
                                            label = strings.appWatchActionLabel(action),
                                            modifier = Modifier.testTag("app_watch_action_${action.storageKey}")
                                        )
                                    }
                                }

                                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                                SettingToggleRow(
                                    icon = Icons.Rounded.RestartAlt,
                                    title = strings.toggleAppWatchAutoDisarm,
                                    subtitle = strings.toggleAppWatchAutoDisarmDesc,
                                    checked = settings.appWatchAutoDisarmOnExit,
                                    onCheckedChange = onAppWatchAutoDisarmChanged,
                                    testTag = "settings_switch_app_watch_disarm"
                                )

                                OutlinedButton(
                                    onClick = onOpenWatchedAppsPicker,
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(34.dp)
                                        .testTag("choose_watched_apps_button"),
                                    shape = MaterialTheme.shapes.small
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Apps,
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = "${strings.btnSelectWatchedApps} • ${strings.watchedAppsSummary(settings.watchedAppPackages.size)}",
                                        style = MaterialTheme.typography.labelMedium,
                                        maxLines = 1
                                    )
                                }
                            }
                        }

                        SettingsSubHeader(strings.subheaderShortcuts)
                        SettingToggleRow(
                            icon = Icons.Rounded.Vibration,
                            title = strings.toggleShakeLock,
                            subtitle = strings.toggleShakeLockDesc,
                            checked = settings.shakeToLockEnabled,
                            onCheckedChange = onShakeToLockChanged,
                            helpTopic = SettingHelpTopic.SHAKE_SHORTCUT,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_shake_lock"
                        )
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.Sensors,
                            title = strings.toggleShakeUnlock,
                            subtitle = strings.toggleShakeUnlockDesc,
                            checked = settings.shakeToUnlockEnabled,
                            onCheckedChange = onShakeToUnlockChanged,
                            helpTopic = SettingHelpTopic.SHAKE_SHORTCUT,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_shake_unlock"
                        )
                        AnimatedVisibility(visible = settings.shakeToLockEnabled || settings.shakeToUnlockEnabled) {
                            Column {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = strings.shakeSensitivityLabel,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    ShakeSensitivity.entries.forEach { sens ->
                                        TightFilterChip(
                                            selected = settings.shakeSensitivity == sens,
                                            onClick = { onShakeSensitivitySelected(sens) },
                                            label = strings.shakeSensitivityLabel(sens),
                                            modifier = Modifier.testTag("shake_sens_${sens.storageKey}")
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.VolumeUp,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = strings.volumeShortcutLabel,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f, fill = false)
                                )
                                ProFeatureBadge(label = strings.proBadgeLabel)
                            }
                            HelpInfoIconButton(
                                contentDescription = strings.helpActionDesc,
                                onClick = { activeHelpTopic = SettingHelpTopic.VOLUME_SHORTCUT },
                                modifier = Modifier.testTag("help_volume_shortcut")
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            VolumeShortcutAction.entries.forEach { volAction ->
                                TightFilterChip(
                                    selected = settings.volumeDoublePressAction == volAction,
                                    onClick = { onVolumeDoublePressSelected(volAction) },
                                    label = strings.volumeShortcutLabel(volAction),
                                    modifier = Modifier.testTag("volume_shortcut_${volAction.storageKey}")
                                )
                            }
                        }

                        SettingsSubHeader(strings.subheaderPowerGuard)
                        TopicHeaderWithHelp(
                            label = strings.lowBatteryBlackoutLabel,
                            topic = SettingHelpTopic.LOW_BATTERY_BLACKOUT,
                            strings = strings,
                            isPro = true,
                            onHelpClick = { activeHelpTopic = it }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf(0, 10, 15, 20).forEach { pct ->
                                TightFilterChip(
                                    selected = settings.lowBatteryBlackoutPercent == pct,
                                    onClick = { onLowBatteryBlackoutSelected(pct) },
                                    label = if (pct == 0) strings.lowBatteryOff else "$pct%",
                                    modifier = Modifier.testTag("low_battery_blackout_$pct")
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.BatteryChargingFull,
                            title = strings.toggleAutoLockChargerConnect,
                            subtitle = strings.toggleAutoLockChargerConnectDesc,
                            checked = settings.autoLockOnChargerConnect,
                            onCheckedChange = onAutoLockChargerConnectChanged,
                            helpTopic = SettingHelpTopic.CHARGER_AUTO_RULE,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_lock_charger_connect"
                        )
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.BatterySaver,
                            title = strings.toggleUnlockChargerDisconnect,
                            subtitle = strings.toggleUnlockChargerDisconnectDesc,
                            checked = settings.unlockOnChargerDisconnect,
                            onCheckedChange = onUnlockChargerDisconnectChanged,
                            helpTopic = SettingHelpTopic.CHARGER_AUTO_RULE,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "settings_switch_unlock_charger_disconnect"
                        )
                    }
                }
            }
        }

        // 5. System, Backup & Diagnostics
        if (matchSystem) {
            item {
                CollapsibleSettingsCard(
                    icon = Icons.Rounded.Tune,
                    title = strings.settingsSystemSection,
                    summary = "${strings.themeModeLabel(settings.themeMode)} • ${strings.languageDisplayName(settings.appLanguage)}",
                    expanded = if (isSearching) true else expandSystem,
                    onToggleExpand = { expandSystem = !expandSystem },
                    testTag = "section_system_controls"
                ) {
                    SettingToggleRow(
                        icon = Icons.Rounded.Vibration,
                        title = strings.toggleHaptics,
                        subtitle = strings.toggleHapticsDesc,
                        checked = settings.hapticFeedbackEnabled,
                        onCheckedChange = onHapticsChanged,
                        helpTopic = SettingHelpTopic.HAPTICS_AND_BOOT,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = { activeHelpTopic = it },
                        testTag = "switch_haptics"
                    )
                    if (showPro) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        SettingToggleRow(
                            icon = Icons.Rounded.RestartAlt,
                            title = strings.toggleAutoBoot,
                            subtitle = strings.toggleAutoBootDesc,
                            checked = settings.autoStartOnBootEnabled,
                            onCheckedChange = onAutoBootChanged,
                            helpTopic = SettingHelpTopic.HAPTICS_AND_BOOT,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            helpContentDesc = strings.helpActionDesc,
                            onHelpClick = { activeHelpTopic = it },
                            testTag = "switch_auto_boot"
                        )
                    }

                    SettingsSubHeader(strings.subheaderAppearance)
                    Text(
                        text = strings.themeModeLabel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        ThemeMode.entries.forEach { mode ->
                            TightFilterChip(
                                selected = settings.themeMode == mode,
                                onClick = { onThemeModeSelected(mode) },
                                label = strings.themeModeLabel(mode),
                                modifier = Modifier.testTag("theme_mode_${mode.storageKey}")
                            )
                        }
                    }

                    if (showPro && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        Spacer(modifier = Modifier.height(4.dp))
                        SettingToggleRow(
                            icon = Icons.Rounded.ColorLens,
                            title = strings.toggleDynamicColor,
                            subtitle = strings.toggleDynamicColorDesc,
                            checked = settings.dynamicColorEnabled,
                            onCheckedChange = onDynamicColorChanged,
                            isPro = true,
                            proBadgeLabel = strings.proBadgeLabel,
                            testTag = "switch_dynamic_color"
                        )
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small)
                            .clickable { showLanguageDialog = true }
                            .padding(horizontal = 2.dp, vertical = 6.dp)
                            .testTag("settings_language_row"),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Translate,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = strings.languageLabel,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = strings.languageDisplayName(settings.appLanguage),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                            )
                        }
                    }

                    if (showPro) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            SettingsSubHeader(
                                title = strings.subheaderBackupReset,
                                modifier = Modifier.weight(1f)
                            )
                            HelpInfoIconButton(
                                contentDescription = strings.helpActionDesc,
                                onClick = { activeHelpTopic = SettingHelpTopic.BACKUP_RESTORE },
                                modifier = Modifier.testTag("help_backup_restore")
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            OutlinedButton(
                                onClick = onExportBackup,
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(36.dp)
                                    .testTag("export_backup_button"),
                                shape = MaterialTheme.shapes.small
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Upload,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(strings.btnExportBackup, maxLines = 1)
                            }

                            OutlinedButton(
                                onClick = onImportBackup,
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(36.dp)
                                    .testTag("import_backup_button"),
                                shape = MaterialTheme.shapes.small
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Download,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(strings.btnImportBackup, maxLines = 1)
                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            OutlinedButton(
                                onClick = { showResetStatsDialog = true },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(36.dp)
                                    .testTag("reset_stats_button"),
                                shape = MaterialTheme.shapes.small
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.DeleteSweep,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(strings.btnResetStats, maxLines = 1)
                            }

                            TextButton(
                                onClick = { showRestoreDefaultsDialog = true },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(36.dp)
                                    .testTag("restore_defaults_button")
                            ) {
                                Text(strings.btnRestoreDefaults, maxLines = 1)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    PrivacyTrustCard(strings = strings)

                    SettingsSubHeader(strings.subheaderDiagnostics)
                    DiagnosticsCard(
                        runtime = runtime,
                        permissions = permissions,
                        strings = strings,
                        onRequestOverlay = onRequestOverlayPermission,
                        onRequestNotifications = onRequestNotificationPermission,
                        onRequestPhoneState = onRequestPhoneStatePermission,
                        onOpenBatterySettings = onOpenBatterySettings,
                        onOpenUsageAccess = onOpenUsageAccessDisclosure
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Card(
                        shape = MaterialTheme.shapes.small,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.BatterySaver,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = strings.batteryCardTitle,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = strings.batteryCardDesc,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedButton(
                                onClick = onOpenBatterySettings,
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(36.dp)
                                    .testTag("open_battery_settings_button")
                            ) {
                                Text(strings.btnBatterySettings)
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
        }
    }

    if (showPinDialog) {
        ChangePinDialog(
            currentPin = settings.unlockPin,
            strings = strings,
            onSavePin = onSaveUnlockPin,
            onDismiss = { showPinDialog = false }
        )
    }

    if (showCreateProfileDialog) {
        SaveProfileDialog(
            title = strings.saveProfileDialogTitle,
            hint = strings.saveProfileHint,
            strings = strings,
            onSave = onSaveCustomProfile,
            onDismiss = { showCreateProfileDialog = false }
        )
    }

    renameTargetProfile?.let { target ->
        SaveProfileDialog(
            title = strings.renameProfileDialogTitle,
            hint = strings.saveProfileHint,
            initialName = target.name,
            strings = strings,
            onSave = { newName -> onRenameCustomProfile(target, newName) },
            onDismiss = { renameTargetProfile = null }
        )
    }

    if (showLanguageDialog) {
        LanguagePickerDialog(
            currentLanguage = settings.appLanguage,
            strings = strings,
            onSelectLanguage = onLanguageSelected,
            onDismiss = { showLanguageDialog = false }
        )
    }

    if (showResetStatsDialog) {
        ConfirmationDialog(
            title = strings.confirmResetStatsTitle,
            message = strings.confirmResetStatsMsg,
            confirmLabel = strings.btnConfirm,
            cancelLabel = strings.btnCancel,
            onConfirm = onResetStats,
            onDismiss = { showResetStatsDialog = false }
        )
    }

    if (showRestoreDefaultsDialog) {
        ConfirmationDialog(
            title = strings.confirmRestoreTitle,
            message = strings.confirmRestoreMsg,
            confirmLabel = strings.btnConfirm,
            cancelLabel = strings.btnCancel,
            onConfirm = onRestoreDefaults,
            onDismiss = { showRestoreDefaultsDialog = false }
        )
    }

    activeHelpTopic?.let { topic ->
        FeatureHelpDialog(
            help = strings.helpContent(topic),
            strings = strings,
            onDismiss = { activeHelpTopic = null }
        )
    }
}

@Composable
fun LanguagePickerDialog(
    currentLanguage: AppLanguage,
    strings: AppStrings,
    onSelectLanguage: (AppLanguage) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = strings.languageLabel,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column {
                AppLanguage.entries.forEach { lang ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small)
                            .clickable {
                                onSelectLanguage(lang)
                                onDismiss()
                            }
                            .padding(vertical = 3.dp, horizontal = 4.dp)
                            .testTag("lang_option_${lang.storageKey}"),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 32.dp) {
                            RadioButton(
                                selected = currentLanguage == lang,
                                onClick = {
                                    onSelectLanguage(lang)
                                    onDismiss()
                                }
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = strings.languageDisplayName(lang),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                modifier = Modifier.height(34.dp)
            ) {
                Text(strings.btnCancel)
            }
        }
    )
}

@Composable
private fun TopicHeaderWithHelp(
    label: String,
    topic: SettingHelpTopic,
    strings: AppStrings,
    isPro: Boolean = false,
    trailingText: String? = null,
    onHelpClick: (SettingHelpTopic) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f, fill = false)
            )
            if (isPro) {
                ProFeatureBadge(label = strings.proBadgeLabel)
            }
            HelpInfoIconButton(
                contentDescription = strings.helpActionDesc,
                onClick = { onHelpClick(topic) },
                iconSize = 14.dp,
                modifier = Modifier.testTag("help_${topic.name.lowercase()}")
            )
        }
        if (trailingText != null) {
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = trailingText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun SliderHeader(
    label: String,
    valueText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = valueText,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun SelectableOptionRow(
    title: String,
    description: String,
    selected: Boolean,
    testTag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() }
            .padding(vertical = 3.dp)
            .testTag(testTag),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 32.dp) {
            RadioButton(
                selected = selected,
                onClick = onClick
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
