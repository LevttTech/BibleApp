package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.cache.BookDao
import com.levtttech.bibleapp.data.cache.BooksDatabase
import com.levtttech.bibleapp.data.chapters.cache.ChapterDao

interface RoomProvider {

    fun book(): BookDao
    fun chapter(): ChapterDao

    class Base(private val db: BooksDatabase) : RoomProvider {
        override fun book(): BookDao = db.bookDao()
        override fun chapter(): ChapterDao = db.chapterDao()
    }
}