package com.levtttech.bibleapp.data.cache

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book

interface BookDbMapper : Abstract.Mapper {
    fun map(id: Int, name: String): Book

    class Base : BookDbMapper {
        override fun map(id: Int, name: String): Book = Book(id, name)
    }
}