package com.levtttech.bibleapp.domain.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.books.ErrorType
import com.levtttech.bibleapp.presentation.chapters.ChaptersUi

sealed class ChaptersDomain : Abstract.Object<ChaptersUi, ChaptersDomainToUi<ChaptersUi>> {

    data class Success(private val chapters: List<ChapterDomain>) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUi<ChaptersUi>): ChaptersUi = mapper.map(chapters)
    }

    data class Fail(private val e: ErrorType) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUi<ChaptersUi>): ChaptersUi = mapper.map(e)
    }
}