package com.levtttech.bibleapp.presentation.books

import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.domain.books.BookDomainToUiMapper
import com.levtttech.bibleapp.domain.books.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper {
    override fun map(
        id: Int,
        name: String,
    ): BookUi = when (id) {
        TestamentType.OLD.getId() -> BookUi.Testament(
            id, resourceProvider.getString(R.string.old_testament)
        )

        TestamentType.NEW.getId() -> BookUi.Testament(
            id, resourceProvider.getString(R.string.new_testament)
        )

        else -> BookUi.Base(id, name)
    }
}

