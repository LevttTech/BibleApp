package com.levtttech.bibleapp.domain.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.books.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookUi
}