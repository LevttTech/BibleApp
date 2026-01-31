package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.data.books.BookData
import com.levtttech.holybibleapp.domain.books.BookDataMapper
import com.levtttech.holybibleapp.data.chapters.ChapterData
import com.levtttech.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.levtttech.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BookDomain
import com.levtttech.holybibleapp.domain.core.BaseDataToDomainMapper

class BaseChaptersDataToDomainMapper(
    private val mapper: ChapterDataToDomainMapper<ChapterDomain>,
    private val bookMapper: BookDataMapper<BookDomain>
) : BaseDataToDomainMapper<Pair<List<ChapterData>, BookData>, ChaptersDomain>(),
    ChaptersDataToDomainMapper<ChaptersDomain> {

    override fun map(data: Pair<List<ChapterData>, BookData>) = data.let { (chapters, book) ->
        ChaptersDomain.Success(
            chapters.map { chapter -> chapter.map(mapper) },
            book.map(bookMapper)
        )
    }

    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))
}