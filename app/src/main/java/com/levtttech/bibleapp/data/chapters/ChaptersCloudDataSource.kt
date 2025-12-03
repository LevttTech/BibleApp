package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.data.chapters.net.ChapterCloud
import com.levtttech.bibleapp.data.chapters.net.ChapterService

interface ChaptersCloudDataSource {
    suspend fun fetchChapters(id: Int): List<ChapterCloud>

    class Base(private val service: ChapterService) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(id: Int): List<ChapterCloud> = service.fetchChapters(id)
    }
}