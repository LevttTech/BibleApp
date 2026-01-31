package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.domain.books.BookDomain

interface ChaptersDomainToUiMapper<T> :
    Abstract.Mapper.DomainToUi<Pair<List<ChapterDomain>, BookDomain>, T>