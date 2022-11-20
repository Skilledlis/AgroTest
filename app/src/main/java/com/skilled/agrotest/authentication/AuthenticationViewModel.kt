package com.skilled.agrotest.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skilled.agrotest.authentication.biometricAuthentication.AuthenticationUseCase

class AuthenticationViewModel(
    private val biometricAuthUseCase: AuthenticationUseCase
) : ViewModel() {

    private val _authStatus: MutableLiveData<Boolean> = MutableLiveData()
    val authStatus: LiveData<Boolean> = _authStatus
    private val _pinCode = MutableLiveData<String>().also {
        it.value = ""
    }
    val pinCode: LiveData<String> = _pinCode


    init {
        _authStatus.postValue(showBioAuth())
    }

    fun showBioAuth() = biometricAuthUseCase.biometricAuth()

    fun clickPinTab(text: String) {
        val existingPinCode = _pinCode.value ?: ""

        if (existingPinCode.length < 4)
            _pinCode.value = _pinCode.value + text

    }

    fun deletePiCode() {
        val existingPinCode = _pinCode.value ?: ""
        if (existingPinCode.isNotEmpty())
            _pinCode.value = existingPinCode.dropLast(1)
    }
}
