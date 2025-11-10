package com.levtttech.bibleapp.data.cache

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookDb>

    class Base(private val roomProvider: RoomProvider) : BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> = roomProvider.provide().fetchBooks()
    }
}