package com.levtttech.bibleapp.data.books.cache

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.ToBookDataMapper

interface BooksCacheMapper : Abstract.Mapper.Data<List<BookDb>, List<BookData>> {
    class Base(private val mapper: ToBookDataMapper) : BooksCacheMapper {
        override fun map(data: List<BookDb>): List<BookData> = data.map { it.map(mapper) }
    }
}