package com.skilled.agrotest.authentication.biometricAuthentication

/**
 * @created 18.11.2022
 * @author SkilledLis
 */
interface ShowBiometricPrompt {
    fun show()

    class Base(
        private val initializePrompt: InitializePrompt,
        private val biometricPromptInfo: BiometricPromptInfo
    ) : ShowBiometricPrompt {
        override fun show(
        ) {
            val promptInfo = biometricPromptInfo.setBiometricPromptInfo()

            val biometricPrompt = initializePrompt.initBiometricPrompt()

            biometricPrompt.authenticate(promptInfo)
        }

    }
}