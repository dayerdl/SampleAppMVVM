package com.example.sampleappmvvm.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.login.LoginRepository
import com.example.sampleappmvvm.login.LoginViewModel
import javax.inject.Inject

class LoginViewModelProviderFactory @Inject constructor(private val repository: LoginRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}