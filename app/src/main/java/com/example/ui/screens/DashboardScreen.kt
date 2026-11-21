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
import androidx.compose.material.icons.rounded.BubbleChart
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.PhonelinkLock
import androidx.compose.material.icons.rounded.SensorOccupied
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
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
import com.example.data.SettingsSection
import com.example.data.SmartPreset
import com.example.ui.AppStrings
import com.example.ui.MainUiState
import com.example.ui.components.AnalyticsCard
import com.example.ui.components.LivePocketSensorCard
import com.example.ui.components.SaveProfileDialog
import com.example.ui.components.SettingsSubHeader
import com.example.ui.components.ShieldHeroCard
import com.example.ui.components.SmartPresetsRow
import com.example.ui.components.SystemReadinessBar

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
    onTogglePocketAutoLock: (Boolean) -> Unit,
    onTogglePocketAutoUnlock: (Boolean) -> Unit,
    onToggleFloatingBubble: (Boolean) -> Unit,
    onToggleKeepScreenOn: (Boolean) -> Unit,
    onOpenSettings: (SettingsSection?) -> Unit,
    modifier: Modifier = Modifier
) {
    var showCreateProfileDialog by rememberSaveable { mutableStateOf(false) }
    var renameTargetProfile by remember { mutableStateOf<CustomProfile?>(null) }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isTwoColumn = maxWidth >= 600.dp

        if (isTwoColumn) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    QuickControlsGridCard(
                        uiState = uiState,
                        strings = strings,
                        onTogglePocketAutoLock = onTogglePocketAutoLock,
                        onTogglePocketAutoUnlock = onTogglePocketAutoUnlock,
                        onToggleFloatingBubble = onToggleFloatingBubble,
                        onToggleKeepScreenOn = onToggleKeepScreenOn,
                        onSelectOverlayStyle = onSelectOverlayStyle,
                        onOpenDisplaySettings = { onOpenSettings(SettingsSection.OVERLAY) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    AnalyticsCard(
                        settings = uiState.settings,
                        strings = strings,
                        initiallyExpanded = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    QuickControlsGridCard(
                        uiState = uiState,
                        strings = strings,
                        onTogglePocketAutoLock = onTogglePocketAutoLock,
                        onTogglePocketAutoUnlock = onTogglePocketAutoUnlock,
                        onToggleFloatingBubble = onToggleFloatingBubble,
                        onToggleKeepScreenOn = onToggleKeepScreenOn,
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

                item {
                    AnalyticsCard(
                        settings = uiState.settings,
                        strings = strings,
                        initiallyExpanded = false
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
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
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuickControlsGridCard(
    uiState: MainUiState,
    strings: AppStrings,
    onTogglePocketAutoLock: (Boolean) -> Unit,
    onTogglePocketAutoUnlock: (Boolean) -> Unit,
    onToggleFloatingBubble: (Boolean) -> Unit,
    onToggleKeepScreenOn: (Boolean) -> Unit,
    onSelectOverlayStyle: (OverlayStyle) -> Unit,
    onOpenDisplaySettings: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
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
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Tune,
                        contentDescription = strings.btnCustomize,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuickControlTile(
                    icon = Icons.Rounded.PhonelinkLock,
                    title = strings.togglePocketAutoLock,
                    checked = uiState.settings.pocketAutoLockEnabled,
                    statusLabel = if (uiState.settings.pocketAutoLockEnabled) strings.statusOnLabel else strings.statusOffLabel,
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
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        onTogglePocketAutoUnlock(!uiState.settings.pocketAutoUnlockEnabled)
                    },
                    testTag = "switch_pocket_auto_unlock",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        onToggleKeepScreenOn(!uiState.settings.keepScreenOnEnabled)
                    },
                    testTag = "switch_keep_screen_on",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = strings.dashQuickStyleLabel,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                OverlayStyle.entries.forEach { style ->
                    val selected = when (style) {
                        OverlayStyle.CLEAR_GLASS -> uiState.settings.customDimPercent == 0
                        OverlayStyle.SUBTLE_TINT -> uiState.settings.customDimPercent in 1..60
                        OverlayStyle.AMOLED_BLACK -> uiState.settings.customDimPercent > 60
                    }
                    FilterChip(
                        selected = selected,
                        onClick = { onSelectOverlayStyle(style) },
                        label = {
                            Text(
                                text = strings.overlayStyleTitle(style),
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1
                            )
                        },
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
    onClick: () -> Unit,
    testTag: String,
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
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(32.dp),
                    shape = RoundedCornerShape(8.dp),
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
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

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
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

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
        horizontalArrangement = Arrangement.spacedBy(10.dp)
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
                .padding(horizontal = 10.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(2.dp))
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
