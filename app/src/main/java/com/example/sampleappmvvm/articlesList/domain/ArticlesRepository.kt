package com.example.sampleappmvvm.articlesList.domain

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.ArticleListItem
import com.example.sampleappmvvm.server.NetworkErrorHandler

class ArticlesRepository(private val apiManager: ApiManager, private val errorHandler: NetworkErrorHandler) {

    suspend fun loadArticles(token: String): Result<List<ArticleListItem>> {
        return try {
            Result.success(apiManager.provideAuthClient(token).getArticles())
        } catch (e: Exception){
            Result.failure(errorHandler.handleError(e))
        }
    }
}

