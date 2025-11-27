package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.BooksUi
import java.net.HttpRetryException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {

    data class Success(
        private val books: List<BookDomain>,
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(books)
    }

    data class Fail(private val e: Exception) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpRetryException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}