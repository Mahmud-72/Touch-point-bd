package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.LockSource
import com.example.data.ShieldPreferences
import com.example.data.ShieldSettings
import com.example.ui.AppStrings
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnalyticsCard(
    settings: ShieldSettings,
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    val resolvedLocale = remember(settings.appLanguage) {
        val resolved = settings.appLanguage.resolve()
        Locale.forLanguageTag(resolved.storageKey)
    }
    val entries = remember(settings.dailyBlockedHistory, resolvedLocale) {
        ShieldPreferences.buildLast7DayEntries(
            history = settings.dailyBlockedHistory,
            locale = resolvedLocale
        )
    }
    val maxVal = remember(entries) {
        entries.maxOfOrNull { it.blockedTouches }?.coerceAtLeast(1L) ?: 1L
    }
    val hasChartData = entries.any { it.blockedTouches > 0L }

    val topSources = remember(settings.lockSourceCounts) {
        settings.lockSourceCounts.entries
            .mapNotNull { (key, count) ->
                val src = runCatching { LockSource.valueOf(key) }.getOrNull()
                if (src != null && src != LockSource.NONE && count > 0L) src to count else null
            }
            .sortedByDescending { it.second }
            .take(4)
    }

    val barColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("analytics_card"),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.BarChart,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = strings.analyticsTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (!hasChartData) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                ) {
                    Text(
                        text = strings.analyticsEmptyHint,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                }
            } else {
                // Bar chart Canvas + count labels
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    entries.forEach { entry ->
                        Text(
                            text = if (entry.blockedTouches > 0) entry.blockedTouches.toString() else "",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(82.dp)
                ) {
                    val count = entries.size.coerceAtLeast(1)
                    val slotWidth = size.width / count
                    val barWidth = (slotWidth * 0.48f).coerceAtMost(28.dp.toPx())
                    val radius = CornerRadius(6.dp.toPx(), 6.dp.toPx())

                    entries.forEachIndexed { idx, entry ->
                        val left = idx * slotWidth + (slotWidth - barWidth) / 2f
                        // Background track
                        drawRoundRect(
                            color = trackColor,
                            topLeft = Offset(left, 0f),
                            size = Size(barWidth, size.height),
                            cornerRadius = radius
                        )
                        if (entry.blockedTouches > 0L) {
                            val ratio = (entry.blockedTouches.toFloat() / maxVal.toFloat()).coerceIn(0.12f, 1f)
                            val barHeight = size.height * ratio
                            drawRoundRect(
                                color = barColor,
                                topLeft = Offset(left, size.height - barHeight),
                                size = Size(barWidth, barHeight),
                                cornerRadius = radius
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    entries.forEach { entry ->
                        Text(
                            text = entry.label,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            if (topSources.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = strings.analyticsSourcesTitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    topSources.forEach { (source, count) ->
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.65f)
                        ) {
                            Text(
                                text = "${strings.lockSourceLabel(source)} • $count",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
