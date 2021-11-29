package com.example.sampleappmvvm.login

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.server.ArticleListItem
import com.example.sampleappmvvm.server.TokenRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<State>()

    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getToken() {
        viewModelScope.launch {
            try {
                val request = TokenRequest("code", "test","password")
                val response = repository.generateToken(request)
                println("The token is ${response.access_token}")
                repository.storeToken(token = response.access_token)
                println("store token ${response.access_token}, saved = ${repository.getToken()}")
                mutableLiveData.value = State.TokenStored
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    sealed class State {
        object TokenStored : State()
    }
}