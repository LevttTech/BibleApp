package com.levtttech.bibleapp.presentation.chapters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.bibleapp.core.Read
import com.levtttech.bibleapp.domain.chapters.ChaptersDomainToUi
import com.levtttech.bibleapp.domain.chapters.ChaptersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChaptersViewModel(
    private val communication: ChaptersCommunication,
    private val interactor: ChaptersInteractor,
    private val mapper: ChaptersDomainToUi<ChaptersUi>,
    private val navigator: ChaptersNavigator,
    private val bookCache: Read<Pair<Int, String>>
) : ViewModel() {

    init {
        Log.d("ViewModel", "chapters viewmodel create hashcode=${hashCode()}")
    }
    fun init() {
        navigator.saveChaptersScreen()
    }
    fun fetchChapters() {
        communication.map(listOf(ChapterUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val chaptersDomain = interactor.fetchChapters()
            val chaptersUi = chaptersDomain.map(mapper)
            withContext(Dispatchers.Main) {
                chaptersUi.map(communication)
            }
        }
    }

    fun observeChapters(lifecycleOwner: LifecycleOwner, observer: Observer<List<ChapterUi>>) {
        communication.observe(lifecycleOwner, observer)
    }

    fun getBookName() = bookCache.read().second

}