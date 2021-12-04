package com.example.sampleappmvvm.articleDetails.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articleDetails.di.ArticleDetailsViewModelProviderFactory
import com.example.sampleappmvvm.articleDetails.viewmodel.ArticleDetailsViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticleDetailsActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: ArticleDetailsViewModel

    @Inject
    lateinit var factory: ArticleDetailsViewModelProviderFactory

    companion object {
        const val ITEM_KEY: String = "ITEM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[ArticleDetailsViewModel::class.java]

        intent.extras?.getInt(ITEM_KEY)?.let { id ->
            setContent {
                ArticleDetailView(viewModel = viewModel, backHandler = { finish() })
            }
            viewModel.loadDetails(id)
        }
    }
}