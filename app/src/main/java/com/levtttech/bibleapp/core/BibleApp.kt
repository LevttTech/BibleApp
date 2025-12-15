package com.levtttech.bibleapp.core

import android.app.Application
import androidx.room.Room
import com.levtttech.bibleapp.MainViewModel
import com.levtttech.bibleapp.data.books.BooksCloudDataSource
import com.levtttech.bibleapp.data.books.BooksCloudMapper
import com.levtttech.bibleapp.data.books.BooksRepository
import com.levtttech.bibleapp.data.books.RoomWrapper
import com.levtttech.bibleapp.data.books.ToBookDataMapper
import com.levtttech.bibleapp.data.books.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.books.cache.BooksCacheMapper
import com.levtttech.bibleapp.data.books.cache.BooksDatabase
import com.levtttech.bibleapp.data.books.cache.ToDbMapper
import com.levtttech.bibleapp.data.books.net.BookService
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
import com.levtttech.bibleapp.presentation.books.BaseBookDomainToUiMapper
import com.levtttech.bibleapp.presentation.books.BaseBooksDomainToUiMapper
import com.levtttech.bibleapp.presentation.books.BookCache
import com.levtttech.bibleapp.presentation.books.BooksCommunication
import com.levtttech.bibleapp.presentation.books.BooksViewModel
import com.levtttech.bibleapp.presentation.books.ResourceProvider
import com.levtttech.bibleapp.presentation.books.UiDataCache
import com.levtttech.bibleapp.presentation.chapters.BaseChapterDomainToUi
import com.levtttech.bibleapp.presentation.chapters.BaseChaptersUiMapper
import com.levtttech.bibleapp.presentation.chapters.ChaptersCommunication
import com.levtttech.bibleapp.presentation.chapters.ChaptersViewModel
import com.levtttech.bibleapp.presentation.chapters.NavigationCommunication
import com.levtttech.bibleapp.presentation.core.Navigator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BibleApp : Application() {
    lateinit var booksViewModel: BooksViewModel
    lateinit var chaptersViewModel: ChaptersViewModel
    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(BookService::class.java)
        val chapterService = retrofit.create(ChapterService::class.java)
        val database = Room.databaseBuilder(
            applicationContext, BooksDatabase::class.java, "books-database"
        ).fallbackToDestructiveMigration().build()
        val roomProvider = RoomWrapper.Book.Base(databaseProvider = object : RoomWrapper {
            override fun provideDatabase(): BooksDatabase = database
        })
        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(roomProvider, ToDbMapper.Base())
        val repository = BooksRepository(
            cloudDataSource,
            cacheDataSource,
            BooksCacheMapper.Base(ToBookDataMapper.Base()),
            BooksCloudMapper.Base(ToBookDataMapper.Base())
        )
        val resourceProvider = ResourceProvider.Base(this)
        val booksInteractor = BooksInteractor.Base(
            repository, BaseBooksDataToDomainMapper(
                BaseBookDomainMapper(), TestamentTypeMapper.Base()
            )
        )
        val chapterProvider = RoomWrapper.Chapter.Base(databaseProvider = object : RoomWrapper {
            override fun provideDatabase(): BooksDatabase = database
        })
        val chapterDataToDb = ChapterDataToDb.Base()
        val chapterCloudToData = ChapterCloudToData.Base()
        val chaptersCloudDataSource = ChaptersCloudDataSource.Base(chapterService)
        val bookCache = BookCache.Base(applicationContext)
        val chapterCacheDataSource = ChaptersCacheDataSource.Base(
            chapterProvider, chapterDataToDb
        )
        val chapterRepository = ChaptersRepository(
            chaptersCloudDataSource,
            chapterCacheDataSource,
            ChaptersCloudMapper.Base(chapterCloudToData,bookCache),
            ChaptersCacheMapper.Base(ChapterDbToDataMapper.Base()),
            bookCache
        )
        val chapterInteractor = ChaptersInteractor.Base(
            chapterRepository, BaseChaptersDomainMapper(BaseChapterDomainMapper())
        )
        val navigationCommunication = NavigationCommunication.Base()
        val communication = BooksCommunication.Base()
        val navigator = Navigator.Base(applicationContext)
        booksViewModel = BooksViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(
                resourceProvider,
                BaseBookDomainToUiMapper(resourceProvider),
            ),
            communication,
            UiDataCache.Base(cacheId = UiDataCache.CacheId.Base(applicationContext)),
            bookCache,
            navigationCommunication,
            navigator
        )
        chaptersViewModel = ChaptersViewModel(
            communication = ChaptersCommunication.Base(),
            interactor = chapterInteractor,
            mapper = BaseChaptersUiMapper(BaseChapterDomainToUi(), resourceProvider),
            navigator,
            bookCache
        )
        mainViewModel = MainViewModel(
            navigator = navigator, navigationCommunication = navigationCommunication
        )
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}