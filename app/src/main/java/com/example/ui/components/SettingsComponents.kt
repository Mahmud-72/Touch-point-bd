package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BatteryChargingFull
import androidx.compose.material.icons.rounded.BatterySaver
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.PhotoLibrary
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.VideoLibrary
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.BadgeVisibilityMode
import com.example.data.BubbleStyle
import com.example.data.ShieldRuntimeState
import com.example.data.ShieldSettings
import com.example.data.SmartPreset
import com.example.ui.AppStrings
import com.example.ui.PermissionStatus

@Composable
fun LiveMiniPreviewCard(
    settings: ShieldSettings,
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    val borderColor = Color(settings.borderColor.colorArgb)
    val dimAlpha = (settings.customDimPercent.coerceIn(0, 95) / 100f)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = strings.settingsLivePreviewTitle,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Simulated live screen showing clear photo underneath + live border + live dim + live badge + live bubble
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(148.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFFDBEAFE),
                                Color(0xFFD1FAE5),
                                Color(0xFFFEF3C7)
                            )
                        )
                    )
            ) {
                // Underlying simulated photo content
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PhotoLibrary,
                        contentDescription = null,
                        tint = Color(0xFF0F172A).copy(alpha = 0.72f),
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${100 - settings.customDimPercent}% Clear",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF0F172A).copy(alpha = 0.8f)
                    )
                }

                // Live Dim Layer
                if (dimAlpha > 0f) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF05080E).copy(alpha = dimAlpha))
                    )
                }

                // Live Edge Glow Border
                if (settings.borderEnabled) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = settings.borderThickness.dpValue.dp,
                                color = borderColor.copy(alpha = 0.85f),
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                }

                // Live Top Status Badge
                if (settings.badgeVisibilityMode != BadgeVisibilityMode.HIDDEN) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 10.dp),
                        shape = RoundedCornerShape(50),
                        color = Color(0xCC0F172A),
                        border = BorderStroke(1.dp, borderColor.copy(alpha = 0.75f))
                    ) {
                        Text(
                            text = if (settings.badgeVisibilityMode == BadgeVisibilityMode.ON_TOUCH) {
                                "🔒 ${strings.badgeOnTouch}"
                            } else {
                                "🔒 ${strings.overlayTouchBlockedTitle}"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                // Live Floating Bubble Preview (if enabled)
                if (settings.floatingBubbleEnabled) {
                    val bubbleDp = (settings.bubbleSize.dpSize * 0.62f).dp
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 10.dp)
                            .alpha(settings.floatingBubbleOpacity)
                    ) {
                        when (settings.bubbleStyle) {
                            BubbleStyle.MINIMAL_DOT -> Surface(
                                modifier = Modifier.size(bubbleDp * 0.8f),
                                shape = CircleShape,
                                color = Color.White,
                                border = BorderStroke(1.5.dp, borderColor),
                                shadowElevation = 4.dp
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .clip(CircleShape)
                                            .background(borderColor)
                                    )
                                }
                            }

                            BubbleStyle.COMPACT_PILL -> Surface(
                                shape = RoundedCornerShape(50),
                                color = Color.White,
                                border = BorderStroke(1.5.dp, borderColor),
                                shadowElevation = 4.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Shield,
                                        contentDescription = null,
                                        tint = borderColor,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "LOCK",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color(0xFF0F172A)
                                    )
                                }
                            }

                            BubbleStyle.SHIELD_CIRCLE -> Surface(
                                modifier = Modifier.size(bubbleDp),
                                shape = CircleShape,
                                color = Color.White,
                                border = BorderStroke(1.5.dp, borderColor),
                                shadowElevation = 4.dp
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Rounded.Shield,
                                        contentDescription = null,
                                        tint = borderColor,
                                        modifier = Modifier.size(bubbleDp * 0.55f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SmartPresetsRow(
    settings: ShieldSettings,
    strings: AppStrings,
    onSelectPreset: (SmartPreset) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = strings.presetsTitle,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SmartPreset.entries.forEach { preset ->
                    val isMatched = when (preset) {
                        SmartPreset.POCKET_PHOTO -> settings.customDimPercent == 0 &&
                            settings.keepScreenOnEnabled &&
                            settings.pocketAutoLockEnabled &&
                            settings.floatingBubbleEnabled &&
                            settings.unlockMethod == com.example.data.UnlockMethod.LONG_PRESS &&
                            settings.holdDurationMs < 2500L

                        SmartPreset.KIDS_VIDEO -> settings.customDimPercent == 0 &&
                            settings.unlockMethod == com.example.data.UnlockMethod.LONG_PRESS &&
                            settings.holdDurationMs >= 2800L &&
                            !settings.floatingBubbleEnabled

                        SmartPreset.BATTERY_SAVER -> settings.customDimPercent >= 80 &&
                            settings.pocketAutoLockEnabled &&
                            !settings.keepScreenOnEnabled
                    }

                    val icon = when (preset) {
                        SmartPreset.POCKET_PHOTO -> Icons.Rounded.PhotoLibrary
                        SmartPreset.KIDS_VIDEO -> Icons.Rounded.VideoLibrary
                        SmartPreset.BATTERY_SAVER -> Icons.Rounded.BatteryChargingFull
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clip(MaterialTheme.shapes.small)
                            .clickable { onSelectPreset(preset) }
                            .testTag("preset_${preset.name.lowercase()}"),
                        shape = MaterialTheme.shapes.small,
                        color = if (isMatched) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
                        },
                        border = BorderStroke(
                            1.dp,
                            if (isMatched) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outlineVariant
                            }
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = if (isMatched) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                },
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = strings.presetTitle(preset),
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isMatched) {
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                },
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = strings.presetDesc(preset),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CollapsibleSettingsCard(
    icon: ImageVector,
    title: String,
    summary: String,
    expanded: Boolean,
    onToggleExpand: () -> Unit,
    testTag: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleExpand() }
                    .padding(16.dp)
                    .testTag(testTag),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(38.dp),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Icon(
                    imageVector = if (expanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(12.dp))
                    content()
                }
            }
        }
    }
}

