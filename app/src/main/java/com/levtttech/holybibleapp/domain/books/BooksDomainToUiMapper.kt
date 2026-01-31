package com.levtttech.holybibleapp.domain.books

import com.levtttech.holybibleapp.core.Abstract

interface BooksDomainToUiMapper<T> : Abstract.Mapper.DomainToUi<List<BookDomain>, T>