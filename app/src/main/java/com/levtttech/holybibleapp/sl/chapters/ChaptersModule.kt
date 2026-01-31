package com.levtttech.holybibleapp.sl.chapters

import com.levtttech.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.levtttech.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.domain.chapters.BaseChapterDataToDomainMapper
import com.levtttech.holybibleapp.domain.chapters.BaseChaptersDataToDomainMapper
import com.levtttech.holybibleapp.domain.chapters.ChaptersInteractor
import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository
import com.levtttech.holybibleapp.presentation.books.BaseBookDomainToUiMapper
import com.levtttech.holybibleapp.presentation.chapters.BaseChapterDomainToUiMapper
import com.levtttech.holybibleapp.presentation.chapters.BaseChaptersDomainToUiMapper
import com.levtttech.holybibleapp.presentation.chapters.ChaptersCommunication
import com.levtttech.holybibleapp.presentation.chapters.ChaptersViewModel
import com.levtttech.holybibleapp.presentation.core.FeatureNavigation
import com.levtttech.holybibleapp.sl.core.BaseModule
import com.levtttech.holybibleapp.sl.core.CoreModule
import com.levtttech.holybibleapp.sl.core.Feature

class ChaptersModule(
    private val coreModule: CoreModule,
    private val booksRepository: BooksRepository,
    private val repository: ChaptersRepository,
    private val clearChapters: () -> Unit,
) : BaseModule<ChaptersViewModel> {

    override fun viewModel() = ChaptersViewModel(
        interactor(),
        communication(),
        mapper(),
        FeatureNavigation.Base(
            coreModule.navigator,
            coreModule.navigationCommunication,
            Feature.CHAPTERS
        ),
        coreModule.chapterCache,
        coreModule.resourceProvider,
        clearChapters
    )

    private fun interactor() = ChaptersInteractor.Base(
        repository,
        BaseChaptersDataToDomainMapper(
            BaseChapterDataToDomainMapper(),
            BaseBookDataToDomainMapper()
        ),
        booksRepository,
        coreModule.bookCache,
        coreModule.scrollPositionCache
    )

    private fun communication() = ChaptersCommunication.Base()

    private fun mapper() = BaseChaptersDomainToUiMapper(
        BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(coreModule.resourceProvider)),
        BaseBookDomainToUiMapper(coreModule.resourceProvider),
        coreModule.resourceProvider
    )
}