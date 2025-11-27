package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication>() {
    data class Base(
        private val books: List<BookUi>,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)
    }
}