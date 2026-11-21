package com.example.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object ShieldStateHolder {
    private val _runtimeState = MutableStateFlow(ShieldRuntimeState())
    val runtimeState: StateFlow<ShieldRuntimeState> = _runtimeState.asStateFlow()

    fun setServiceRunning(running: Boolean) {
        _runtimeState.update {
            if (!running) {
                it.copy(
                    isServiceRunning = false,
                    isScreenLocked = false,
                    lockSource = LockSource.NONE,
                    countdownRemainingSec = 0,
                    isBubbleTemporarilyHidden = false,
                    sessionBlockedTouches = 0,
                    lockStartTimeMs = 0L
                )
            } else {
                it.copy(isServiceRunning = true)
            }
        }
    }

    fun setScreenLocked(locked: Boolean, source: LockSource) {
        _runtimeState.update {
            if (locked) {
                it.copy(
                    isScreenLocked = true,
                    lockSource = source,
                    countdownRemainingSec = 0,
                    sessionBlockedTouches = 0,
                    lockStartTimeMs = System.currentTimeMillis()
                )
            } else {
                it.copy(
                    isScreenLocked = false,
                    lockSource = LockSource.NONE,
                    countdownRemainingSec = 0,
                    lockStartTimeMs = 0L
                )
            }
        }
    }

    fun setCountdownRemaining(seconds: Int) {
        _runtimeState.update { it.copy(countdownRemainingSec = seconds.coerceAtLeast(0)) }
    }

    fun setBubbleTemporarilyHidden(hidden: Boolean) {
        _runtimeState.update { it.copy(isBubbleTemporarilyHidden = hidden) }
    }

    fun incrementSessionBlockedTouches() {
        _runtimeState.update { it.copy(sessionBlockedTouches = it.sessionBlockedTouches + 1) }
    }

    fun updateSensorInfo(
        hasProximity: Boolean,
        proximityName: String,
        maxRangeCm: Float,
        hasAccelerometer: Boolean,
        accelerometerName: String
    ) {
        _runtimeState.update {
            it.copy(
                hasProximitySensor = hasProximity,
                sensorName = proximityName,
                proximityMaxRangeCm = maxRangeCm,
                proximityDistanceCm = maxRangeCm,
                hasAccelerometer = hasAccelerometer,
                accelerometerName = accelerometerName
            )
        }
    }

    fun updateProximityReading(isNear: Boolean, distanceCm: Float) {
        _runtimeState.update {
            it.copy(
                proximityIsNear = isNear,
                proximityDistanceCm = distanceCm
            )
        }
    }

    fun updateOrientationReading(isFlatFaceUp: Boolean, isPocketOrientationValid: Boolean) {
        _runtimeState.update {
            it.copy(
                isFlatFaceUp = isFlatFaceUp,
                isPocketOrientationValid = isPocketOrientationValid
            )
        }
    }
}
