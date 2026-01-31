package com.levtttech.holybibleapp.domain.verses

import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.domain.books.BookDomain


sealed class VersesDomain {

    abstract fun <T> map(mapper: VersesDomainToUiMapper<T>): T

    data class Success(
        private val list: List<VerseDomain>,
        private val title: BookDomain,
        private val chapterNumber: Int,
    ) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) =
            mapper.map(Triple(list, title, chapterNumber))
    }

    data class Fail(private val errorType: ErrorType) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) = mapper.map(errorType)
    }
}