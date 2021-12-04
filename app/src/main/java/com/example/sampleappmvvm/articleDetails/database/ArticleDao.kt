package com.example.sampleappmvvm.articleDetails.database

import androidx.room.*

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articleDao: ArticleLocal)

    @Delete
    suspend fun deleteArticle(articleDao: ArticleLocal)

    @Query("SELECT * FROM "+"article")
    suspend fun getAllFavourites(): List<ArticleLocal>

}