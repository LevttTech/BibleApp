package com.levtttech.holybibleapp.data.verses.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.core.FavoritesList
import com.levtttech.holybibleapp.data.verses.ToVerseMapper
import com.levtttech.holybibleapp.data.verses.VerseData

interface VersesCloudMapper :
    Abstract.Mapper.Data<Pair<List<VerseCloud>, FavoritesList>, List<VerseData>> {

    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCloudMapper {
        override fun map(data: Pair<List<VerseCloud>, FavoritesList>) = data.let { (verses, ids) ->
            verses.map { verse -> verse.map(mapper, ids.isFavorite(verse)) }
        }
    }
}