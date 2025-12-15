package com.levtttech.bibleapp.domain.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.books.ErrorType
import com.levtttech.bibleapp.presentation.chapters.ChapterUi

interface ChapterDomainToUi : Abstract.Mapper {
    fun map(id: Int): ChapterUi
}