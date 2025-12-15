package com.levtttech.bibleapp.presentation.chapters

import com.levtttech.bibleapp.domain.books.ErrorType
import com.levtttech.bibleapp.domain.chapters.ChapterDomain
import com.levtttech.bibleapp.domain.chapters.ChapterDomainToUi
import com.levtttech.bibleapp.domain.chapters.ChaptersDomainToUi
import com.levtttech.bibleapp.presentation.books.ResourceProvider

class BaseChaptersUiMapper(
    private val mapper: ChapterDomainToUi,
    resourceProvider: ResourceProvider,
) : ChaptersDomainToUi<ChaptersUi>(resourceProvider) {
    override fun map(e: ErrorType): ChaptersUi =
        ChaptersUi.Fail(listOf(ChapterUi.Fail(uiError(e))))

    override fun map(data: List<ChapterDomain>): ChaptersUi =
        ChaptersUi.Success(data.map { it.map(mapper) })
}