package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.data.BookData
import com.levtttech.bibleapp.data.BookDomainMapper
import com.levtttech.bibleapp.data.BooksDataToDomainMapper

class BaseBookDataToDomainMapper(
    private val mapper: BookDomainMapper,
    private val mapperTestament: TestamentTypeMapper,
) : BooksDataToDomainMapper {
    override fun map(books: List<BookData>): BooksDomain =
        BooksDomain.Success(books, mapper, mapperTestament)

    override fun map(e: Exception): BooksDomain = BooksDomain.Fail(e)
}
