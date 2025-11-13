package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.data.BooksDataToDomainMapper

class BaseBookDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>): BookDomain = BookDomain.Success(books)

    override fun map(e: Exception): BookDomain = BookDomain.Fail(e)
}