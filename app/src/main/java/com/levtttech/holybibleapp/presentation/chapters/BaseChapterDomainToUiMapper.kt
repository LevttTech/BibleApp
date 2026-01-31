package com.levtttech.holybibleapp.presentation.chapters

import com.levtttech.holybibleapp.data.chapters.ChapterId
import com.levtttech.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.levtttech.holybibleapp.domain.chapters.ChapterDomainToUiMapper

class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper<ChapterUi>) :
    ChapterDomainToUiMapper<ChapterUi> {

    override fun map(data: Pair<ChapterId, Boolean>) = data.first.map(mapper, data.second)
}