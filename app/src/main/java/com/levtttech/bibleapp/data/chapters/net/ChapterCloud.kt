package com.levtttech.bibleapp.data.chapters.net

import com.google.gson.annotations.SerializedName
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.chapters.ChapterData

data class ChapterCloud(
    @SerializedName("id") private val id: Int,
) : Abstract.Object<ChapterData, ChapterCloudToData> {
    override fun map(mapper: ChapterCloudToData) = throw IllegalStateException("Cant be used")

    fun map(bookId: Int, mapper: ChapterCloudToData) = mapper.map(id, bookId)
}

interface ChapterCloudToData : Abstract.Mapper {
    fun map(id: Int, bookId: Int): ChapterData

    class Base : ChapterCloudToData {
        override fun map(
            id: Int,
            bookId: Int,
        ): ChapterData = ChapterData(id, bookId)
    }
}

