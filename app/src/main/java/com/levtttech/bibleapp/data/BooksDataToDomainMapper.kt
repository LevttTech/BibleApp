package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.domain.BooksDomain

interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<Book>): BooksDomain

    fun map(e: Exception): BooksDomain

}