package com.levtttech.holybibleapp.domain.verses

import com.levtttech.holybibleapp.domain.core.Interactor
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.data.verses.VersesDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository
import com.levtttech.holybibleapp.domain.core.ScrollPosition
import com.levtttech.holybibleapp.presentation.chapters.ChapterCache
import com.levtttech.holybibleapp.sl.core.Feature


interface VersesInteractor : Interactor {

    suspend fun fetchVerses(): VersesDomain

    fun showNextChapter()

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        scrollPositionCache: ScrollPosition,
        private val chaptersRepository: ChaptersRepository,
        private val chapterCache: ChapterCache,
    ) : Interactor.Abstract(repository, scrollPositionCache), VersesInteractor {

        override fun showNextChapter() {
            chapterCache.save(chapterCache.read() + 1)
            saveScrollPosition(Feature.CHAPTERS, scrollPosition(Feature.CHAPTERS) + 1)
        }

        override suspend fun fetchVerses() = VersesAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            chaptersRepository.fetchData(),
            bookIdContainer,
            chapterCache
        ).map(mapper)
    }
}