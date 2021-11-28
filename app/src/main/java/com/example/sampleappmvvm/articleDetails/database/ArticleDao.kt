package com.example.sampleappmvvm.articleDetails.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articleDao: ArticleLocal)

    @Query("SELECT * FROM "+"article")
    suspend fun getAllFavourites(): List<ArticleLocal>

}