package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Abstract

interface BooksCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<Abstract.Object<BookData, ToBookDataMapper>>): List<BookData>

    class Base(private val bookMapper: ToBookDataMapper) : BooksCloudMapper {
        override fun map(cloudList: List<Abstract.Object<BookData, ToBookDataMapper>>): List<BookData> {
            return cloudList.map { it.map(bookMapper) }
        }
    }
}

interface ToBookDataMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookData

    class Base : ToBookDataMapper {
        override fun map(
            id: Int,
            name: String,
        ): BookData = BookData(id, name)
    }
}