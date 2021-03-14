package com.example.sampleappmvvm.data

import com.example.sampleappmvvm.BuildConfig
import com.example.sampleappmvvm.model.Search
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET(BuildConfig.BASE_URL)
    fun getSearch() : Single<Search>
}