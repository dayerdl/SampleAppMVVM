package com.example.sampleappmvvm.login.ui

import androidx.lifecycle.ViewModel
import com.example.sampleappmvvm.login.repository.AuthRepository

class StartUpViewModel(val repository: AuthRepository, private val loggable: Loggable) :
    ViewModel() {

    fun start() {
        if (loggable.shouldLogOut()) {
            repository.logOut()
        }

        repository.getToken()?.let {
            loggable.onLoggedIn()
        } ?: run {
            loggable.onLoggedOut()
        }
    }

}