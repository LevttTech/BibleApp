package com.levtttech.bibleapp.data.books

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.data.books.net.BookCloud
import com.levtttech.bibleapp.data.books.net.BookService
import com.levtttech.bibleapp.data.core.Fetch
import com.levtttech.bibleapp.presentation.books.ResourceProvider


interface BooksCloudDataSource : Fetch<List<BookCloud>> {

    class Base(private val service: BookService) : BooksCloudDataSource {
        override suspend fun fetch(): List<BookCloud> = service.fetchBooks()
    }

    // independent from cloud service
    class Mock(
        private val resourceProvider: ResourceProvider,
        private val gson: Gson,
    ) : BooksCloudDataSource {
        override suspend fun fetch(): List<BookCloud> {
            val raw = resourceProvider.getRawResource(R.raw.books_successful_response)
            return gson.fromJson(
                raw, object : TypeToken<List<BookCloud>>() {}.type)
        }
    }
}