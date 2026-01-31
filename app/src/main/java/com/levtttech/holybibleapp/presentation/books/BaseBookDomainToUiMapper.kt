package com.levtttech.holybibleapp.presentation.books

import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.domain.books.BookDomainToUiMapper
import com.levtttech.holybibleapp.domain.books.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper<BookUi> {
    override fun map(id: Int, name: String, isFavorite: Boolean) = when {
        TestamentType.NEW.matches(id) ->
            BookUi.Testament(id, resourceProvider.string(R.string.new_testament))
        TestamentType.OLD.matches(id) ->
            BookUi.Testament(id, resourceProvider.string(R.string.old_testament))
        else -> BookUi.Base(id, name, isFavorite)
    }
}