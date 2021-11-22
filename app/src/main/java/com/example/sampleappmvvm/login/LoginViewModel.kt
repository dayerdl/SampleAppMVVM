package com.example.sampleappmvvm.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.server.TokenRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<DomainModel>()

    val viewModelData: LiveData<DomainModel> by lazy { mutableLiveData }

    fun loadData() {
        viewModelScope.launch {
            try {
                val request = TokenRequest("code", "test","password")
                val response = repository.loadResponse(request)
                println("The token is ${response.access_token}")
                //store in preferences
//                val articles = repository.loadArticles(response.access_token)
//                println("number of articles is ${articles.size}")
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}