package com.levtttech.bibleapp.data.books

import com.levtttech.bibleapp.data.books.net.BookCloud
import com.levtttech.bibleapp.data.books.net.BookService
import com.levtttech.bibleapp.data.core.Fetch


interface BooksCloudDataSource : Fetch<List<BookCloud>> {

    class Base(private val service: BookService) : BooksCloudDataSource {
        override suspend fun fetch(): List<BookCloud> = service.fetchBooks()
    }
}