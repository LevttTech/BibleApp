package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.BookDomain

interface BookDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookDomain
}