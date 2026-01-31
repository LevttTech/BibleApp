package com.levtttech.holybibleapp.data.chapters.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Multiply
import com.levtttech.holybibleapp.data.chapters.ChapterData
import com.levtttech.holybibleapp.data.chapters.ToChapterMapper
import com.levtttech.holybibleapp.data.core.FavoritesList

interface ChaptersCloudMapper :
    Abstract.Mapper.Data<Pair<List<ChapterCloud>, FavoritesList>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>, private val multiply: Multiply) :
        ChaptersCloudMapper {
        override fun map(data: Pair<List<ChapterCloud>, FavoritesList>) =
            data.let { (chapters, ids) ->
                chapters.map { chapterCloud ->
                    val isFavorite = ids.isFavorite(chapterCloud) { id -> multiply.rest(id) }
                    chapterCloud.map(mapper, isFavorite)
                }
            }
    }
}