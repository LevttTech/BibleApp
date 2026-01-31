package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.data.books.BookData
import com.levtttech.holybibleapp.data.books.BooksData
import com.levtttech.holybibleapp.domain.books.BooksDataMapper
import com.levtttech.holybibleapp.data.chapters.ChaptersData
import com.levtttech.holybibleapp.data.chapters.ChaptersDataToDomainMapper

class ChaptersAndBooksDomain(
    private val chapters: ChaptersData,
    private val books: BooksData,
    private val bookId: Read<Int>
) : Abstract.CoreObject<ChaptersDomain, ChaptersDataToDomainMapper<ChaptersDomain>> {

    override fun map(mapper: ChaptersDataToDomainMapper<ChaptersDomain>) = when {
        books is BooksData.Success && chapters is ChaptersData.Success ->
            chapters.map(mapper, books.map(BooksDataMapper.Id(bookId)))
        chapters is ChaptersData.Fail -> chapters.map(mapper, BookData.Empty())
        else -> ChaptersDomain.Fail(books.map(BooksDataMapper.Error()))
    }
}