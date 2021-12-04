package com.example.sampleappmvvm.articlesList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.sampleappmvvm.articlesList.domain.ArticlesRepository
import com.example.sampleappmvvm.login.AuthRepository
import com.example.sampleappmvvm.server.ArticleListItem
import com.example.sampleappmvvm.server.NetworkErrors
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

open class ArticlesListViewModel(
    private val repository: ArticlesRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<State>()
    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    fun loadArticles() {
        authRepository.getToken()?.let {
            viewModelScope.launch {
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

    fun itemClick(article: ArticleListItem): () -> Unit = {
        println("article clicked ${article.id}")
        mutableLiveData.value = State.ItemClick(article)
    }

    fun logOut() {
        authRepository.logOut()
    }

    sealed class State {
        object NoAuth : State()
        class Loaded(val articles: List<ArticleListItem>) : State()
        class ItemClick(val item: ArticleListItem) : State()
    }
}