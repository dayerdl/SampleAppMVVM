package com.example.sampleappmvvm.articleDetails.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articleDetails.di.ArticleDetailsViewModelProviderFactory
import com.example.sampleappmvvm.articleDetails.viewmodel.ArticleDetailsViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

interface HasArticleId {
    fun getArticleId(): Int
}

class ArticleDetailsActivity : DaggerAppCompatActivity(), HasArticleId {

    private lateinit var viewModel: ArticleDetailsViewModel

    @Inject
    lateinit var factory: ArticleDetailsViewModelProviderFactory

    companion object {
        const val ITEM_KEY: String = "ITEM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[ArticleDetailsViewModel::class.java]

        setContent {
            ArticleDetailView(
                state = viewModel.viewModelData,
                backHandler = { finish() },
                onClickFavourite = viewModel::saveFavorite
            )
        }
    }

    override fun getArticleId(): Int {
        return intent.extras?.getInt(ITEM_KEY) ?: run { return 0 }
    }

}