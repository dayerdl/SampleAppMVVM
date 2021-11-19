package com.example.sampleappmvvm.server

data class TokenRequest(val username: String, val password: String, val grant_type: String)