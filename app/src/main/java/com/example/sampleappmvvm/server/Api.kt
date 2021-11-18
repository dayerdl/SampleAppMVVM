package com.example.sampleappmvvm.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/{movie_id}")
    suspend fun loadData(@Path("movie_id")movieId: Int = 19404,
                        @Query("api_key") apiKey: String = "07473a01a734d6aa462ef4b401276805"
    ): Movie
}