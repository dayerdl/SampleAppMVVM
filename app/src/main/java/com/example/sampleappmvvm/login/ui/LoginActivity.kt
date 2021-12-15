package com.example.sampleappmvvm.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.articlesList.view.ArticlesListActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

interface Loggable {
    fun shouldLogOut(): Boolean
    fun onLoggedOut()
    fun onLoggedIn()
}
class LoginActivity : DaggerAppCompatActivity(), Loggable {

    companion object {
        const val LOGOUT = "LOGOUT"
    }

    lateinit var viewModel: StartUpViewModel

    @Inject
    lateinit var factory: StartupViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[StartUpViewModel::class.java]
        viewModel.start()
    }

    override fun shouldLogOut(): Boolean {
        return intent.getBooleanExtra(LOGOUT, false)
    }

    override fun onLoggedOut() {
        setContentView(R.layout.login_activity)
    }

    override fun onLoggedIn() {
        val intent = Intent(this, ArticlesListActivity::class.java)
        startActivity(intent)
        finish()
    }
}