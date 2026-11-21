package com.example.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.Looper
import com.example.data.ShieldStateHolder
import kotlin.math.abs

class PocketSensorManager(
    context: Context,
    private val onPocketLockTriggered: () -> Unit,
    private val onPocketUnlockTriggered: () -> Unit
) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val proximitySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    private val accelerometerSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val handler = Handler(Looper.getMainLooper())

    private var isListening = false
    private var autoLockEnabled = false
    private var autoUnlockEnabled = false
    private var orientationFilterEnabled = true
    private var nearDebounceMs = 700L

    private var pendingNearRunnable: Runnable? = null
    private var pendingFarRunnable: Runnable? = null
    private var lastIsNear = false
    private var isFlatFaceUp = false

    init {
        val maxRange = proximitySensor?.maximumRange ?: 5f
        ShieldStateHolder.updateSensorInfo(
            hasProximity = proximitySensor != null,
            proximityName = proximitySensor?.name ?: "None",
            maxRangeCm = maxRange,
            hasAccelerometer = accelerometerSensor != null,
            accelerometerName = accelerometerSensor?.name ?: "None"
        )
    }

    fun startListening() {
        if (isListening) return
        proximitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        accelerometerSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        isListening = proximitySensor != null || accelerometerSensor != null
    }

    fun stopListening() {
        if (!isListening) return
        sensorManager.unregisterListener(this)
        isListening = false
        cancelPendingCallbacks()
    }

    fun updateConfig(
        autoLock: Boolean,
        autoUnlock: Boolean,
        debounceMs: Long,
        orientationFilter: Boolean
    ) {
        autoLockEnabled = autoLock
        autoUnlockEnabled = autoUnlock
        nearDebounceMs = debounceMs.coerceIn(400L, 2500L)
        orientationFilterEnabled = orientationFilter

        val validOrientation = !orientationFilterEnabled || !isFlatFaceUp || accelerometerSensor == null
        ShieldStateHolder.updateOrientationReading(
            isFlatFaceUp = isFlatFaceUp,
            isPocketOrientationValid = validOrientation
        )

        if (!autoLock) {
            pendingNearRunnable?.let { handler.removeCallbacks(it) }
            pendingNearRunnable = null
        }
        if (!autoUnlock) {
            pendingFarRunnable?.let { handler.removeCallbacks(it) }
            pendingFarRunnable = null
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensorEvent = event ?: return
        when (sensorEvent.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> handleAccelerometer(sensorEvent)
            Sensor.TYPE_PROXIMITY -> handleProximity(sensorEvent)
        }
    }

    private fun handleAccelerometer(event: SensorEvent) {
        if (event.values.size < 3) return
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        // Lying flat face-up on a desk/table
        val flat = z > 8.2f && abs(x) < 2.8f && abs(y) < 2.8f
        isFlatFaceUp = flat
        val validOrientation = !orientationFilterEnabled || !flat
        ShieldStateHolder.updateOrientationReading(
            isFlatFaceUp = flat,
            isPocketOrientationValid = validOrientation
        )
        // If phone was covered on desk and user picks it up into pocket orientation while still covered
        if (lastIsNear && validOrientation && autoLockEnabled && pendingNearRunnable == null) {
            scheduleNearLock()
        }
    }

    private fun handleProximity(event: SensorEvent) {
        val distance = event.values.firstOrNull() ?: return
        val maxRange = proximitySensor?.maximumRange ?: 5f
        val threshold = if (maxRange > 1f) minOf(maxRange * 0.6f, 3.0f) else maxRange * 0.5f
        val isNear = distance < threshold

        ShieldStateHolder.updateProximityReading(isNear = isNear, distanceCm = distance)

        if (isNear == lastIsNear) return
        lastIsNear = isNear

        if (isNear) {
            pendingFarRunnable?.let { handler.removeCallbacks(it) }
            pendingFarRunnable = null
            if (autoLockEnabled) {
                scheduleNearLock()
            }
        } else {
            pendingNearRunnable?.let { handler.removeCallbacks(it) }
            pendingNearRunnable = null
            if (autoUnlockEnabled) {
                val runnable = Runnable { onPocketUnlockTriggered() }
                pendingFarRunnable = runnable
                handler.postDelayed(runnable, FAR_DEBOUNCE_MS)
            }
        }
    }

    private fun scheduleNearLock() {
        pendingNearRunnable?.let { handler.removeCallbacks(it) }
        val runnable = Runnable {
            val orientationOk = !orientationFilterEnabled || !isFlatFaceUp || accelerometerSensor == null
            if (lastIsNear && autoLockEnabled && orientationOk) {
                onPocketLockTriggered()
            }
        }
        pendingNearRunnable = runnable
        handler.postDelayed(runnable, nearDebounceMs)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not required
    }

    private fun cancelPendingCallbacks() {
        pendingNearRunnable?.let { handler.removeCallbacks(it) }
        pendingFarRunnable?.let { handler.removeCallbacks(it) }
        pendingNearRunnable = null
        pendingFarRunnable = null
    }

    companion object {
        private const val FAR_DEBOUNCE_MS = 600L
    }
}
