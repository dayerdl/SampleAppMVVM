package com.example.sampleappmvvm.utils

// This custom result solved a bug in kotlin 1.5.0 with Result class and unit testing
// https://stackoverflow.com/questions/65420765/problems-with-kotlin-resultt-on-unit-tests/68457710#68457710
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