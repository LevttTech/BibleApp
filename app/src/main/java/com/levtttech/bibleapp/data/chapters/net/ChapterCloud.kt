package com.levtttech.bibleapp.data.chapters.net

import com.google.gson.annotations.SerializedName
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.chapters.ChapterData

data class ChapterCloud(
    @SerializedName("id") private val id: Int
) : Abstract.Object<ChapterData, ChapterCloudToData>() {
    override fun map(mapper: ChapterCloudToData): ChapterData = mapper.map(id)
}

interface ChapterCloudToData : Abstract.Mapper {
    fun map(id: Int) : ChapterData
}