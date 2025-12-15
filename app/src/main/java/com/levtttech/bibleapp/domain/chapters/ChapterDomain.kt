package com.levtttech.bibleapp.domain.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.chapters.ChapterUi

data class ChapterDomain(private val id: Int) : Abstract.Object<ChapterUi, ChapterDomainToUi> {
    override fun map(mapper: ChapterDomainToUi): ChapterUi = mapper.map(id)
}