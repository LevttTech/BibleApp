package com.levtttech.holybibleapp.data.verses

import com.levtttech.holybibleapp.core.Multiply
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.data.core.AbstractRepository
import com.levtttech.holybibleapp.data.verses.cache.VerseDb
import com.levtttech.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.levtttech.holybibleapp.data.verses.cache.VersesCacheMapper
import com.levtttech.holybibleapp.data.verses.cache.VersesLimits
import com.levtttech.holybibleapp.data.verses.cloud.VerseCloud
import com.levtttech.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.levtttech.holybibleapp.data.verses.cloud.VersesCloudMapper
import com.levtttech.holybibleapp.domain.verses.VersesRepository

class BaseVersesRepository(
    private val cloudDataSource: VersesCloudDataSource,
    private val cacheDataSource: VersesCacheDataSource,
    cloudMapper: VersesCloudMapper,
    cacheMapper: VersesCacheMapper,
    private val chapterIdContainer: Read<Int>,
    private val bookIdContainer: Read<Int>,
    private val multiply: Multiply,
    private val multiplyTwice: Multiply,
) : AbstractRepository<VerseDb, VerseCloud, VerseData, VersesData>(
    cacheDataSource, cloudMapper, cacheMapper
), VersesRepository {
    private val bookId by lazy { bookIdContainer.read() }
    private val chapterId get() = chapterIdContainer.read()
    private val limits get() = VersesLimits(bookId, chapterId, multiplyTwice, multiply)

    override suspend fun fetchCloudData() = cloudDataSource.fetchVerses(bookId, chapterId)
    override fun cachedDataList() = cacheDataSource.fetchVerses(limits)
    override fun returnSuccess(list: List<VerseData>) = VersesData.Success(list)
    override fun returnFail(e: Exception) = VersesData.Fail(e)
    override fun limits() = limits
}