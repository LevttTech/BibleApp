package com.levtttech.bibleapp.data.books

import com.levtttech.bibleapp.data.books.cache.BookDao
import com.levtttech.bibleapp.data.books.cache.BooksDatabase
import com.levtttech.bibleapp.data.chapters.cache.ChapterDao


interface RoomWrapper {

    fun provideDatabase(): BooksDatabase

    abstract class ProviderDb(private val databaseProvider: RoomWrapper) {
        protected val db = databaseProvider.provideDatabase()
    }

    interface Book {
        fun provide(): BookDao

        class Base(
            databaseProvider: RoomWrapper,
        ) : Book, ProviderDb(databaseProvider) {
            override fun provide(): BookDao = db.bookDao()
        }
    }

    interface Chapter {
        fun provide(): ChapterDao
        class Base(databaseProvider: RoomWrapper) : Chapter, ProviderDb(databaseProvider) {
            override fun provide(): ChapterDao = db.chapterDao()
        }
    }
}