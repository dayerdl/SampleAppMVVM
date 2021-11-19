package com.example.sampleappmvvm.login

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.Article
import com.example.sampleappmvvm.server.TokenRequest
import com.example.sampleappmvvm.server.TokenResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiManager: ApiManager) {

    suspend fun loadResponse(request: TokenRequest): TokenResponse {
        return apiManager.provideFutureApiManager().getToken(request)
    }

    suspend fun loadArticles(token: String): List<Article> {
        val header = "Bearer $token"
        return apiManager.provideFutureApiManager().getArticles(header)
    }
}