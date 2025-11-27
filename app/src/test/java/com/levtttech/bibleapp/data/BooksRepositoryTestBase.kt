package com.levtttech.bibleapp.data


abstract class BooksRepositoryTestBase {

    protected class ToBookDataMapperTest : ToBookDataMapper {
        override fun map(
            id: Int,
            name: String,
            testament: String,
        ): BookData = BookData(id, name, testament)
    }

}