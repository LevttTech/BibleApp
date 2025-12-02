package com.levtttech.bibleapp.core

import android.app.Application
import androidx.room.Room
import com.levtttech.bibleapp.data.BooksCloudDataSource
import com.levtttech.bibleapp.data.BooksCloudMapper
import com.levtttech.bibleapp.data.BooksRepository
import com.levtttech.bibleapp.data.ToBookDataMapper
import com.levtttech.bibleapp.data.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.cache.BooksCacheMapper
import com.levtttech.bibleapp.data.cache.BooksDatabase
import com.levtttech.bibleapp.data.cache.RoomProvider
import com.levtttech.bibleapp.data.cache.ToDbMapper
import com.levtttech.bibleapp.data.net.BookService
import com.levtttech.bibleapp.domain.BaseBooksDataToDomainMapper
import com.levtttech.bibleapp.domain.BaseBookDomainMapper
import com.levtttech.bibleapp.domain.BooksInteractor
import com.levtttech.bibleapp.domain.TestamentTypeMapper
import com.levtttech.bibleapp.presentation.BaseBookDomainToUiMapper
import com.levtttech.bibleapp.presentation.BaseBooksDomainToUiMapper
import com.levtttech.bibleapp.presentation.BooksCommunication
import com.levtttech.bibleapp.presentation.MainViewModel
import com.levtttech.bibleapp.presentation.ResourceProvider
import com.levtttech.bibleapp.presentation.UiDataCache
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BibleApp : Application() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(BookService::class.java)

        val database = Room.databaseBuilder(
            applicationContext, BooksDatabase::class.java, "books-database"
        ).fallbackToDestructiveMigration().build()
        val roomProvider = RoomProvider.Base(database)
        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(roomProvider, ToDbMapper.Base())
        val repository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            BooksCloudMapper.Base(ToBookDataMapper.Base()),
            BooksCacheMapper.Base(ToBookDataMapper.Base())
        )
        val resourceProvider = ResourceProvider.Base(this)
        val booksInteractor = BooksInteractor.Base(
            repository, BaseBooksDataToDomainMapper(
                BaseBookDomainMapper(), TestamentTypeMapper.Base()
            )
        )
        val communication = BooksCommunication.Base()
        mainViewModel = MainViewModel(
            booksInteractor, BaseBooksDomainToUiMapper(
                resourceProvider,
                BaseBookDomainToUiMapper(resourceProvider),
            ), communication, UiDataCache.Base(cacheId = UiDataCache.CacheId.Base(applicationContext))
        )
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}