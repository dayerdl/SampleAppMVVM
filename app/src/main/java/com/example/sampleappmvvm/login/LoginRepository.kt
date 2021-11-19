package com.example.sampleappmvvm.login

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.LoginRequest
import com.example.sampleappmvvm.server.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiManager: ApiManager) {

    suspend fun loadResponse(request: LoginRequest): LoginResponse {
        return apiManager.provideFutureApiManager().login(request)
    }
}