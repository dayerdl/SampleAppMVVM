package com.example.sampleappmvvm.login.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.compose.setContent
import com.example.sampleappmvvm.R
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity

class LoginActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(onClick = {
                Toast.makeText(this, "hoola", Toast.LENGTH_SHORT).show()})
        }
    }
}