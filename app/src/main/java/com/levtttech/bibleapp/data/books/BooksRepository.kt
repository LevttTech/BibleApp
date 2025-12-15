package com.levtttech.bibleapp.data.books

import com.levtttech.bibleapp.data.books.cache.BookDb
import com.levtttech.bibleapp.data.books.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.books.cache.BooksCacheMapper
import com.levtttech.bibleapp.data.books.net.BookCloud
import com.levtttech.bibleapp.data.core.BaseRepository

class BooksRepository(
    private val cloudDataSource: BooksCloudDataSource,
    private val cacheDataSource: BooksCacheDataSource,
    cacheMapper: BooksCacheMapper,
    cloudMapper: BooksCloudMapper,
) : BaseRepository<BookDb, BookCloud, BookData, BooksData>(
    cloudMapper, cacheMapper
) {
    override suspend fun cachedList(): List<BookDb> = cacheDataSource.fetch()

    override suspend fun cloudList(): List<BookCloud> = cloudDataSource.fetch()

    override suspend fun save(data: List<BookData>) = cacheDataSource.save(data)

    override fun returnSuccess(data: List<BookData>): BooksData = BooksData.Success(data)

    override fun returnFail(e: Exception): BooksData = BooksData.Fail(e)
}
