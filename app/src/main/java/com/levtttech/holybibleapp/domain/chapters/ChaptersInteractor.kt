package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.domain.core.Interactor
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.domain.core.ScrollPosition

interface ChaptersInteractor : Interactor {

    suspend fun fetchChapters(): ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper<ChaptersDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        scrollPosition: ScrollPosition
    ) : Interactor.Abstract(repository, scrollPosition), ChaptersInteractor {
        override suspend fun fetchChapters() = ChaptersAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer
        ).map(mapper)
    }
}