package com.example.sampleappmvvm.server

data class Article(
    val date: String,
    val id: Int,
    val summary: String,
    val thumbnail_template_url: String,
    val thumbnail_url: String,
    val title: String,
    var favourite: Boolean
)