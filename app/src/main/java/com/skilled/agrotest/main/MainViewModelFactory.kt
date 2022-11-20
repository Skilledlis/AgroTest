package com.skilled.agrotest.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val getUserUseCase: GetUserUseCase = GetUserInteractor()
        return MainViewModel(getUserUseCase) as T
    }
}

class GetUserInteractor : GetUserUseCase {
    override fun execute(): Boolean {
        return true
    }
}
