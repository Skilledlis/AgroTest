package com.skilled.agrotest.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(
    getUserUseCase: GetUserUseCase
) : ViewModel() {

    val haveUser: MutableLiveData<Boolean> = MutableLiveData()

    init {
        haveUser.postValue(getUserUseCase.execute())
    }

}
