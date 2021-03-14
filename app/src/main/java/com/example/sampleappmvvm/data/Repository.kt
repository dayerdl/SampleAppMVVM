package com.example.sampleappmvvm.data

import com.example.sampleappmvvm.model.Search
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(private val apiManager: ApiManager) {

    fun loadSearchResults(): Single<Search> {
        return apiManager.provideApiManager().getSearch()
    }
}