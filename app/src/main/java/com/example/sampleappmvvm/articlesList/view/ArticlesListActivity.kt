package com.example.sampleappmvvm.articlesList.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articleDetails.ui.ArticleDetailsActivity
import com.example.sampleappmvvm.articlesList.di.ArticlesListViewModelProviderFactory
import com.example.sampleappmvvm.articlesList.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.articlesList.viewmodel.OnArticleClickListener
import com.example.sampleappmvvm.login.ui.LoginActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticlesListActivity : DaggerAppCompatActivity(), OnArticleClickListener {

    private lateinit var viewModel: ArticlesListViewModel

    @Inject
    lateinit var factory: ArticlesListViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[ArticlesListViewModel::class.java]

        setContent {
            ArticlesList(state = viewModel.viewModelData, logout(), viewModel::itemClick)
        }
    }

    private fun logout(): () -> Unit = {
        viewModel.onLogOutClicked()
        finish()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.viewModelData.observe(this, { state ->
            when (state) {
                is ArticlesListViewModel.State.NoAuth -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra(LoginActivity.LOGOUT, true)
                    startActivity(intent)
                }
                is ArticlesListViewModel.State.Loaded -> {}
            }
        })
    }

    override fun onItemClickListener(id: Int) {
        val intent = Intent(this, ArticleDetailsActivity::class.java)
        intent.apply { putExtra(ArticleDetailsActivity.ITEM_KEY, id) }
        startActivity(intent)
    }
}