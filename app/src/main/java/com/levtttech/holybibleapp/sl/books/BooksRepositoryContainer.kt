package com.levtttech.holybibleapp.sl.books

import com.levtttech.holybibleapp.data.books.BaseBooksRepository
import com.levtttech.holybibleapp.data.books.ToBookMapper
import com.levtttech.holybibleapp.data.books.cache.BookDataToDbMapper
import com.levtttech.holybibleapp.data.books.cache.BooksCacheDataSource
import com.levtttech.holybibleapp.data.books.cache.BooksCacheMapper
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.sl.core.CoreModule
import com.levtttech.holybibleapp.sl.core.RepositoryContainer
import com.levtttech.holybibleapp.data.books.cloud.BookRu
import com.levtttech.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.levtttech.holybibleapp.data.books.cloud.BooksCloudMapper
import com.levtttech.holybibleapp.data.books.cloud.BooksService
import com.levtttech.holybibleapp.data.books.cloud.BooksTypeToken

class BooksRepositoryContainer(
    private val coreModule: CoreModule,
    private val useMocks: Boolean,
    private val booksRu: () -> List<BookRu>,
) : RepositoryContainer<BooksRepository> {

    override fun repository(): BooksRepository {
        val toBookMapper = ToBookMapper.Base()
        return BaseBooksRepository(if (useMocks)
            mockBooksCloudDataSource()
        else
            booksCloudDataSource(),
            BooksCacheDataSource.Base(coreModule.realmProvider, BookDataToDbMapper.Base()),
            BooksCloudMapper.Base(toBookMapper),
            BooksCacheMapper.Base(toBookMapper)
        )
    }

    private fun booksCloudDataSource() =
        BooksCloudDataSource.Base(coreModule.language, english(), russian())

    private fun english() =
        BooksCloudDataSource.English(booksService(), coreModule.gson, booksTypeToken())

    private fun mockBooksCloudDataSource() = if (coreModule.language.isChosenRussian())
        russian()
    else
        BooksCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson, booksTypeToken())

    private fun booksTypeToken() = BooksTypeToken()

    private fun russian() = BooksCloudDataSource.Russian(booksRu)

    private fun booksService() = coreModule.makeService(BooksService::class.java)
}