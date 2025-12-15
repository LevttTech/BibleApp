package com.levtttech.bibleapp.domain.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.books.BookUi
import com.levtttech.bibleapp.presentation.books.BooksUi
import java.net.HttpRetryException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper<BooksUi>> {

    data class Success(
        private val books: List<BookDomain>,
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper<BooksUi>): BooksUi = mapper.map(books)
    }

    data class Fail(private val e: ErrorType) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper<BooksUi>): BooksUi = mapper.map(e)
    }
}