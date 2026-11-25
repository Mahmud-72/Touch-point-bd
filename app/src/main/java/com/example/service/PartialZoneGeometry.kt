package com.example.service

import com.example.data.NormalizedRect
import com.example.data.PartialZonePreset

data class OverlayStripBounds(
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int
)

data class PartialZoneLayout(
    val cutout: OverlayStripBounds,
    val topStrip: OverlayStripBounds?,
    val bottomStrip: OverlayStripBounds?,
    val leftStrip: OverlayStripBounds?,
    val rightStrip: OverlayStripBounds?
)

object PartialZoneGeometry {

    fun resolveNormalizedRect(
        preset: PartialZonePreset,
        customRect: NormalizedRect
    ): NormalizedRect {
        return when (preset) {
            PartialZonePreset.FULL_LOCK -> NormalizedRect(0f, 0f, 0f, 0f)
            PartialZonePreset.TOP_HALF -> NormalizedRect(0.02f, 0.05f, 0.98f, 0.48f)
            PartialZonePreset.BOTTOM_HALF -> NormalizedRect(0.02f, 0.50f, 0.98f, 0.91f)
            PartialZonePreset.CENTER_WINDOW -> NormalizedRect(0.06f, 0.22f, 0.94f, 0.56f)
            PartialZonePreset.CUSTOM -> customRect.clamped()
        }
    }

    fun computeFourStripLayout(
        screenWidthPx: Int,
        screenHeightPx: Int,
        preset: PartialZonePreset,
        customRect: NormalizedRect
    ): PartialZoneLayout {
        val w = screenWidthPx.coerceAtLeast(100)
        val h = screenHeightPx.coerceAtLeast(100)
        val norm = resolveNormalizedRect(preset, customRect).clamped()

        val cutLeft = (norm.left * w).toInt().coerceIn(0, w - 40)
        val cutTop = (norm.top * h).toInt().coerceIn(0, h - 40)
        val cutRight = (norm.right * w).toInt().coerceIn(cutLeft + 40, w)
        val cutBottom = (norm.bottom * h).toInt().coerceIn(cutTop + 40, h)
        val cutWidth = cutRight - cutLeft
        val cutHeight = cutBottom - cutTop

        val topStrip = if (cutTop > 4) {
            OverlayStripBounds(left = 0, top = 0, width = w, height = cutTop)
        } else null

        val bottomHeight = h - cutBottom
        val bottomStrip = if (bottomHeight > 4) {
            OverlayStripBounds(left = 0, top = cutBottom, width = w, height = bottomHeight)
        } else null

        val leftStrip = if (cutLeft > 4 && cutHeight > 0) {
            OverlayStripBounds(left = 0, top = cutTop, width = cutLeft, height = cutHeight)
        } else null

        val rightWidth = w - cutRight
        val rightStrip = if (rightWidth > 4 && cutHeight > 0) {
            OverlayStripBounds(left = cutRight, top = cutTop, width = rightWidth, height = cutHeight)
        } else null

        return PartialZoneLayout(
            cutout = OverlayStripBounds(cutLeft, cutTop, cutWidth, cutHeight),
            topStrip = topStrip,
            bottomStrip = bottomStrip,
            leftStrip = leftStrip,
            rightStrip = rightStrip
        )
    }
}

class WrongAttemptCooldownTracker {
    var wrongAttempts: Int = 0
        private set
    private var lockedUntilMs: Long = 0L

    fun recordWrongAttempt(nowMs: Long = System.currentTimeMillis()): Int {
        wrongAttempts += 1
        val cooldownSec = when {
            wrongAttempts >= 8 -> 30
            wrongAttempts >= 5 -> 15
            else -> 0
        }
        if (cooldownSec > 0) {
            lockedUntilMs = nowMs + cooldownSec * 1000L
        }
        return cooldownSec
    }

    fun remainingCooldownSeconds(nowMs: Long = System.currentTimeMillis()): Int {
        val diff = lockedUntilMs - nowMs
        if (diff <= 0L) return 0
        return ((diff + 999L) / 1000L).toInt()
    }

    fun isCoolingDown(nowMs: Long = System.currentTimeMillis()): Boolean =
        remainingCooldownSeconds(nowMs) > 0

    fun reset() {
        wrongAttempts = 0
        lockedUntilMs = 0L
    }
}
