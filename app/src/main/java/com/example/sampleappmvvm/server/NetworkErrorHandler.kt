package com.example.sampleappmvvm.server

import retrofit2.HttpException

class NetworkErrorHandler {

    fun handleError(it: Throwable): Throwable {
        when (it) {
            is HttpException -> {
                if (it.code() == 401) {
                    return NetworkErrors.Unauthorized
                }
            }
        }
        return it
    }
}

sealed class NetworkErrors {
    object Unauthorized: Throwable()
}