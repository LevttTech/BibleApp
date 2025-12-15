package com.levtttech.bibleapp.data.chapters

import android.util.Log
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Read
import com.levtttech.bibleapp.data.chapters.net.ChapterCloud
import com.levtttech.bibleapp.data.chapters.net.ChapterCloudToData

interface ChaptersCloudMapper : Abstract.Mapper.Data<List<ChapterCloud>, List<ChapterData>> {
    class Base(private val mapper: ChapterCloudToData, private val bookCache: Read<Pair<Int,String>>) :
        ChaptersCloudMapper {
        override fun map(data: List<ChapterCloud>): List<ChapterData> {
            val bookId = bookCache.read().first
            return data.map { it.map(bookId, mapper) }
        }
    }
}