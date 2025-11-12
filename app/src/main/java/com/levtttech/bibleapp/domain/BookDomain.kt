package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.presentation.BooksUi
import java.net.HttpRetryException
import java.net.UnknownHostException

sealed class BookDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {

    class Success(private val books: List<Book>) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpRetryException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
