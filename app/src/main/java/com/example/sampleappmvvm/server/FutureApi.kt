package com.example.sampleappmvvm.server

import retrofit2.http.Body
import retrofit2.http.GET

interface FutureApi {

    @GET("auth/token")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}