package com.levtttech.bibleapp.data.net

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book

interface BookCloudMapper : Abstract.Mapper {
    fun map(id: Int, name: String): Book

    class Base : BookCloudMapper {
        override fun map(id: Int, name: String): Book = Book(id, name)
    }
}