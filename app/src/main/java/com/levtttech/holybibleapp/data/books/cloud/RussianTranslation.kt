package com.levtttech.holybibleapp.data.books.cloud

import com.levtttech.holybibleapp.core.Content
import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.books.ToBookMapper
import com.levtttech.holybibleapp.data.chapters.cloud.ChapterCloud
import com.levtttech.holybibleapp.data.chapters.cloud.ChapterRu
import com.google.gson.annotations.SerializedName

data class RussianTranslation(
    @SerializedName("version") private val allData: Map<String, BookRu>
) : Content<BookCloud> {
    override fun contentAsList() = allData.map { it.value }
}

data class BookRu(
    @SerializedName("book_name") private val name: String,
    @SerializedName("book") private val content: Map<String, ChapterRu>,
    @SerializedName("book_nr") private val number: Int
) : BookCloud, Matcher<Int>, Content<ChapterCloud> {

    override fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean) = mapper.map(
        number,
        name,
        if (number < NEW_TESTAMENT_POSITION) OLD_TESTAMENT else NEW_TESTAMENT,
        isFavorite
    )

    override fun matches(arg: Int) = number == arg

    override fun contentAsList() = content.map { it.value }

    private companion object {
        const val NEW_TESTAMENT_POSITION = 40
        const val OLD_TESTAMENT = "old"
        const val NEW_TESTAMENT = "new"
    }
}