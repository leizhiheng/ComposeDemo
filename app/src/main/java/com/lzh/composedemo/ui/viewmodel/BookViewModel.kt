package com.lzh.composedemo.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lzh.composedemo.ui.dao.Book
import com.lzh.composedemo.ui.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class BookViewModel(app: Application): AndroidViewModel(app) {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val repository = BookRepository()

    private var _books: MutableLiveData<MutableList<Book>> = MutableLiveData(mutableListOf())

    fun insertBook(book: Book) {
        uiScope.launch {
            repository.insertBook(book)
        }
    }

    fun queryAllBooks() {
        uiScope.launch {
            var books = repository.queryAllBooks()
            _books.value.clear()
            _books.value.addAll(books)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}