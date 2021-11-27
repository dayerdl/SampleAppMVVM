package com.example.sampleappmvvm.login

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.TokenRequest
import com.example.sampleappmvvm.server.TokenResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiManager: ApiManager,
    private val privatePreferences: SharedPreferences
) {
    companion object {
        const val KEY_TOKEN = "TOKEN"
    }

    suspend fun generateToken(request: TokenRequest): TokenResponse {
        return apiManager.provideNoAuthClient().getToken(request)
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