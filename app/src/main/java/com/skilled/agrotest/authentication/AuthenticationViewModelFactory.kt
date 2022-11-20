package com.skilled.agrotest.authentication

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skilled.agrotest.main.ManageResources
import com.skilled.agrotest.R
import com.skilled.agrotest.authentication.biometricAuthentication.*

class AuthenticationViewModelFactory(
    context: Context,
    activity: FragmentActivity,
    biometricAuthListener: BiometricAuthListener,
    manageResources: ManageResources
) : ViewModelProvider.Factory {

    private val biometricTitle = manageResources.string(R.string.biometricTitle)
    private val biometricSubTitle = manageResources.string(R.string.biometricSubtitle)
    private val biometricDescription = manageResources.string(R.string.biometricDescription)
    private val biometricNegativeButton = manageResources.string(R.string.cancel)

    private val biometricAuthenticator: BiometricAuthenticator = BiometricCapability.Base(context)
    private val initPrompt: InitializePrompt =
        InitializePrompt.Base(activity, biometricAuthListener)


    private val biometricPromptInfo: BiometricPromptInfo = BiometricPromptInfo.Base(
        biometricTitle,
        biometricSubTitle,
        biometricDescription,
        biometricNegativeButton
    )


    private val biometricAuthentication: BiometricAuthenticatorReady =
        BiometricAuthenticatorReady.Base(biometricAuthenticator, initPrompt, biometricPromptInfo)
    private val authentication = Authentication.AuthenticationImp(biometricAuthentication)


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(BiometricAuthenticationInteractor(authentication) ,  ) as T
    }
}
