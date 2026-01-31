package com.levtttech.holybibleapp.presentation.deeplink

import com.levtttech.holybibleapp.core.Multiply
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.levtttech.holybibleapp.domain.verses.VersesInteractor
import com.levtttech.holybibleapp.presentation.verses.VersesCommunication
import com.levtttech.holybibleapp.presentation.verses.VersesNavigator
import com.levtttech.holybibleapp.presentation.verses.VersesUi
import com.levtttech.holybibleapp.presentation.verses.VersesViewModel
import com.levtttech.holybibleapp.sl.core.Feature

class DeeplinkVersesViewModel(
    navigator: VersesNavigator,
    interactor: VersesInteractor,
    communication: VersesCommunication,
    mapper: VersesDomainToUiMapper<VersesUi>,
    deeplinkData: DeeplinkData,
    resourceProvider: ResourceProvider,
    multiply: Multiply,
) : VersesViewModel(
    navigator, interactor, communication, mapper, deeplinkData, resourceProvider, multiply) {
    override val feature = Feature.DEEPLINK_VERSES
    override fun init() = fetch()
}