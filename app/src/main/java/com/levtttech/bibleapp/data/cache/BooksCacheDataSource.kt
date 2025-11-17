package com.levtttech.bibleapp.data.cache

import com.levtttech.bibleapp.core.Book

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookDb>
    suspend fun saveBooks(books: List<Book>)

    class Base(private val roomProvider: RoomProvider) : BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> = roomProvider.provide().fetchBooks()
        override suspend fun saveBooks(books: List<Book>) {
            roomProvider.provide().saveBooks(books.map { BookDb(it.id, it.name) })
        }
    }
}