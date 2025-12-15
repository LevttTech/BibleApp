package com.levtttech.bibleapp.domain.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.BookDomainMapper
import com.levtttech.bibleapp.data.books.BooksDataToDomainMapper
import com.levtttech.bibleapp.data.books.TestamentWrapper

class BaseBooksDataToDomainMapper(
    private val bookDomainMapper: BookDomainMapper,
    private val testamentMapper: TestamentTypeMapper,
) : BooksDataToDomainMapper<BooksDomain>() {
    override fun map(data: List<BookData>): BooksDomain {
        val testament = TestamentWrapper.Base()
        val (oldTestament, newTestament) = data.partition { it.compareTestament(testament) }
        val booksDomain = mutableListOf<BookDomain>()
        if (oldTestament.isNotEmpty()) booksDomain.add(testamentMapper.map(TestamentType.OLD))
        booksDomain.addAll(oldTestament.map { it.map(bookDomainMapper) })
        if (newTestament.isNotEmpty()) booksDomain.add(testamentMapper.map(TestamentType.NEW))
        booksDomain.addAll(newTestament.map {
            it.map(bookDomainMapper)
        })
        return BooksDomain.Success(booksDomain)
    }

    override fun map(e: Exception): BooksDomain = BooksDomain.Fail(errorType(e))
}
