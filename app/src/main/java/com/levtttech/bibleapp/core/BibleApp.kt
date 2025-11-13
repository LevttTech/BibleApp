package com.levtttech.bibleapp.core

import android.app.Application
import com.levtttech.bibleapp.presentation.BaseBooksDomainToUiMapper
import com.levtttech.bibleapp.presentation.BooksCommunication
import com.levtttech.bibleapp.presentation.MainViewModel
import com.levtttech.bibleapp.presentation.ResourceProvider
import androidx.room.Room
import com.levtttech.bibleapp.data.BooksRepository
import com.levtttech.bibleapp.data.BooksCloudDataSource
import com.levtttech.bibleapp.data.BooksCloudMapper
import com.levtttech.bibleapp.data.cache.BookDbMapper
import com.levtttech.bibleapp.data.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.cache.BooksCacheMapper
import com.levtttech.bibleapp.data.cache.BooksDatabase
import com.levtttech.bibleapp.data.cache.RoomProvider
import com.levtttech.bibleapp.data.net.BookCloudMapper
import com.levtttech.bibleapp.data.net.BookService
import retrofit2.Retrofit
import com.levtttech.bibleapp.domain.BaseBookDataToDomainMapper
import com.levtttech.bibleapp.domain.BooksInteractor

class BibleApp : Application() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).build()

        val service = retrofit.create(BookService::class.java)

        val database = Room.databaseBuilder(
            applicationContext, BooksDatabase::class.java, "books-database"
        ).build()
        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RoomProvider.Base(database))
        val repository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            BooksCloudMapper.Base(BookCloudMapper.Base()),
            BooksCacheMapper.Base(BookDbMapper.Base())
        )
        val booksInteractor = BooksInteractor.Base(repository, BaseBookDataToDomainMapper())
        mainViewModel = MainViewModel(
            booksInteractor, BaseBooksDomainToUiMapper(
                BooksCommunication.Base(), ResourceProvider.Base(this)
            ), BooksCommunication.Base()
        )
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}