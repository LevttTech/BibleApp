package com.levtttech.holybibleapp.sl.books

import com.levtttech.holybibleapp.domain.books.BooksRepository

interface BooksRepositoryProvider {

    fun booksRepository(): BooksRepository
}