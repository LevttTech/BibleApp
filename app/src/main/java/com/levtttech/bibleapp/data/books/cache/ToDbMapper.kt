package com.levtttech.bibleapp.data.books.cache

import com.levtttech.bibleapp.core.Abstract

interface ToDbMapper : Abstract.Mapper {
    fun map(id: Int, name: String, testament: String): BookDb

    class Base : ToDbMapper {
        override fun map(
            id: Int,
            name: String,
            testament: String
        ): BookDb = BookDb(id, name, testament)
    }
}