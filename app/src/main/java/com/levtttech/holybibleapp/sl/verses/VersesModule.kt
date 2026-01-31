package com.levtttech.holybibleapp.sl.verses

import com.levtttech.holybibleapp.data.books.cloud.BookRu
import com.levtttech.holybibleapp.data.verses.BaseVersesRepository
import com.levtttech.holybibleapp.data.verses.ToVerseMapper
import com.levtttech.holybibleapp.data.verses.cache.VerseDataToDbMapper
import com.levtttech.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.levtttech.holybibleapp.data.verses.cache.VersesCacheMapper
import com.levtttech.holybibleapp.data.verses.cloud.VerseTypeToken
import com.levtttech.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.levtttech.holybibleapp.data.verses.cloud.VersesCloudMapper
import com.levtttech.holybibleapp.data.verses.cloud.VersesService
import com.levtttech.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository
import com.levtttech.holybibleapp.presentation.books.BookCache
import com.levtttech.holybibleapp.presentation.chapters.ChapterCache
import com.levtttech.holybibleapp.sl.core.BaseModule
import com.levtttech.holybibleapp.sl.core.CoreModule
import com.levtttech.holybibleapp.domain.verses.BaseVerseDataToDomainMapper
import com.levtttech.holybibleapp.domain.verses.BaseVersesDataToDomainMapper
import com.levtttech.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.levtttech.holybibleapp.domain.verses.VersesInteractor
import com.levtttech.holybibleapp.domain.verses.VersesRepository
import com.levtttech.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.levtttech.holybibleapp.presentation.verses.BaseVersesDomainToUiMapper
import com.levtttech.holybibleapp.presentation.verses.VersesCommunication
import com.levtttech.holybibleapp.presentation.verses.VersesUi
import com.levtttech.holybibleapp.presentation.verses.VersesViewModel
abstract class VersesModule(
    protected val coreModule: CoreModule,
    private val booksRepository: BooksRepository,
    private val chaptersRepository: ChaptersRepository,
    private val useMocks: Boolean,
    private val bookCache: BookCache,
    private val chapterCache: ChapterCache,
    private val booksRu: () -> List<BookRu>,
) : BaseModule<VersesViewModel> {

    class Base(
        coreModule: CoreModule,
        booksRepository: BooksRepository,
        chaptersRepository: ChaptersRepository,
        useMocks: Boolean,
        booksRu: () -> List<BookRu>,
    ) : VersesModule(
        coreModule,
        booksRepository,
        chaptersRepository,
        useMocks,
        coreModule.bookCache,
        coreModule.chapterCache,
        booksRu,
    ) {
        override fun mapper() = BaseVersesDomainToUiMapper(
            BaseVerseDomainToUiMapper(coreModule.resourceProvider), coreModule.resourceProvider
        )

        override fun viewModel() = VersesViewModel.Base(
            coreModule.navigator,
            interactor(),
            communications(),
            mapper(),
            coreModule.resourceProvider,
            coreModule.deeplinkData,
            coreModule.multiply
        )
    }

    protected fun communications() = VersesCommunication.Base()

    protected abstract fun mapper(): VersesDomainToUiMapper<VersesUi>

    protected fun interactor() = VersesInteractor.Base(
        repository(),
        BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper(), BaseBookDataToDomainMapper()),
        booksRepository,
        bookCache,
        coreModule.scrollPositionCache,
        chaptersRepository,
        chapterCache,
    )

    private fun repository(): VersesRepository {
        val mapper = ToVerseMapper.Base()
        return BaseVersesRepository(
            cloudDataSource(),
            cacheDataSource(),
            VersesCloudMapper.Base(mapper),
            VersesCacheMapper.Base(mapper),
            chapterCache,
            bookCache,
            coreModule.multiply,
            coreModule.multiplyTwice
        )
    }

    private fun cacheDataSource() =
        VersesCacheDataSource.Base(coreModule.realmProvider, VerseDataToDbMapper.Base())

    private fun cloudDataSource() = if (useMocks)
        VersesCloudDataSource.Mock(coreModule.multiplyTwice, coreModule.multiply)
    else
        VersesCloudDataSource.Base(coreModule.language, english(), russian())

    private fun russian() =
        VersesCloudDataSource.Russian(booksRu, coreModule.multiplyTwice, coreModule.multiply)

    private fun english() = VersesCloudDataSource.English(
        coreModule.makeService(VersesService::class.java),
        coreModule.gson,
        VerseTypeToken()
    )
}