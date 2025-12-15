package com.levtttech.bibleapp.data.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.books.BooksDomain

abstract class BooksDataToDomainMapper<T> : Abstract.Mapper.DataToDomain.Base<List<BookData>, T>()