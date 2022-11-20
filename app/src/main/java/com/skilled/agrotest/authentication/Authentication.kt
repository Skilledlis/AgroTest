package com.skilled.agrotest.authentication

import com.skilled.agrotest.authentication.biometricAuthentication.BiometricAuthenticatorReady

/**
 * @created 19.11.2022
 * @author SkilledLis
 */
interface Authentication {
    fun bioAuthentication() : Boolean
    fun pinCodeAuthentication(pinCode: String) /*: PinCodeResponse*/

    class AuthenticationImp(
        private val biometricAuth: BiometricAuthenticatorReady
    ) : Authentication {
        override fun bioAuthentication() = biometricAuth.auth()

        override fun pinCodeAuthentication(pinCode: String) {
            TODO()
        }
    }
}