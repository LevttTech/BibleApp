package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.data.chapters.net.ChapterCloud
import com.levtttech.bibleapp.data.chapters.net.ChapterCloudToData

interface ChaptersCloudMapper {

    fun map(chapters: List<ChapterCloud>): List<ChapterData>

    class Base(private val mapper: ChapterCloudToData) : ChaptersCloudMapper {
        override fun map(chapters: List<ChapterCloud>): List<ChapterData> =
            chapters.map { it.map(mapper) }
    }
}