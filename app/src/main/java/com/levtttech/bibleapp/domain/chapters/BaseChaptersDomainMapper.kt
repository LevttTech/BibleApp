package com.levtttech.bibleapp.domain.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.chapters.ChapterData
import com.levtttech.bibleapp.data.chapters.ChapterDataToDomain
import com.levtttech.bibleapp.data.chapters.ChaptersDataToDomain


class BaseChaptersDomainMapper(
    private val mapper: ChapterDataToDomain,
) : ChaptersDataToDomain<ChaptersDomain>(){
    override fun map(e: Exception): ChaptersDomain = ChaptersDomain.Fail(errorType(e))

    override fun map(data: List<ChapterData>): ChaptersDomain =
        ChaptersDomain.Success(data.map { it.map(mapper) })
}

