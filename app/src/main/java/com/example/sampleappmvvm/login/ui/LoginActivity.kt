package com.example.sampleappmvvm.login.ui

import android.content.Intent
import android.os.Bundle
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.articlesList.view.ArticlesListActivity
import com.example.sampleappmvvm.login.repository.AuthRepository
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    companion object {
        const val LOGOUT = "LOGOUT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getBooleanExtra(LOGOUT, false)) {
            authRepository.logOut()
        }

        authRepository.getToken()?.let {
            val intent = Intent(this, ArticlesListActivity::class.java)
            startActivity(intent)
            finish()
        } ?: run {
            setContentView(R.layout.login_activity)
        }
    }
}