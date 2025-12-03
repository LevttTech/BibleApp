package com.levtttech.bibleapp.data.cache

import com.levtttech.bibleapp.data.BookData
import com.levtttech.bibleapp.data.RoomProvider

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookDb>
    suspend fun saveBooks(books: List<BookData>)

    class Base(private val roomProvider: RoomProvider,
               private val dbMapper: ToDbMapper) : BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> = roomProvider.book().fetchBooks()
        override suspend fun saveBooks(books: List<BookData>) {
            roomProvider.book().saveBooks(books.map { it.mapToDb(dbMapper) })
        }
    }
}