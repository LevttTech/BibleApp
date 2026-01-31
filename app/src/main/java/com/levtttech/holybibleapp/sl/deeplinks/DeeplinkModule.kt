package com.levtttech.holybibleapp.sl.deeplinks

import com.levtttech.holybibleapp.data.verse.DeeplinkIds
import com.levtttech.holybibleapp.data.verse.VerseIdCache
import com.levtttech.holybibleapp.presentation.books.BookCache
import com.levtttech.holybibleapp.presentation.chapters.ChapterCache
import com.levtttech.holybibleapp.presentation.deeplink.DeeplinkViewModel
import com.levtttech.holybibleapp.presentation.main.NavigationCommunication
import com.levtttech.holybibleapp.sl.core.BaseModule
import com.levtttech.holybibleapp.sl.core.CoreModule

class DeeplinkModule(private val coreModule: CoreModule) : BaseModule<DeeplinkViewModel> {

    override fun viewModel() = coreModule.resourceProvider.let { resourceProvider ->
        DeeplinkViewModel(
            resourceProvider,
            DeeplinkIds(
                BookCache.Deeplink(resourceProvider),
                ChapterCache.Deeplink(resourceProvider),
                VerseIdCache.Deeplink(resourceProvider),
                coreModule.deeplinkData
            ),
            NavigationCommunication.Base()
        )
    }
}