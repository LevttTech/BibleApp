package com.levtttech.bibleapp.servicelocator.core

import android.content.Context
import androidx.room.Room
import com.levtttech.bibleapp.MainViewModel
import com.levtttech.bibleapp.data.books.cache.BooksDatabase
import com.levtttech.bibleapp.presentation.books.BookCache
import com.levtttech.bibleapp.presentation.books.ResourceProvider
import com.levtttech.bibleapp.presentation.books.UiDataCache
import com.levtttech.bibleapp.presentation.chapters.NavigationCommunication
import com.levtttech.bibleapp.presentation.core.Navigator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoreModule(
    private val context: Context,
) : BaseModule<MainViewModel> {
    lateinit var database: BooksDatabase
    lateinit var bookCache: BookCache
    lateinit var retrofit: Retrofit
    lateinit var resourceProvider: ResourceProvider
    lateinit var navigator: Navigator
    lateinit var uiDataCache: UiDataCache
    lateinit var navigationCommunication: NavigationCommunication
    fun init() {
        database = Room.databaseBuilder(
            context, BooksDatabase::class.java, "books-database"
        ).fallbackToDestructiveMigration().build()
        bookCache = BookCache.Base(context)
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        resourceProvider = ResourceProvider.Base(context)
        navigator = Navigator.Base(context)
        uiDataCache = UiDataCache.Base(UiDataCache.CacheId.Base(context))
        navigationCommunication = NavigationCommunication.Base()
    }

    override fun viewModel() = MainViewModel(navigator, navigationCommunication)

    private companion object {
        private const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}