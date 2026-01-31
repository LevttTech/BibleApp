package com.levtttech.holybibleapp.data.chapters.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.chapters.ToChapterMapper
import com.google.gson.annotations.SerializedName

interface ChapterCloud : Abstract.CloudObject, Matcher<Int> {

    fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean): T

    data class Base(
        @SerializedName("id")
        private val id: Int
    ) : ChapterCloud {
        override fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean) =
            mapper.map(id, isFavorite)

        override fun matches(arg: Int) = arg == id
    }
}