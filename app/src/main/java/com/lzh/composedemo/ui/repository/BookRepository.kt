package com.lzh.composedemo.ui.repository

import com.lzh.composedemo.App
import com.lzh.composedemo.ui.dao.Book
import com.lzh.composedemo.ui.dao.BookDataBase

class BookRepository {
    private val bookDao = BookDataBase.getInstance(App.getApp()).bookDao()

    suspend fun insertBook(book: Book) {
        bookDao.insertAll(book)
    }

    suspend fun queryAllBooks(): List<Book> {
        return bookDao.getAll()
    }
}