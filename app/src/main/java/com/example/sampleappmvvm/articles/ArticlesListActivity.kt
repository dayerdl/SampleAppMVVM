package com.example.sampleappmvvm.articles

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.sampleappmvvm.articles.ui.ArticlesList
import com.example.sampleappmvvm.articles.ui.getMockArticles
import dagger.android.support.DaggerAppCompatActivity

class ArticlesListActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mockArticles = getMockArticles()
            ArticlesList(articles = mockArticles)
        }
    }
}