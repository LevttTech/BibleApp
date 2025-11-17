package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.data.BooksDataToDomainMapper
import com.levtttech.bibleapp.data.BooksRepository
import com.levtttech.bibleapp.presentation.BooksUi

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain


    class Base(private val bookRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper) : BooksInteractor {
        override suspend fun fetchBooks(): BooksDomain {
            return bookRepository.fetchBooks().map(mapper)
        }
    }
} 