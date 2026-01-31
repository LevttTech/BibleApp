package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.domain.books.BookDomain

sealed class ChaptersDomain {

    abstract fun <T> map(mapper: ChaptersDomainToUiMapper<T>): T

    data class Success(
        private val chapters: List<ChapterDomain>, private val bookName: BookDomain,
    ) : ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) =
            mapper.map(Pair(chapters, bookName))
    }

    data class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) = mapper.map(errorType)
    }
}