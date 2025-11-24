package com.levtttech.bibleapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookDb::class], version = 2, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}