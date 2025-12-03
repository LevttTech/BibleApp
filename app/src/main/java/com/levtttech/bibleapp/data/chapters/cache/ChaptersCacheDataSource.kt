package com.levtttech.bibleapp.data.chapters.cache

import com.levtttech.bibleapp.data.RoomProvider
import com.levtttech.bibleapp.data.chapters.ChapterData
import com.levtttech.bibleapp.data.chapters.ChapterDataToDb

interface ChaptersCacheDataSource {

    fun saveChapters(chapters: List<ChapterData>)
    fun fetchChapters(): List<ChapterDb>

    class Base(
        roomProvider: RoomProvider,
        private val mapper: ChapterDataToDb,
    ) : ChaptersCacheDataSource {
        private val dao = roomProvider.chapter()
        override fun saveChapters(chapters: List<ChapterData>) =
            dao.saveChapters(chapters.map { it.mapToDb(mapper) })

        override fun fetchChapters(): List<ChapterDb> = dao.fetchChapters()
    }
}
