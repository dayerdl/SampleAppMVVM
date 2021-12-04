package com.example.sampleappmvvm.server

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FutureApi {

    @POST("auth/token")
    suspend fun getToken(@Body request: TokenRequest): TokenResponse
}

interface FutureApiPrivate {

    @GET("api/v1/articles")
    suspend fun getArticles(): List<ArticleListItem>

    @GET("api/v1/articles/{id}")
    suspend fun getArticleDetails(@Path("id") articleId: Int): ArticleDetails
}