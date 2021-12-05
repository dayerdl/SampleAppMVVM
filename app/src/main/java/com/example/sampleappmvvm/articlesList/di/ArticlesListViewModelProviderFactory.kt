package com.example.sampleappmvvm.articlesList.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articlesList.repository.ArticlesRepository
import com.example.sampleappmvvm.articlesList.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.articlesList.viewmodel.OnArticleClickListener
import com.example.sampleappmvvm.login.repository.AuthRepository
import javax.inject.Inject

class ArticlesListViewModelProviderFactory @Inject constructor(
    private val articlesRepository: ArticlesRepository,
    private val repository: AuthRepository,
    private val articleClickListener: OnArticleClickListener
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticlesListViewModel(articlesRepository, repository, articleClickListener) as T
    }
}