package com.lzh.composedemo.ui.dao

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_table")
data class Book(@PrimaryKey var id: Long, var name: String, var author: String)