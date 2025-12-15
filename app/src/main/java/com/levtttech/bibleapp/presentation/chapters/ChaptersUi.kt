package com.levtttech.bibleapp.presentation.chapters

import com.levtttech.bibleapp.core.Abstract

sealed class ChaptersUi : Abstract.Object<Unit, ChaptersCommunication> {

    data class Success(private val chapters: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(chapters)
    }

    data class Fail(private val error: List<ChapterUi>): ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(error)
    }
}