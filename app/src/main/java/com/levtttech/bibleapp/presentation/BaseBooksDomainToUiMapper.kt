package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.domain.BookDomain
import com.levtttech.bibleapp.domain.BookDomainToUiMapper
import com.levtttech.bibleapp.domain.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookDomainToUiMapper: BookDomainToUiMapper,
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>): BooksUi =
        BooksUi.Base(books.map { it.map(bookDomainToUiMapper) })

    override fun map(errorType: ErrorType): BooksUi {
        val message = when (errorType) {
            ErrorType.NO_CONNECTION -> resourceProvider.getString(R.string.no_connection_message)
            ErrorType.SERVICE_UNAVAILABLE -> resourceProvider.getString(R.string.service_unavailable_message)
            ErrorType.GENERIC_ERROR -> resourceProvider.getString(R.string.generic_error_message)
        }
        return BooksUi.Base(listOf(BookUi.Fail(message)))
    }
}