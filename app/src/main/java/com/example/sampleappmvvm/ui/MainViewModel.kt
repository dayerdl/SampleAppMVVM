package com.example.sampleappmvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleappmvvm.domain.DomainModel
import com.example.sampleappmvvm.domain.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val mutableLiveData = MutableLiveData<DomainModel>()

    val viewModelData: LiveData<DomainModel> by lazy { mutableLiveData }

    fun loadData() {
        compositeDisposable.add(
            repository.loadResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ item ->
                    println(item)
                    mutableLiveData.value = item
                }, {
                    println("Error loading data $it")
                })
        )
    }

}