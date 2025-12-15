package com.levtttech.bibleapp.data.chapters.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.chapters.ChapterData

@Entity(tableName = "chapters", primaryKeys = ["book_id", "chapter"])
data class ChapterDb(
    @ColumnInfo("book_id") val bookId: Int,
    @ColumnInfo("chapter") val chapter: Int,
) : Abstract.Object<ChapterData, ChapterDbToDataMapper> {
    override fun map(mapper: ChapterDbToDataMapper): ChapterData = mapper.map(chapter, bookId)
}


interface ChapterDbToDataMapper : Abstract.Mapper {
    fun map(chapter: Int, bookId: Int): ChapterData
    class Base : ChapterDbToDataMapper {
        override fun map(
            chapter: Int,
            bookId: Int,
        ) =  ChapterData(chapter, bookId)
    }
}