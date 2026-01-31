package com.levtttech.holybibleapp.data.books

import com.levtttech.holybibleapp.data.books.cache.BookDb
import com.levtttech.holybibleapp.data.books.cache.BooksCacheDataSource
import com.levtttech.holybibleapp.data.books.cache.BooksCacheMapper
import com.levtttech.holybibleapp.data.books.cloud.BookCloud
import com.levtttech.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.levtttech.holybibleapp.data.books.cloud.BooksCloudMapper
import com.levtttech.holybibleapp.data.core.AbstractRepository
import com.levtttech.holybibleapp.data.core.Limits
import com.levtttech.holybibleapp.domain.books.BooksRepository

class BaseBooksRepository(
    private val cloudDataSource: BooksCloudDataSource,
    private val cacheDataSource: BooksCacheDataSource,
    booksCloudMapper: BooksCloudMapper,
    booksCacheMapper: BooksCacheMapper
) : AbstractRepository<BookDb, BookCloud, BookData, BooksData>(
    cacheDataSource,
    booksCloudMapper,
    booksCacheMapper
), BooksRepository {

    override suspend fun fetchCloudData() = cloudDataSource.fetchBooks()
    override fun cachedDataList() = cacheDataSource.read()
    override fun returnSuccess(list: List<BookData>) = BooksData.Success(list)
    override fun returnFail(e: Exception) = BooksData.Fail(e)
    override fun limits(): Limits = BooksLimits()
}