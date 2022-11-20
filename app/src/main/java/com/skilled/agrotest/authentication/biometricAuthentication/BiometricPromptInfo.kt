package com.skilled.agrotest.authentication.biometricAuthentication

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt

interface BiometricPromptInfo {

    fun setBiometricPromptInfo(): BiometricPrompt.PromptInfo

    class Base(
        private val title: String,
        private val subtitle: String,
        private val description: String,
        private val negativeButtonText: String
    ) : BiometricPromptInfo {
        override fun setBiometricPromptInfo(

        ): BiometricPrompt.PromptInfo {

            val builder = BiometricPrompt.PromptInfo.Builder().setTitle(title).setSubtitle(subtitle)
                .setDescription(description)
                .setNegativeButtonText(negativeButtonText)
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)

            return builder.build()
        }
    }
}
