package com.example.sampleappmvvm.server

data class TokenResponse(
    val access_token: String,
    val refresh_token: String
)