package com.levtttech.bibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.bibleapp.domain.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication,
    private val uiCache: UiDataCache,
) : ViewModel() {

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val books = booksInteractor.fetchBooks()
            val booksUi = books.map(mapper)
            val cachedList = booksUi.cache(uiCache)
            withContext(Dispatchers.Main) {
                cachedList.map(communication)
            }
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        val newList = uiCache.getList(id)
        communication.map(newList)
    }

    fun saveCollapsedState() {
        uiCache.saveState()
    }

}
