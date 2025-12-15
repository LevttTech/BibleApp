package com.levtttech.bibleapp.data.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.books.net.BookCloud

interface BooksCloudMapper : Abstract.Mapper.Data<List<BookCloud>, List<BookData>> {
    class Base(private val mapper: ToBookDataMapper) : BooksCloudMapper {
        override fun map(data: List<BookCloud>): List<BookData> = data.map { it.map(mapper) }
    }
}

