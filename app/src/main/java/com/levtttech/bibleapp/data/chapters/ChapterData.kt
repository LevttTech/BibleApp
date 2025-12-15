package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.books.DbMapper
import com.levtttech.bibleapp.data.chapters.cache.ChapterDb
import com.levtttech.bibleapp.domain.chapters.ChapterDomain

data class ChapterData(private val id: Int, private val bookId: Int) :
    Abstract.Object<ChapterDomain, ChapterDataToDomain>, DbMapper<ChapterDb, ChapterDataToDb> {
    override fun map(mapper: ChapterDataToDomain): ChapterDomain = mapper.map(id)
    override fun mapToDb(mapper: ChapterDataToDb): ChapterDb = mapper.map(id, bookId)
}

interface ChapterDataToDb : Abstract.Mapper {
    fun map(id: Int, bookId: Int): ChapterDb

    class Base : ChapterDataToDb {
        override fun map(
            id: Int,
            bookId: Int,
        ): ChapterDb = ChapterDb(bookId, id)
    }
}