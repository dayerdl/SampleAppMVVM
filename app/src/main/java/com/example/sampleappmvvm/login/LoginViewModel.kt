package com.example.sampleappmvvm.login

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.articlesList.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.server.NetworkErrors
import com.example.sampleappmvvm.server.TokenRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<State>()

    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getToken(user: String, password: String) {
        viewModelScope.launch {
            try {
                val request = TokenRequest(user, password,"password")
                val response = repository.generateToken(request)
                response.fold(onSuccess = {
                    repository.storeToken(token = it.access_token)
                    mutableLiveData.value = State.TokenStored
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
                println(e)
            }
        }
    }

    sealed class State {
        object TokenStored : State()
        object IncorrectCredentials : State()
        object TechnicalError : State()
    }
}