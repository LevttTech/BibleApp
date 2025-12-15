package com.levtttech.bibleapp.data.chapters

import android.util.Log
import com.levtttech.bibleapp.core.Read
import com.levtttech.bibleapp.data.chapters.cache.ChapterDb
import com.levtttech.bibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.levtttech.bibleapp.data.chapters.cache.ChaptersCacheMapper
import com.levtttech.bibleapp.data.chapters.net.ChapterCloud
import com.levtttech.bibleapp.data.core.BaseRepository
class ChaptersRepository(
    private val cloudDataSource: ChaptersCloudDataSource,
    private val cacheDataSource: ChaptersCacheDataSource,
    cloudMapper: ChaptersCloudMapper,
    cacheMapper: ChaptersCacheMapper,
    private val idContainer: Read<Pair<Int,String>>
) : BaseRepository<ChapterDb, ChapterCloud, ChapterData, ChaptersData>(
    cloudMapper, cacheMapper
) {


    override suspend fun cachedList(): List<ChapterDb> = cacheDataSource.fetch(idContainer.read().first)

    override suspend fun cloudList(): List<ChapterCloud> = cloudDataSource.fetchChapters(idContainer.read().first)

    override suspend fun save(data: List<ChapterData>) = cacheDataSource.save(data)

    override fun returnSuccess(data: List<ChapterData>) = ChaptersData.Success(data)

    override fun returnFail(e: Exception) = ChaptersData.Fail(e)
}