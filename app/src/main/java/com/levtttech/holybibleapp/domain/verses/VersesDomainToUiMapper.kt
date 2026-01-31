package com.levtttech.holybibleapp.domain.verses

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.domain.books.BookDomain

interface VersesDomainToUiMapper<T> :
    Abstract.Mapper.DomainToUi<Triple<List<VerseDomain>, BookDomain, Int>, T>