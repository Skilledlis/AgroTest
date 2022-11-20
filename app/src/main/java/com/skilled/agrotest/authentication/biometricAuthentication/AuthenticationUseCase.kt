package com.skilled.agrotest.authentication.biometricAuthentication

import com.skilled.agrotest.authentication.Authentication

/**
 * @created 19.11.2022
 * @author SkilledLis
 */
interface AuthenticationUseCase {
    fun biometricAuth() : Boolean
}

class BiometricAuthenticationInteractor(private val authentication: Authentication) :
    AuthenticationUseCase {
    override fun biometricAuth() = authentication.bioAuthentication()
}
