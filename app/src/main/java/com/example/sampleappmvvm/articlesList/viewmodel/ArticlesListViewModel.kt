package com.example.sampleappmvvm.articlesList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.articlesList.repository.ArticlesRepository
import com.example.sampleappmvvm.login.repository.AuthRepository
import com.example.sampleappmvvm.server.ArticleListItem
import com.example.sampleappmvvm.server.NetworkErrors
import kotlinx.coroutines.launch

interface OnArticleClickListener {
    fun onItemClickListener(id: Int)
}

open class ArticlesListViewModel(
    private val repository: ArticlesRepository,
    private val authRepository: AuthRepository,
    private val onItemClickListener: OnArticleClickListener
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<State>()
    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    fun loadArticles() {
        authRepository.getToken()?.let {
            viewModelScope.launch {
                mutableLiveData.value = State.Loading
                val result = repository.loadArticles(it)
                result.fold(onSuccess = {
                    mutableLiveData.value = State.Loaded(it)
                }, onFailure = {
                    when (it) {
                        is NetworkErrors.Unauthorized -> {
                            mutableLiveData.value = State.NoAuth
                        }
                    }
                })
            }

        } ?: run {
            mutableLiveData.value = State.NoAuth
        }
    }

    fun itemClick(article: ArticleListItem) {
        onItemClickListener.onItemClickListener(article.id)
    }

    fun logOut() {
        authRepository.logOut()
    }

    sealed class State {
        object NoAuth : State()
        class Loaded(val articles: List<ArticleListItem>) : State()
        object Loading : State()
    }
}