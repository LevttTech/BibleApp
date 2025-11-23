package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.net.ToBookMapper

abstract class BooksRepositoryTestBase {
    protected class TestBookCloudMapper: com.levtttech.bibleapp.data.net.ToBookMapper {
        override fun map(id: Int, name: String): Book = Book(id, name)
    }

    protected class TestToBookMapper: ToBookMapper {
        override fun map(id: Int, name: String): Book = Book(id, name)
    }
}