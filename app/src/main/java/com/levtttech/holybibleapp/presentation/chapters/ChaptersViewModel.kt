package com.levtttech.holybibleapp.presentation.chapters

import androidx.lifecycle.viewModelScope
import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.core.Save
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.levtttech.holybibleapp.domain.chapters.ChaptersInteractor
import com.levtttech.holybibleapp.presentation.books.BookUi
import com.levtttech.holybibleapp.presentation.books.BookUiMapper
import com.levtttech.holybibleapp.presentation.core.FeatureNavigation
import com.levtttech.holybibleapp.presentation.main.BaseViewModel
import com.levtttech.holybibleapp.sl.core.Feature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChaptersViewModel(
    private val interactor: ChaptersInteractor,
    communication: ChaptersCommunication,
    private val chaptersMapper: ChaptersDomainToUiMapper<ChaptersUi>,
    private val navigation: FeatureNavigation,
    private val chapterCache: Save<Int>,
    resourceProvider: ResourceProvider,
    private val clearChapters: () -> Unit,
) : BaseViewModel<ChaptersCommunication, ChaptersUi>(resourceProvider, communication),
    Show<Pair<Int, Int>>, ChangeFavorite<Pair<Int, Int>> {

    private var clear = true
    private val bookNameProgress = object : BookUi {
        override fun <T> map(mapper: BookUiMapper<T>) =
            mapper.map(0, title(), false)
    }

    override fun fetch() {
        communication.map(ChaptersUi.Base(mutableListOf(ChapterUi.Progress), bookNameProgress))
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = interactor.fetchChapters()
            val chaptersUi = chapters.map(chaptersMapper)
            withContext(Dispatchers.Main) { communication.map(chaptersUi) }
        }
    }

    fun init() {
        navigation.init()
        fetch()
    }

    override fun open(id: Pair<Int, Int>): Unit = id.let { (visibleId, _) ->
        chapterCache.save(visibleId)
        clear = false
        navigation.showNextScreen()
    }

    override fun scrollPosition() = interactor.scrollPosition(Feature.CHAPTERS)
    override fun saveScrollPosition(position: Int) =
        interactor.saveScrollPosition(Feature.CHAPTERS, position)

    override fun onCleared() {
        super.onCleared()
        if (clear) clearChapters()
    }

    override fun changeFavorite(id: Pair<Int, Int>): Unit = id.let { (_, generatedId) ->
        super.changeFavorite(generatedId, communication, interactor)
    }
}