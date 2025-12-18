package com.levtttech.bibleapp.servicelocator.core

interface BaseModule<T> {
    fun viewModel(): T
}