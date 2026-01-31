package com.levtttech.holybibleapp.data.verses.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.verses.ToVerseMapper
import com.google.gson.annotations.SerializedName

data class VerseRu(@SerializedName("verse") private val text: String) :
    Abstract.CoreObject<VerseCloud, VerseToWrapperMapper> {
    override fun map(mapper: VerseToWrapperMapper) = mapper.map(text)
}

data class VerseRuWrapper(
    private val finalId: Int, private val id: Int, private val verse: String
) : VerseCloud {
    override fun <T> map(mapper: ToVerseMapper<T>, isFavorite: Boolean) =
        mapper.map(finalId, id, verse, isFavorite)

    override fun matches(arg: Int) = arg == finalId
}