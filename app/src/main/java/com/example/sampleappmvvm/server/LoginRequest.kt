package com.example.sampleappmvvm.server

data class LoginRequest(val username: String, val password: String, val grant_type: String)