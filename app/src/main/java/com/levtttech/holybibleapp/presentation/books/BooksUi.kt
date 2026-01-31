package com.levtttech.holybibleapp.presentation.books

import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.presentation.core.ListMapper

sealed class BooksUi : ChangeFavorite<Int> {

    abstract fun map(mapper: ListMapper<BookUi>)

    data class Base(private val books: MutableList<BookUi>) : BooksUi() {

        override fun map(mapper: ListMapper<BookUi>) = mapper.map(books)

        override fun changeFavorite(id: Int) {
            val itemToChange = books.find { it.map(BookUiMapper.Id(id)) } ?: BookUi.Empty
            val newItem = itemToChange.map(BookUiMapper.ChangeBookState())
            books[books.indexOf(itemToChange)] = newItem
        }
    }
}