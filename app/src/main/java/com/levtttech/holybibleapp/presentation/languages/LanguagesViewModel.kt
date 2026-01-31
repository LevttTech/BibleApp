package com.levtttech.holybibleapp.presentation.languages

import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.ChooseLanguages
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.presentation.main.BackNavigationUi
import com.levtttech.holybibleapp.presentation.main.BaseViewModel
import com.levtttech.holybibleapp.presentation.main.NavigationCommunication
import com.levtttech.holybibleapp.sl.core.ClearRussianBooks
import com.levtttech.holybibleapp.sl.core.Feature


class LanguagesViewModel(
    communication: LanguagesCommunication,
    private val language: Language,
    private val navigationCommunication: NavigationCommunication,
    private val navigator: LanguagesNavigator,
    private val resourceProvider: ResourceProvider,
    private val clearRussianBooks: ClearRussianBooks,
) : BaseViewModel<LanguagesCommunication, LanguagesUi>(resourceProvider, communication),
    ChooseLanguages, BackNavigationUi {

    override fun titleResId() = R.string.choose_language

    override fun chooseEnglish() {
        clearRussianBooks.clearBooksRu()
        language.chooseEnglish()
        navigate()
    }

    override fun chooseRussian() {
        language.chooseRussian()
        navigate()
    }

    override fun fetch() {
        val choice = when {
            language.isChosenRussian() -> LanguageChoice.Russian()
            language.isChosenEnglish() -> LanguageChoice.English()
            else -> LanguageChoice.None()
        }
        communication.map(
            LanguagesUi.Base(
                choice,
                resourceProvider.string(R.string.english),
                resourceProvider.string(R.string.russian)
            )
        )
    }

    override fun showBack(): Boolean = with(navigator) {
        save(Feature.LANGUAGES)
        return canGoBack()
    }

    private fun navigate() = navigationCommunication.map(navigator.nextFromLanguages())
}