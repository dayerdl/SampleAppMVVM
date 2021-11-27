package com.example.sampleappmvvm.articlesList.view

import android.os.Bundle
import com.example.sampleappmvvm.R
import dagger.android.support.DaggerAppCompatActivity

class ArticlesListActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles_layout)
    }
}