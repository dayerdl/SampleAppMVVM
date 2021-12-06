package com.example.sampleappmvvm.login.repository

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.NetworkErrorHandler
import com.example.sampleappmvvm.server.TokenRequest
import com.example.sampleappmvvm.server.TokenResponse
import com.example.sampleappmvvm.utils.CustomResult
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiManager: ApiManager,
    private val privatePreferences: SharedPreferences,
    private val errorHandler: NetworkErrorHandler
) {
    companion object {
        const val KEY_TOKEN = "TOKEN"
    }

    suspend fun generateToken(request: TokenRequest): CustomResult<TokenResponse> {
        return try {
            CustomResult.Success(apiManager.provideNoAuthClient().getToken(request))
        } catch (e: Exception) {
            CustomResult.Failure(errorHandler.handleError(e))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun storeToken(token: String) {
        privatePreferences.edit {
            putString(KEY_TOKEN, token).commit()
        }
    }

    fun getToken(): String? {
        return privatePreferences.getString(KEY_TOKEN, null)
    }

    fun logOut() {
        privatePreferences.edit() {
            remove(KEY_TOKEN).commit()
        }
    }
}