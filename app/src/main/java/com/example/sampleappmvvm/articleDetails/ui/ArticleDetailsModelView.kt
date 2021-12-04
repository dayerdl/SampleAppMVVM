package com.example.sampleappmvvm.articleDetails.ui

import com.example.sampleappmvvm.server.ArticleDetails

data class ArticleDetailsModelView(
    val articleDetails: ArticleDetails,
    val favourite: Boolean
)