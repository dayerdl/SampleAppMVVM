package com.example.sampleappmvvm.server

data class ArticleDetails(
    val content: String,
    val date: String,
    val id: Int,
    val image_url: String,
    val source_url: String,
    val summary: String,
    val thumbnail_template_url: String,
    val thumbnail_url: String,
    val title: String
)