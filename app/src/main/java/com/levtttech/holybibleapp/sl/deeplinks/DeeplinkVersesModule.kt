package com.levtttech.holybibleapp.sl.deeplinks

import com.levtttech.holybibleapp.data.books.cloud.BookRu
import com.levtttech.holybibleapp.data.verse.VerseIdCache
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository
import com.levtttech.holybibleapp.presentation.books.BookCache
import com.levtttech.holybibleapp.presentation.chapters.ChapterCache
import com.levtttech.holybibleapp.presentation.deeplink.DeeplinkVersesDomainToUiMapper
import com.levtttech.holybibleapp.presentation.deeplink.DeeplinkVersesViewModel
import com.levtttech.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.levtttech.holybibleapp.sl.core.CoreModule
import com.levtttech.holybibleapp.sl.verses.VersesModule

class DeeplinkVersesModule(
    coreModule: CoreModule,
    booksRepository: BooksRepository,
    chaptersRepository: ChaptersRepository,
    bookCache: BookCache,
    useMocks: Boolean,
    booksRu: () -> List<BookRu>
) : VersesModule(
    coreModule,
    booksRepository,
    chaptersRepository,
    useMocks,
    bookCache,
    ChapterCache.Deeplink(coreModule.resourceProvider),
    booksRu
) {

    override fun mapper() = DeeplinkVersesDomainToUiMapper(
        VerseIdCache.Deeplink(coreModule.resourceProvider),
        BaseVerseDomainToUiMapper(coreModule.resourceProvider),
        coreModule.resourceProvider
    )

    override fun viewModel() = DeeplinkVersesViewModel(
        coreModule.navigator,
        interactor(),
        communications(),
        mapper(),
        coreModule.deeplinkData,
        coreModule.resourceProvider,
        coreModule.multiply
    )
}