package com.levtttech.bibleapp.domain.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.books.ResourceProvider

abstract class ChaptersDomainToUi<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ChapterDomain>, T>(resourceProvider)