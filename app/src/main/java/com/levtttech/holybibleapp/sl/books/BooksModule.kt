package com.levtttech.holybibleapp.sl.books

import com.levtttech.holybibleapp.data.books.TestamentTemp
import com.levtttech.holybibleapp.presentation.core.FeatureNavigation
import com.levtttech.holybibleapp.sl.core.BaseModule
import com.levtttech.holybibleapp.sl.core.CoreModule
import com.levtttech.holybibleapp.sl.core.Feature
import com.levtttech.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BaseBooksDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BookDataMapper
import com.levtttech.holybibleapp.domain.books.BooksInteractor
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.presentation.books.BaseBookDomainToUiMapper
import com.levtttech.holybibleapp.presentation.books.BaseBooksDomainToUiMapper
import com.levtttech.holybibleapp.presentation.books.BooksCommunication
import com.levtttech.holybibleapp.presentation.books.BooksViewModel
import com.levtttech.holybibleapp.presentation.books.CollapsedIdsCache
import com.levtttech.holybibleapp.presentation.books.UiDataCache


class BooksModule(
    private val coreModule: CoreModule,
    private val useMocks: Boolean,
    private val repository: BooksRepository,
    private val clear: () -> Unit,
) : BaseModule<BooksViewModel> {

    override fun viewModel(): BooksViewModel {
        val uiDataCache = booksUiDataCache()
        return BooksViewModel(
            interactor(),
            mapper(uiDataCache),
            communication(),
            uiDataCache,
            coreModule.bookCache,
            FeatureNavigation.Base(
                coreModule.navigator, coreModule.navigationCommunication, Feature.BOOKS
            ),
            coreModule.resourceProvider,
            clear
        )
    }

    private fun interactor(): BooksInteractor.Base {
        val temp = TestamentTemp.Base()
        return BooksInteractor.Base(
            repository,
            BaseBooksDataToDomainMapper(
                BaseBookDataToDomainMapper(),
                temp,
                BookDataMapper.CompareTestament(temp),
                BookDataMapper.SaveTestament(temp)
            ),
            coreModule.scrollPositionCache
        )
    }

    private fun mapper(uiDataCache: UiDataCache) = BaseBooksDomainToUiMapper(
        coreModule.resourceProvider,
        BaseBookDomainToUiMapper(coreModule.resourceProvider),
        uiDataCache
    )

    private fun booksUiDataCache() = UiDataCache.Base(if (useMocks)
        CollapsedIdsCache.Mock(coreModule.resourceProvider)
    else
        CollapsedIdsCache.Base(coreModule.resourceProvider)
    )

    private fun communication() = BooksCommunication.Base()
}