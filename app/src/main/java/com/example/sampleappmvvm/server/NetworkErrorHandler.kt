package com.example.sampleappmvvm.server

import retrofit2.HttpException

class NetworkErrorHandler {

    fun handleError(it: Throwable): Throwable {
        when (it) {
            is HttpException -> {
                when (it.code()) {
                    401 -> return NetworkErrors.Unauthorized
                    403 -> return NetworkErrors.IncorrectCredentials
                }
            }
        }
        return it
    }
}

sealed class NetworkErrors {
    object Unauthorized : Throwable()
    object IncorrectCredentials : Throwable()
}