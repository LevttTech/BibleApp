package com.levtttech.bibleapp.data.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.books.BooksDomain

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper<BooksDomain>> {

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper<BooksDomain>): BooksDomain =
            mapper.map(books)
    }

    data class Fail(private val e: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper<BooksDomain>): BooksDomain = mapper.map(e)
    }
}
