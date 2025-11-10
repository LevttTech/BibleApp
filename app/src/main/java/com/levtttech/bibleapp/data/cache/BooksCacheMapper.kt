package com.levtttech.bibleapp.data.cache

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book

interface BooksCacheMapper : Abstract.Mapper {
    fun map(books: List<BookDb>): List<Book>

    class Base(private val bookDbMapper: BookDbMapper) : BooksCacheMapper {
        override fun map(books: List<BookDb>): List<Book> {
            return books.map { it.map(bookDbMapper)}
        }
    }

}