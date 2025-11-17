package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.data.cache.BookDbMapper
import com.levtttech.bibleapp.data.net.BookCloudMapper

abstract class BooksRepositoryTestBase {
    protected class TestBookCloudMapper: BookCloudMapper {
        override fun map(id: Int, name: String): Book = Book(id, name)
    }

    protected class TestBookDbMapper: BookDbMapper {
        override fun map(id: Int, name: String): Book = Book(id, name)
    }
}