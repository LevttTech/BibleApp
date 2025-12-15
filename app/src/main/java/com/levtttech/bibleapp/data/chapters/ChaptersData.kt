package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.chapters.ChaptersDomain

sealed class ChaptersData :
    Abstract.Object<ChaptersDomain, ChaptersDataToDomain<ChaptersDomain>> {

    class Success(private val chapters: List<ChapterData>) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomain<ChaptersDomain>): ChaptersDomain =
            mapper.map(chapters)
    }

    class Fail(private val e: Exception) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomain<ChaptersDomain>): ChaptersDomain =
            mapper.map(e)
    }
}

