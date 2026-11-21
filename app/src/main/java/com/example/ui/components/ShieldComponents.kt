package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material.icons.rounded.PhotoLibrary
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.VerifiedUser
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderStyle
import com.example.data.ShieldRuntimeState
import com.example.data.ShieldSettings
import com.example.data.UnlockMethod
import com.example.ui.AppStrings
import com.example.ui.PermissionStatus
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun ShieldHeroCard(
    isServiceRunning: Boolean,
    isScreenLocked: Boolean,
    hasOverlayPermission: Boolean,
    strings: AppStrings,
    onToggleArmed: () -> Unit,
    onLockNow: () -> Unit,
    onTestSandbox: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accentColor by animateColorAsState(
        targetValue = when {
            !hasOverlayPermission -> MaterialTheme.colorScheme.tertiary
            isScreenLocked -> MaterialTheme.colorScheme.error
            isServiceRunning -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.secondary
        },
        animationSpec = tween(400),
        label = "heroAccent"
    )

    val statusTitle = when {
        !hasOverlayPermission -> strings.statusPermissionNeeded
        isScreenLocked -> strings.statusLocked
        isServiceRunning -> strings.statusArmed
        else -> strings.statusStandby
    }

    val statusDesc = when {
        !hasOverlayPermission -> strings.statusPermissionDesc
        isScreenLocked -> strings.statusLockedDesc
        isServiceRunning -> strings.statusArmedDesc
        else -> strings.statusStandbyDesc
    }

    val infiniteTransition = rememberInfiniteTransition(label = "radarPulse")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.88f,
        targetValue = 1.12f,
        animationSpec = infiniteRepeatable(
            animation = tween(1900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.35f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            accentColor.copy(alpha = 0.16f),
                            Color.Transparent
                        ),
                        center = Offset(300f, 160f),
                        radius = 650f
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = accentColor.copy(alpha = 0.14f),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(accentColor)
                        )
                        Text(
                            text = statusTitle,
                            style = MaterialTheme.typography.labelMedium,
                            color = accentColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Box(
                    modifier = Modifier
                        .size(136.dp)
                        .clip(CircleShape)
                        .clickable { onToggleArmed() }
                        .testTag("shield_orb_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val baseRadius = size.minDimension / 2f

                        drawCircle(
                            color = accentColor.copy(alpha = if (isServiceRunning) 0.12f else 0.06f),
                            radius = baseRadius * if (isServiceRunning) pulse.coerceAtMost(1f) else 0.92f,
                            center = center
                        )
                        drawCircle(
                            color = accentColor.copy(alpha = 0.25f),
                            radius = baseRadius * 0.78f,
                            center = center,
                            style = Stroke(width = 2.dp.toPx())
                        )
                        drawCircle(
                            color = accentColor.copy(alpha = 0.65f),
                            radius = baseRadius * 0.62f,
                            center = center,
                            style = Stroke(width = 2.5.dp.toPx())
                        )
                    }

                    Surface(
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        color = accentColor.copy(alpha = 0.18f),
                        border = BorderStroke(1.5.dp, accentColor)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = when {
                                    !hasOverlayPermission -> Icons.Rounded.WarningAmber
                                    isScreenLocked -> Icons.Rounded.Lock
                                    isServiceRunning -> Icons.Rounded.VerifiedUser
                                    else -> Icons.Rounded.Shield
                                },
                                contentDescription = statusTitle,
                                tint = accentColor,
                                modifier = Modifier.size(34.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = statusDesc,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onToggleArmed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("arm_shield_button"),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isServiceRunning) {
                            MaterialTheme.colorScheme.surfaceContainerHigh
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                        contentColor = if (isServiceRunning) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            MaterialTheme.colorScheme.onPrimary
                        }
                    )
                ) {
                    Icon(
                        imageVector = if (isServiceRunning) Icons.Rounded.LockOpen else Icons.Rounded.Shield,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when {
                            !hasOverlayPermission -> strings.btnGrantPermission
                            isServiceRunning -> strings.btnStopShield
                            else -> strings.btnArmShield
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FilledTonalButton(
                        onClick = onLockNow,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("lock_now_button"),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = strings.btnLockScreenNow,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    OutlinedButton(
                        onClick = onTestSandbox,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("sandbox_test_button"),
                        shape = MaterialTheme.shapes.medium,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.TouchApp,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = strings.btnTestLockSandbox,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PermissionChecklistCard(
    permissions: PermissionStatus,
    strings: AppStrings,
    onRequestOverlay: () -> Unit,
    onRequestNotifications: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (permissions.hasOverlayPermission && permissions.hasNotificationPermission) return

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.WarningAmber,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = strings.permissionsCardTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            PermissionRowItem(
                icon = Icons.Rounded.Layers,
                title = strings.permOverlayTitle,
                description = strings.permOverlayDesc,
                granted = permissions.hasOverlayPermission,
                badgeText = if (permissions.hasOverlayPermission) strings.permGranted else strings.permRequired,
                actionText = strings.btnGrantPermission,
                testTag = "grant_overlay_button",
                onClick = onRequestOverlay
            )

            Spacer(modifier = Modifier.height(10.dp))

            PermissionRowItem(
                icon = Icons.Rounded.NotificationsActive,
                title = strings.permNotificationTitle,
                description = strings.permNotificationDesc,
                granted = permissions.hasNotificationPermission,
                badgeText = if (permissions.hasNotificationPermission) strings.permGranted else strings.permOptional,
                actionText = strings.btnEnableNow,
                testTag = "grant_notification_button",
                onClick = onRequestNotifications
            )
        }
    }
}

@Composable
private fun PermissionRowItem(
    icon: ImageVector,
    title: String,
    description: String,
    granted: Boolean,
    badgeText: String,
    actionText: String,
    testTag: String,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (granted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (granted) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.tertiaryContainer
                        }
                    ) {
                        Text(
                            text = badgeText,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (granted) {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            } else {
                                MaterialTheme.colorScheme.onTertiaryContainer
                            },
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (!granted) {
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalButton(
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = actionText, style = MaterialTheme.typography.labelLarge)
                }
            } else {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = badgeText,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LivePocketSensorCard(
    runtime: ShieldRuntimeState,
    settings: ShieldSettings,
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    val sensorColor by animateColorAsState(
        targetValue = when {
            !runtime.hasProximitySensor -> MaterialTheme.colorScheme.onSurfaceVariant
            runtime.proximityIsNear -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.secondary
        },
        label = "sensorColor"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Sensors,
                        contentDescription = null,
                        tint = sensorColor
                    )
                    Text(
                        text = strings.sensorCardTitle,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (runtime.hasProximitySensor) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = sensorColor.copy(alpha = 0.14f)
                    ) {
                        Text(
                            text = "${String.format("%.1f", runtime.proximityDistanceCm)} cm",
                            style = MaterialTheme.typography.labelMedium,
                            color = sensorColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (runtime.hasProximitySensor) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = sensorColor.copy(alpha = 0.08f),
                    border = BorderStroke(1.dp, sensorColor.copy(alpha = 0.25f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (runtime.proximityIsNear) strings.sensorNear else strings.sensorFar,
                            style = MaterialTheme.typography.labelLarge,
                            color = sensorColor
                        )
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(sensorColor)
                        )
                    }
                }
            } else {
                Text(
                    text = strings.sensorUnavailable,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Orientation & Context Badges
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (runtime.hasAccelerometer) {
                    val orientationText = when {
                        runtime.isFaceDown -> strings.sensorOrientationFaceDown
                        runtime.isFlatFaceUp -> strings.sensorOrientationFlat
                        else -> strings.sensorOrientationPocket
                    }
                    val orientationColor = when {
                        runtime.isFaceDown -> MaterialTheme.colorScheme.secondary
                        runtime.isFlatFaceUp -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.primary
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = orientationColor.copy(alpha = 0.12f)
                    ) {
                        Text(
                            text = orientationText,
                            style = MaterialTheme.typography.labelSmall,
                            color = orientationColor,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }

                if (runtime.isLandscape && settings.pausePocketInLandscape) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Text(
                            text = strings.sensorPausedLandscape,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }

                if (runtime.isCharging && settings.pausePocketWhileCharging) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Text(
                            text = strings.sensorPausedCharging,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }

            if (runtime.hasProximitySensor) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = strings.sensorTestHint,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun SettingToggleRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    testTag: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onCheckedChange(!checked) }
            .padding(horizontal = 4.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = MaterialTheme.shapes.small,
            color = if (checked) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (checked) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
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
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.testTag(testTag),
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
fun InAppLockSandboxDialog(
    settings: ShieldSettings,
    strings: AppStrings,
    onBlockedTouch: () -> Unit,
    onDismiss: () -> Unit
) {
    var sandboxLocked by remember { mutableStateOf(false) }
    var blockedCount by remember { mutableIntStateOf(0) }
    var dummyClicks by remember { mutableIntStateOf(0) }
    var showBlockedBanner by remember { mutableStateOf(false) }
    var showHud by remember { mutableStateOf(true) }
    val haptic = LocalHapticFeedback.current

    val infiniteTransition = rememberInfiniteTransition(label = "sandboxPulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.92f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sandboxPulseAlpha"
    )

    LaunchedEffect(showBlockedBanner) {
        if (showBlockedBanner) {
            delay(1500)
            showBlockedBanner = false
        }
    }

    LaunchedEffect(sandboxLocked, showHud, settings.hudTimeoutMs) {
        if (sandboxLocked && showHud) {
            delay(settings.hudTimeoutMs)
            showHud = false
        }
    }

    Dialog(
        onDismissRequest = { if (!sandboxLocked) onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = strings.sandboxTitle,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = strings.sandboxSubtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("sandbox_close_button")
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = strings.sandboxExitBtn,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.primaryContainer,
                                            MaterialTheme.colorScheme.secondaryContainer
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.PhotoLibrary,
                                contentDescription = strings.sandboxDummyPhotoTitle,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(64.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = strings.sandboxDummyPhotoTitle,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = strings.sandboxDummyPhotoDesc,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            onClick = { dummyClicks++ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("sandbox_underlying_button")
                        ) {
                            Text("${strings.sandboxTryClicking} ($dummyClicks)")
                        }
                    }
                }

                Button(
                    onClick = {
                        sandboxLocked = true
                        showHud = true
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("sandbox_activate_lock_button"),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Rounded.Lock, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(strings.sandboxLockTriggerBtn)
                }
            }

            AnimatedVisibility(
                visible = sandboxLocked,
                enter = fadeIn(tween(200)),
                exit = fadeOut(tween(200))
            ) {
                val dimAlpha = (settings.customDimPercent.coerceIn(0, 95) / 100f)
                val overlayColor = if (dimAlpha == 0f) {
                    Color.Transparent
                } else {
                    Color(0xFF05080E).copy(alpha = dimAlpha)
                }
                val accentColor = Color(settings.borderColor.colorArgb)
                val effectiveBorderAlpha = if (settings.borderPulseEnabled) pulseAlpha else 0.8f

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(overlayColor)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    blockedCount++
                                    onBlockedTouch()
                                    showBlockedBanner = true
                                    showHud = true
                                }
                            )
                        }
                ) {
                    if (settings.borderEnabled) {
                        if (settings.borderStyle == BorderStyle.FULL_GLOW) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .border(
                                        width = settings.borderThickness.dpValue.dp,
                                        color = accentColor.copy(alpha = effectiveBorderAlpha),
                                        shape = RoundedCornerShape(18.dp)
                                    )
                            )
                        } else {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val strokePx = settings.borderThickness.dpValue.dp.toPx() * 1.4f
                                val arm = 36.dp.toPx()
                                val pad = strokePx / 2f + 4.dp.toPx()
                                val col = accentColor.copy(alpha = effectiveBorderAlpha)
                                val w = size.width
                                val h = size.height
                                drawLine(col, Offset(pad, pad), Offset(pad + arm, pad), strokePx, StrokeCap.Round)
                                drawLine(col, Offset(pad, pad), Offset(pad, pad + arm), strokePx, StrokeCap.Round)
                                drawLine(col, Offset(w - pad - arm, pad), Offset(w - pad, pad), strokePx, StrokeCap.Round)
                                drawLine(col, Offset(w - pad, pad), Offset(w - pad, pad + arm), strokePx, StrokeCap.Round)
                                drawLine(col, Offset(pad, h - pad - arm), Offset(pad, h - pad), strokePx, StrokeCap.Round)
                                drawLine(col, Offset(pad, h - pad), Offset(pad + arm, h - pad), strokePx, StrokeCap.Round)
                                drawLine(col, Offset(w - pad - arm, h - pad), Offset(w - pad, h - pad), strokePx, StrokeCap.Round)
                                drawLine(col, Offset(w - pad, h - pad - arm), Offset(w - pad, h - pad), strokePx, StrokeCap.Round)
                            }
                        }
                    }

                    if (settings.oledClockEnabled) {
                        val clockStr = remember {
                            SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                        }
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = clockStr,
                                style = MaterialTheme.typography.displayMedium,
                                fontWeight = FontWeight.Thin,
                                color = Color.White.copy(alpha = if (dimAlpha > 0.4f) 0.7f else 0.35f)
                            )
                        }
                    }

                    val showTopBadge = when (settings.badgeVisibilityMode) {
                        BadgeVisibilityMode.ALWAYS -> true
                        BadgeVisibilityMode.ON_TOUCH -> showHud || showBlockedBanner
                        BadgeVisibilityMode.HIDDEN -> showBlockedBanner
                    }

                    AnimatedVisibility(
                        visible = showTopBadge,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 18.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color(0xC80F172A),
                            border = BorderStroke(1.dp, accentColor.copy(alpha = 0.75f))
                        ) {
                            Text(
                                text = if (showBlockedBanner) {
                                    "${strings.sandboxTapBlockedToast} (#$blockedCount)"
                                } else {
                                    val brightInfo = if (settings.lockBrightnessPercent >= 0) {
                                        " • ☀ ${settings.lockBrightnessPercent}%"
                                    } else {
                                        ""
                                    }
                                    "🔒 ${strings.overlayTouchBlockedTitle}$brightInfo"
                                },
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = showHud,
                        enter = fadeIn(tween(180)),
                        exit = fadeOut(tween(260)),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 44.dp, start = 24.dp, end = 24.dp)
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.large,
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f),
                            border = BorderStroke(1.dp, accentColor.copy(alpha = 0.5f)),
                            shadowElevation = 12.dp
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 18.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = strings.overlayTouchBlockedTitle,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = strings.overlayUnlockHint(
                                        settings.unlockMethod,
                                        settings.holdDurationMs
                                    ),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(14.dp))

                                when (settings.unlockMethod) {
                                    UnlockMethod.LONG_PRESS -> SandboxHoldUnlockButton(
                                        holdDurationMs = settings.holdDurationMs.toInt(),
                                        accentColor = accentColor,
                                        onUnlocked = {
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            sandboxLocked = false
                                        }
                                    )

                                    UnlockMethod.SLIDE -> SandboxSlideUnlockTrack(
                                        accentColor = accentColor,
                                        onUnlocked = {
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            sandboxLocked = false
                                        }
                                    )

                                    UnlockMethod.DOUBLE_TAP -> SandboxDoubleTapButton(
                                        accentColor = accentColor,
                                        onUnlocked = {
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            sandboxLocked = false
                                        }
                                    )

                                    UnlockMethod.PIN -> SandboxPinUnlockPad(
                                        expectedPin = settings.unlockPin,
                                        accentColor = accentColor,
                                        strings = strings,
                                        biometricEnabled = settings.biometricUnlockEnabled,
                                        emergencyBypassEnabled = settings.emergencyHoldBypassEnabled,
                                        onWrongPin = {
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                        },
                                        onUnlocked = {
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            sandboxLocked = false
                                        }
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
internal fun SandboxHoldUnlockButton(
    holdDurationMs: Int = 1800,
    accentColor: Color,
    onUnlocked: () -> Unit
) {
    var holding by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (holding) 1f else 0f,
        animationSpec = if (holding) tween(holdDurationMs) else tween(220),
        finishedListener = { finalVal ->
            if (finalVal >= 0.99f && holding) {
                holding = false
                onUnlocked()
            }
        },
        label = "holdProgress"
    )
    val primary = accentColor
    val secondary = MaterialTheme.colorScheme.secondary

    Box(
        modifier = Modifier
            .size(78.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        holding = true
                        tryAwaitRelease()
                        holding = false
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = 4.dp.toPx()
            drawCircle(
                color = secondary.copy(alpha = 0.25f),
                style = Stroke(width = stroke)
            )
            if (progress > 0f) {
                drawArc(
                    color = primary,
                    startAngle = -90f,
                    sweepAngle = 360f * progress,
                    useCenter = false,
                    style = Stroke(width = stroke, cap = StrokeCap.Round)
                )
            }
        }
        Surface(
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, primary.copy(alpha = 0.5f))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Rounded.LockOpen,
                    contentDescription = "Hold to unlock",
                    tint = primary
                )
            }
        }
    }
}

@Composable
internal fun SandboxSlideUnlockTrack(
    accentColor: Color,
    onUnlocked: () -> Unit
) {
    val density = LocalDensity.current
    val thumbSizeDp = 46.dp
    val paddingDp = 5.dp

    BoxWithConstraints(
        modifier = Modifier
            .width(240.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(50))
            .padding(paddingDp),
        contentAlignment = Alignment.CenterStart
    ) {
        val maxOffsetPx = with(density) { (maxWidth - thumbSizeDp).toPx().coerceAtLeast(1f) }
        var dragX by remember { mutableFloatStateOf(0f) }
        val animatedX by animateFloatAsState(
            targetValue = dragX,
            animationSpec = spring(),
            label = "slideX"
        )

        Text(
            text = ">>>",
            style = MaterialTheme.typography.labelLarge,
            color = accentColor.copy(alpha = 0.75f),
            modifier = Modifier.align(Alignment.Center)
        )

        Surface(
            modifier = Modifier
                .offset { IntOffset(animatedX.roundToInt(), 0) }
                .size(thumbSizeDp)
                .pointerInput(maxOffsetPx) {
                    detectDragGestures(
                        onDragEnd = {
                            if (dragX >= maxOffsetPx * 0.82f) {
                                onUnlocked()
                            }
                            dragX = 0f
                        },
                        onDragCancel = { dragX = 0f },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            dragX = (dragX + dragAmount.x).coerceIn(0f, maxOffsetPx)
                            if (dragX >= maxOffsetPx * 0.92f) {
                                dragX = 0f
                                onUnlocked()
                            }
                        }
                    )
                },
            shape = CircleShape,
            color = accentColor
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Rounded.LockOpen,
                    contentDescription = "Slide to unlock",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
internal fun SandboxDoubleTapButton(
    accentColor: Color,
    onUnlocked: () -> Unit
) {
    var lastTapMs by remember { mutableLongStateOf(0L) }

    Surface(
        modifier = Modifier
            .size(74.dp)
            .clip(CircleShape)
            .clickable {
                val now = System.currentTimeMillis()
                if (now - lastTapMs <= 450L) {
                    lastTapMs = 0L
                    onUnlocked()
                } else {
                    lastTapMs = now
                }
            },
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(2.dp, accentColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Rounded.LockOpen,
                contentDescription = "Double tap to unlock",
                tint = accentColor,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
