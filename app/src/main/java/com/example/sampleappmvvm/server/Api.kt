package com.example.sampleappmvvm.server

import com.example.sampleappmvvm.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET(BuildConfig.BASE_URL)
    fun loadData(): Single<ServerResponse>
}