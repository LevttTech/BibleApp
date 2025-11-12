package com.levtttech.bibleapp.core

import android.app.Application
import com.levtttech.bibleapp.data.BooksRepository
import com.levtttech.bibleapp.domain.BaseBookDataToDomainMapper
import com.levtttech.bibleapp.domain.BooksInteractor

class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val booksRepository: BooksRepository = TODO("merge")

        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBookDataToDomainMapper())
    }
}