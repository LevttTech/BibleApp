package com.levtttech.holybibleapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levtttech.holybibleapp.presentation.books.BooksViewModel
import com.levtttech.holybibleapp.presentation.chapters.ChaptersViewModel
import com.levtttech.holybibleapp.presentation.deeplink.DeeplinkVersesViewModel
import com.levtttech.holybibleapp.presentation.deeplink.DeeplinkViewModel
import com.levtttech.holybibleapp.presentation.languages.LanguagesViewModel
import com.levtttech.holybibleapp.presentation.main.MainViewModel
import com.levtttech.holybibleapp.presentation.verses.VersesViewModel

class ViewModelsFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    private val map = HashMap<Class<*>, Feature>().apply {
        put(MainViewModel::class.java, Feature.MAIN)
        put(BooksViewModel::class.java, Feature.BOOKS)
        put(ChaptersViewModel::class.java, Feature.CHAPTERS)
        put(VersesViewModel.Base::class.java, Feature.VERSES)
        put(LanguagesViewModel::class.java, Feature.LANGUAGES)
        put(DeeplinkViewModel::class.java, Feature.DEEPLINK)
        put(DeeplinkVersesViewModel::class.java, Feature.DEEPLINK_VERSES)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val feature =
            map[modelClass] ?: throw IllegalStateException("unknown viewModel $modelClass")
        return dependencyContainer.module(feature).viewModel() as T
    }
}