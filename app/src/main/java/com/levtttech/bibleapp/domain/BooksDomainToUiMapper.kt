package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.presentation.BooksUi

interface BooksDomainToUiMapper: Abstract.Mapper {
    fun map(books: List<Book>): BooksUi

    fun map(errorType: ErrorType): BooksUi
}