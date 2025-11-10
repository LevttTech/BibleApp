package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.net.BookCloud
import com.levtttech.bibleapp.data.net.BookService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BookService) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> = service.fetchBooks()
    }
}