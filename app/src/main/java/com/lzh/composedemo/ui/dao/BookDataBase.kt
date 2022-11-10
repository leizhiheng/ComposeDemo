package com.lzh.composedemo.ui.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class BookDataBase: RoomDatabase(){
    abstract fun bookDao(): BookDao

    companion object {
        private const val DB_NAME = "book_db"

        private var instance: BookDataBase? = null

        fun getInstance(context: Context): BookDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, BookDataBase::class.java, DB_NAME).build()
            }
            return instance as BookDataBase
        }
    }
}