package com.skilled.agrotest.authentication.biometricAuthentication

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 * @created 18.11.2022
 * @author SkilledLis
 */
interface InitializePrompt {
    fun initBiometricPrompt(): BiometricPrompt

    class Base(
        private val activity: FragmentActivity,
        private val listener: BiometricAuthListener
    ) : InitializePrompt {

        override fun initBiometricPrompt(): BiometricPrompt {

            val executor = ContextCompat.getMainExecutor(activity)

            val callback = object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.w(this.javaClass.simpleName, "Authentication failed for an unknown reason")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    listener.onBiometricAuthenticationSuccess()
                }
            }

            return BiometricPrompt(activity, executor, callback)
        }
    }
}