package com.example.sampleappmvvm.domain

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.LoginRequest
import com.example.sampleappmvvm.server.LoginResponse
import javax.inject.Inject

class Repository @Inject constructor(private val apiManager: ApiManager) {

    suspend fun loadResponse(request: LoginRequest): LoginResponse {
        return apiManager.provideFutureApiManager().login(request)
    }
}