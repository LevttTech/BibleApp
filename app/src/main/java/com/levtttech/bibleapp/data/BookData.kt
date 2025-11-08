package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.BookDomain

sealed class BookData: Abstract.Object<BookDomain, Abstract.Mapper.Empty>() {
}