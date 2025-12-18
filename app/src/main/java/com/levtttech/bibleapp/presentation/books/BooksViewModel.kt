package com.levtttech.bibleapp.presentation.books

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.bibleapp.core.Save
import com.levtttech.bibleapp.domain.books.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.books.BooksInteractor
import com.levtttech.bibleapp.presentation.chapters.NavigationCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper<BooksUi>,
    private val communication: BooksCommunication,
    private val uiCache: UiDataCache,
    private val bookCache: Save<Pair<Int,String>>,
    private val navigationCommunication: NavigationCommunication,
    private val navigator: BooksNavigator,
) : ViewModel() {
    init {
        Log.d("ViewModel","create booksViewModel hashcode=${hashCode()}")
    }
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

    fun show(id: Int, name: String) {
        bookCache.save(Pair(id,name))
        navigator.nextScreen(navigationCommunication)
    }

    fun init() {
        navigator.saveBooksScreen()
        fetchBooks()
    }
}
