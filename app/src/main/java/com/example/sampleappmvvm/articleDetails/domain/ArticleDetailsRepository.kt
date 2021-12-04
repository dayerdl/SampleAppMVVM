package com.example.sampleappmvvm.articleDetails.domain

import com.example.sampleappmvvm.articleDetails.database.ArticlesCache
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.ArticleDetails
import com.example.sampleappmvvm.server.NetworkErrorHandler

class ArticleDetailsRepository(
    private val apiManager: ApiManager,
    private val errorHandler: NetworkErrorHandler,
    private val cache: ArticlesCache
) {

    suspend fun loadArticleDetails(articleId: Int, token: String): Result<ArticleDetails> {
        return try {
            Result.success(apiManager.provideAuthClient(token).getArticleDetails(articleId))
        } catch (e: Exception) {
            Result.failure(errorHandler.handleError(e))
        }
    }

    suspend fun isArticleFavourite(articleId: Int): Boolean {
        return cache.isArticleFavourite(articleId)
    }
}