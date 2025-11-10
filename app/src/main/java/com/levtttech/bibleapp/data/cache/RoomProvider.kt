package com.levtttech.bibleapp.data.cache

import androidx.room.Dao
import androidx.room.RoomDatabase
import com.levtttech.bibleapp.data.BooksData

interface RoomProvider {

    fun provide(): BookDao

    class Base(private val db: BookDatabase) : RoomProvider {
        override fun provide(): BookDao = db.bookDao()
    }
}