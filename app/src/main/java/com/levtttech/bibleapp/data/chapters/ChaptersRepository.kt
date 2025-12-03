package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.data.chapters.cache.ChaptersCacheDataSource

interface ChaptersRepository {

    fun fetchChapters(id: Int): ChaptersData

    class Base(
        private val cacheDataSource: ChaptersCacheDataSource,
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cloudMapper: ChaptersCloudMapper,
    ) : ChaptersRepository {
        override fun fetchChapters(id: Int): ChaptersData {
            return try {
                val cacheChapters = cacheDataSource.fetchChapters()
                if (cacheChapters.isEmpty()) {
                    val cloudChapters = cloudDataSource.fetchChapters(id)
                    val dataChapters = cloudMapper.map(cloudChapters)
                    cacheDataSource.saveChapters(dataChapters)
                    ChaptersData.Success(dataChapters)
                } else {
                    ChaptersData.Success(cacheChapters)
                }
            } catch (e: Exception) {
                ChaptersData.Fail(e)
            }
        }
    }
}