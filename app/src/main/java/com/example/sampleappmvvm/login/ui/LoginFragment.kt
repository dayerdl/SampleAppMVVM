package com.example.sampleappmvvm.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.login.LoginViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment: DaggerFragment() {

    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var factory: LoginViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        loginViewModel.viewModelData.observe(this, { data ->

        })

        loginViewModel.loadData()
    }

}