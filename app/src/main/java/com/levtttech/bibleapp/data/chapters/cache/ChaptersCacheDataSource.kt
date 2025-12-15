package com.levtttech.bibleapp.data.chapters.cache

import com.levtttech.bibleapp.core.Read
import com.levtttech.bibleapp.data.books.RoomWrapper
import com.levtttech.bibleapp.data.chapters.ChapterData
import com.levtttech.bibleapp.data.chapters.ChapterDataToDb
import com.levtttech.bibleapp.data.core.Fetch
import com.levtttech.bibleapp.data.core.Save

interface ChaptersCacheDataSource : Save<List<ChapterData>> {
    suspend fun fetch(bookId:Int): List<ChapterDb>
    class Base(
        roomProvider: RoomWrapper.Chapter,
        private val mapper: ChapterDataToDb,
    ) : ChaptersCacheDataSource {
        private val dao = roomProvider.provide()
        override suspend fun fetch(bookId:Int): List<ChapterDb> = dao.fetchChapters(bookId)
        override suspend fun save(data: List<ChapterData>) =
            dao.saveChapters(data.map { it.mapToDb(mapper) })
    }
}


