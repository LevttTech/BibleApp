package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.BookUi

data class BookDomain(private val id: Int, private val name: String) : Abstract.Object<BookUi, BookDomainToUiMapper>() {
    override fun map(mapper: BookDomainToUiMapper): BookUi = mapper.map(id, name)
}