package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.ToBookDataMapper


abstract class BooksRepositoryTestBase {

    protected class ToBookDataMapperTest : ToBookDataMapper {
        override fun map(
            id: Int,
            name: String,
            testament: String,
        ): BookData = BookData(id, name, testament)
    }

}