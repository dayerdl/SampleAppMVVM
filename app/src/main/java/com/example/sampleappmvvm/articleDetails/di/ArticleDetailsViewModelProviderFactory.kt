package com.example.sampleappmvvm.articleDetails.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articleDetails.repository.ArticleDetailsRepository
import com.example.sampleappmvvm.articleDetails.ui.HasArticleId
import com.example.sampleappmvvm.articleDetails.viewmodel.ArticleDetailsViewModel
import com.example.sampleappmvvm.login.repository.AuthRepository
import javax.inject.Inject

class ArticleDetailsViewModelProviderFactory @Inject constructor(
    private val articlesRepository: ArticleDetailsRepository,
    private val repository: AuthRepository,
    private val provider: HasArticleId
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleDetailsViewModel(articlesRepository, repository, provider.getArticleId()) as T
    }
}