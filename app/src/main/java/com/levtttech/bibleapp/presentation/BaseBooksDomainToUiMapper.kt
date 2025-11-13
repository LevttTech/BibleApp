package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.domain.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider) : BooksDomainToUiMapper {
    override fun map(books: List<Book>): BooksUi = BooksUi.Success(communication, books)
    override fun map(errorType: ErrorType): BooksUi = BooksUi.Fail(communication, errorType,resourceProvider)
}