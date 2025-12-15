package com.levtttech.bibleapp.domain.books

import com.levtttech.bibleapp.data.books.BookDomainMapper

class BaseBookDomainMapper : BookDomainMapper {
    override fun map(
        id: Int,
        name: String,
    ): BookDomain = BookDomain.Base(id, name)
}