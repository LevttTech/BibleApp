package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.data.BookData
import com.levtttech.bibleapp.data.BookDomainMapper
import com.levtttech.bibleapp.data.BooksDataToDomainMapper
import com.levtttech.bibleapp.data.TestamentWrapper

class BaseBooksDataToDomainMapper(
    private val bookDomainMapper: BookDomainMapper,
    private val testamentMapper: TestamentTypeMapper,
) : BooksDataToDomainMapper {
    override fun map(books: List<BookData>): BooksDomain {
        val testament = TestamentWrapper.Base()
        val (oldTestament, newTestament) = books.partition { it.compareTestament(testament)}
        val booksDomain = mutableListOf<BookDomain>()
        if (oldTestament.isNotEmpty())
            booksDomain.add(testamentMapper.map(TestamentType.OLD))
            booksDomain.addAll(oldTestament.map { it.map(bookDomainMapper) })
        if (newTestament.isNotEmpty())
            booksDomain.add(testamentMapper.map(TestamentType.NEW))
            booksDomain.addAll(newTestament.map {
            it.map(bookDomainMapper)
        })
        return BooksDomain.Success(booksDomain)
    }

    override fun map(e: Exception): BooksDomain = BooksDomain.Fail(e)
}
