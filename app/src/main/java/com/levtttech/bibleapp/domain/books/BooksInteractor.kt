package com.levtttech.bibleapp.domain.books

import com.levtttech.bibleapp.data.books.BooksDataToDomainMapper
import com.levtttech.bibleapp.data.books.BooksRepository

interface BooksInteractor {
    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val bookRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper<BooksDomain>,
    ) : BooksInteractor {
        override suspend fun fetchBooks(): BooksDomain {
            return bookRepository.fetch().map(mapper)
        }
    }
} 