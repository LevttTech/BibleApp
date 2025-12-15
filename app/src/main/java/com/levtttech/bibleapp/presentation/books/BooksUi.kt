package com.levtttech.bibleapp.presentation.books

import com.levtttech.bibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {
    abstract fun cache(uiDataCache: UiDataCache): BooksUi

    data class Base(
        private val books: List<BookUi>,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)
        override fun cache(uiDataCache: UiDataCache): BooksUi = uiDataCache.cache(books)
    }
}