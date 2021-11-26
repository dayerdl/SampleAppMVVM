package com.example.sampleappmvvm.login

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.server.TokenRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<DomainModel>()

    val viewModelData: LiveData<DomainModel> by lazy { mutableLiveData }

    @RequiresApi(Build.VERSION_CODES.M)
    fun loadData(context: Context) {
        viewModelScope.launch {
            try {
                val request = TokenRequest("code", "test","password")
                val response = repository.generateToken(request)
                println("The token is ${response.access_token}")
                //store in preferences
                repository.storeToken(context = context,token = response.access_token)
//                val articles = repository.loadArticles(response.access_token)
//                println("number of articles is ${articles.size}")
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}