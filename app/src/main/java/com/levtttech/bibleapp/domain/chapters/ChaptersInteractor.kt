package com.levtttech.bibleapp.domain.chapters

import com.levtttech.bibleapp.data.chapters.ChaptersDataToDomain
import com.levtttech.bibleapp.data.chapters.ChaptersRepository
import com.levtttech.bibleapp.domain.core.Repository


interface ChaptersInteractor {
    suspend fun fetchChapters() : ChaptersDomain

    class Base(private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomain<ChaptersDomain>) : ChaptersInteractor {
        override suspend fun fetchChapters(): ChaptersDomain = repository.fetch().map(mapper)
    }
}