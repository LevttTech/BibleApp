package com.levtttech.bibleapp.data.books.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.levtttech.bibleapp.data.chapters.cache.ChapterDao
import com.levtttech.bibleapp.data.chapters.cache.ChapterDb

@Database(entities = [BookDb::class, ChapterDb::class], version = 3, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun chapterDao(): ChapterDao
}