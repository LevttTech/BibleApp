package com.levtttech.bibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.domain.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication) : ViewModel() {

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val books: BooksUi = booksInteractor.fetchBooks().map(mapper)
        withContext(Dispatchers.Main) {
            books.map(Abstract.Mapper.Empty())
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<Book>>) {
        communication.observeSuccess(owner, observer)
    }
}