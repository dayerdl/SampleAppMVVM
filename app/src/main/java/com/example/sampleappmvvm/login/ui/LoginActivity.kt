package com.example.sampleappmvvm.login.ui

import android.content.Intent
import android.os.Bundle
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.articles.view.ArticlesListActivity
import com.example.sampleappmvvm.login.AuthRepository
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    @Inject lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authRepository.getToken()?.let {
            val intent = Intent(this, ArticlesListActivity::class.java)
            startActivity(intent)
        } ?: run {
            setContentView(R.layout.login_activity)
        }
    }
}