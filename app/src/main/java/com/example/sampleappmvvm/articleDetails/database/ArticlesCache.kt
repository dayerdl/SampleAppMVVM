package com.example.sampleappmvvm.articleDetails.database

import android.content.Context
import javax.inject.Inject

class ArticlesCache @Inject constructor(private val context: Context) {

    suspend fun saveFavourite(article: ArticleLocal) {
        ArticlesDataBase.getInstance(context).articleDao().insertArticle(article)
    }

    suspend fun deleteFavourite(article: ArticleLocal) {
        ArticlesDataBase.getInstance(context).articleDao().deleteArticle(article)
    }

    suspend fun isArticleFavourite(articleId: Int): Boolean {
        return ArticlesDataBase.getInstance(context).articleDao().getAllFavourites()
            .find { it.id == articleId } != null
    }
}