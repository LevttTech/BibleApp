package com.levtttech.bibleapp.data.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.books.BookDomain

interface BookDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookDomain
}