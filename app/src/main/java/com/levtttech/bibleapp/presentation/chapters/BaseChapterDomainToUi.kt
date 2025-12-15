package com.levtttech.bibleapp.presentation.chapters

import com.levtttech.bibleapp.domain.chapters.ChapterDomainToUi

class BaseChapterDomainToUi : ChapterDomainToUi {
    override fun map(id: Int): ChapterUi = ChapterUi.Base(id)
}