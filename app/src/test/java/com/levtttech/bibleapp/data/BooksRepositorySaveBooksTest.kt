package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.data.cache.BookDb
import com.levtttech.bibleapp.data.cache.BookDbMapper
import com.levtttech.bibleapp.data.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.cache.BooksCacheMapper
import com.levtttech.bibleapp.data.net.BookCloud
import com.levtttech.bibleapp.data.net.BookCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BooksRepositorySaveBooksTest : BooksRepositoryTestBase() {

    @Test
    fun `test book save`() = runBlocking{
        val testCacheDataSource = TestBooksCacheDataSource()
        val testCloudDataSource = TestBooksCloudDataSource()
        val repository = BooksRepository.Base(
            cloudDataSource = testCloudDataSource,
            cacheDataSource = testCacheDataSource,
            cloudMapper = BooksCloudMapper.Base(TestBookCloudMapper()),
            cacheMapper = BooksCacheMapper.Base(TestBookDbMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(
            Book(1,"book1"),
            Book(2,"book2"),
            Book(3, "book3")
        ))

        assertEquals(expected,actual)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                Book(1,"book1 db"),
                Book(2,"book2 db"),
                Book(3,"book3 db")
            )
        )

        assertEquals(expectedCache, actualCache)
    }
    private inner class TestBooksCacheDataSource :
        BooksCacheDataSource {
            private val list = mutableListOf<BookDb>()
        override suspend fun fetchBooks(): List<BookDb> {
          return list
        }

        override suspend fun saveBooks(books: List<Book>) {
            books.map { book ->
                list.add(BookDb(book.id, "${book.name} db"))
            }
        }
    }

    private inner class TestBooksCloudDataSource :
        BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                    BookCloud(1, "book1"),
                    BookCloud(2, "book2"),
                    BookCloud(3, "book3")
                )
        }
    }

}