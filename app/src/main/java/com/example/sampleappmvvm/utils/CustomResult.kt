package com.example.sampleappmvvm.utils

sealed class CustomResult<T> {
    data class Success<T>(val value: T) : CustomResult<T>()
    data class Failure<T>(val error: Throwable) : CustomResult<T>()
}

inline fun <R, T> CustomResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R
): R = when (this) {
    is CustomResult.Success -> onSuccess(value)
    is CustomResult.Failure -> onFailure(error)
}