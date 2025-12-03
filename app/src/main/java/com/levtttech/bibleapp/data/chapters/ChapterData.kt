package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.DbMapper
import com.levtttech.bibleapp.data.chapters.cache.ChapterDb
import com.levtttech.bibleapp.domain.chapter.ChapterDomain

data class ChapterData(private val id: Int) : Abstract.Object<ChapterDomain, ChapterDataToDomain>(), DbMapper<ChapterDb, ChapterDataToDb> {
    override fun map(mapper: ChapterDataToDomain): ChapterDomain = mapper.map(id)
    override fun mapToDb(mapper: ChapterDataToDb): ChapterDb = mapper.map(id,bookId)
}
interface ChapterDataToDb {
    fun map(id:Int, bookId: Int): ChapterDb
}