package com.levtttech.holybibleapp.data.books

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.domain.books.BooksDataMapper

sealed class BooksData : Abstract.DataObject {
    abstract fun <T> map(mapper: BooksDataMapper<T>): T

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun <T> map(mapper: BooksDataMapper<T>) = mapper.map(books)
    }

    data class Fail(private val e: Exception) : BooksData() {
        override fun <T> map(mapper: BooksDataMapper<T>) = mapper.map(e)
    }
}