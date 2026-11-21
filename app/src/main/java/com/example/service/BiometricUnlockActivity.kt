package com.example.service

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.data.ShieldPreferences
import com.example.ui.getAppStrings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class BiometricUnlockActivity : ComponentActivity() {

    private var cancellationSignal: CancellationSignal? = null

    private val credentialLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            TouchShieldService.unlockScreenNow(this)
        }
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = runBlocking { ShieldPreferences(applicationContext).settingsFlow.first() }
        val strings = getAppStrings(settings.appLanguage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val promptBuilder = BiometricPrompt.Builder(this)
                .setTitle(strings.appTitle)
                .setSubtitle(strings.overlayBtnBiometric)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                promptBuilder.setAllowedAuthenticators(
                    BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.BIOMETRIC_WEAK or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
                )
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                @Suppress("DEPRECATION")
                promptBuilder.setDeviceCredentialAllowed(true)
            } else {
                promptBuilder.setNegativeButton(
                    strings.btnCancel,
                    ContextCompat.getMainExecutor(this)
                ) { _, _ -> finish() }
            }

            val signal = CancellationSignal()
            cancellationSignal = signal

            runCatching {
                promptBuilder.build().authenticate(
                    signal,
                    ContextCompat.getMainExecutor(this),
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                            TouchShieldService.unlockScreenNow(applicationContext)
                            finish()
                        }

                        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                            finish()
                        }

                        override fun onAuthenticationFailed() {
                            // User can retry until canceled or locked out
                        }
                    }
                )
            }.onFailure {
                launchLegacyCredentialFallback(strings.appTitle, strings.overlayBtnBiometric)
            }
        } else {
            launchLegacyCredentialFallback(strings.appTitle, strings.overlayBtnBiometric)
        }
    }

    private fun launchLegacyCredentialFallback(title: String, desc: String) {
        val km = getSystemService(Context.KEYGUARD_SERVICE) as? KeyguardManager
        @Suppress("DEPRECATION")
        val confirmIntent = km?.createConfirmDeviceCredentialIntent(title, desc)
        if (confirmIntent != null) {
            credentialLauncher.launch(confirmIntent)
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        cancellationSignal?.cancel()
        cancellationSignal = null
        super.onDestroy()
    }

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, BiometricUnlockActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }
            runCatching { context.startActivity(intent) }
        }
    }
}
