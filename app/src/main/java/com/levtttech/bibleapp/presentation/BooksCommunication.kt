package com.levtttech.bibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.levtttech.bibleapp.core.Book

interface BooksCommunication {
    fun show(books: List<Book>)
    fun show(message: String)

    fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Book>>)
    fun observeFail(owner: LifecycleOwner, observer: Observer<String>)

    class Base : BooksCommunication {
        private val successLiveData = MutableLiveData<List<Book>>()
        private val failLiveData = MutableLiveData<String>()
        override fun show(books: List<Book>) {
            successLiveData.value = books
        }

        override fun show(message: String) {
            failLiveData.value = message
        }

        override fun observeSuccess(
            owner: LifecycleOwner,
            observer: Observer<List<Book>>,
        ) {
            successLiveData.observe(owner,observer)
        }

        override fun observeFail(
            owner: LifecycleOwner,
            observer: Observer<String>,
        ) {
            observeFail(owner, observer)
        }
    }
}