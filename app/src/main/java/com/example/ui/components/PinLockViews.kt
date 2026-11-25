package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.service.WrongAttemptCooldownTracker
import com.example.ui.AppStrings
import kotlinx.coroutines.delay

@Composable
fun SandboxPinUnlockPad(
    expectedPin: String,
    accentColor: Color,
    strings: AppStrings,
    biometricEnabled: Boolean = false,
    emergencyBypassEnabled: Boolean = false,
    kidsSafetyEnabled: Boolean = false,
    wrongAttemptCooldownEnabled: Boolean = true,
    onWrongPin: () -> Unit,
    onUnlocked: () -> Unit
) {
    var entered by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val cooldownTracker = remember { WrongAttemptCooldownTracker() }
    var cooldownRemaining by remember { mutableIntStateOf(0) }

    LaunchedEffect(cooldownRemaining) {
        if (cooldownRemaining > 0) {
            delay(1000L)
            cooldownRemaining = cooldownTracker.remainingCooldownSeconds()
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (cooldownRemaining > 0) {
            Text(
                text = strings.overlayPinCooldownWait(cooldownRemaining),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(6.dp))
        } else if (isError) {
            Text(
                text = strings.overlayPinWrong,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(6.dp))
        }

        // 4-dot indicator
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(
                            if (index < entered.length) accentColor else Color(0x44CBD5E1)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val rows = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("C", "0", "⌫")
        )

        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            rows.forEach { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    row.forEach { key ->
                        Surface(
                            modifier = Modifier
                                .width(52.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            onClick = {
                                if (cooldownRemaining > 0) return@Surface
                                when (key) {
                                    "C" -> {
                                        entered = ""
                                        isError = false
                                    }

                                    "⌫" -> {
                                        if (entered.isNotEmpty()) {
                                            entered = entered.dropLast(1)
                                            isError = false
                                        }
                                    }

                                    else -> {
                                        if (entered.length < 4) {
                                            isError = false
                                            val next = entered + key
                                            if (next.length == 4) {
                                                if (next == expectedPin) {
                                                    entered = ""
                                                    cooldownTracker.reset()
                                                    cooldownRemaining = 0
                                                    onUnlocked()
                                                } else {
                                                    entered = ""
                                                    isError = true
                                                    if (wrongAttemptCooldownEnabled) {
                                                        val sec = cooldownTracker.recordWrongAttempt()
                                                        if (sec > 0) {
                                                            cooldownRemaining = cooldownTracker.remainingCooldownSeconds()
                                                        }
                                                    }
                                                    onWrongPin()
                                                }
                                            } else {
                                                entered = next
                                            }
                                        }
                                    }
                                }
                            }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = key,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }

        if (biometricEnabled) {
            Spacer(modifier = Modifier.height(8.dp))
            FilledTonalButton(
                onClick = onUnlocked,
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                modifier = Modifier.height(34.dp),
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Fingerprint,
                    contentDescription = strings.overlayBtnBiometric,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = strings.overlayBtnBiometric,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        if (emergencyBypassEnabled && !kidsSafetyEnabled) {
            Spacer(modifier = Modifier.height(6.dp))
            var holdingEmergency by remember { mutableStateOf(false) }
            val holdProgress by animateFloatAsState(
                targetValue = if (holdingEmergency) 1f else 0f,
                animationSpec = if (holdingEmergency) tween(5000) else tween(200),
                finishedListener = { value ->
                    if (value >= 0.99f && holdingEmergency) {
                        holdingEmergency = false
                        onUnlocked()
                    }
                },
                label = "emergencyBypassProgress"
            )
            Surface(
                modifier = Modifier
                    .width(188.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                holdingEmergency = true
                                tryAwaitRelease()
                                holdingEmergency = false
                            }
                        )
                    },
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.55f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.WarningAmber,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = strings.overlayEmergencyHoldLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    if (holdProgress > 0f) {
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { holdProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp)
                                .clip(RoundedCornerShape(50)),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChangePinDialog(
    currentPin: String,
    strings: AppStrings,
    onSavePin: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var pinInput by remember { mutableStateOf(currentPin) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = strings.pinDialogTitle,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column {
                CompactTextInputBox(
                    value = pinInput,
                    onValueChange = { raw ->
                        pinInput = raw.filter { it.isDigit() }.take(4)
                    },
                    placeholder = strings.pinDialogHint,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier.testTag("pin_input_field")
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (pinInput.length == 4) {
                        onSavePin(pinInput)
                        onDismiss()
                    }
                },
                enabled = pinInput.length == 4,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier
                    .height(36.dp)
                    .testTag("pin_save_button")
            ) {
                Text(strings.btnSave)
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
fun SaveProfileDialog(
    title: String,
    hint: String,
    initialName: String = "",
    strings: AppStrings,
    onSave: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf(initialName) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
        text = {
            CompactTextInputBox(
                value = name,
                onValueChange = { name = it.take(24) },
                placeholder = hint,
                modifier = Modifier.testTag("profile_name_input")
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    val trimmed = name.trim()
                    if (trimmed.isNotEmpty()) {
                        onSave(trimmed)
                        onDismiss()
                    }
                },
                enabled = name.trim().isNotEmpty(),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier
                    .height(36.dp)
                    .testTag("profile_save_button")
            ) {
                Text(strings.btnSave)
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
fun ConfirmationDialog(
    title: String,
    message: String,
    confirmLabel: String,
    cancelLabel: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title, style = MaterialTheme.typography.titleLarge) },
        text = { Text(message, style = MaterialTheme.typography.bodyMedium) },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                },
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(confirmLabel)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(cancelLabel)
            }
        }
    )
}
