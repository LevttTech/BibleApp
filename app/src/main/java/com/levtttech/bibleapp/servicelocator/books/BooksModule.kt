package com.levtttech.bibleapp.servicelocator.books

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
import com.levtttech.bibleapp.data.books.net.BookService
import com.levtttech.bibleapp.domain.books.BaseBookDomainMapper
import com.levtttech.bibleapp.domain.books.BaseBooksDataToDomainMapper
import com.levtttech.bibleapp.domain.books.BooksInteractor
import com.levtttech.bibleapp.domain.books.TestamentTypeMapper
import com.levtttech.bibleapp.presentation.books.BaseBookDomainToUiMapper
import com.levtttech.bibleapp.presentation.books.BaseBooksDomainToUiMapper
import com.levtttech.bibleapp.presentation.books.BooksCommunication
import com.levtttech.bibleapp.presentation.books.BooksViewModel
import com.levtttech.bibleapp.servicelocator.core.BaseModule
import com.levtttech.bibleapp.servicelocator.core.CoreModule

class BooksModule(
    private val useMocks: Boolean,
    private val coreModule: CoreModule,
) : BaseModule<BooksViewModel> {

    override fun viewModel(): BooksViewModel {
        return BooksViewModel(
            provideBooksInteractor(),
            mapper = BaseBooksDomainToUiMapper(
                coreModule.resourceProvider,
                BaseBookDomainToUiMapper(coreModule.resourceProvider),
            ),
            provideBooksCommunication(),
            coreModule.uiDataCache,
            coreModule.bookCache,
            coreModule.navigationCommunication,
            coreModule.navigator
        )
    }

    private fun provideBooksService(): BookService {
        return coreModule.retrofit.create(BookService::class.java)
    }

    private fun provideBooksCommunication() = BooksCommunication.Base()
    private fun provideCacheDataSource() = BooksCacheDataSource.Base(
        RoomWrapper.Book.Base(databaseProvider = object : RoomWrapper {
            override fun provideDatabase(): BooksDatabase = coreModule.database
        }), ToDbMapper.Base()
    )

    private fun provideCloudDataSource() = if (useMocks) {
        BooksCloudDataSource.Mock(coreModule.resourceProvider, Gson())
    } else {
        BooksCloudDataSource.Base(provideBooksService())
    }

    private fun provideBooksInteractor(): BooksInteractor {
        val repository = BooksRepository(
            provideCloudDataSource(),
            provideCacheDataSource(),
            BooksCacheMapper.Base(ToBookDataMapper.Base()),
            BooksCloudMapper.Base(ToBookDataMapper.Base())
        )

        return BooksInteractor.Base(
            repository, BaseBooksDataToDomainMapper(
                BaseBookDomainMapper(), TestamentTypeMapper.Base()
            )
        )
    }

}