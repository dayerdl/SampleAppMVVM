package com.example.sampleappmvvm.articles.view

import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.android.support.DaggerAppCompatActivity

class ArticleDetailsActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: ArticleDetailsViewModel

    companion object {
        const val ITEM_KEY: String = "ITEM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getStringExtra(ITEM_KEY)?.let { id ->
            setContent {
                ArticleDetailView(viewModel)
            }
            viewModel.loadDetails(id)
        }
    }
}