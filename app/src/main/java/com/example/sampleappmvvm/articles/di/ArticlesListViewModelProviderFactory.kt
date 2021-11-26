package com.example.sampleappmvvm.articles.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articles.domain.ArticlesRepository
import com.example.sampleappmvvm.articles.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.login.AuthRepository
import javax.inject.Inject

class ArticlesListViewModelProviderFactory @Inject constructor(
    private val articlesRepository: ArticlesRepository,
    private val repository: AuthRepository
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticlesListViewModel(articlesRepository, repository) as T
    }
}