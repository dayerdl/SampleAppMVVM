package com.example.sampleappmvvm.articlesList.domain

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.ArticleListItem

class ArticlesRepository(private val apiManager: ApiManager) {

    suspend fun loadArticles(token: String): List<ArticleListItem> {
        return apiManager.provideAuthClient(token).getArticles()
    }

}