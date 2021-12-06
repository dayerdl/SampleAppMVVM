package com.example.sampleappmvvm.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.login.repository.AuthRepository
import com.example.sampleappmvvm.login.ui.OnTokenStored
import com.example.sampleappmvvm.server.NetworkErrors
import com.example.sampleappmvvm.server.TokenRequest
import com.example.sampleappmvvm.utils.fold
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: AuthRepository, private val listener: OnTokenStored) : ViewModel() {

    private val mutableLiveData = MutableLiveData<State>()

    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onLoginClicked(user: String, password: String) {
        viewModelScope.launch {
            try {
                mutableLiveData.value = State.Loading
                val request = TokenRequest(user, password,"password")
                val response = repository.generateToken(request)
                response.fold(onSuccess = {
                    repository.storeToken(token = it.access_token)
                    listener.onTokenStored()
                }, onFailure = {
                    when (it) {
                        is NetworkErrors.IncorrectCredentials -> {
                            mutableLiveData.value = State.IncorrectCredentials
                        }
                        else -> {
                            mutableLiveData.value = State.IncorrectCredentials
                        }
                    }

                })

            } catch (e: Exception) {
                mutableLiveData.value = State.TechnicalError
            }
        }
    }

    sealed class State {
        object IncorrectCredentials : State()
        object TechnicalError : State()
        object Loading: State()
    }
}