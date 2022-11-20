package com.skilled.agrotest.authentication.biometricAuthentication

import android.content.Context
import androidx.biometric.BiometricManager

/**
 * @created 19.11.2022
 * @author SkilledLis
 */

interface BiometricAuthenticator {
    fun isBiometricReady(): Boolean
}

interface BiometricAuthenticatorReady {

    fun auth() : Boolean

    class Base(
        private val biometric: BiometricAuthenticator,
        private val initPrompt: InitializePrompt,
        private val biometricPromptInfo: BiometricPromptInfo
    ) : BiometricAuthenticatorReady {


        override fun auth(): Boolean {
            if (biometric.isBiometricReady()) {
                ShowBiometricPrompt.Base(
                    initPrompt,
                    biometricPromptInfo,
                ).show()
                return true
            }
            return false
        }
    }
}
interface BiometricCapability : BiometricAuthenticator {
    fun hasCapability(): Int

    class Base(private val context: Context) : BiometricCapability {

        override fun isBiometricReady() = hasCapability() == BiometricManager.BIOMETRIC_SUCCESS

        override fun hasCapability(): Int {
            val biometricManager = BiometricManager.from(context)
            return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
        }
    }
}