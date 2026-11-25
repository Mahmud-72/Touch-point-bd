package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.BubbleChart
import androidx.compose.material.icons.rounded.ChildCare
import androidx.compose.material.icons.rounded.CropFree
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.NightsStay
import androidx.compose.material.icons.rounded.PhonelinkLock
import androidx.compose.material.icons.rounded.SensorOccupied
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.CustomProfile
import com.example.data.OverlayStyle
import com.example.data.SettingHelpTopic
import com.example.data.SettingsSection
import com.example.data.SmartPreset
import com.example.ui.AppStrings
import com.example.ui.MainUiState
import com.example.ui.components.AnalyticsCard
import com.example.ui.components.EasyProModeBanner
import com.example.ui.components.FeatureHelpDialog
import com.example.ui.components.HelpInfoIconButton
import com.example.ui.components.LivePocketSensorCard
import com.example.ui.components.ProFeatureBadge
import com.example.ui.components.SaveProfileDialog
import com.example.ui.components.SettingsSubHeader
import com.example.ui.components.ShieldHeroCard
import com.example.ui.components.SmartPresetsRow
import com.example.ui.components.SystemReadinessBar
import com.example.ui.components.TightFilterChip

@Composable
fun DashboardScreen(
    uiState: MainUiState,
    strings: AppStrings,
    onToggleArmed: () -> Unit,
    onLockNow: () -> Unit,
    onOpenSandbox: () -> Unit,
    onSelectPreset: (SmartPreset) -> Unit,
    onSelectOverlayStyle: (OverlayStyle) -> Unit,
    onSaveCustomProfile: (String) -> Unit,
    onApplyCustomProfile: (CustomProfile) -> Unit,
    onRenameCustomProfile: (CustomProfile, String) -> Unit,
    onDeleteCustomProfile: (CustomProfile) -> Unit,
    onRequestOverlayPermission: () -> Unit,
    onRequestNotificationPermission: () -> Unit,
    onToggleAdvancedMode: (Boolean) -> Unit,
    onTogglePocketAutoLock: (Boolean) -> Unit,
    onTogglePocketAutoUnlock: (Boolean) -> Unit,
    onToggleFloatingBubble: (Boolean) -> Unit,
    onToggleKeepScreenOn: (Boolean) -> Unit,
    onTogglePartialZone: (Boolean) -> Unit,
    onTogglePrivacyVeil: (Boolean) -> Unit,
    onToggleKidsSafety: (Boolean) -> Unit,
    onToggleAppWatch: (Boolean) -> Unit,
    onOpenSettings: (SettingsSection?) -> Unit,
    modifier: Modifier = Modifier
) {
    var showCreateProfileDialog by rememberSaveable { mutableStateOf(false) }
    var renameTargetProfile by remember { mutableStateOf<CustomProfile?>(null) }
    var activeHelpTopic by remember { mutableStateOf<SettingHelpTopic?>(null) }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isTwoColumn = maxWidth >= 600.dp

        if (isTwoColumn) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    SettingsSubHeader(strings.dashSectionOverview)
                    ShieldHeroCard(
                        isServiceRunning = uiState.runtime.isServiceRunning,
                        isScreenLocked = uiState.runtime.isScreenLocked,
                        hasOverlayPermission = uiState.permissions.hasOverlayPermission,
                        settings = uiState.settings,
                        strings = strings,
                        onToggleArmed = onToggleArmed,
                        onLockNow = onLockNow,
                        onTestSandbox = onOpenSandbox,
                        onOpenSettings = onOpenSettings
                    )
                    SystemReadinessBar(
                        permissions = uiState.permissions,
                        strings = strings,
                        onRequestOverlay = onRequestOverlayPermission,
                        onRequestNotifications = onRequestNotificationPermission,
                        onOpenDiagnostics = { onOpenSettings(SettingsSection.SYSTEM) }
                    )
                    SettingsSubHeader(strings.dashSectionQuickControls)
                    EasyProModeBanner(
                        isAdvancedMode = uiState.settings.isAdvancedMode,
                        strings = strings,
                        onModeSelected = onToggleAdvancedMode,
                        onHelpClick = { activeHelpTopic = SettingHelpTopic.MODE_EASY_PRO }
                    )
                    QuickControlsGridCard(
                        uiState = uiState,
                        strings = strings,
                        onToggleAdvancedMode = onToggleAdvancedMode,
                        onHelpClick = { activeHelpTopic = it },
                        onTogglePocketAutoLock = onTogglePocketAutoLock,
                        onTogglePocketAutoUnlock = onTogglePocketAutoUnlock,
                        onToggleFloatingBubble = onToggleFloatingBubble,
                        onToggleKeepScreenOn = onToggleKeepScreenOn,
                        onTogglePartialZone = onTogglePartialZone,
                        onTogglePrivacyVeil = onTogglePrivacyVeil,
                        onToggleKidsSafety = onToggleKidsSafety,
                        onToggleAppWatch = onToggleAppWatch,
                        onSelectOverlayStyle = onSelectOverlayStyle,
                        onOpenDisplaySettings = { onOpenSettings(SettingsSection.OVERLAY) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    SettingsSubHeader(strings.dashSectionProfiles)
                    SmartPresetsRow(
                        settings = uiState.settings,
                        strings = strings,
                        onSelectPreset = onSelectPreset,
                        onSaveCurrentProfile = { showCreateProfileDialog = true },
                        onApplyCustomProfile = onApplyCustomProfile,
                        onRenameCustomProfile = { renameTargetProfile = it },
                        onDeleteCustomProfile = onDeleteCustomProfile
                    )
                    SettingsSubHeader(strings.dashSectionInsights)
                    DashboardKpiRow(uiState = uiState, strings = strings)
                    LivePocketSensorCard(
                        runtime = uiState.runtime,
                        settings = uiState.settings,
                        strings = strings,
                        onOpenPocketSettings = { onOpenSettings(SettingsSection.POCKET) }
                    )
                    if (uiState.settings.isAdvancedMode) {
                        AnalyticsCard(
                            settings = uiState.settings,
                            strings = strings,
                            initiallyExpanded = true
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item {
                    SettingsSubHeader(
                        title = strings.dashSectionOverview,
                        modifier = Modifier.padding(top = 0.dp)
                    )
                }

                item {
                    ShieldHeroCard(
                        isServiceRunning = uiState.runtime.isServiceRunning,
                        isScreenLocked = uiState.runtime.isScreenLocked,
                        hasOverlayPermission = uiState.permissions.hasOverlayPermission,
                        settings = uiState.settings,
                        strings = strings,
                        onToggleArmed = onToggleArmed,
                        onLockNow = onLockNow,
                        onTestSandbox = onOpenSandbox,
                        onOpenSettings = onOpenSettings
                    )
                }

                item {
                    SystemReadinessBar(
                        permissions = uiState.permissions,
                        strings = strings,
                        onRequestOverlay = onRequestOverlayPermission,
                        onRequestNotifications = onRequestNotificationPermission,
                        onOpenDiagnostics = { onOpenSettings(SettingsSection.SYSTEM) }
                    )
                }

                item {
                    SettingsSubHeader(strings.dashSectionQuickControls)
                }

                item {
                    EasyProModeBanner(
                        isAdvancedMode = uiState.settings.isAdvancedMode,
                        strings = strings,
                        onModeSelected = onToggleAdvancedMode,
                        onHelpClick = { activeHelpTopic = SettingHelpTopic.MODE_EASY_PRO }
                    )
                }

                item {
                    QuickControlsGridCard(
                        uiState = uiState,
                        strings = strings,
                        onToggleAdvancedMode = onToggleAdvancedMode,
                        onHelpClick = { activeHelpTopic = it },
                        onTogglePocketAutoLock = onTogglePocketAutoLock,
                        onTogglePocketAutoUnlock = onTogglePocketAutoUnlock,
                        onToggleFloatingBubble = onToggleFloatingBubble,
                        onToggleKeepScreenOn = onToggleKeepScreenOn,
                        onTogglePartialZone = onTogglePartialZone,
                        onTogglePrivacyVeil = onTogglePrivacyVeil,
                        onToggleKidsSafety = onToggleKidsSafety,
                        onToggleAppWatch = onToggleAppWatch,
                        onSelectOverlayStyle = onSelectOverlayStyle,
                        onOpenDisplaySettings = { onOpenSettings(SettingsSection.OVERLAY) }
                    )
                }

                item {
                    SettingsSubHeader(strings.dashSectionProfiles)
                }

                item {
                    SmartPresetsRow(
                        settings = uiState.settings,
                        strings = strings,
                        onSelectPreset = onSelectPreset,
                        onSaveCurrentProfile = { showCreateProfileDialog = true },
                        onApplyCustomProfile = onApplyCustomProfile,
                        onRenameCustomProfile = { renameTargetProfile = it },
                        onDeleteCustomProfile = onDeleteCustomProfile
                    )
                }

                item {
                    SettingsSubHeader(strings.dashSectionInsights)
                }

                item {
                    DashboardKpiRow(uiState = uiState, strings = strings)
                }

                item {
                    LivePocketSensorCard(
                        runtime = uiState.runtime,
                        settings = uiState.settings,
                        strings = strings,
                        onOpenPocketSettings = { onOpenSettings(SettingsSection.POCKET) }
                    )
                }

                if (uiState.settings.isAdvancedMode) {
                    item {
                        AnalyticsCard(
                            settings = uiState.settings,
                            strings = strings,
                            initiallyExpanded = false
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
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

    activeHelpTopic?.let { topic ->
        FeatureHelpDialog(
            help = strings.helpContent(topic),
            strings = strings,
            onDismiss = { activeHelpTopic = null }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuickControlsGridCard(
    uiState: MainUiState,
    strings: AppStrings,
    onToggleAdvancedMode: (Boolean) -> Unit,
    onHelpClick: (SettingHelpTopic) -> Unit,
    onTogglePocketAutoLock: (Boolean) -> Unit,
    onTogglePocketAutoUnlock: (Boolean) -> Unit,
    onToggleFloatingBubble: (Boolean) -> Unit,
    onToggleKeepScreenOn: (Boolean) -> Unit,
    onTogglePartialZone: (Boolean) -> Unit,
    onTogglePrivacyVeil: (Boolean) -> Unit,
    onToggleKidsSafety: (Boolean) -> Unit,
    onToggleAppWatch: (Boolean) -> Unit,
    onSelectOverlayStyle: (OverlayStyle) -> Unit,
    onOpenDisplaySettings: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val isProMode = uiState.settings.isAdvancedMode

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = strings.quickControlsTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                IconButton(
                    onClick = onOpenDisplaySettings,
                    modifier = Modifier.size(26.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Tune,
                        contentDescription = strings.btnCustomize,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            if (!isProMode) {
                // Easy Mode: 4 essential everyday tiles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    QuickControlTile(
                        icon = Icons.Rounded.PhonelinkLock,
                        title = strings.togglePocketAutoLock,
                        checked = uiState.settings.pocketAutoLockEnabled,
                        statusLabel = if (uiState.settings.pocketAutoLockEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.POCKET_AUTO_LOCK,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onTogglePocketAutoLock(!uiState.settings.pocketAutoLockEnabled)
                        },
                        testTag = "switch_pocket_auto_lock",
                        modifier = Modifier.weight(1f)
                    )
                    QuickControlTile(
                        icon = Icons.Rounded.BubbleChart,
                        title = strings.toggleFloatingBubble,
                        checked = uiState.settings.floatingBubbleEnabled,
                        statusLabel = if (uiState.settings.floatingBubbleEnabled) {
                            strings.bubbleSizeLabel(uiState.settings.bubbleSize)
                        } else {
                            strings.statusOffLabel
                        },
                        helpTopic = SettingHelpTopic.FLOATING_BUBBLE,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onToggleFloatingBubble(!uiState.settings.floatingBubbleEnabled)
                        },
                        testTag = "switch_floating_bubble",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    QuickControlTile(
                        icon = Icons.Rounded.ChildCare,
                        title = strings.toggleKidsSafety,
                        checked = uiState.settings.kidsSafetyEnabled,
                        statusLabel = if (uiState.settings.kidsSafetyEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.KIDS_SAFETY,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onToggleKidsSafety(!uiState.settings.kidsSafetyEnabled)
                        },
                        testTag = "switch_kids_safety",
                        modifier = Modifier.weight(1f)
                    )
                    QuickControlTile(
                        icon = Icons.Rounded.NightsStay,
                        title = strings.togglePrivacyVeil,
                        checked = uiState.settings.privacyVeilEnabled,
                        statusLabel = if (uiState.settings.privacyVeilEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.PRIVACY_VEIL,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onTogglePrivacyVeil(!uiState.settings.privacyVeilEnabled)
                        },
                        testTag = "switch_privacy_veil",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.small)
                        .clickable { onToggleAdvancedMode(true) }
                        .testTag("dash_unlock_pro_tiles"),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AutoAwesome,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = strings.dashUnlockProTilesHint,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            } else {
                // Pro Mode: all 8 controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    QuickControlTile(
                        icon = Icons.Rounded.PhonelinkLock,
                        title = strings.togglePocketAutoLock,
                        checked = uiState.settings.pocketAutoLockEnabled,
                        statusLabel = if (uiState.settings.pocketAutoLockEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.POCKET_AUTO_LOCK,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onTogglePocketAutoLock(!uiState.settings.pocketAutoLockEnabled)
                        },
                        testTag = "switch_pocket_auto_lock",
                        modifier = Modifier.weight(1f)
                    )
                    QuickControlTile(
                        icon = Icons.Rounded.SensorOccupied,
                        title = strings.togglePocketAutoUnlock,
                        checked = uiState.settings.pocketAutoUnlockEnabled,
                        statusLabel = if (uiState.settings.pocketAutoUnlockEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.POCKET_AUTO_UNLOCK,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        isPro = true,
                        proBadgeLabel = strings.proBadgeLabel,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onTogglePocketAutoUnlock(!uiState.settings.pocketAutoUnlockEnabled)
                        },
                        testTag = "switch_pocket_auto_unlock",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    QuickControlTile(
                        icon = Icons.Rounded.BubbleChart,
                        title = strings.toggleFloatingBubble,
                        checked = uiState.settings.floatingBubbleEnabled,
                        statusLabel = if (uiState.settings.floatingBubbleEnabled) {
                            strings.bubbleSizeLabel(uiState.settings.bubbleSize)
                        } else {
                            strings.statusOffLabel
                        },
                        helpTopic = SettingHelpTopic.FLOATING_BUBBLE,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onToggleFloatingBubble(!uiState.settings.floatingBubbleEnabled)
                        },
                        testTag = "switch_floating_bubble",
                        modifier = Modifier.weight(1f)
                    )
                    QuickControlTile(
                        icon = Icons.Rounded.LightMode,
                        title = strings.toggleKeepScreenOn,
                        checked = uiState.settings.keepScreenOnEnabled,
                        statusLabel = if (uiState.settings.keepScreenOnEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.KEEP_SCREEN_ON,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        isPro = true,
                        proBadgeLabel = strings.proBadgeLabel,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onToggleKeepScreenOn(!uiState.settings.keepScreenOnEnabled)
                        },
                        testTag = "switch_keep_screen_on",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    QuickControlTile(
                        icon = Icons.Rounded.CropFree,
                        title = strings.togglePartialZone,
                        checked = uiState.settings.partialZoneEnabled,
                        statusLabel = if (uiState.settings.partialZoneEnabled) {
                            strings.partialZonePresetLabel(uiState.settings.partialZonePreset)
                        } else {
                            strings.statusOffLabel
                        },
                        helpTopic = SettingHelpTopic.PARTIAL_TOUCH_ZONE,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        isPro = true,
                        proBadgeLabel = strings.proBadgeLabel,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onTogglePartialZone(!uiState.settings.partialZoneEnabled)
                        },
                        testTag = "switch_partial_zone",
                        modifier = Modifier.weight(1f)
                    )
                    QuickControlTile(
                        icon = Icons.Rounded.NightsStay,
                        title = strings.togglePrivacyVeil,
                        checked = uiState.settings.privacyVeilEnabled,
                        statusLabel = if (uiState.settings.privacyVeilEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.PRIVACY_VEIL,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onTogglePrivacyVeil(!uiState.settings.privacyVeilEnabled)
                        },
                        testTag = "switch_privacy_veil",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    QuickControlTile(
                        icon = Icons.Rounded.ChildCare,
                        title = strings.toggleKidsSafety,
                        checked = uiState.settings.kidsSafetyEnabled,
                        statusLabel = if (uiState.settings.kidsSafetyEnabled) strings.statusOnLabel else strings.statusOffLabel,
                        helpTopic = SettingHelpTopic.KIDS_SAFETY,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onToggleKidsSafety(!uiState.settings.kidsSafetyEnabled)
                        },
                        testTag = "switch_kids_safety",
                        modifier = Modifier.weight(1f)
                    )
                    QuickControlTile(
                        icon = Icons.Rounded.Apps,
                        title = strings.toggleAppWatch,
                        checked = uiState.settings.appWatchEnabled,
                        statusLabel = if (uiState.settings.appWatchEnabled) {
                            strings.watchedAppsSummary(uiState.settings.watchedAppPackages.size)
                        } else {
                            strings.statusOffLabel
                        },
                        helpTopic = SettingHelpTopic.APP_WATCH,
                        helpContentDesc = strings.helpActionDesc,
                        onHelpClick = onHelpClick,
                        isPro = true,
                        proBadgeLabel = strings.proBadgeLabel,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onToggleAppWatch(!uiState.settings.appWatchEnabled)
                        },
                        testTag = "switch_app_watch",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = strings.dashQuickStyleLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                HelpInfoIconButton(
                    contentDescription = strings.helpActionDesc,
                    onClick = { onHelpClick(SettingHelpTopic.OVERLAY_STYLE) },
                    iconSize = 13.dp,
                    modifier = Modifier.testTag("help_dash_overlay_style")
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                OverlayStyle.entries.forEach { style ->
                    val selected = when (style) {
                        OverlayStyle.CLEAR_GLASS -> uiState.settings.customDimPercent == 0
                        OverlayStyle.SUBTLE_TINT -> uiState.settings.customDimPercent in 1..60
                        OverlayStyle.AMOLED_BLACK -> uiState.settings.customDimPercent > 60
                    }
                    TightFilterChip(
                        selected = selected,
                        onClick = { onSelectOverlayStyle(style) },
                        label = strings.overlayStyleTitle(style),
                        modifier = Modifier.testTag("dash_style_${style.storageKey}")
                    )
                }
            }
        }
    }
}

@Composable
private fun QuickControlTile(
    icon: ImageVector,
    title: String,
    checked: Boolean,
    statusLabel: String,
    helpTopic: SettingHelpTopic,
    helpContentDesc: String,
    onHelpClick: (SettingHelpTopic) -> Unit,
    onClick: () -> Unit,
    testTag: String,
    isPro: Boolean = false,
    proBadgeLabel: String = "PRO",
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() }
            .testTag(testTag),
        shape = MaterialTheme.shapes.small,
        color = if (checked) {
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
        } else {
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (checked) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp, vertical = 6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Surface(
                        modifier = Modifier.size(24.dp),
                        shape = RoundedCornerShape(6.dp),
                        color = if (checked) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = if (checked) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                },
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    if (isPro) {
                        ProFeatureBadge(label = proBadgeLabel)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (checked) {
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        }
                    ) {
                        Text(
                            text = statusLabel,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = if (checked) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            maxLines = 1,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                        )
                    }
                    HelpInfoIconButton(
                        contentDescription = helpContentDesc,
                        onClick = { onHelpClick(helpTopic) },
                        iconSize = 13.dp,
                        modifier = Modifier.testTag("help_$testTag")
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium,
                color = if (checked) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                minLines = 2
            )
        }
    }
}

@Composable
private fun DashboardKpiRow(
    uiState: MainUiState,
    strings: AppStrings
) {
    val clarityPercent = (100 - uiState.settings.customDimPercent).coerceIn(5, 100)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        StatTileCard(
            icon = Icons.Rounded.TouchApp,
            label = strings.statBlockedTouches,
            value = uiState.settings.totalBlockedTouches.toString(),
            modifier = Modifier.weight(1f)
        )
        StatTileCard(
            icon = Icons.Rounded.Lock,
            label = strings.statLockSessions,
            value = uiState.settings.totalLockSessions.toString(),
            modifier = Modifier.weight(1f)
        )
        StatTileCard(
            icon = Icons.Rounded.Visibility,
            label = strings.statScreenClarity,
            value = "$clarityPercent%",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StatTileCard(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
