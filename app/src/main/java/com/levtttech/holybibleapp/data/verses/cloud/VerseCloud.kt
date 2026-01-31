package com.levtttech.holybibleapp.data.verses.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.verses.ToVerseMapper
import com.google.gson.annotations.SerializedName

interface VerseCloud : Abstract.CloudObject, Matcher<Int> {
    fun <T> map(mapper: ToVerseMapper<T>, isFavorite: Boolean): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("verseId")
        private val verseId: Int,
        @SerializedName("verse")
        private val text: String
    ) : VerseCloud {
        override fun <T> map(mapper: ToVerseMapper<T>, isFavorite: Boolean) =
            mapper.map(id, verseId, text, isFavorite)

        override fun matches(arg: Int) = arg == id
    }
}