package com.example.sampleappmvvm.server

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface FutureApi {

    @POST("auth/token")
    suspend fun getToken(@Body request: TokenRequest): TokenResponse

    @GET("api/v1/articles")
    suspend fun getArticles(@Header("Authorization") header: String): List<Article>
}