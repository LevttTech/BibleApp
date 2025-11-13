package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.data.BooksDataToDomainMapper
import com.levtttech.bibleapp.data.BooksRepository
import com.levtttech.bibleapp.presentation.BooksUi

interface BooksInteractor {

    suspend fun fetchBooks(): BookDomain


    class Base(private val bookRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper) : BooksInteractor {
        override suspend fun fetchBooks(): BookDomain {
            return bookRepository.fetchBooks().map(mapper)
        }
    }
} 