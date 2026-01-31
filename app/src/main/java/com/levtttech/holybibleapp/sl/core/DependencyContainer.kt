package com.levtttech.holybibleapp.sl.core

import com.levtttech.holybibleapp.data.books.cloud.BookRu
import com.levtttech.holybibleapp.presentation.books.BookCache
import com.levtttech.holybibleapp.sl.books.BooksModule
import com.levtttech.holybibleapp.sl.books.BooksRepositoryContainer
import com.levtttech.holybibleapp.sl.chapters.ChaptersModule
import com.levtttech.holybibleapp.sl.chapters.ChaptersRepositoryContainer
import com.levtttech.holybibleapp.sl.deeplinks.DeeplinkModule
import com.levtttech.holybibleapp.sl.deeplinks.DeeplinkVersesModule
import com.levtttech.holybibleapp.sl.languages.LanguagesModule
import com.levtttech.holybibleapp.sl.verses.VersesModule

interface DependencyContainer {

    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule, private val useMocks: Boolean) :
        DependencyContainer {

        private val commonRepositoryContainer = CommonRepositoryContainer(
            ::booksRepository,
            ::chaptersRepository,
            ::russianBooks
        )

        override fun module(feature: Feature) = when (feature) {
            Feature.MAIN -> coreModule
            Feature.LANGUAGES -> LanguagesModule(coreModule, commonRepositoryContainer)
            Feature.BOOKS -> BooksModule(
                coreModule,
                useMocks,
                commonRepositoryContainer.booksRepository()
            ) {
                commonRepositoryContainer.clearBooksRepository()
            }
            Feature.CHAPTERS -> ChaptersModule(
                coreModule,
                commonRepositoryContainer.booksRepository(),
                commonRepositoryContainer.chaptersRepository()
            ) {
                commonRepositoryContainer.clearChaptersRepository()
            }
            Feature.VERSES -> VersesModule.Base(
                coreModule,
                commonRepositoryContainer.booksRepository(),
                commonRepositoryContainer.chaptersRepository(),
                useMocks,
            ) {
                commonRepositoryContainer.booksRu()
            }
            Feature.DEEPLINK_VERSES -> {
                val bookCache = BookCache.Deeplink(coreModule.resourceProvider)
                DeeplinkVersesModule(
                    coreModule,
                    commonRepositoryContainer.booksRepository(),
                    ChaptersRepositoryContainer(
                        coreModule,
                        useMocks,
                        bookCache,
                    ) { commonRepositoryContainer.booksRu() }.repository(),
                    bookCache,
                    useMocks
                ) {
                    commonRepositoryContainer.booksRu()
                }
            }
            Feature.DEEPLINK -> DeeplinkModule(coreModule)
        }

        private fun booksRepository(booksRu: () -> List<BookRu>) =
            BooksRepositoryContainer(coreModule, useMocks, booksRu).repository()

        private fun chaptersRepository(booksRu: () -> List<BookRu>) =
            ChaptersRepositoryContainer(coreModule, useMocks, coreModule.bookCache, booksRu)
                .repository()

        private fun russianBooks() =
            RussianBooksContainer(coreModule, RussianBooksTypeToken()).booksRu()
    }
}