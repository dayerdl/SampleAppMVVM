package com.example.sampleappmvvm.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.domain.DomainModel
import com.example.sampleappmvvm.server.LoginRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<DomainModel>()

    val viewModelData: LiveData<DomainModel> by lazy { mutableLiveData }

    fun loadData() {
        viewModelScope.launch {
            try {
                val request = LoginRequest("code", "test","password")
                val response = repository.loadResponse(request)
                println("The movie is $response")
            } catch (e: Exception) {

            }
        }
    }
}