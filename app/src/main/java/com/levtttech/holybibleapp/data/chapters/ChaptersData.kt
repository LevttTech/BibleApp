package com.levtttech.holybibleapp.data.chapters

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.books.BookData


sealed class ChaptersData : Abstract.DataObject, Matcher<Int> {

    abstract fun <T> map(mapper: ChaptersDataToDomainMapper<T>, book: BookData): T

    override fun matches(arg: Int) = false

    data class Success(private val chapters: List<ChapterData>) : ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>, book: BookData) =
            mapper.map(Pair(chapters, book))

        override fun matches(arg: Int) = arg == chapters.size
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>, book: BookData) =
            mapper.map(e)
    }
}