package com.levtttech.bibleapp.data.cache

interface RoomProvider {

    fun provide(): BookDao

    class Base(private val db: BooksDatabase) : RoomProvider {
        override fun provide(): BookDao = db.bookDao()
    }
}