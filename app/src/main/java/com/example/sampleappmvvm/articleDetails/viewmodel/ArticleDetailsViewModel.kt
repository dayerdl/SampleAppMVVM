package com.example.sampleappmvvm.articleDetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.articleDetails.database.ArticleLocal
import com.example.sampleappmvvm.articleDetails.database.ArticlesCache
import com.example.sampleappmvvm.articleDetails.domain.ArticleDetailsRepository
import com.example.sampleappmvvm.articleDetails.ui.ArticleDetailsModelView
import com.example.sampleappmvvm.login.AuthRepository
import com.example.sampleappmvvm.server.ArticleDetails
import com.example.sampleappmvvm.server.NetworkErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    private val repository: ArticleDetailsRepository,
    private val authRepository: AuthRepository,
    private val cache: ArticlesCache
) : ViewModel() {
    private val mutableLiveData = MutableLiveData<State>()
    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    fun loadDetails(articleId: Int) {
        authRepository.getToken()?.let { token ->
            viewModelScope.launch(Dispatchers.Main) {
                val articleDetails = repository.loadArticleDetails(
                    articleId = articleId,
                    token = token
                )
                articleDetails.fold(onSuccess = {
                    val favourite = repository.isArticleFavourite(it.id)
                    val articleDetailsModelView = ArticleDetailsModelView(it, favourite)
                    mutableLiveData.value =
                        State.Loaded(articleDetailsModelView)
                }, onFailure = {
                    when (it) {
                        is NetworkErrors.Unauthorized -> {
                            mutableLiveData.value = State.NoAuth
                        }
                        else -> {
                            println(it.localizedMessage)
                        }
                    }
                })

            }

        } ?: run {
            mutableLiveData.value = State.NoAuth
        }
    }

    fun saveFavorite(articleListItem: ArticleDetails) {
        viewModelScope.launch {
            val local = ArticleLocal(articleListItem.id, articleListItem.title)
            val isFavourite = cache.isArticleFavourite(articleId = articleListItem.id)
            if (isFavourite) {
                cache.deleteFavourite(local)
            } else {
                cache.saveFavourite(local)
            }
            val article = ArticleDetailsModelView(articleListItem, !isFavourite)
            mutableLiveData.value = State.Loaded(article)
        }
    }

    sealed class State {
        object NoAuth : State()
        class Loaded(val details: ArticleDetailsModelView) : State()
    }

}