package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BubbleChart
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.PhonelinkLock
import androidx.compose.material.icons.rounded.SensorOccupied
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.CustomProfile
import com.example.data.SmartPreset
import com.example.ui.AppStrings
import com.example.ui.MainUiState
import com.example.ui.components.AnalyticsCard
import com.example.ui.components.LivePocketSensorCard
import com.example.ui.components.PermissionChecklistCard
import com.example.ui.components.SaveProfileDialog
import com.example.ui.components.SettingToggleRow
import com.example.ui.components.ShieldHeroCard
import com.example.ui.components.SmartPresetsRow

@Composable
fun DashboardScreen(
    uiState: MainUiState,
    strings: AppStrings,
    onToggleArmed: () -> Unit,
    onLockNow: () -> Unit,
    onOpenSandbox: () -> Unit,
    onSelectPreset: (SmartPreset) -> Unit,
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
    modifier: Modifier = Modifier
) {
    var showCreateProfileDialog by rememberSaveable { mutableStateOf(false) }
    var renameTargetProfile by remember { mutableStateOf<CustomProfile?>(null) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            ShieldHeroCard(
                isServiceRunning = uiState.runtime.isServiceRunning,
                isScreenLocked = uiState.runtime.isScreenLocked,
                hasOverlayPermission = uiState.permissions.hasOverlayPermission,
                strings = strings,
                onToggleArmed = onToggleArmed,
                onLockNow = onLockNow,
                onTestSandbox = onOpenSandbox
            )
        }

        item {
            PermissionChecklistCard(
                permissions = uiState.permissions,
                strings = strings,
                onRequestOverlay = onRequestOverlayPermission,
                onRequestNotifications = onRequestNotificationPermission
            )
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatTileCard(
                    label = strings.statBlockedTouches,
                    value = uiState.settings.totalBlockedTouches.toString(),
                    modifier = Modifier.weight(1f)
                )
                StatTileCard(
                    label = strings.statLockSessions,
                    value = uiState.settings.totalLockSessions.toString(),
                    modifier = Modifier.weight(1f)
                )
                StatTileCard(
                    label = strings.statActiveMode,
                    value = "${uiState.settings.customDimPercent}%",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            AnalyticsCard(
                settings = uiState.settings,
                strings = strings
            )
        }

        item {
            LivePocketSensorCard(
                runtime = uiState.runtime,
                settings = uiState.settings,
                strings = strings
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = strings.quickControlsTitle,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    SettingToggleRow(
                        icon = Icons.Rounded.PhonelinkLock,
                        title = strings.togglePocketAutoLock,
                        subtitle = strings.togglePocketAutoLockDesc,
                        checked = uiState.settings.pocketAutoLockEnabled,
                        onCheckedChange = onTogglePocketAutoLock,
                        testTag = "switch_pocket_auto_lock"
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    SettingToggleRow(
                        icon = Icons.Rounded.SensorOccupied,
                        title = strings.togglePocketAutoUnlock,
                        subtitle = strings.togglePocketAutoUnlockDesc,
                        checked = uiState.settings.pocketAutoUnlockEnabled,
                        onCheckedChange = onTogglePocketAutoUnlock,
                        testTag = "switch_pocket_auto_unlock"
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    SettingToggleRow(
                        icon = Icons.Rounded.BubbleChart,
                        title = strings.toggleFloatingBubble,
                        subtitle = strings.toggleFloatingBubbleDesc,
                        checked = uiState.settings.floatingBubbleEnabled,
                        onCheckedChange = onToggleFloatingBubble,
                        testTag = "switch_floating_bubble"
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    SettingToggleRow(
                        icon = Icons.Rounded.LightMode,
                        title = strings.toggleKeepScreenOn,
                        subtitle = strings.toggleKeepScreenOnDesc,
                        checked = uiState.settings.keepScreenOnEnabled,
                        onCheckedChange = onToggleKeepScreenOn,
                        testTag = "switch_keep_screen_on"
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
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

@Composable
private fun StatTileCard(
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
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
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
