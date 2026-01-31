package com.levtttech.holybibleapp.data.chapters.cache

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.chapters.ChapterData
import com.levtttech.holybibleapp.data.chapters.ToChapterMapper
import com.levtttech.holybibleapp.data.core.FavoritesList

interface ChaptersCacheMapper :
    Abstract.Mapper.Data<Pair<List<ChapterDb>, FavoritesList>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>) : ChaptersCacheMapper {
        override fun map(data: Pair<List<ChapterDb>, FavoritesList>) = data.let { (chapters, ids) ->
            chapters.map { chapterDb -> chapterDb.map(mapper, ids.isFavorite(chapterDb)) }
        }
    }
}