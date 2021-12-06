package com.example.sampleappmvvm.articlesList.repository

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.ArticleListItem
import com.example.sampleappmvvm.server.NetworkErrorHandler
import com.example.sampleappmvvm.utils.CustomResult

class ArticlesRepository(private val apiManager: ApiManager, private val errorHandler: NetworkErrorHandler) {

    suspend fun loadArticles(token: String): CustomResult<List<ArticleListItem>> {
        return try {
            CustomResult.Success(apiManager.provideAuthClient(token).getArticles())
        } catch (e: Exception){
            CustomResult.Failure(errorHandler.handleError(e))
        }
    }
}

