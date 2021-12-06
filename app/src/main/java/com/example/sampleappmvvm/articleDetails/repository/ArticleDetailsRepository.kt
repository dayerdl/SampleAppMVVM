package com.example.sampleappmvvm.articleDetails.repository

import com.example.sampleappmvvm.articleDetails.database.ArticleLocal
import com.example.sampleappmvvm.articleDetails.database.ArticlesCache
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.ArticleDetails
import com.example.sampleappmvvm.server.NetworkErrorHandler
import com.example.sampleappmvvm.utils.CustomResult

class ArticleDetailsRepository(
    private val apiManager: ApiManager,
    private val errorHandler: NetworkErrorHandler,
    private val cache: ArticlesCache
) {

    suspend fun loadArticleDetails(articleId: Int, token: String): CustomResult<ArticleDetails> {
        return try {
            CustomResult.Success(apiManager.provideAuthClient(token).getArticleDetails(articleId))
        } catch (e: Exception) {
            CustomResult.Failure(errorHandler.handleError(e))
        }
    }

    suspend fun isArticleFavourite(articleId: Int): Boolean {
        return cache.isArticleFavourite(articleId)
    }

    suspend fun deleteFavourite(article: ArticleLocal) {
        cache.deleteFavourite(article)
    }

    suspend fun saveFavourite(article: ArticleLocal) {
        cache.saveFavourite(article)
    }
}