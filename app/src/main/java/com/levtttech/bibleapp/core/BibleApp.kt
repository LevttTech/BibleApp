package com.levtttech.bibleapp.core

import android.app.Application
import com.levtttech.bibleapp.presentation.BaseBooksDomainToUiMapper
import com.levtttech.bibleapp.presentation.BooksCommunication
import com.levtttech.bibleapp.presentation.MainViewModel
import com.levtttech.bibleapp.presentation.ResourceProvider

class BibleApp : Application() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val booksInteractor = TODO()
        mainViewModel = MainViewModel(
            booksInteractor, BaseBooksDomainToUiMapper(
                BooksCommunication.Base(), ResourceProvider.Base(this)
            ), BooksCommunication.Base()
        )
    }
}