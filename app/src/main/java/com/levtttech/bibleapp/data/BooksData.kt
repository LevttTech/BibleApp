package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.BooksDomain

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper>() {

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain = mapper.map(books)
    }

    data class Fail(private val e: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain = mapper.map(e)
    }
}
