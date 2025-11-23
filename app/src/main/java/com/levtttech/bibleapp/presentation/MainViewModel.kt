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
) : ViewModel() {
    //    fun fetchBooks() {
//        val handlerThread = HandlerThread("BooksFetcherThread")
//        handlerThread.start()
//        val backgroundHandler = Handler(handlerThread.looper)
//
//        backgroundHandler.post {
//            val books: BooksDomain = BooksDomain.Success(listOf(Book(1,"HELLO")))
//            mainHandler.post {
//                val booksUi = books.map(mapper)
//                Log.d("ViewModel", "IN mainHandler=${Thread.currentThread().name}")
//                booksUi.map(Abstract.Mapper.Empty())
//            }
//
//            handlerThread.quitSafely()
//        }
//    }
    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val books = booksInteractor.fetchBooks()
            val booksUi = books.map(mapper)
            withContext(Dispatchers.Main) {
                booksUi.map(communication)
            }
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }
}
