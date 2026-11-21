package com.example.service

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.BatteryManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.R
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderStyle
import com.example.data.BubbleStyle
import com.example.data.ShieldSettings
import com.example.data.UnlockMethod
import com.example.ui.getAppStrings
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

class OverlayWindowController(
    context: Context,
    private val onBubbleLockClicked: () -> Unit,
    private val onBubbleDoubleTapHidden: () -> Unit,
    private val onCountdownCancelled: () -> Unit,
    private val onTouchBlocked: () -> Unit,
    private val onBiometricRequested: () -> Unit,
    private val onUnlockTriggered: () -> Unit
) {
    private val overlayContext: Context = ContextThemeWrapper(
        context.applicationContext,
        android.R.style.Theme_Translucent_NoTitleBar
    )
    private val windowManager = overlayContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val handler = Handler(Looper.getMainLooper())

    private var bubbleView: FrameLayout? = null
    private var bubbleParams: WindowManager.LayoutParams? = null
    private var savedBubbleX = dp(16)
    private var savedBubbleY = dp(240)
    private var lastBubbleSizeKey: String? = null
    private var lastBubbleStyleKey: String? = null
    private var lastBubbleColorArgb: Int? = null
    private var lastBubbleLangKey: String? = null
    private var bubbleTuckRunnable: Runnable? = null
    private var pendingSingleTapRunnable: Runnable? = null
    private var lastBubbleTapTime = 0L

    private var countdownView: TextView? = null

    private var lockOverlayView: FrameLayout? = null
    private var lockOverlayParams: WindowManager.LayoutParams? = null
    private var unlockHudContainer: LinearLayout? = null
    private var statusBadgePill: TextView? = null
    private var borderFrameView: BorderOverlayView? = null
    private var borderPulseAnimator: ValueAnimator? = null
    private var oledClockContainer: LinearLayout? = null
    private var oledClockText: TextView? = null
    private var oledBatteryText: TextView? = null
    private var oledClockRunnable: Runnable? = null
    private var hideHudRunnable: Runnable? = null

    private var currentSettings = ShieldSettings()

    fun updateSettings(
        settings: ShieldSettings,
        isServiceArmed: Boolean,
        isScreenLocked: Boolean,
        isBubbleTemporarilyHidden: Boolean
    ) {
        currentSettings = settings
        if (!canDrawOverlays()) {
            removeAllOverlays()
            return
        }

        if (isScreenLocked) {
            hideCountdownOverlay()
            hideFloatingBubble()
            if (lockOverlayView == null) {
                showTouchLockOverlay()
            } else {
                refreshTouchLockOverlayAppearance()
            }
        } else {
            hideTouchLockOverlay()
            if (isServiceArmed && settings.floatingBubbleEnabled && !isBubbleTemporarilyHidden) {
                val styleChanged = lastBubbleSizeKey != settings.bubbleSize.storageKey ||
                    lastBubbleStyleKey != settings.bubbleStyle.storageKey ||
                    lastBubbleColorArgb != settings.borderColor.colorArgb ||
                    lastBubbleLangKey != settings.appLanguage.storageKey
                if (bubbleView == null) {
                    showFloatingBubble()
                } else if (styleChanged) {
                    hideFloatingBubble()
                    showFloatingBubble()
                } else {
                    if (!settings.bubbleAutoTuckEnabled) {
                        cancelBubbleAutoTuck()
                        bubbleView?.animate()?.translationX(0f)?.alpha(settings.floatingBubbleOpacity)
                            ?.setDuration(150)?.start()
                    } else {
                        bubbleView?.alpha = settings.floatingBubbleOpacity
                        scheduleBubbleAutoTuck()
                    }
                }
            } else {
                hideFloatingBubble()
            }
        }
    }

    fun showCountdownOverlay(secondsRemaining: Int) {
        if (!canDrawOverlays()) return
        val strings = getAppStrings(currentSettings.appLanguage)
        val text = "${strings.overlayCountdownLabel}: ${secondsRemaining}s • ${strings.overlayCountdownCancel}"

        val existing = countdownView
        if (existing != null) {
            existing.text = text
            return
        }

        val pill = TextView(overlayContext).apply {
            this.text = text
            setTextColor(0xFFFFFFFF.toInt())
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(dp(18), dp(12), dp(18), dp(12))
            background = GradientDrawable().apply {
                setColor(0xE80F172A.toInt())
                cornerRadius = dp(50).toFloat()
                setStroke(dp(1.5f), currentSettings.borderColor.colorArgb)
            }
            elevation = dp(10).toFloat()
            setOnClickListener {
                triggerHaptic(light = true)
                onCountdownCancelled()
            }
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            overlayWindowType(),
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            y = dp(64)
            dimAmount = 0f
        }

        try {
            windowManager.addView(pill, params)
            countdownView = pill
        } catch (_: Exception) {
            countdownView = null
        }
    }

    fun hideCountdownOverlay() {
        countdownView?.let { view ->
            try {
                windowManager.removeView(view)
            } catch (_: Exception) {
            }
        }
        countdownView = null
    }

    fun onConfigurationChanged() {
        if (lockOverlayView != null) {
            hideTouchLockOverlay()
            showTouchLockOverlay()
        }
        val bubble = bubbleView ?: return
        val params = bubbleParams ?: return
        val metrics = overlayContext.resources.displayMetrics
        val maxX = (metrics.widthPixels - bubble.width - dp(12)).coerceAtLeast(dp(12))
        val maxY = (metrics.heightPixels - bubble.height - dp(24)).coerceAtLeast(dp(24))
        params.x = params.x.coerceIn(dp(12), maxX)
        params.y = params.y.coerceIn(dp(24), maxY)
        savedBubbleX = params.x
        savedBubbleY = params.y
        bubble.translationX = 0f
        try {
            windowManager.updateViewLayout(bubble, params)
            snapBubbleToNearestEdge(bubble, params)
            scheduleBubbleAutoTuck()
        } catch (_: Exception) {
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun showTouchLockOverlay() {
        if (!canDrawOverlays() || lockOverlayView != null) return

        val strings = getAppStrings(currentSettings.appLanguage)
        val root = FrameLayout(overlayContext).apply {
            setBackgroundColor(overlayBackgroundColor())
            isClickable = true
            isFocusable = false
        }

        // 1. Edge Border View (Full Glow OR Corner Brackets + Optional Pulse)
        val borderView = BorderOverlayView(overlayContext).apply {
            configure(
                color = applyAlpha(currentSettings.borderColor.colorArgb, 0xCC),
                strokeWidthPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    currentSettings.borderThickness.dpValue,
                    overlayContext.resources.displayMetrics
                ),
                cornerRadiusPx = dp(18).toFloat(),
                style = currentSettings.borderStyle
            )
            visibility = if (currentSettings.borderEnabled) View.VISIBLE else View.GONE
            alpha = 0.85f
        }
        root.addView(
            borderView,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(dp(4), dp(4), dp(4), dp(4))
            }
        )

        // 2. OLED Clock & Battery Info Overlay (Subtle centered HUD)
        val oledBox = LinearLayout(overlayContext).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(dp(22), dp(14), dp(22), dp(14))
            background = GradientDrawable().apply {
                setColor(0x6605080E)
                cornerRadius = dp(20).toFloat()
            }
            visibility = if (currentSettings.oledClockEnabled) View.VISIBLE else View.GONE
            alpha = 0.78f
        }
        val clockTv = TextView(overlayContext).apply {
            setTextColor(0xFFE2E8F0.toInt())
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
            typeface = Typeface.MONOSPACE
            gravity = Gravity.CENTER
        }
        val batteryTv = TextView(overlayContext).apply {
            setTextColor(currentSettings.borderColor.colorArgb)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        oledBox.addView(clockTv)
        oledBox.addView(batteryTv)
        root.addView(
            oledBox,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
        )

        // 3. Top Status Badge
        val topBadge = TextView(overlayContext).apply {
            text = "🔒 ${strings.overlayTouchBlockedTitle}"
            setTextColor(0xFFFFFFFF.toInt())
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(dp(14), dp(6), dp(14), dp(6))
            background = GradientDrawable().apply {
                setColor(0xB80F172A.toInt())
                cornerRadius = dp(50).toFloat()
                setStroke(dp(1), applyAlpha(currentSettings.borderColor.colorArgb, 0x80))
            }
            visibility = when (currentSettings.badgeVisibilityMode) {
                BadgeVisibilityMode.ALWAYS -> View.VISIBLE
                BadgeVisibilityMode.ON_TOUCH -> View.VISIBLE
                BadgeVisibilityMode.HIDDEN -> View.GONE
            }
            alpha = 0.85f
        }
        root.addView(
            topBadge,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                topMargin = dp(38)
            }
        )

        // 4. Bottom Unlock HUD
        val hudContainer = LinearLayout(overlayContext).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(dp(20), dp(16), dp(20), dp(16))
            background = GradientDrawable().apply {
                setColor(0xEB0F172A.toInt())
                cornerRadius = dp(24).toFloat()
                setStroke(dp(1), applyAlpha(currentSettings.borderColor.colorArgb, 0x99))
            }
            elevation = dp(12).toFloat()
            alpha = 1f
        }

        val titleView = TextView(overlayContext).apply {
            text = strings.overlayTouchBlockedTitle
            setTextColor(0xFFF8FAFC.toInt())
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val hintView = TextView(overlayContext).apply {
            text = strings.overlayUnlockHint(currentSettings.unlockMethod, currentSettings.holdDurationMs)
            setTextColor(0xFFCBD5E1.toInt())
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            gravity = Gravity.CENTER
            setPadding(0, dp(4), 0, dp(12))
        }

        hudContainer.addView(titleView)
        hudContainer.addView(hintView)

        val unlockWidget = when (currentSettings.unlockMethod) {
            UnlockMethod.LONG_PRESS -> createHoldUnlockView()
            UnlockMethod.SLIDE -> createSlideUnlockView()
            UnlockMethod.DOUBLE_TAP -> createDoubleTapUnlockView()
            UnlockMethod.PIN -> createPinUnlockView(hintView)
        }
        hudContainer.addView(unlockWidget)

        val isLandscape = overlayContext.resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
        val hudLp = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            bottomMargin = if (isLandscape) dp(16) else dp(48)
        }
        root.addView(hudContainer, hudLp)

        root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                onTouchBlocked()
                if (currentSettings.borderEnabled && !currentSettings.borderPulseEnabled) {
                    borderView.animate().cancel()
                    borderView.alpha = 0.95f
                    borderView.animate().alpha(0.55f).setDuration(600L).start()
                }
                revealHudTemporarily()
            }
            true
        }

        val params = createLockLayoutParams()
        try {
            windowManager.addView(root, params)
            lockOverlayView = root
            lockOverlayParams = params
            unlockHudContainer = hudContainer
            statusBadgePill = topBadge
            borderFrameView = borderView
            oledClockContainer = oledBox
            oledClockText = clockTv
            oledBatteryText = batteryTv

            startOrStopBorderPulse()
            startOrStopOledClockTicker()
            scheduleHudHide(currentSettings.hudTimeoutMs)
        } catch (_: Exception) {
            lockOverlayView = null
        }
    }

    fun hideTouchLockOverlay() {
        hideHudRunnable?.let { handler.removeCallbacks(it) }
        hideHudRunnable = null
        stopBorderPulse()
        stopOledClockTicker()
        lockOverlayView?.let { view ->
            try {
                windowManager.removeView(view)
            } catch (_: Exception) {
            }
        }
        lockOverlayView = null
        lockOverlayParams = null
        unlockHudContainer = null
        statusBadgePill = null
        borderFrameView = null
        oledClockContainer = null
        oledClockText = null
        oledBatteryText = null
    }

    @SuppressLint("ClickableViewAccessibility")
    fun showFloatingBubble() {
        if (!canDrawOverlays() || bubbleView != null) return

        val strings = getAppStrings(currentSettings.appLanguage)
        val sizeDp = currentSettings.bubbleSize.dpSize
        val widthPx = when (currentSettings.bubbleStyle) {
            BubbleStyle.COMPACT_PILL -> dp((sizeDp * 1.48f).toInt())
            BubbleStyle.MINIMAL_DOT -> dp((sizeDp * 0.78f).toInt())
            BubbleStyle.SHIELD_CIRCLE -> dp(sizeDp)
        }
        val heightPx = when (currentSettings.bubbleStyle) {
            BubbleStyle.COMPACT_PILL -> dp((sizeDp * 0.78f).toInt())
            BubbleStyle.MINIMAL_DOT -> dp((sizeDp * 0.78f).toInt())
            BubbleStyle.SHIELD_CIRCLE -> dp(sizeDp)
        }

        val borderColor = currentSettings.borderColor.colorArgb
        val container = FrameLayout(overlayContext).apply {
            background = GradientDrawable().apply {
                shape = if (currentSettings.bubbleStyle == BubbleStyle.COMPACT_PILL) {
                    GradientDrawable.RECTANGLE
                } else {
                    GradientDrawable.OVAL
                }
                if (currentSettings.bubbleStyle == BubbleStyle.COMPACT_PILL) {
                    cornerRadius = heightPx / 2f
                }
                setColor(0xF2FFFFFF.toInt())
                setStroke(dp(2), borderColor)
            }
            elevation = dp(10).toFloat()
            alpha = currentSettings.floatingBubbleOpacity
        }

        when (currentSettings.bubbleStyle) {
            BubbleStyle.MINIMAL_DOT -> {
                val dot = View(overlayContext).apply {
                    background = GradientDrawable().apply {
                        shape = GradientDrawable.OVAL
                        setColor(borderColor)
                    }
                }
                val dotSize = (widthPx * 0.42f).toInt()
                container.addView(
                    dot,
                    FrameLayout.LayoutParams(dotSize, dotSize).apply {
                        gravity = Gravity.CENTER
                    }
                )
            }

            BubbleStyle.COMPACT_PILL -> {
                val row = LinearLayout(overlayContext).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    setPadding(dp(8), 0, dp(8), 0)
                }
                val icon = ImageView(overlayContext).apply {
                    setImageDrawable(ContextCompat.getDrawable(overlayContext, R.drawable.ic_shield_lock))
                    setColorFilter(borderColor)
                }
                val iconSize = (heightPx * 0.50f).toInt()
                row.addView(icon, LinearLayout.LayoutParams(iconSize, iconSize))
                val label = TextView(overlayContext).apply {
                    text = strings.bubblePillLabel
                    setTextColor(0xFF0F172A.toInt())
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
                    typeface = Typeface.DEFAULT_BOLD
                    setPadding(dp(4), 0, 0, 0)
                }
                row.addView(label)
                container.addView(
                    row,
                    FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                )
            }

            BubbleStyle.SHIELD_CIRCLE -> {
                val icon = ImageView(overlayContext).apply {
                    setImageDrawable(ContextCompat.getDrawable(overlayContext, R.drawable.ic_shield_lock))
                    setColorFilter(borderColor)
                    val pad = (sizeDp * 0.24f).toInt()
                    setPadding(dp(pad), dp(pad), dp(pad), dp(pad))
                    contentDescription = strings.btnLockScreenNow
                }
                container.addView(
                    icon,
                    FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                )
            }
        }

        val params = WindowManager.LayoutParams(
            widthPx,
            heightPx,
            overlayWindowType(),
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            dimAmount = 0f
            x = savedBubbleX
            y = savedBubbleY
        }

        val touchSlop = ViewConfiguration.get(overlayContext).scaledTouchSlop
        var initialX = 0
        var initialY = 0
        var initialTouchX = 0f
        var initialTouchY = 0f
        var isDragging = false

        container.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    cancelBubbleAutoTuck()
                    view.animate().translationX(0f).alpha(currentSettings.floatingBubbleOpacity)
                        .scaleX(0.92f).scaleY(0.92f).setDuration(90).start()
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    isDragging = false
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    val dx = (event.rawX - initialTouchX).toInt()
                    val dy = (event.rawY - initialTouchY).toInt()
                    if (!isDragging && (abs(dx) > touchSlop || abs(dy) > touchSlop)) {
                        isDragging = true
                    }
                    if (isDragging) {
                        params.x = initialX + dx
                        params.y = initialY + dy
                        savedBubbleX = params.x
                        savedBubbleY = params.y
                        try {
                            windowManager.updateViewLayout(view, params)
                        } catch (_: Exception) {
                        }
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    view.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
                    if (!isDragging) {
                        handleBubbleTap()
                    } else {
                        snapBubbleToNearestEdge(view, params)
                        scheduleBubbleAutoTuck()
                    }
                    true
                }

                MotionEvent.ACTION_CANCEL -> {
                    view.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
                    scheduleBubbleAutoTuck()
                    true
                }

                else -> false
            }
        }

        try {
            windowManager.addView(container, params)
            bubbleView = container
            bubbleParams = params
            lastBubbleSizeKey = currentSettings.bubbleSize.storageKey
            lastBubbleStyleKey = currentSettings.bubbleStyle.storageKey
            lastBubbleColorArgb = currentSettings.borderColor.colorArgb
            lastBubbleLangKey = currentSettings.appLanguage.storageKey
            scheduleBubbleAutoTuck()
        } catch (_: Exception) {
            bubbleView = null
        }
    }

    private fun handleBubbleTap() {
        if (!currentSettings.bubbleDoubleTapHideEnabled) {
            triggerHaptic(light = true)
            onBubbleLockClicked()
            return
        }

        val now = System.currentTimeMillis()
        if (now - lastBubbleTapTime <= 240L) {
            pendingSingleTapRunnable?.let { handler.removeCallbacks(it) }
            pendingSingleTapRunnable = null
            lastBubbleTapTime = 0L
            triggerHaptic(light = false)
            onBubbleDoubleTapHidden()
        } else {
            lastBubbleTapTime = now
            pendingSingleTapRunnable?.let { handler.removeCallbacks(it) }
            val runnable = Runnable {
                lastBubbleTapTime = 0L
                triggerHaptic(light = true)
                onBubbleLockClicked()
            }
            pendingSingleTapRunnable = runnable
            handler.postDelayed(runnable, 250L)
        }
    }

    private fun cancelBubbleAutoTuck() {
        bubbleTuckRunnable?.let { handler.removeCallbacks(it) }
        bubbleTuckRunnable = null
    }

    private fun scheduleBubbleAutoTuck() {
        cancelBubbleAutoTuck()
        if (!currentSettings.bubbleAutoTuckEnabled) return
        val runnable = Runnable {
            val bubble = bubbleView ?: return@Runnable
            val params = bubbleParams ?: return@Runnable
            val displayWidth = overlayContext.resources.displayMetrics.widthPixels
            val isLeft = params.x + bubble.width / 2 < displayWidth / 2
            val shift = if (isLeft) -(bubble.width * 0.42f) else (bubble.width * 0.42f)
            bubble.animate()
                .translationX(shift)
                .alpha((currentSettings.floatingBubbleOpacity * 0.62f).coerceAtLeast(0.28f))
                .setDuration(260)
                .start()
        }
        bubbleTuckRunnable = runnable
        handler.postDelayed(runnable, 3000L)
    }

    fun hideFloatingBubble() {
        cancelBubbleAutoTuck()
        pendingSingleTapRunnable?.let { handler.removeCallbacks(it) }
        pendingSingleTapRunnable = null
        bubbleView?.let { view ->
            try {
                windowManager.removeView(view)
            } catch (_: Exception) {
            }
        }
        bubbleView = null
        bubbleParams = null
    }

    fun removeAllOverlays() {
        hideCountdownOverlay()
        hideTouchLockOverlay()
        hideFloatingBubble()
    }

    private fun refreshTouchLockOverlayAppearance() {
        val view = lockOverlayView ?: return
        val params = lockOverlayParams ?: return

        view.setBackgroundColor(overlayBackgroundColor())

        borderFrameView?.let { border ->
            border.configure(
                color = applyAlpha(currentSettings.borderColor.colorArgb, 0xCC),
                strokeWidthPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    currentSettings.borderThickness.dpValue,
                    overlayContext.resources.displayMetrics
                ),
                cornerRadiusPx = dp(18).toFloat(),
                style = currentSettings.borderStyle
            )
            border.visibility = if (currentSettings.borderEnabled) View.VISIBLE else View.GONE
            startOrStopBorderPulse()
        }

        oledClockContainer?.visibility = if (currentSettings.oledClockEnabled) View.VISIBLE else View.GONE
        oledBatteryText?.setTextColor(currentSettings.borderColor.colorArgb)
        startOrStopOledClockTicker()

        statusBadgePill?.visibility = when (currentSettings.badgeVisibilityMode) {
            BadgeVisibilityMode.ALWAYS -> View.VISIBLE
            BadgeVisibilityMode.ON_TOUCH -> {
                if (unlockHudContainer?.visibility == View.VISIBLE) View.VISIBLE else View.GONE
            }
            BadgeVisibilityMode.HIDDEN -> View.GONE
        }

        if (currentSettings.keepScreenOnEnabled) {
            params.flags = params.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        } else {
            params.flags = params.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON.inv()
        }
        params.flags = params.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        params.dimAmount = 0f
        params.screenBrightness = resolveOverlayBrightness()

        try {
            windowManager.updateViewLayout(view, params)
        } catch (_: Exception) {
        }
    }

    private fun resolveOverlayBrightness(): Float {
        val percent = currentSettings.lockBrightnessPercent
        return if (percent < 0) {
            WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
        } else {
            (percent / 100f).coerceIn(0.01f, 1.0f)
        }
    }

    private fun startOrStopBorderPulse() {
        stopBorderPulse()
        val border = borderFrameView ?: return
        if (!currentSettings.borderEnabled) return
        if (!currentSettings.borderPulseEnabled) {
            border.alpha = 0.72f
            return
        }
        borderPulseAnimator = ValueAnimator.ofFloat(0.40f, 0.96f).apply {
            duration = 1600L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { anim ->
                border.alpha = anim.animatedValue as Float
            }
            start()
        }
    }

    private fun stopBorderPulse() {
        borderPulseAnimator?.cancel()
        borderPulseAnimator = null
    }

    private fun startOrStopOledClockTicker() {
        stopOledClockTicker()
        if (!currentSettings.oledClockEnabled) return
        val runnable = object : Runnable {
            override fun run() {
                updateOledClockNow()
                handler.postDelayed(this, 20_000L)
            }
        }
        oledClockRunnable = runnable
        updateOledClockNow()
        handler.postDelayed(runnable, 20_000L)
    }

    private fun stopOledClockTicker() {
        oledClockRunnable?.let { handler.removeCallbacks(it) }
        oledClockRunnable = null
    }

    private fun updateOledClockNow() {
        val timeText = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        oledClockText?.text = timeText
        val pct = readBatteryPercent()
        oledBatteryText?.text = "$pct%"
    }

    private fun readBatteryPercent(): Int {
        val status = overlayContext.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = status?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = status?.getIntExtra(BatteryManager.EXTRA_SCALE, 100) ?: 100
        return if (level >= 0 && scale > 0) ((level * 100f) / scale).toInt().coerceIn(0, 100) else 100
    }

    private fun createLockLayoutParams(): WindowManager.LayoutParams {
        var flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED

        if (currentSettings.keepScreenOnEnabled) {
            flags = flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        }
        flags = flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()

        return WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            overlayWindowType(),
            flags,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            dimAmount = 0f
            screenBrightness = resolveOverlayBrightness()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createHoldUnlockView(): View {
        val size = dp(78)
        val accent = currentSettings.borderColor.colorArgb
        val container = FrameLayout(overlayContext).apply {
            layoutParams = LinearLayout.LayoutParams(size, size)
        }

        val ringView = HoldProgressRingView(overlayContext, accent)
        val circleBg = FrameLayout(overlayContext).apply {
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(0xFF152033.toInt())
                setStroke(dp(1), applyAlpha(accent, 0x66))
            }
            val icon = ImageView(overlayContext).apply {
                setImageDrawable(ContextCompat.getDrawable(overlayContext, R.drawable.ic_shield_lock))
                setColorFilter(accent)
                val pad = dp(17)
                setPadding(pad, pad, pad, pad)
            }
            addView(icon)
        }

        val innerMargin = dp(8)
        container.addView(
            circleBg,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(innerMargin, innerMargin, innerMargin, innerMargin)
            }
        )
        container.addView(
            ringView,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        var holdAnimator: ValueAnimator? = null
        var completed = false

        container.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    completed = false
                    cancelScheduledHudHide()
                    revealHudTemporarily(autoHideAfterMs = currentSettings.holdDurationMs + 2000L)
                    v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
                    holdAnimator?.cancel()
                    holdAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
                        duration = currentSettings.holdDurationMs
                        addUpdateListener { anim ->
                            val value = anim.animatedValue as Float
                            ringView.progress = value
                            if (value >= 1f && !completed) {
                                completed = true
                                triggerHaptic(light = false)
                                onUnlockTriggered()
                            }
                        }
                        start()
                    }
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
                    if (!completed) {
                        holdAnimator?.cancel()
                        ringView.progress = 0f
                        scheduleHudHide(currentSettings.hudTimeoutMs)
                    }
                    true
                }

                else -> true
            }
        }

        return container
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createSlideUnlockView(): View {
        val trackWidth = dp(248)
        val trackHeight = dp(56)
        val thumbSize = dp(46)
        val padding = dp(5)
        val maxOffset = (trackWidth - thumbSize - padding * 2).toFloat()
        val accent = currentSettings.borderColor.colorArgb

        val track = FrameLayout(overlayContext).apply {
            layoutParams = LinearLayout.LayoutParams(trackWidth, trackHeight)
            background = GradientDrawable().apply {
                setColor(0xFF152033.toInt())
                cornerRadius = trackHeight / 2f
                setStroke(dp(1), applyAlpha(accent, 0x66))
            }
        }

        val label = TextView(overlayContext).apply {
            text = ">>>"
            setTextColor(applyAlpha(accent, 0x99))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            typeface = Typeface.MONOSPACE
            gravity = Gravity.CENTER
        }
        track.addView(
            label,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        val thumb = FrameLayout(overlayContext).apply {
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(accent)
            }
            val icon = ImageView(overlayContext).apply {
                setImageDrawable(ContextCompat.getDrawable(overlayContext, R.drawable.ic_shield_lock))
                setColorFilter(0xFF042217.toInt())
                val pad = dp(11)
                setPadding(pad, pad, pad, pad)
            }
            addView(icon)
        }
        val thumbLp = FrameLayout.LayoutParams(thumbSize, thumbSize).apply {
            gravity = Gravity.CENTER_VERTICAL or Gravity.START
            leftMargin = padding
        }
        track.addView(thumb, thumbLp)

        var downX = 0f
        var unlocked = false

        thumb.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    unlocked = false
                    downX = event.rawX
                    cancelScheduledHudHide()
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    val delta = (event.rawX - downX).coerceIn(0f, maxOffset)
                    view.translationX = delta
                    label.alpha = (1f - (delta / maxOffset)).coerceIn(0.15f, 1f)
                    if (delta >= maxOffset * 0.85f && !unlocked) {
                        unlocked = true
                        triggerHaptic(light = false)
                        onUnlockTriggered()
                    }
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    if (!unlocked) {
                        view.animate().translationX(0f).setDuration(200).start()
                        label.animate().alpha(1f).setDuration(200).start()
                        scheduleHudHide(currentSettings.hudTimeoutMs)
                    }
                    true
                }

                else -> true
            }
        }

        return track
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createDoubleTapUnlockView(): View {
        val size = dp(76)
        val accent = currentSettings.borderColor.colorArgb
        val button = FrameLayout(overlayContext).apply {
            layoutParams = LinearLayout.LayoutParams(size, size)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(0xFF152033.toInt())
                setStroke(dp(2), accent)
            }
        }

        val icon = ImageView(overlayContext).apply {
            setImageDrawable(ContextCompat.getDrawable(overlayContext, R.drawable.ic_shield_lock))
            setColorFilter(accent)
            val pad = dp(20)
            setPadding(pad, pad, pad, pad)
        }
        button.addView(icon)

        var lastTapTime = 0L
        button.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val now = System.currentTimeMillis()
                revealHudTemporarily(currentSettings.hudTimeoutMs)
                v.animate().scaleX(0.90f).scaleY(0.90f).setDuration(70).withEndAction {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(90).start()
                }.start()

                if (now - lastTapTime <= 420L) {
                    triggerHaptic(light = false)
                    onUnlockTriggered()
                    lastTapTime = 0L
                } else {
                    lastTapTime = now
                    triggerHaptic(light = true)
                }
            }
            true
        }

        return button
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createPinUnlockView(hintView: TextView): View {
        val strings = getAppStrings(currentSettings.appLanguage)
        val accent = currentSettings.borderColor.colorArgb
        val entered = StringBuilder()

        val wrapper = LinearLayout(overlayContext).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }

        val dotsRow = LinearLayout(overlayContext).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, dp(8))
        }

        val dotViews = List(4) {
            View(overlayContext).apply {
                layoutParams = LinearLayout.LayoutParams(dp(11), dp(11)).apply {
                    setMargins(dp(5), 0, dp(5), 0)
                }
                background = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(0x33CBD5E1)
                }
            }
        }
        dotViews.forEach { dotsRow.addView(it) }
        wrapper.addView(dotsRow)

        fun refreshDots() {
            dotViews.forEachIndexed { idx, dot ->
                dot.background = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(if (idx < entered.length) accent else 0x33CBD5E1)
                }
            }
        }

        val isLandscape = overlayContext.resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE

        val grid = GridLayout(overlayContext).apply {
            columnCount = 3
            rowCount = 4
        }

        val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "C", "0", "⌫")
        keys.forEach { key ->
            val btn = TextView(overlayContext).apply {
                text = key
                setTextColor(0xFFF8FAFC.toInt())
                setTextSize(TypedValue.COMPLEX_UNIT_SP, if (isLandscape) 14f else 15f)
                typeface = Typeface.DEFAULT_BOLD
                gravity = Gravity.CENTER
                layoutParams = GridLayout.LayoutParams().apply {
                    width = dp(if (isLandscape) 50 else 56)
                    height = dp(if (isLandscape) 33 else 41)
                    setMargins(dp(3), dp(3), dp(3), dp(3))
                }
                background = GradientDrawable().apply {
                    setColor(0xFF1E293B.toInt())
                    cornerRadius = dp(10).toFloat()
                    setStroke(dp(1), 0x3394A3B8)
                }
                setOnClickListener {
                    revealHudTemporarily(maxOf(currentSettings.hudTimeoutMs, 5000L))
                    triggerHaptic(light = true)
                    when (key) {
                        "C" -> {
                            entered.clear()
                            hintView.text = strings.overlayPinHint
                            hintView.setTextColor(0xFFCBD5E1.toInt())
                            refreshDots()
                        }

                        "⌫" -> {
                            if (entered.isNotEmpty()) {
                                entered.deleteCharAt(entered.length - 1)
                                refreshDots()
                            }
                        }

                        else -> {
                            if (entered.length < 4) {
                                entered.append(key)
                                refreshDots()
                                if (entered.length == 4) {
                                    if (entered.toString() == currentSettings.unlockPin) {
                                        triggerHaptic(light = false)
                                        onUnlockTriggered()
                                    } else {
                                        triggerHaptic(light = false)
                                        hintView.text = strings.overlayPinWrong
                                        hintView.setTextColor(0xFFFB7185.toInt())
                                        entered.clear()
                                        refreshDots()
                                    }
                                }
                            }
                        }
                    }
                }
            }
            grid.addView(btn)
        }
        wrapper.addView(grid)

        if (currentSettings.biometricUnlockEnabled || currentSettings.emergencyHoldBypassEnabled) {
            val recoveryRow = LinearLayout(overlayContext).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                setPadding(0, dp(8), 0, 0)
            }

            if (currentSettings.biometricUnlockEnabled) {
                val bioBtn = TextView(overlayContext).apply {
                    text = strings.overlayBtnBiometric
                    setTextColor(accent)
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                    typeface = Typeface.DEFAULT_BOLD
                    setPadding(dp(12), dp(7), dp(12), dp(7))
                    background = GradientDrawable().apply {
                        setColor(0xFF172030.toInt())
                        cornerRadius = dp(50).toFloat()
                        setStroke(dp(1), applyAlpha(accent, 0x80))
                    }
                    setOnClickListener {
                        triggerHaptic(light = true)
                        onBiometricRequested()
                    }
                }
                recoveryRow.addView(bioBtn)
            }

            if (currentSettings.emergencyHoldBypassEnabled) {
                if (currentSettings.biometricUnlockEnabled) {
                    recoveryRow.addView(
                        View(overlayContext).apply {
                            layoutParams = LinearLayout.LayoutParams(dp(8), dp(1))
                        }
                    )
                }
                val emergencyBtn = TextView(overlayContext).apply {
                    text = strings.overlayEmergencyHoldLabel
                    setTextColor(0xFFFBBF24.toInt())
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
                    typeface = Typeface.DEFAULT_BOLD
                    setPadding(dp(12), dp(7), dp(12), dp(7))
                    background = GradientDrawable().apply {
                        setColor(0xFF2B2110.toInt())
                        cornerRadius = dp(50).toFloat()
                        setStroke(dp(1), 0x88F59E0B.toInt())
                    }
                }
                var bypassAnim: ValueAnimator? = null
                var bypassed = false
                emergencyBtn.setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            bypassed = false
                            cancelScheduledHudHide()
                            revealHudTemporarily(7000L)
                            v.alpha = 0.7f
                            bypassAnim?.cancel()
                            bypassAnim = ValueAnimator.ofFloat(0f, 1f).apply {
                                duration = 5000L
                                addUpdateListener { anim ->
                                    val p = anim.animatedValue as Float
                                    emergencyBtn.text = "${strings.overlayEmergencyHoldLabel} (${(p * 100).toInt()}%)"
                                    if (p >= 1f && !bypassed) {
                                        bypassed = true
                                        triggerHaptic(light = false)
                                        onUnlockTriggered()
                                    }
                                }
                                start()
                            }
                            true
                        }

                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            v.alpha = 1f
                            if (!bypassed) {
                                bypassAnim?.cancel()
                                emergencyBtn.text = strings.overlayEmergencyHoldLabel
                                scheduleHudHide(currentSettings.hudTimeoutMs)
                            }
                            true
                        }

                        else -> true
                    }
                }
                recoveryRow.addView(emergencyBtn)
            }

            wrapper.addView(recoveryRow)
        }

        return wrapper
    }

    private fun revealHudTemporarily(autoHideAfterMs: Long = currentSettings.hudTimeoutMs) {
        val hud = unlockHudContainer ?: return
        hud.animate().cancel()
        hud.visibility = View.VISIBLE
        hud.animate().alpha(1f).setDuration(180).start()

        if (currentSettings.badgeVisibilityMode == BadgeVisibilityMode.ON_TOUCH) {
            statusBadgePill?.let { badge ->
                badge.animate().cancel()
                badge.visibility = View.VISIBLE
                badge.animate().alpha(0.85f).setDuration(180).start()
            }
        }
        scheduleHudHide(autoHideAfterMs)
    }

    private fun cancelScheduledHudHide() {
        hideHudRunnable?.let { handler.removeCallbacks(it) }
        hideHudRunnable = null
    }

    private fun scheduleHudHide(delayMs: Long) {
        cancelScheduledHudHide()
        val runnable = Runnable {
            unlockHudContainer?.let { hud ->
                hud.animate().alpha(0.0f).setDuration(280).withEndAction {
                    if (hud.alpha <= 0.05f) {
                        hud.visibility = View.GONE
                    }
                }.start()
            }
            if (currentSettings.badgeVisibilityMode == BadgeVisibilityMode.ON_TOUCH) {
                statusBadgePill?.let { badge ->
                    badge.animate().alpha(0.0f).setDuration(280).withEndAction {
                        if (badge.alpha <= 0.05f) {
                            badge.visibility = View.GONE
                        }
                    }.start()
                }
            }
        }
        hideHudRunnable = runnable
        handler.postDelayed(runnable, delayMs)
    }

    private fun snapBubbleToNearestEdge(view: View, params: WindowManager.LayoutParams) {
        val displayWidth = overlayContext.resources.displayMetrics.widthPixels
        val margin = dp(12)
        val startX = params.x
        val targetX = if (startX + view.width / 2 < displayWidth / 2) {
            margin
        } else {
            (displayWidth - view.width - margin).coerceAtLeast(margin)
        }

        ValueAnimator.ofInt(startX, targetX).apply {
            duration = 220
            interpolator = DecelerateInterpolator()
            addUpdateListener { anim ->
                params.x = anim.animatedValue as Int
                savedBubbleX = params.x
                try {
                    if (bubbleView != null) {
                        windowManager.updateViewLayout(view, params)
                    }
                } catch (_: Exception) {
                }
            }
            start()
        }
    }

    private fun triggerHaptic(light: Boolean) {
        if (!currentSettings.hapticFeedbackEnabled) return
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vm = overlayContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vm.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                overlayContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }
            val duration = if (light) 22L else 55L
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(duration)
            }
        } catch (_: Exception) {
        }
    }

    private fun overlayBackgroundColor(): Int {
        val percent = currentSettings.customDimPercent.coerceIn(0, 95)
        if (percent == 0) return Color.TRANSPARENT
        val alpha = (percent * 255) / 100
        return Color.argb(alpha, 5, 8, 14)
    }

    private fun applyAlpha(rgbColor: Int, alphaByte: Int): Int {
        return (alphaByte shl 24) or (rgbColor and 0x00FFFFFF)
    }

    private fun overlayWindowType(): Int =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_PHONE
        }

    private fun canDrawOverlays(): Boolean = Settings.canDrawOverlays(overlayContext)

    private fun dp(value: Int): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            overlayContext.resources.displayMetrics
        ).toInt()

    private fun dp(value: Float): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            overlayContext.resources.displayMetrics
        ).toInt()

    private class BorderOverlayView(context: Context) : View(context) {
        private var style: BorderStyle = BorderStyle.FULL_GLOW
        private var cornerRadiusPx: Float = 24f
        private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        private val rect = RectF()
        private val bracketPath = Path()

        fun configure(
            color: Int,
            strokeWidthPx: Float,
            cornerRadiusPx: Float,
            style: BorderStyle
        ) {
            this.style = style
            this.cornerRadiusPx = cornerRadiusPx
            strokePaint.color = color
            strokePaint.strokeWidth = strokeWidthPx
            invalidate()
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val half = strokePaint.strokeWidth / 2f
            rect.set(half, half, width - half, height - half)

            if (style == BorderStyle.FULL_GLOW) {
                canvas.drawRoundRect(rect, cornerRadiusPx, cornerRadiusPx, strokePaint)
            } else {
                val len = minOf(width, height) * 0.10f
                val r = cornerRadiusPx
                bracketPath.reset()
                // Top-left
                bracketPath.moveTo(rect.left, rect.top + len)
                bracketPath.lineTo(rect.left, rect.top + r)
                bracketPath.quadTo(rect.left, rect.top, rect.left + r, rect.top)
                bracketPath.lineTo(rect.left + len, rect.top)
                // Top-right
                bracketPath.moveTo(rect.right - len, rect.top)
                bracketPath.lineTo(rect.right - r, rect.top)
                bracketPath.quadTo(rect.right, rect.top, rect.right, rect.top + r)
                bracketPath.lineTo(rect.right, rect.top + len)
                // Bottom-right
                bracketPath.moveTo(rect.right, rect.bottom - len)
                bracketPath.lineTo(rect.right, rect.bottom - r)
                bracketPath.quadTo(rect.right, rect.bottom, rect.right - r, rect.bottom)
                bracketPath.lineTo(rect.right - len, rect.bottom)
                // Bottom-left
                bracketPath.moveTo(rect.left + len, rect.bottom)
                bracketPath.lineTo(rect.left + r, rect.bottom)
                bracketPath.quadTo(rect.left, rect.bottom, rect.left, rect.bottom - r)
                bracketPath.lineTo(rect.left, rect.bottom - len)
                canvas.drawPath(bracketPath, strokePaint)
            }
        }
    }

    private class HoldProgressRingView(
        context: Context,
        accentColor: Int
    ) : View(context) {
        var progress: Float = 0f
            set(value) {
                field = value.coerceIn(0f, 1f)
                invalidate()
            }

        private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 8f
            color = 0x33CBD5E1
        }

        private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 10f
            strokeCap = Paint.Cap.ROUND
            color = accentColor
        }

        private val arcBounds = RectF()

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val inset = 8f
            arcBounds.set(inset, inset, width - inset, height - inset)
            canvas.drawOval(arcBounds, trackPaint)
            if (progress > 0f) {
                canvas.drawArc(arcBounds, -90f, 360f * progress, false, progressPaint)
            }
        }
    }
}
