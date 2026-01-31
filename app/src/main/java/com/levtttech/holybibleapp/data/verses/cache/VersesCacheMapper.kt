package com.levtttech.holybibleapp.data.verses.cache

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.core.FavoritesList
import com.levtttech.holybibleapp.data.verses.ToVerseMapper
import com.levtttech.holybibleapp.data.verses.VerseData


interface VersesCacheMapper :
    Abstract.Mapper.Data<Pair<List<VerseDb>, FavoritesList>, List<VerseData>> {

    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCacheMapper {
        override fun map(data: Pair<List<VerseDb>, FavoritesList>) = data.let { (verses, ids) ->
            verses.map { verseDb -> verseDb.map(mapper, ids.isFavorite(verseDb)) }
        }
    }
}