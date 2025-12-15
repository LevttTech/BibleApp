package com.levtttech.bibleapp.data.books.cache

import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.RoomWrapper
import com.levtttech.bibleapp.data.core.Fetch
import com.levtttech.bibleapp.data.core.Save

interface BooksCacheDataSource : Fetch<List<BookDb>>, Save<List<BookData>> {

    class Base(
        roomProvider: RoomWrapper.Book,
        private val dbMapper: ToDbMapper,
    ) : BooksCacheDataSource {
        private val bookDao = roomProvider.provide()
        override suspend fun fetch(): List<BookDb> = bookDao.fetchBooks()

        override suspend fun save(data: List<BookData>) =
            bookDao.saveBooks(data.map { it.mapToDb(dbMapper) })
    }
}
