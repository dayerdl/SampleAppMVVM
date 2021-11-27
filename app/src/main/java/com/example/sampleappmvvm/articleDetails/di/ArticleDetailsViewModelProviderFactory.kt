package com.example.sampleappmvvm.articleDetails.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articleDetails.domain.ArticleDetailsRepository
import com.example.sampleappmvvm.articleDetails.ui.ArticleDetailsViewModel
import com.example.sampleappmvvm.articlesList.domain.ArticlesRepository
import com.example.sampleappmvvm.articlesList.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.login.AuthRepository
import javax.inject.Inject

class ArticleDetailsViewModelProviderFactory @Inject constructor(
    private val articlesRepository: ArticleDetailsRepository,
    private val repository: AuthRepository
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleDetailsViewModel(articlesRepository, repository) as T
    }
}