package com.example.sampleappmvvm.server

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FutureApi {

    @POST("auth/token")
    suspend fun getToken(@Body request: TokenRequest): TokenResponse
}

interface FutureApiPrivate {

    @GET("api/v1/articles")
    suspend fun getArticles(): List<ArticleListItem>

    @GET("api/v1/articles/id={article_id}")
    suspend fun getArticleDetails(articleId: String): ArticleListItem
}