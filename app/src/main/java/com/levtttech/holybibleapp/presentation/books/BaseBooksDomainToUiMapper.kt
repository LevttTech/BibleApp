package com.levtttech.holybibleapp.presentation.books

import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.domain.books.BookDomain
import com.levtttech.holybibleapp.domain.books.BookDomainToUiMapper
import com.levtttech.holybibleapp.domain.books.BooksDomainToUiMapper
import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.presentation.core.BaseDomainToUiMapper


class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper<BookUi>,
    private val uiDataCache: UiDataCache
) : BaseDomainToUiMapper<List<BookDomain>, BooksUi>(resourceProvider),
    BooksDomainToUiMapper<BooksUi> {

    override fun map(data: List<BookDomain>) =
        BooksUi.Base(uiDataCache.cache(data.map { it.map(bookMapper) }))

    override fun map(errorType: ErrorType) =
        BooksUi.Base(mutableListOf(BookUi.Fail(errorMessage(errorType))))
}