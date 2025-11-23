package com.levtttech.bibleapp.data.cache

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.BookData
import com.levtttech.bibleapp.data.ToBookDataMapper

interface BooksCacheMapper : Abstract.Mapper {
    fun map(books: List<Abstract.Object<BookData, ToBookDataMapper>>): List<BookData>

    class Base(private val bookMapper: ToBookDataMapper) : BooksCacheMapper {
        override fun map(books: List<Abstract.Object<BookData, ToBookDataMapper>>): List<BookData> {
            return books.map { it.map(bookMapper) }
        }
    }

}