package com.levtttech.holybibleapp.sl.languages

import com.levtttech.holybibleapp.presentation.languages.Language
import com.levtttech.holybibleapp.presentation.languages.LanguagesCommunication
import com.levtttech.holybibleapp.presentation.languages.LanguagesViewModel
import com.levtttech.holybibleapp.sl.core.BaseModule
import com.levtttech.holybibleapp.sl.core.ClearRussianBooks
import com.levtttech.holybibleapp.sl.core.CoreModule

class LanguagesModule(
    private val coreModule: CoreModule,
    private val clearRussianBooks: ClearRussianBooks
) : BaseModule<LanguagesViewModel> {

    override fun viewModel() = LanguagesViewModel(
        LanguagesCommunication.Base(),
        Language.Change(coreModule.language, coreModule.realmProvider, coreModule.resourceProvider),
        coreModule.navigationCommunication,
        coreModule.navigator,
        coreModule.resourceProvider,
        clearRussianBooks
    )
}