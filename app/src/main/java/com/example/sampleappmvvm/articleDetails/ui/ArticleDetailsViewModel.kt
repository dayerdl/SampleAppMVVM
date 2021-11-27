package com.example.sampleappmvvm.articleDetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.articleDetails.domain.ArticleDetailsRepository
import com.example.sampleappmvvm.login.AuthRepository
import com.example.sampleappmvvm.server.Article
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    private val repository: ArticleDetailsRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val mutableLiveData = MutableLiveData<State>()
    val viewModelData: LiveData<State> by lazy { mutableLiveData }

    fun loadDetails(articleId: String) {
        authRepository.getToken()?.let { token ->
            viewModelScope.launch {
                mutableLiveData.value =
                    State.Loaded(
                        repository.loadArticleDetails(
                            articleId = articleId,
                            token = token
                        )
                    )
            }

        } ?: run {
            mutableLiveData.value = State.NoAuth
        }
    }

    sealed class State {
        object NoAuth : State()
        class Loaded(val details: Article) : State()
    }

}