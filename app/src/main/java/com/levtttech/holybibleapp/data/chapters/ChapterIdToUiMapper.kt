package com.levtttech.holybibleapp.data.chapters

import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.presentation.chapters.ChapterUi


interface ChapterIdToUiMapper<T> {
    fun map(realId: Int, generatedId: Int, isFavorite: Boolean): T

    class Base(private val resourceProvider: ResourceProvider) : ChapterIdToUiMapper<ChapterUi> {
        override fun map(realId: Int, generatedId: Int, isFavorite: Boolean) = ChapterUi.Base(
            realId,
            generatedId,
            resourceProvider.string(R.string.chapter_number, realId),
            isFavorite
        )
    }
}