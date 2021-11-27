package com.example.sampleappmvvm.articles.domain

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.Article

class ArticlesRepository(private val apiManager: ApiManager) {

    suspend fun loadArticles(token: String): List<Article> {
        return apiManager.provideAuthClient(token).getArticles()
    }

}