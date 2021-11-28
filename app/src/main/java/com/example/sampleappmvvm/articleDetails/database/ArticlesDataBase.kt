package com.example.sampleappmvvm.articleDetails.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleLocal::class], version = 1, exportSchema = false)
abstract class ArticlesDataBase: RoomDatabase(){
    companion object {
        private var instance: ArticlesDataBase? = null

        fun getInstance(applicationContext: Context): ArticlesDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    applicationContext,
                    ArticlesDataBase::class.java, "articles"
                ).build()
            }
            return instance as ArticlesDataBase
        }
    }

    abstract fun articleDao(): ArticleDao
}