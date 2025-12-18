package com.levtttech.bibleapp.servicelocator.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levtttech.bibleapp.MainViewModel
import com.levtttech.bibleapp.presentation.books.BooksViewModel
import com.levtttech.bibleapp.presentation.chapters.ChaptersViewModel
import com.levtttech.bibleapp.servicelocator.books.BooksModule
import com.levtttech.bibleapp.servicelocator.chapters.ChaptersModule

class ViewModelsFactory(
    private val coreModule: CoreModule,
    private val booksModule: BooksModule,
    private val chaptersModule: ChaptersModule
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val module = when {
            modelClass.isAssignableFrom(BooksViewModel::class.java) -> booksModule
            modelClass.isAssignableFrom(ChaptersViewModel::class.java) -> chaptersModule
            modelClass.isAssignableFrom(MainViewModel::class.java) -> coreModule
            else -> throw IllegalStateException("$modelClass unknown")
        }
        return module.viewModel() as T
    }
}