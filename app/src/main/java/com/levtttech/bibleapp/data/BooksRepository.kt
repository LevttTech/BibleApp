package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.cache.BooksCacheMapper

interface BooksRepository {
    suspend fun fetchBooks(): BooksData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val cloudMapper: BooksCloudMapper,
        private val cacheMapper: BooksCacheMapper,
    ) : BooksRepository {
        override suspend fun fetchBooks() = try {
            val booksCacheList = cacheDataSource.fetchBooks()
            if (booksCacheList.isEmpty()) {
                val booksCloud = cloudDataSource.fetchBooks()
                val booksList = cloudMapper.map(booksCloud)
                cacheDataSource.saveBooks(booksList)
                BooksData.Success(booksList)
            } else {
                BooksData.Success(cacheMapper.map(booksCacheList))
            }
        } catch (e: Exception) {
            BooksData.Fail(e)
        }
    }
}
