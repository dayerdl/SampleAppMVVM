package com.example.sampleappmvvm.login.ui

import android.os.Bundle
import com.example.sampleappmvvm.R
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity

class LoginActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }
}