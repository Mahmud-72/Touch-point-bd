package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.CropFree
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.NormalizedRect
import com.example.data.PartialZonePreset
import com.example.service.PartialZoneGeometry
import com.example.service.WatchedAppInfo
import com.example.ui.AppStrings
import kotlin.math.roundToInt

@Composable
fun UsageAccessDisclosureDialog(
    strings: AppStrings,
    onConfirmOpenSettings: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Rounded.Security,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text(
                text = strings.usageAccessDisclosureTitle,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = strings.usageAccessDisclosureBody,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmOpenSettings()
                    onDismiss()
                },
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier
                    .height(36.dp)
                    .testTag("confirm_open_usage_access_btn")
            ) {
                Text(strings.btnOpenUsageAccess)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(strings.btnCancel)
            }
        }
    )
}

@Composable
fun AppWatchPickerDialog(
    apps: List<WatchedAppInfo>,
    selectedPackages: Set<String>,
    strings: AppStrings,
    onTogglePackage: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredApps = remember(apps, searchQuery) {
        val q = searchQuery.trim().lowercase()
        if (q.isEmpty()) {
            apps
        } else {
            apps.filter {
                it.label.lowercase().contains(q) || it.packageName.lowercase().contains(q)
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Apps,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = strings.appWatchPickerTitle,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                CompactTextInputBox(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = strings.appWatchSearchHint,
                    leadingIcon = Icons.Rounded.Search,
                    trailingContent = if (searchQuery.isNotEmpty()) {
                        {
                            IconButton(
                                onClick = { searchQuery = "" },
                                modifier = Modifier.size(26.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = strings.btnCancel,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    } else null,
                    modifier = Modifier.testTag("app_watch_search_input")
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 160.dp, max = 300.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(filteredApps, key = { it.packageName }) { app ->
                        val isSelected = app.packageName in selectedPackages
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.small)
                                .clickable { onTogglePackage(app.packageName) }
                                .testTag("watched_app_item_${app.packageName}"),
                            shape = MaterialTheme.shapes.small,
                            color = if (isSelected) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f)
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                            },
                            border = BorderStroke(
                                1.dp,
                                if (isSelected) {
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.65f)
                                } else {
                                    MaterialTheme.colorScheme.outlineVariant
                                }
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = if (isSelected) {
                                        Icons.Rounded.CheckCircle
                                    } else {
                                        Icons.Rounded.RadioButtonUnchecked
                                    },
                                    contentDescription = null,
                                    tint = if (isSelected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    },
                                    modifier = Modifier.size(18.dp)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = app.label,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = app.packageName,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier
                    .height(36.dp)
                    .testTag("app_watch_picker_done")
            ) {
                Text(strings.btnSave)
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PartialZoneEditorCard(
    preset: PartialZonePreset,
    customRect: NormalizedRect,
    accentColor: Color,
    strings: AppStrings,
    onPresetSelected: (PartialZonePreset) -> Unit,
    onCustomRectChanged: (NormalizedRect) -> Unit,
    onHelpClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val resolvedRect = remember(preset, customRect) {
        PartialZoneGeometry.resolveNormalizedRect(preset, customRect)
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = strings.partialZonePresetLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                if (onHelpClick != null) {
                    HelpInfoIconButton(
                        contentDescription = strings.helpActionDesc,
                        onClick = onHelpClick,
                        modifier = Modifier.testTag("help_partial_zone_editor")
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                PartialZonePreset.entries.forEach { option ->
                    TightFilterChip(
                        selected = preset == option,
                        onClick = { onPresetSelected(option) },
                        label = strings.partialZonePresetLabel(option),
                        modifier = Modifier.testTag("partial_preset_${option.storageKey}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(7.dp))

            // Interactive visual phone cutout preview
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .width(74.dp)
                        .height(116.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF0F172A).copy(alpha = 0.85f))
                        .border(1.dp, accentColor.copy(alpha = 0.65f), RoundedCornerShape(10.dp))
                ) {
                    val wDp = maxWidth
                    val hDp = maxHeight
                    if (preset != PartialZonePreset.FULL_LOCK) {
                        val cutW = (wDp * (resolvedRect.right - resolvedRect.left)).coerceAtLeast(14.dp)
                        val cutH = (hDp * (resolvedRect.bottom - resolvedRect.top)).coerceAtLeast(14.dp)
                        val cutX = wDp * resolvedRect.left
                        val cutY = hDp * resolvedRect.top
                        Box(
                            modifier = Modifier
                                .offset(x = cutX, y = cutY)
                                .width(cutW)
                                .height(cutH)
                                .clip(RoundedCornerShape(6.dp))
                                .background(accentColor.copy(alpha = 0.24f))
                                .border(1.5.dp, accentColor, RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.CropFree,
                                contentDescription = strings.overlayPartialZoneBadge,
                                tint = accentColor,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = strings.overlayPartialZoneBadge,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = accentColor
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = strings.partialZoneHint,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            AnimatedVisibility(visible = preset == PartialZonePreset.CUSTOM) {
                Column(modifier = Modifier.padding(top = 6.dp)) {
                    EdgeSliderRow(
                        label = strings.partialZoneTopEdge,
                        percent = (customRect.top * 100).roundToInt(),
                        value = customRect.top,
                        range = 0.05f..(customRect.bottom - 0.16f).coerceAtLeast(0.06f),
                        onValueChange = { newTop ->
                            onCustomRectChanged(customRect.copy(top = newTop))
                        }
                    )
                    EdgeSliderRow(
                        label = strings.partialZoneBottomEdge,
                        percent = (customRect.bottom * 100).roundToInt(),
                        value = customRect.bottom,
                        range = (customRect.top + 0.16f).coerceAtMost(0.94f)..0.95f,
                        onValueChange = { newBottom ->
                            onCustomRectChanged(customRect.copy(bottom = newBottom))
                        }
                    )
                    EdgeSliderRow(
                        label = strings.partialZoneLeftEdge,
                        percent = (customRect.left * 100).roundToInt(),
                        value = customRect.left,
                        range = 0.0f..(customRect.right - 0.20f).coerceAtLeast(0.02f),
                        onValueChange = { newLeft ->
                            onCustomRectChanged(customRect.copy(left = newLeft))
                        }
                    )
                    EdgeSliderRow(
                        label = strings.partialZoneRightEdge,
                        percent = (customRect.right * 100).roundToInt(),
                        value = customRect.right,
                        range = (customRect.left + 0.20f).coerceAtMost(0.98f)..1.0f,
                        onValueChange = { newRight ->
                            onCustomRectChanged(customRect.copy(right = newRight))
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun EdgeSliderRow(
    label: String,
    percent: Int,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "$percent%",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 28.dp) {
            Slider(
                value = value.coerceIn(range.start, range.endInclusive),
                onValueChange = onValueChange,
                valueRange = range
            )
        }
    }
}
