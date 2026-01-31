package com.levtttech.holybibleapp.domain.books

import com.levtttech.holybibleapp.domain.core.Interactor
import com.levtttech.holybibleapp.domain.core.ScrollPosition

interface BooksInteractor : Interactor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataMapper<BooksDomain>,
        scrollPosition: ScrollPosition
    ) : Interactor.Abstract(booksRepository, scrollPosition), BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchData().map(mapper)
    }
}