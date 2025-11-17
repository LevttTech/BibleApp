package com.levtttech.bibleapp.presentation

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.domain.BooksDomain
import com.levtttech.bibleapp.domain.BooksDomainToUiMapper
import com.levtttech.bibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainViewModel(private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication) : ViewModel() {
        private val mainHandler = Handler(Looper.getMainLooper())
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
    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val books = booksInteractor.fetchBooks()
        withContext(Dispatchers.Main) {
            val booksUi = books.map(mapper)
            booksUi.map(Abstract.Mapper.Empty())
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<Book>>) {
        communication.observeSuccess(owner, observer)
    }
}