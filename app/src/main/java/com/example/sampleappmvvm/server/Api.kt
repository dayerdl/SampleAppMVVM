package com.example.sampleappmvvm.server

import com.example.sampleappmvvm.BuildConfig
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/{movie_id}")
    fun loadData(@Path("movie_id")movieId: Int = 19404,
                        @Query("api_key") apiKey: String = "07473a01a734d6aa462ef4b401276805"
    ): Single<Movie>
}