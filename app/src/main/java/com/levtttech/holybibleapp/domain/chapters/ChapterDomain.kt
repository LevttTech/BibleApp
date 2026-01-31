package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.data.chapters.ChapterId


interface ChapterDomain {
    fun <T> map(mapper: ChapterDomainToUiMapper<T>): T

    data class Base(private val chapterId: ChapterId, private val isFavorite: Boolean) :
        ChapterDomain {
        override fun <T> map(mapper: ChapterDomainToUiMapper<T>) =
            mapper.map(Pair(chapterId, isFavorite))
    }
}