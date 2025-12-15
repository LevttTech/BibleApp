package com.levtttech.bibleapp.data.chapters.cache

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.chapters.ChapterData

interface ChaptersCacheMapper : Abstract.Mapper.Data<List<ChapterDb>, List<ChapterData>> {
    class Base(private val mapper: ChapterDbToDataMapper) : ChaptersCacheMapper {
        override fun map(data: List<ChapterDb>): List<ChapterData> = data.map { it.map(mapper) }
    }
}