@Composable
fun DiagnosticsCard(
    runtime: ShieldRuntimeState,
    permissions: PermissionStatus,
    strings: AppStrings,
    onRequestOverlay: () -> Unit,
    onRequestNotifications: () -> Unit,
    onRequestPhoneState: () -> Unit,
    onOpenBatterySettings: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DiagnosticRow(
            label = strings.diagProximity,
            value = if (runtime.hasProximitySensor) strings.diagAvailable else strings.diagUnavailable,
            isHealthy = runtime.hasProximitySensor
        )
        DiagnosticRow(
            label = strings.diagAccelerometer,
            value = if (runtime.hasAccelerometer) strings.diagAvailable else strings.diagUnavailable,
            isHealthy = runtime.hasAccelerometer
        )
        DiagnosticRow(
            label = strings.diagOverlay,
            value = if (permissions.hasOverlayPermission) strings.permGranted else strings.permRequired,
            isHealthy = permissions.hasOverlayPermission,
            onClick = if (!permissions.hasOverlayPermission) onRequestOverlay else null
        )
        DiagnosticRow(
            label = strings.diagNotifications,
            value = if (permissions.hasNotificationPermission) strings.permGranted else strings.permOptional,
            isHealthy = permissions.hasNotificationPermission,
            onClick = if (!permissions.hasNotificationPermission) onRequestNotifications else null
        )
        DiagnosticRow(
            label = strings.diagPhoneState,
            value = if (permissions.hasPhoneStatePermission) strings.permGranted else strings.permOptional,
            isHealthy = permissions.hasPhoneStatePermission,
            onClick = if (!permissions.hasPhoneStatePermission) onRequestPhoneState else null
        )
        DiagnosticRow(
            label = strings.diagBattery,
            value = if (permissions.isIgnoringBatteryOptimizations) strings.diagUnrestricted else strings.diagOptimized,
            isHealthy = permissions.isIgnoringBatteryOptimizations,
            onClick = onOpenBatterySettings
        )
    }
}

@Composable
private fun DiagnosticRow(
    label: String,
    value: String,
    isHealthy: Boolean,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isHealthy) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
                )
                Icon(
                    imageVector = if (isHealthy) Icons.Rounded.CheckCircle else Icons.Rounded.WarningAmber,
                    contentDescription = null,
                    tint = if (isHealthy) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
