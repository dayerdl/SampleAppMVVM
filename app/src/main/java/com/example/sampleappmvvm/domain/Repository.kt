package com.example.sampleappmvvm.domain

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.Movie
import javax.inject.Inject

class Repository @Inject constructor(private val apiManager: ApiManager) {

    suspend fun loadResponse(): Movie {
        return apiManager.provideApiManager().loadData()
    }
}