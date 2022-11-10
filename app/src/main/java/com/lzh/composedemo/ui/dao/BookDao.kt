package com.lzh.composedemo.ui.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM book_table")
    suspend fun getAll(): List<Book>

    @Insert
    suspend fun insertAll(vararg books: Book)

    @Delete
    suspend fun delete(book: Book)
}