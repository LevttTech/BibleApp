package com.levtttech.holybibleapp.sl.chapters

import com.levtttech.holybibleapp.data.books.cloud.BookRu
import com.levtttech.holybibleapp.data.chapters.BaseChaptersRepository
import com.levtttech.holybibleapp.data.chapters.ToChapterMapper
import com.levtttech.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.levtttech.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.levtttech.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.levtttech.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.levtttech.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.levtttech.holybibleapp.data.chapters.cloud.ChaptersService
import com.levtttech.holybibleapp.data.chapters.cloud.ChaptersTypeToken
import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository
import com.levtttech.holybibleapp.presentation.books.BookCache
import com.levtttech.holybibleapp.sl.core.CoreModule
import com.levtttech.holybibleapp.sl.core.RepositoryContainer

class ChaptersRepositoryContainer(
    private val coreModule: CoreModule,
    private val useMocks: Boolean,
    private val bookCache: BookCache,
    private val booksRu: () -> List<BookRu>,
) : RepositoryContainer<ChaptersRepository> {

    override fun repository() = BaseChaptersRepository(
        cloudDataSource(),
        cacheDataSource(),
        cloudMapper(),
        cacheMapper(),
        bookCache,
        coreModule.multiply
    )

    private fun cacheMapper() =
        ChaptersCacheMapper.Base(ToChapterMapper.Db(bookCache, coreModule.multiply))

    private fun cacheDataSource() =
        ChaptersCacheDataSource.Base(coreModule.realmProvider, ChapterDataToDbMapper.Base())

    private fun cloudDataSource() = if (useMocks) mockCloudDataSource()
    else ChaptersCloudDataSource.Base(coreModule.language, english(), russian())

    private fun mockCloudDataSource() = if (coreModule.language.isChosenRussian())
        russian()
    else
        ChaptersCloudDataSource.Mock(
            coreModule.resourceProvider, coreModule.gson, chaptersTypeToken()
        )

    private fun russian() = ChaptersCloudDataSource.Russian(booksRu)

    private fun english() = ChaptersCloudDataSource.English(
        coreModule.makeService(ChaptersService::class.java), coreModule.gson, chaptersTypeToken()
    )

    private fun chaptersTypeToken() = ChaptersTypeToken()

    private fun cloudMapper() =
        ChaptersCloudMapper.Base(ToChapterMapper.Cloud(bookCache, coreModule.multiply),
            coreModule.multiply)
}