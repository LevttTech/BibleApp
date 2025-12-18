package com.levtttech.bibleapp.servicelocator.chapters

import com.google.gson.Gson
import com.levtttech.bibleapp.data.books.BooksCloudDataSource
import com.levtttech.bibleapp.data.books.BooksCloudMapper
import com.levtttech.bibleapp.data.books.BooksRepository
import com.levtttech.bibleapp.data.books.RoomWrapper
import com.levtttech.bibleapp.data.books.ToBookDataMapper
import com.levtttech.bibleapp.data.books.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.books.cache.BooksCacheMapper
import com.levtttech.bibleapp.data.books.cache.BooksDatabase
import com.levtttech.bibleapp.data.books.cache.ToDbMapper
import com.levtttech.bibleapp.data.chapters.ChapterDataToDb
import com.levtttech.bibleapp.data.chapters.ChaptersCloudDataSource
import com.levtttech.bibleapp.data.chapters.ChaptersCloudMapper
import com.levtttech.bibleapp.data.chapters.ChaptersRepository
import com.levtttech.bibleapp.data.chapters.cache.ChapterDbToDataMapper
import com.levtttech.bibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.levtttech.bibleapp.data.chapters.cache.ChaptersCacheMapper
import com.levtttech.bibleapp.data.chapters.net.ChapterCloudToData
import com.levtttech.bibleapp.data.chapters.net.ChapterService
import com.levtttech.bibleapp.domain.books.BaseBookDomainMapper
import com.levtttech.bibleapp.domain.books.BaseBooksDataToDomainMapper
import com.levtttech.bibleapp.domain.books.BooksInteractor
import com.levtttech.bibleapp.domain.books.TestamentTypeMapper
import com.levtttech.bibleapp.domain.chapters.BaseChapterDomainMapper
import com.levtttech.bibleapp.domain.chapters.BaseChaptersDomainMapper
import com.levtttech.bibleapp.domain.chapters.ChaptersInteractor
import com.levtttech.bibleapp.presentation.chapters.BaseChapterDomainToUi
import com.levtttech.bibleapp.presentation.chapters.BaseChaptersUiMapper
import com.levtttech.bibleapp.presentation.chapters.ChaptersCommunication
import com.levtttech.bibleapp.presentation.chapters.ChaptersViewModel
import com.levtttech.bibleapp.servicelocator.core.BaseModule
import com.levtttech.bibleapp.servicelocator.core.CoreModule

class ChaptersModule(
    private val useMocks: Boolean,
    private val coreModule: CoreModule
) : BaseModule<ChaptersViewModel> {
    private fun provideChapterService(): ChapterService {
        return coreModule.retrofit.create(ChapterService::class.java)
    }

    private fun provideChaptersInteractor(): ChaptersInteractor {
        val chapterProvider = RoomWrapper.Chapter.Base(databaseProvider = object : RoomWrapper {
            override fun provideDatabase(): BooksDatabase = coreModule.database
        })

        val chapterDataToDb = ChapterDataToDb.Base()
        val chapterCloudToData = ChapterCloudToData.Base()

        val chapterCacheDataSource = ChaptersCacheDataSource.Base(
            chapterProvider,
            chapterDataToDb
        )

        val chaptersCloudDataSource = ChaptersCloudDataSource.Base(provideChapterService())

        val chapterRepository = ChaptersRepository(
            chaptersCloudDataSource,
            chapterCacheDataSource,
            ChaptersCloudMapper.Base(chapterCloudToData, coreModule.bookCache),
            ChaptersCacheMapper.Base(ChapterDbToDataMapper.Base()),
            coreModule.bookCache
        )

        return ChaptersInteractor.Base(
            chapterRepository,
            BaseChaptersDomainMapper(BaseChapterDomainMapper())
        )
    }
    fun provideChaptersCommunication() = ChaptersCommunication.Base()
    fun provideMapper() = BaseChaptersUiMapper(BaseChapterDomainToUi(), coreModule.resourceProvider)
    override fun viewModel(): ChaptersViewModel {
        return ChaptersViewModel(
            communication = provideChaptersCommunication(),
            interactor = provideChaptersInteractor(),
            mapper = provideMapper(),
            navigator = coreModule.navigator,
            bookCache = coreModule.bookCache
        )
    }
}