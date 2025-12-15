package com.levtttech.bibleapp.domain.chapters

import com.levtttech.bibleapp.data.chapters.ChapterDataToDomain

class BaseChapterDomainMapper : ChapterDataToDomain {
    override fun map(id: Int): ChapterDomain = ChapterDomain(id)
}