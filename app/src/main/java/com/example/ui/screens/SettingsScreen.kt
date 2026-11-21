package com.example.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BatterySaver
import androidx.compose.material.icons.rounded.BorderAll
import androidx.compose.material.icons.rounded.BubbleChart
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.DeleteSweep
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.HealthAndSafety
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.PhonelinkLock
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.Vibration
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.AppLanguage
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderColorOption
import com.example.data.BorderThickness
import com.example.data.BubbleSize
import com.example.data.BubbleStyle
import com.example.data.OverlayStyle
import com.example.data.ShieldRuntimeState
import com.example.data.ShieldSettings
import com.example.data.SmartPreset
import com.example.data.UnlockMethod
import com.example.ui.AppStrings
import com.example.ui.PermissionStatus
import com.example.ui.components.ChangePinDialog
import com.example.ui.components.CollapsibleSettingsCard
import com.example.ui.components.ConfirmationDialog
import com.example.ui.components.DiagnosticsCard
import com.example.ui.components.LiveMiniPreviewCard
import com.example.ui.components.SettingToggleRow
import com.example.ui.components.SmartPresetsRow
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    settings: ShieldSettings,
    runtime: ShieldRuntimeState,
    permissions: PermissionStatus,
    strings: AppStrings,
    onSelectPreset: (SmartPreset) -> Unit,
    onUnlockMethodSelected: (UnlockMethod) -> Unit,
    onHoldDurationChanged: (Long) -> Unit,
    onSaveUnlockPin: (String) -> Unit,
    onCountdownSelected: (Int) -> Unit,
    onHudTimeoutSelected: (Long) -> Unit,
    onOverlayStyleSelected: (OverlayStyle) -> Unit,
    onCustomDimChanged: (Int) -> Unit,
    onBorderEnabledChanged: (Boolean) -> Unit,
    onBorderColorSelected: (BorderColorOption) -> Unit,
    onBorderThicknessSelected: (BorderThickness) -> Unit,
    onBadgeModeSelected: (BadgeVisibilityMode) -> Unit,
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
    onAutoUnlockOnCallChanged: (Boolean) -> Unit,
    onSessionTimerSelected: (Int) -> Unit,
    onHapticsChanged: (Boolean) -> Unit,
    onAutoBootChanged: (Boolean) -> Unit,
    onDarkThemeChanged: (Boolean) -> Unit,
    onToggleLanguage: () -> Unit,
    onRequestOverlayPermission: () -> Unit,
    onRequestNotificationPermission: () -> Unit,
    onRequestPhoneStatePermission: () -> Unit,
    onOpenBatterySettings: () -> Unit,
    onResetStats: () -> Unit,
    onRestoreDefaults: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expandUnlock by rememberSaveable { mutableStateOf(true) }
    var expandOverlay by rememberSaveable { mutableStateOf(true) }
    var expandBubble by rememberSaveable { mutableStateOf(false) }
    var expandPocket by rememberSaveable { mutableStateOf(false) }
    var expandSystem by rememberSaveable { mutableStateOf(false) }
    var expandDiagnostics by rememberSaveable { mutableStateOf(false) }

    var showPinDialog by rememberSaveable { mutableStateOf(false) }
    var showResetStatsDialog by rememberSaveable { mutableStateOf(false) }
    var showRestoreDefaultsDialog by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 1. Live Mini Preview Window
        item {
            LiveMiniPreviewCard(settings = settings, strings = strings)
        }

        // 2. Smart One-Tap Presets
        item {
            SmartPresetsRow(
                settings = settings,
                strings = strings,
                onSelectPreset = onSelectPreset
            )
        }

        // 3. Unlock & Security Settings
        item {
            CollapsibleSettingsCard(
                icon = Icons.Rounded.Lock,
                title = strings.settingsUnlockSection,
                summary = strings.unlockMethodTitle(settings.unlockMethod),
                expanded = expandUnlock,
                onToggleExpand = { expandUnlock = !expandUnlock },
                testTag = "section_unlock_security"
            ) {
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

                if (settings.unlockMethod == UnlockMethod.LONG_PRESS) {
                    Spacer(modifier = Modifier.height(10.dp))
                    SliderHeader(
                        label = strings.holdDurationLabel,
                        valueText = "${String.format("%.1f", settings.holdDurationMs / 1000f)}s"
                    )
                    Slider(
                        value = settings.holdDurationMs.toFloat(),
                        onValueChange = { onHoldDurationChanged(it.roundToInt().toLong()) },
                        valueRange = 1000f..3500f,
                        steps = 4,
                        modifier = Modifier.testTag("hold_duration_slider")
                    )
                }

                if (settings.unlockMethod == UnlockMethod.PIN) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${strings.pinCodeLabel}: •••• (${settings.unlockPin})",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        OutlinedButton(
                            onClick = { showPinDialog = true },
                            modifier = Modifier.testTag("change_pin_button")
                        ) {
                            Text(strings.btnChangePin)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = strings.lockCountdownLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(0, 3, 5).forEach { sec ->
                        FilterChip(
                            selected = settings.lockCountdownSeconds == sec,
                            onClick = { onCountdownSelected(sec) },
                            label = {
                                Text(if (sec == 0) strings.countdownOff else "${sec}s")
                            },
                            modifier = Modifier.testTag("countdown_chip_$sec")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = strings.hudTimeoutLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(1500L, 3000L, 5000L).forEach { ms ->
                        FilterChip(
                            selected = settings.hudTimeoutMs == ms,
                            onClick = { onHudTimeoutSelected(ms) },
                            label = { Text("${String.format("%.1f", ms / 1000f)}s") },
                            modifier = Modifier.testTag("hud_timeout_chip_$ms")
                        )
                    }
                }
            }
        }

        // 4. Overlay & Visual Customization
        item {
            CollapsibleSettingsCard(
                icon = Icons.Rounded.Visibility,
                title = strings.settingsOverlaySection,
                summary = "${settings.customDimPercent}% Dim • ${strings.borderColorName(settings.borderColor)}",
                expanded = expandOverlay,
                onToggleExpand = { expandOverlay = !expandOverlay },
                testTag = "section_overlay_visuals"
            ) {
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

                Spacer(modifier = Modifier.height(12.dp))
                SliderHeader(
                    label = strings.customDimLabel,
                    valueText = "${settings.customDimPercent}%"
                )
                Slider(
                    value = settings.customDimPercent.toFloat(),
                    onValueChange = { onCustomDimChanged(it.roundToInt()) },
                    valueRange = 0f..95f,
                    modifier = Modifier.testTag("custom_dim_slider")
                )

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                SettingToggleRow(
                    icon = Icons.Rounded.BorderAll,
                    title = strings.toggleBorderEnabled,
                    subtitle = strings.toggleBorderDesc,
                    checked = settings.borderEnabled,
                    onCheckedChange = onBorderEnabledChanged,
                    testTag = "switch_border_enabled"
                )

                if (settings.borderEnabled) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = strings.borderColorLabel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        BorderColorOption.entries.forEach { option ->
                            val selected = settings.borderColor == option
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .clickable { onBorderColorSelected(option) }
                                    .testTag("border_color_${option.storageKey}"),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(option.colorArgb))
                                        .border(
                                            width = if (selected) 3.dp else 1.dp,
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
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = strings.borderThicknessLabel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        BorderThickness.entries.forEach { thickness ->
                            FilterChip(
                                selected = settings.borderThickness == thickness,
                                onClick = { onBorderThicknessSelected(thickness) },
                                label = { Text(strings.borderThicknessLabel(thickness)) },
                                modifier = Modifier.testTag("border_thickness_${thickness.storageKey}")
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = strings.badgeModeLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    BadgeVisibilityMode.entries.forEach { mode ->
                        FilterChip(
                            selected = settings.badgeVisibilityMode == mode,
                            onClick = { onBadgeModeSelected(mode) },
                            label = { Text(strings.badgeModeLabel(mode)) },
                            modifier = Modifier.testTag("badge_mode_${mode.storageKey}")
                        )
                    }
                }
            }
        }

        // 5. Floating Bubble Pro Tuning
        item {
            CollapsibleSettingsCard(
                icon = Icons.Rounded.BubbleChart,
                title = strings.settingsBubbleSection,
                summary = "${strings.bubbleSizeLabel(settings.bubbleSize)} • ${(settings.floatingBubbleOpacity * 100).roundToInt()}%",
                expanded = expandBubble,
                onToggleExpand = { expandBubble = !expandBubble },
                testTag = "section_floating_bubble"
            ) {
                SettingToggleRow(
                    icon = Icons.Rounded.BubbleChart,
                    title = strings.toggleFloatingBubble,
                    subtitle = strings.toggleFloatingBubbleDesc,
                    checked = settings.floatingBubbleEnabled,
                    onCheckedChange = onFloatingBubbleEnabledChanged,
                    testTag = "settings_switch_bubble"
                )
                Spacer(modifier = Modifier.height(6.dp))
                SliderHeader(
                    label = strings.bubbleOpacityLabel,
                    valueText = "${(settings.floatingBubbleOpacity * 100).roundToInt()}%"
                )
                Slider(
                    value = settings.floatingBubbleOpacity,
                    onValueChange = onFloatingBubbleOpacityChanged,
                    valueRange = 0.35f..1.0f,
                    enabled = settings.floatingBubbleEnabled,
                    modifier = Modifier.testTag("bubble_opacity_slider")
                )

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = strings.bubbleSizeLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    BubbleSize.entries.forEach { size ->
                        FilterChip(
                            selected = settings.bubbleSize == size,
                            onClick = { onBubbleSizeSelected(size) },
                            enabled = settings.floatingBubbleEnabled,
                            label = { Text(strings.bubbleSizeLabel(size)) },
                            modifier = Modifier.testTag("bubble_size_${size.storageKey}")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = strings.bubbleStyleLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    BubbleStyle.entries.forEach { style ->
                        FilterChip(
                            selected = settings.bubbleStyle == style,
                            onClick = { onBubbleStyleSelected(style) },
                            enabled = settings.floatingBubbleEnabled,
                            label = { Text(strings.bubbleStyleLabel(style)) },
                            modifier = Modifier.testTag("bubble_style_${style.storageKey}")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                SettingToggleRow(
                    icon = Icons.Rounded.Tune,
                    title = strings.toggleBubbleAutoTuck,
                    subtitle = strings.toggleBubbleAutoTuckDesc,
                    checked = settings.bubbleAutoTuckEnabled,
                    onCheckedChange = onBubbleAutoTuckChanged,
                    testTag = "switch_bubble_auto_tuck"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                SettingToggleRow(
                    icon = Icons.Rounded.TouchApp,
                    title = strings.toggleBubbleDoubleTapHide,
                    subtitle = strings.toggleBubbleDoubleTapHideDesc,
                    checked = settings.bubbleDoubleTapHideEnabled,
                    onCheckedChange = onBubbleDoubleTapHideChanged,
                    testTag = "switch_bubble_double_tap_hide"
                )
            }
        }

        // 6. Pocket Sensor & Smart Automation
        item {
            CollapsibleSettingsCard(
                icon = Icons.Rounded.Sensors,
                title = strings.settingsPocketSection,
                summary = "${String.format("%.1f", settings.pocketDebounceMs / 1000f)}s • ${if (settings.pocketAutoLockEnabled) "ON" else "OFF"}",
                expanded = expandPocket,
                onToggleExpand = { expandPocket = !expandPocket },
                testTag = "section_pocket_automation"
            ) {
                SettingToggleRow(
                    icon = Icons.Rounded.PhonelinkLock,
                    title = strings.togglePocketAutoLock,
                    subtitle = strings.togglePocketAutoLockDesc,
                    checked = settings.pocketAutoLockEnabled,
                    onCheckedChange = onPocketAutoLockChanged,
                    testTag = "settings_switch_pocket_lock"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                SettingToggleRow(
                    icon = Icons.Rounded.Sensors,
                    title = strings.togglePocketAutoUnlock,
                    subtitle = strings.togglePocketAutoUnlockDesc,
                    checked = settings.pocketAutoUnlockEnabled,
                    onCheckedChange = onPocketAutoUnlockChanged,
                    testTag = "settings_switch_pocket_unlock"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                SettingToggleRow(
                    icon = Icons.Rounded.Explore,
                    title = strings.toggleOrientationFilter,
                    subtitle = strings.toggleOrientationFilterDesc,
                    checked = settings.pocketOrientationFilterEnabled,
                    onCheckedChange = onOrientationFilterChanged,
                    testTag = "switch_orientation_filter"
                )

                Spacer(modifier = Modifier.height(8.dp))
                SliderHeader(
                    label = strings.pocketDebounceLabel,
                    valueText = "${String.format("%.1f", settings.pocketDebounceMs / 1000f)}s"
                )
                Slider(
                    value = settings.pocketDebounceMs.toFloat(),
                    onValueChange = { onPocketDebounceChanged(it.roundToInt().toLong()) },
                    valueRange = 400f..2500f,
                    modifier = Modifier.testTag("pocket_debounce_slider")
                )

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
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
                    testTag = "switch_auto_unlock_call"
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = strings.sessionTimerLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(0, 15, 30, 60).forEach { mins ->
                        FilterChip(
                            selected = settings.sessionTimerMinutes == mins,
                            onClick = { onSessionTimerSelected(mins) },
                            label = {
                                Text(if (mins == 0) strings.timerOff else "${mins}m")
                            },
                            modifier = Modifier.testTag("session_timer_$mins")
                        )
                    }
                }
            }
        }

        // 7. System & Reset Controls
        item {
            CollapsibleSettingsCard(
                icon = Icons.Rounded.Tune,
                title = strings.settingsSystemSection,
                summary = if (settings.darkThemeEnabled) "Dark • Haptics" else "Light • Haptics",
                expanded = expandSystem,
                onToggleExpand = { expandSystem = !expandSystem },
                testTag = "section_system_controls"
            ) {
                SettingToggleRow(
                    icon = Icons.Rounded.Vibration,
                    title = strings.toggleHaptics,
                    subtitle = strings.toggleHapticsDesc,
                    checked = settings.hapticFeedbackEnabled,
                    onCheckedChange = onHapticsChanged,
                    testTag = "switch_haptics"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                SettingToggleRow(
                    icon = Icons.Rounded.RestartAlt,
                    title = strings.toggleAutoBoot,
                    subtitle = strings.toggleAutoBootDesc,
                    checked = settings.autoStartOnBootEnabled,
                    onCheckedChange = onAutoBootChanged,
                    testTag = "switch_auto_boot"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                SettingToggleRow(
                    icon = Icons.Rounded.DarkMode,
                    title = strings.toggleDarkTheme,
                    subtitle = strings.toggleDarkThemeDesc,
                    checked = settings.darkThemeEnabled,
                    onCheckedChange = onDarkThemeChanged,
                    testTag = "switch_dark_theme"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.small)
                        .clickable { onToggleLanguage() }
                        .padding(horizontal = 4.dp, vertical = 12.dp)
                        .testTag("settings_language_row"),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Translate,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
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
                            text = if (settings.appLanguage == AppLanguage.BENGALI) "বাংলা" else "English",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { showResetStatsDialog = true },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("reset_stats_button"),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.DeleteSweep,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(strings.btnResetStats, maxLines = 1)
                    }

                    TextButton(
                        onClick = { showRestoreDefaultsDialog = true },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("restore_defaults_button")
                    ) {
                        Text(strings.btnRestoreDefaults, maxLines = 1)
                    }
                }
            }
        }

        // 8. Device Diagnostics & Battery Reliability
        item {
            CollapsibleSettingsCard(
                icon = Icons.Rounded.HealthAndSafety,
                title = strings.settingsDiagnosticsSection,
                summary = if (permissions.hasOverlayPermission) strings.permGranted else strings.permRequired,
                expanded = expandDiagnostics,
                onToggleExpand = { expandDiagnostics = !expandDiagnostics },
                testTag = "section_diagnostics"
            ) {
                DiagnosticsCard(
                    runtime = runtime,
                    permissions = permissions,
                    strings = strings,
                    onRequestOverlay = onRequestOverlayPermission,
                    onRequestNotifications = onRequestNotificationPermission,
                    onRequestPhoneState = onRequestPhoneStatePermission,
                    onOpenBatterySettings = onOpenBatterySettings
                )
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    shape = MaterialTheme.shapes.small,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.BatterySaver,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = strings.batteryCardTitle,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = strings.batteryCardDesc,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedButton(
                            onClick = onOpenBatterySettings,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("open_battery_settings_button")
                        ) {
                            Text(strings.btnBatterySettings)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(14.dp))
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
            .padding(vertical = 8.dp)
            .testTag(testTag),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(6.dp))
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
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
