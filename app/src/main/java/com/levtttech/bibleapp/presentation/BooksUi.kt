package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.BookDomain
import com.levtttech.bibleapp.domain.BookDomainToUiMapper
import com.levtttech.bibleapp.domain.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication>() {
    class Success(
        private val books: List<BookDomain>,
        private val mapperToBookUi: BookDomainToUiMapper
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) =
            mapper.map(books.map { book -> book.map(mapperToBookUi) })

    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val message = when (errorType) {
                ErrorType.NO_CONNECTION -> resourceProvider.getString(R.string.no_connection_message)
                ErrorType.SERVICE_UNAVAILABLE -> resourceProvider.getString(R.string.service_unavailable_message)
                ErrorType.GENERIC_ERROR -> resourceProvider.getString(R.string.generic_error_message)
            }
            mapper.map(listOf(BookUi.Fail(message)))
        }
    }
}