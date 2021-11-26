package com.example.sampleappmvvm.articles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.sampleappmvvm.articles.domain.ArticlesRepository
import com.example.sampleappmvvm.login.AuthRepository
import com.example.sampleappmvvm.server.Article
import kotlinx.coroutines.launch

open class ArticlesListViewModel(
    private val repository: ArticlesRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<State>()
    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    fun loadArticles() {
        authRepository.getToken()?.let {
            viewModelScope.launch {
                mutableLiveData.value = State.Loaded(repository.loadArticles(it))
            }

        } ?: run {
            mutableLiveData.value = State.NoAuth
        }
    }

    sealed class State {
        object NoAuth : State()
        class Loaded(val articles: List<Article>) : State()
    }
}