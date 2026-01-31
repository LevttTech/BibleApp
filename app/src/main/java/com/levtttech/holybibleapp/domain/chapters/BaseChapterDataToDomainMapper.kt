package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.levtttech.holybibleapp.data.chapters.ChapterId


class BaseChapterDataToDomainMapper : ChapterDataToDomainMapper<ChapterDomain> {
    override fun map(data: Pair<ChapterId, Boolean>) = ChapterDomain.Base(data.first, data.second)
}