package com.example.sampleappmvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvm.domain.DomainModel
import com.example.sampleappmvvm.domain.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<DomainModel>()

    val viewModelData: LiveData<DomainModel> by lazy { mutableLiveData }

    fun loadData() {
        viewModelScope.launch {
            try {
                val movie = repository.loadResponse()
                println("The movie is $movie")
                mutableLiveData.value = DomainModel(movie.title)
            } catch (e: Exception) {

            }
        }
    }
}