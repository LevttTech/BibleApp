package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.data.BookDomainMapper

class BaseBookDomainMapper : BookDomainMapper {
    override fun map(
        id: Int,
        name: String,
    ): BookDomain = BookDomain.Base(id, name)
}