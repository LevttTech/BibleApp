package com.levtttech.holybibleapp.data.chapters.cloud

import com.levtttech.holybibleapp.core.Content
import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.chapters.ToChapterMapper
import com.levtttech.holybibleapp.data.verses.cloud.VerseRu
import com.google.gson.annotations.SerializedName
data class ChapterRu(
    @SerializedName("chapter_nr") private val number: Int,
    @SerializedName("chapter") private val content: Map<String, VerseRu>
) : ChapterCloud, Matcher<Int>, Content<Pair<Int, VerseRu>> {
    override fun matches(arg: Int) = number == arg
    override fun contentAsList() = content.map { (key, value) -> Pair(key.toInt(), value) }
    override fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean) =
        mapper.map(number, isFavorite)
}