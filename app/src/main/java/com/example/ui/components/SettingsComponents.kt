package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.BatteryChargingFull
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.PhotoLibrary
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.VerifiedUser
import androidx.compose.material.icons.rounded.VideoLibrary
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.BuildConfig
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderStyle
import com.example.data.BubbleStyle
import com.example.data.CustomProfile
import com.example.data.PartialZonePreset
import com.example.data.ShieldRuntimeState
import com.example.data.ShieldSettings
import com.example.data.SmartPreset
import com.example.data.UnlockMethod
import com.example.service.PartialZoneGeometry
import com.example.ui.AppStrings
import com.example.ui.PermissionStatus
import kotlinx.coroutines.delay

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CompactTextInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var focused by remember { mutableStateOf(false) }
    val borderColor = if (focused) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(if (focused) 1.5.dp else 1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = if (focused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    keyboardOptions = keyboardOptions,
                    visualTransformation = visualTransformation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focused = it.isFocused }
                )
            }
            if (trailingContent != null) {
                Spacer(modifier = Modifier.width(4.dp))
                trailingContent()
            }
        }
    }
}

@Composable
fun TightFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .heightIn(min = 30.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        color = if (selected) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (selected) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(13.dp)
                )
            }
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                color = if (selected) {
                    MaterialTheme.colorScheme.onSecondaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun SettingsSubHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(top = 4.dp, bottom = 1.dp)
    )
}

