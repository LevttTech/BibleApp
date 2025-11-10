package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.net.BookCloudMapper

interface BookRepository {
    suspend fun fetchBooks(): BooksData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cloudMapper: BookCloudMapper
    ) : BookRepository {
        override suspend fun fetchBooks(): BooksData {
            return try {
                val booksCloudList = cloudDataSource.fetchBooks()
                BooksData.Success(booksCloudList.map {
                    it.map(cloudMapper)
                })
            } catch (e: Exception) {
                BooksData.Fail(e)
            }
        }
    }
}