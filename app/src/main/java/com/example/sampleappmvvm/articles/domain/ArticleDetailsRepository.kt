package com.example.sampleappmvvm.articles.domain

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.Article

class ArticleDetailsRepository(private val apiManager: ApiManager) {

    suspend fun loadArticleDetails(articleId: String, token: String): Article {
        val header = "Bearer $token"
        return apiManager.provideAuthClient(token).getArticleDetails(articleId)
    }

}