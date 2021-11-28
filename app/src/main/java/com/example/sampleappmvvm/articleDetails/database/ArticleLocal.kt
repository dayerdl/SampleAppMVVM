package com.example.sampleappmvvm.articleDetails.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleLocal(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String = "",
)