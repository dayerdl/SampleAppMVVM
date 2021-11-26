package com.example.sampleappmvvm.articles.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articles.di.ArticlesListViewModelProviderFactory
import com.example.sampleappmvvm.articles.viewmodel.ArticlesListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ArticlesListFragment: DaggerFragment() {

    private lateinit var viewModel: ArticlesListViewModel

    @Inject
    lateinit var factory: ArticlesListViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ArticlesList(viewModel = viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[ArticlesListViewModel::class.java]
        viewModel.loadArticles()
    }
}