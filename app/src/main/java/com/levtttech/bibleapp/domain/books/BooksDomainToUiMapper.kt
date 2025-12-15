package com.levtttech.bibleapp.domain.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.books.BooksUi
import com.levtttech.bibleapp.presentation.books.ResourceProvider

abstract class BooksDomainToUiMapper<T>(
    resourceProvider: ResourceProvider
) : Abstract.Mapper.DomainToUi.Base<List<BookDomain>, T>(resourceProvider)