@Composable
fun LiveMiniPreviewCard(
    settings: ShieldSettings,
    strings: AppStrings,
    onTestGesture: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val borderColor = Color(settings.borderColor.colorArgb)
    val effectiveDimPct = if (settings.privacyVeilEnabled) 99 else settings.customDimPercent.coerceIn(0, 95)
    val dimAlpha = effectiveDimPct / 100f
    val stealthHidden = settings.stealthBlackoutEnabled
    val isPartialCutout = settings.partialZoneEnabled && settings.partialZonePreset != PartialZonePreset.FULL_LOCK
    val cutoutNorm = remember(settings.partialZonePreset, settings.partialZoneRect) {
        PartialZoneGeometry.resolveNormalizedRect(settings.partialZonePreset, settings.partialZoneRect)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "previewBorderPulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.45f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )
    val effectiveBorderAlpha = if (settings.borderPulseEnabled) pulseAlpha else 0.85f

    Card(
        modifier = modifier.fillMaxWidth(),
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
                    text = strings.settingsLivePreviewTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                if (onTestGesture != null) {
                    FilledTonalButton(
                        onClick = onTestGesture,
                        modifier = Modifier
                            .height(28.dp)
                            .testTag("preview_test_gesture_button"),
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(
                            horizontal = 8.dp,
                            vertical = 0.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = strings.btnTestGestureHere,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(104.dp)
                    .clip(RoundedCornerShape(12.dp))
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
                val previewW = maxWidth
                val previewH = maxHeight

                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (settings.oledClockEnabled && !stealthHidden && !isPartialCutout) {
                        Text(
                            text = "12:45",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Light,
                            color = if (dimAlpha > 0.45f) Color.White.copy(alpha = 0.78f) else Color(0xFF0F172A)
                        )
                        Text(
                            text = "🔋 85% • ${100 - effectiveDimPct}% ${strings.clearPercentLabel}",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (dimAlpha > 0.45f) Color.White.copy(alpha = 0.65f) else Color(0xFF0F172A).copy(alpha = 0.78f)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.PhotoLibrary,
                            contentDescription = null,
                            tint = Color(0xFF0F172A).copy(alpha = 0.72f),
                            modifier = Modifier.size(34.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${100 - effectiveDimPct}% ${strings.clearPercentLabel}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFF0F172A).copy(alpha = 0.8f)
                        )
                    }
                }

                // Live Dim Layer
                if (dimAlpha > 0f) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF05080E).copy(alpha = dimAlpha))
                    )
                }

                // Live Partial Zone Cutout Box
                if (isPartialCutout) {
                    val cw = (previewW * (cutoutNorm.right - cutoutNorm.left)).coerceAtLeast(24.dp)
                    val ch = (previewH * (cutoutNorm.bottom - cutoutNorm.top)).coerceAtLeast(18.dp)
                    Box(
                        modifier = Modifier
                            .offset(x = previewW * cutoutNorm.left, y = previewH * cutoutNorm.top)
                            .width(cw)
                            .height(ch)
                            .clip(RoundedCornerShape(8.dp))
                            .background(borderColor.copy(alpha = 0.22f))
                            .border(1.5.dp, borderColor, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = strings.overlayPartialZoneBadge,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xB00F172A))
                                .padding(horizontal = 5.dp, vertical = 1.dp)
                        )
                    }
                }

                // Live Edge Border (Full Glow or Corner Frame)
                if (settings.borderEnabled && !stealthHidden) {
                    if (settings.borderStyle == BorderStyle.FULL_GLOW) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    width = settings.borderThickness.dpValue.dp,
                                    color = borderColor.copy(alpha = effectiveBorderAlpha),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        )
                    } else {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val strokePx = settings.borderThickness.dpValue.dp.toPx() * 1.25f
                            val arm = 22.dp.toPx()
                            val pad = strokePx / 2f
                            val col = borderColor.copy(alpha = effectiveBorderAlpha)
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

                // Live Top Status Badge
                if (settings.badgeVisibilityMode != BadgeVisibilityMode.HIDDEN && !stealthHidden) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 7.dp),
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
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                        )
                    }
                }

                if (settings.lockBrightnessPercent >= 0) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(7.dp),
                        shape = RoundedCornerShape(50),
                        color = Color(0xB80F172A)
                    ) {
                        Text(
                            text = "☀ ${settings.lockBrightnessPercent}%",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                // Live Floating Bubble Preview (if enabled)
                if (settings.floatingBubbleEnabled) {
                    val bubbleDp = (settings.bubbleSize.dpSize * 0.62f).dp
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp)
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
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
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
                                        text = strings.bubblePillLabel,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SmartPresetsRow(
    settings: ShieldSettings,
    strings: AppStrings,
    onSelectPreset: (SmartPreset) -> Unit,
    onSaveCurrentProfile: (() -> Unit)? = null,
    onApplyCustomProfile: ((CustomProfile) -> Unit)? = null,
    onRenameCustomProfile: ((CustomProfile) -> Unit)? = null,
    onDeleteCustomProfile: ((CustomProfile) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val rows = remember { SmartPreset.entries.chunked(2) }

    Card(
        modifier = modifier.fillMaxWidth(),
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
                    text = strings.presetsTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (onSaveCurrentProfile != null) {
                    OutlinedButton(
                        onClick = onSaveCurrentProfile,
                        modifier = Modifier
                            .height(28.dp)
                            .testTag("save_custom_profile_button"),
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(
                            horizontal = 8.dp,
                            vertical = 0.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = strings.btnSaveProfile,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                rows.forEach { pair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        pair.forEach { preset ->
                            val isMatched = when (preset) {
                                SmartPreset.POCKET_PHOTO -> settings.customDimPercent == 0 &&
                                    settings.keepScreenOnEnabled &&
                                    settings.pocketAutoLockEnabled &&
                                    settings.floatingBubbleEnabled &&
                                    settings.unlockMethod == UnlockMethod.LONG_PRESS &&
                                    settings.holdDurationMs < 2500L &&
                                    settings.lockBrightnessPercent < 0

                                SmartPreset.KIDS_VIDEO -> settings.customDimPercent == 0 &&
                                    settings.unlockMethod == UnlockMethod.LONG_PRESS &&
                                    settings.holdDurationMs >= 2800L &&
                                    !settings.floatingBubbleEnabled

                                SmartPreset.BATTERY_SAVER -> settings.customDimPercent >= 80 &&
                                    settings.pocketAutoLockEnabled &&
                                    !settings.keepScreenOnEnabled

                                SmartPreset.NAVIGATION_MAP -> settings.customDimPercent == 0 &&
                                    settings.lockBrightnessPercent == 100 &&
                                    !settings.pocketAutoLockEnabled &&
                                    settings.keepScreenOnEnabled
                            }

                            val icon = when (preset) {
                                SmartPreset.POCKET_PHOTO -> Icons.Rounded.PhotoLibrary
                                SmartPreset.KIDS_VIDEO -> Icons.Rounded.VideoLibrary
                                SmartPreset.BATTERY_SAVER -> Icons.Rounded.BatteryChargingFull
                                SmartPreset.NAVIGATION_MAP -> Icons.Rounded.Map
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
                                Row(
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = if (isMatched) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.onSurfaceVariant
                                        },
                                        modifier = Modifier.size(17.dp)
                                    )
                                    Column(modifier = Modifier.weight(1f)) {
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

            if (onApplyCustomProfile != null) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = strings.customProfilesTitle,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (settings.customProfiles.isEmpty()) {
                    Text(
                        text = strings.noCustomProfilesHint,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        settings.customProfiles.forEach { profile ->
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                                modifier = Modifier.testTag("custom_profile_${profile.id}")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable { onApplyCustomProfile(profile) }
                                        .padding(start = 8.dp, end = 2.dp, top = 2.dp, bottom = 2.dp)
                                ) {
                                    Text(
                                        text = profile.name,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    if (onRenameCustomProfile != null) {
                                        IconButton(
                                            onClick = { onRenameCustomProfile(profile) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.Edit,
                                                contentDescription = strings.renameProfileDialogTitle,
                                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                                modifier = Modifier.size(13.dp)
                                            )
                                        }
                                    }
                                    if (onDeleteCustomProfile != null) {
                                        IconButton(
                                            onClick = { onDeleteCustomProfile(profile) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.Close,
                                                contentDescription = strings.btnCancel,
                                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                                modifier = Modifier.size(13.dp)
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
    }
}

@Composable
fun InlineGestureTesterBox(
    settings: ShieldSettings,
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    var unlockedBanner by remember { mutableStateOf(false) }

    LaunchedEffect(unlockedBanner) {
        if (unlockedBanner) {
            delay(1800)
            unlockedBanner = false
        }
    }

    val accentColor = Color(settings.borderColor.colorArgb)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("inline_gesture_tester"),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = strings.inlineTesterTitle,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = if (unlockedBanner) {
                    "✓ ${strings.inlineTesterSuccess}"
                } else {
                    strings.overlayUnlockHint(settings.unlockMethod, settings.holdDurationMs)
                },
                style = MaterialTheme.typography.bodySmall,
                color = if (unlockedBanner) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            Spacer(modifier = Modifier.height(6.dp))

            when (settings.unlockMethod) {
                UnlockMethod.LONG_PRESS -> SandboxHoldUnlockButton(
                    holdDurationMs = settings.holdDurationMs.toInt(),
                    accentColor = accentColor,
                    onUnlocked = { unlockedBanner = true }
                )

                UnlockMethod.SLIDE -> SandboxSlideUnlockTrack(
                    accentColor = accentColor,
                    onUnlocked = { unlockedBanner = true }
                )

                UnlockMethod.DOUBLE_TAP -> SandboxDoubleTapButton(
                    accentColor = accentColor,
                    onUnlocked = { unlockedBanner = true }
                )

                UnlockMethod.PIN -> SandboxPinUnlockPad(
                    expectedPin = settings.unlockPin,
                    accentColor = accentColor,
                    strings = strings,
                    biometricEnabled = settings.biometricUnlockEnabled,
                    emergencyBypassEnabled = settings.emergencyHoldBypassEnabled,
                    kidsSafetyEnabled = settings.kidsSafetyEnabled,
                    wrongAttemptCooldownEnabled = settings.wrongAttemptCooldownEnabled,
                    onWrongPin = {},
                    onUnlocked = { unlockedBanner = true }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PrivacyTrustCard(
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("privacy_trust_card"),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.VerifiedUser,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "${strings.privacyCardTitle} • v${BuildConfig.VERSION_NAME}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                listOf(
                    strings.privacyBadgeOffline,
                    strings.privacyBadgeNoNet,
                    strings.privacyBadgeZeroTrack
                ).forEach { badge ->
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)
                    ) {
                        Text(
                            text = "✓ $badge",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = strings.privacyCardDesc,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
                    .padding(horizontal = 10.dp, vertical = 7.dp)
                    .testTag(testTag),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(28.dp),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
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
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 8.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(6.dp))
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
    onOpenBatterySettings: () -> Unit,
    onOpenUsageAccess: () -> Unit = {}
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
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
            label = strings.diagUsageAccess,
            value = if (permissions.hasUsageAccessPermission) strings.permGranted else strings.permOptional,
            isHealthy = permissions.hasUsageAccessPermission,
            onClick = onOpenUsageAccess
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
                .padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isHealthy) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
                )
                Icon(
                    imageVector = if (isHealthy) Icons.Rounded.CheckCircle else Icons.Rounded.WarningAmber,
                    contentDescription = null,
                    tint = if (isHealthy) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}
