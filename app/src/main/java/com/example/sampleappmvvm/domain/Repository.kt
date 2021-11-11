package com.example.sampleappmvvm.domain

import com.example.sampleappmvvm.server.ApiManager
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(private val apiManager: ApiManager) {

    fun loadResponse(): Single<DomainModel> {
        return apiManager.provideApiManager().loadData().map { data -> DomainModel(data.title) }
    }
}