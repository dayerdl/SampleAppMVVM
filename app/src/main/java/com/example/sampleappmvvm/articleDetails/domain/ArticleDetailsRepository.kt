package com.example.sampleappmvvm.articleDetails.domain

import com.example.sampleappmvvm.articleDetails.database.ArticlesCache
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.ArticleListItem

class ArticleDetailsRepository(private val apiManager: ApiManager,
                               private val cache: ArticlesCache) {

    suspend fun loadArticleDetails(articleId: String, token: String): ArticleListItem {
        val articleDetails = apiManager.provideAuthClient(token).getArticleDetails(articleId)
        articleDetails.favourite = cache.isArticleFavourite(articleDetails.id)
        return articleDetails
    }

}