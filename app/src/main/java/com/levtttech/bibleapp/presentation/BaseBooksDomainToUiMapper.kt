package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.domain.BookDomain
import com.levtttech.bibleapp.domain.BookDomainToUiMapper
import com.levtttech.bibleapp.domain.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val mapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>): BooksUi = BooksUi.Success(books, mapper)
    override fun map(errorType: ErrorType): BooksUi = BooksUi.Fail(errorType,resourceProvider)
}