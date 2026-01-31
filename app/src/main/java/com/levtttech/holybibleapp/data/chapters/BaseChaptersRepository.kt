package com.levtttech.holybibleapp.data.chapters

import com.levtttech.holybibleapp.core.Multiply
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.data.chapters.cache.ChapterDb
import com.levtttech.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.levtttech.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.levtttech.holybibleapp.data.chapters.cloud.ChapterCloud
import com.levtttech.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.levtttech.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.levtttech.holybibleapp.data.core.AbstractRepository
import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository

class BaseChaptersRepository(
    private val cloudDataSource: ChaptersCloudDataSource,
    private val cacheDataSource: ChaptersCacheDataSource,
    cloudMapper: ChaptersCloudMapper,
    cacheMapper: ChaptersCacheMapper,
    private val bookIdContainer: Read<Int>,
    private val multiply: Multiply,
) : AbstractRepository<ChapterDb, ChapterCloud, ChapterData, ChaptersData>(
    cacheDataSource,
    cloudMapper,
    cacheMapper
), ChaptersRepository {

    private val bookId by lazy { bookIdContainer.read() }
    private val chapterId by lazy { ChapterId.Base(bookId, multiply = multiply) }

    override suspend fun fetchCloudData() = cloudDataSource.fetchChapters(bookId)
    override fun cachedDataList() = cacheDataSource.fetchChapters(chapterId)
    override fun returnSuccess(list: List<ChapterData>) = ChaptersData.Success(list)
    override fun returnFail(e: Exception) = ChaptersData.Fail(e)
    override fun limits() = chapterId
}