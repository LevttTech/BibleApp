package com.levtttech.bibleapp.domain.chapter

import com.levtttech.bibleapp.data.chapters.ChapterDataToDomain

class BaseChapterDomainMapper : ChapterDataToDomain {
    override fun map(id: Int): ChapterDomain = ChapterDomain(id)
}