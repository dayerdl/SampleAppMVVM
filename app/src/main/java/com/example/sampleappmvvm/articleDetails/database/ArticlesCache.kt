package com.example.sampleappmvvm.articleDetails.database

import android.content.Context
import javax.inject.Inject

class ArticlesCache @Inject constructor(private val context: Context) {

    suspend fun saveFavourite(article: ArticleLocal) {
        println("Inserting ${article.id}>>>>>>>>>>>>>")
        ArticlesDataBase.getInstance(context).articleDao().insertArticle(article)
        printList()
    }

    suspend fun deleteFavourite(article: ArticleLocal) {
        println("Deleting ${article.id}>>>>>>>>>>>>>")
        ArticlesDataBase.getInstance(context).articleDao().deleteArticle(article)
        printList()
    }

    suspend fun isArticleFavourite(articleId: Int): Boolean {
        return ArticlesDataBase.getInstance(context).articleDao().getAllFavourites()
            .find { it.id == articleId } != null
    }

    private suspend fun printList(){
        val favs = ArticlesDataBase.getInstance(context).articleDao().getAllFavourites()
        for (f in favs) {
            println(">>>>>>> fav is ${f.id} , ${f.title}")
        }
    }

}