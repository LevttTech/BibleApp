package com.levtttech.holybibleapp.presentation.chapters

import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.domain.books.BookDomain
import com.levtttech.holybibleapp.domain.books.BookDomainToUiMapper
import com.levtttech.holybibleapp.domain.chapters.ChapterDomain
import com.levtttech.holybibleapp.domain.chapters.ChapterDomainToUiMapper
import com.levtttech.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.levtttech.holybibleapp.presentation.books.BookUi
import com.levtttech.holybibleapp.presentation.core.BaseDomainToUiMapper

class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper<ChapterUi>,
    private val bookMapper: BookDomainToUiMapper<BookUi>,
    resourceProvider: ResourceProvider
) : BaseDomainToUiMapper<Pair<List<ChapterDomain>, BookDomain>, ChaptersUi>(resourceProvider),
    ChaptersDomainToUiMapper<ChaptersUi> {

    override fun map(data: Pair<List<ChapterDomain>, BookDomain>) = ChaptersUi.Base(
        ArrayList(data.first.map { chapterDomain -> chapterDomain.map(mapper) }),
        data.second.map(bookMapper)
    )

    override fun map(errorType: ErrorType) = errorMessage(errorType).let { error ->
        ChaptersUi.Base(mutableListOf(ChapterUi.Fail(error)), BookUi.Empty)
    }
}