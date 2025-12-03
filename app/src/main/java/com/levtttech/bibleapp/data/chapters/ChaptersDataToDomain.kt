package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.chapter.ChaptersDomain

interface ChaptersDataToDomain : Abstract.Mapper {
    fun map(chapters: List<ChapterData>): ChaptersDomain

    fun map(e: Exception): ChaptersDomain
}