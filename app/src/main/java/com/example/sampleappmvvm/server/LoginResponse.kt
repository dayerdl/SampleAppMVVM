package com.example.sampleappmvvm.server

data class LoginResponse(
    val access_token: String,
    val refresh_token: String
)