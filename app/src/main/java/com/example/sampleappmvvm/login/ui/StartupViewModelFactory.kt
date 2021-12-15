package com.example.sampleappmvvm.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.login.repository.AuthRepository
import javax.inject.Inject

class StartupViewModelFactory @Inject constructor(
    private val repository: AuthRepository,
    private val loggable: Loggable
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StartUpViewModel(repository, loggable) as T
    }
}