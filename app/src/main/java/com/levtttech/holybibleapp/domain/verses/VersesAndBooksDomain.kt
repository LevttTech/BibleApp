package com.levtttech.holybibleapp.domain.verses

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.data.books.BookData
import com.levtttech.holybibleapp.data.books.BooksData
import com.levtttech.holybibleapp.domain.books.BooksDataMapper
import com.levtttech.holybibleapp.data.chapters.ChaptersData
import com.levtttech.holybibleapp.data.verses.VersesData
import com.levtttech.holybibleapp.data.verses.VersesDataToDomainMapper
import com.levtttech.holybibleapp.domain.chapters.ErrorChaptersDataToDomainMapper


class VersesAndBooksDomain(
    private val verses: VersesData,
    private val books: BooksData,
    private val chapters: ChaptersData,
    private val bookId: Read<Int>,
    private val chapterNumber: Read<Int>
) : Abstract.CoreObject<VersesDomain, VersesDataToDomainMapper<VersesDomain>> {
    override fun map(mapper: VersesDataToDomainMapper<VersesDomain>) = when {
        books is BooksData.Success && verses is VersesData.Success && chapters is ChaptersData.Success -> {
            val chapterId = chapterNumber.read()
            verses.map(
                mapper,
                books.map(BooksDataMapper.Id(bookId)),
                chapterId,
                chapters.matches(chapterId)
            )
        }
        verses is VersesData.Fail -> verses.map(mapper, BookData.Empty(), 0, true)
        chapters is ChaptersData.Fail -> {
            val errorType = chapters.map(ErrorChaptersDataToDomainMapper(), BookData.Empty())
            VersesDomain.Fail(errorType)
        }
        else -> VersesDomain.Fail(books.map(BooksDataMapper.Error()))
    }
}