package com.levtttech.bibleapp.presentation.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.books.BookDomain
import com.levtttech.bibleapp.domain.books.BookDomainToUiMapper
import com.levtttech.bibleapp.domain.books.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.books.ErrorType

class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookDomainToUiMapper: BookDomainToUiMapper,
) : BooksDomainToUiMapper<BooksUi>(resourceProvider) {
    override fun map(data: List<BookDomain>): BooksUi =
        BooksUi.Base(data.map { it.map(bookDomainToUiMapper) })

    override fun map(e: ErrorType): BooksUi {
        return BooksUi.Base(listOf(BookUi.Fail(uiError(e))))
    }
}