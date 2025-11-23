package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper : BookDomainToUiMapper {
    override fun map(
        id: Int,
        name: String,
    ): BookUi = BookUi.Base(id, name)
}