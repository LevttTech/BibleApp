package com.levtttech.bibleapp.data

import android.database.SQLException
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
import java.net.UnknownHostException

class BooksRepositoryTest : BooksRepositoryTestBase() {
    private val exceptionCloud = UnknownHostException()

    @Test
    fun `no connection no cache`() = runBlocking {
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val testCloudDataSource = TestBooksCloudDataSource(false)
        val repository = BooksRepository.Base(
            cloudDataSource = testCloudDataSource,
            cacheDataSource = testCacheDataSource,
            cloudMapper = BooksCloudMapper.Base(TestBookCloudMapper()),
            cacheMapper = BooksCacheMapper.Base(TestBookDbMapper())
        )
        val actual = repository.fetchBooks()
        val expected = BooksData.Fail(exceptionCloud)

        assertEquals(expected, actual)

    }
    @Test
    fun `no connection but cache exist`() = runBlocking {
        val testCacheDataSource = TestBooksCacheDataSource(true)
        val testCloudDataSource = TestBooksCloudDataSource(false)
        val repository = BooksRepository.Base(
            cloudDataSource = testCloudDataSource,
            cacheDataSource = testCacheDataSource,
            cloudMapper = BooksCloudMapper.Base(TestBookCloudMapper()),
            cacheMapper = BooksCacheMapper.Base(TestBookDbMapper())
        )
        val actual = repository.fetchBooks()
        val expected = BooksData.Success( listOf(
            Book(1, "book1 db"),
            Book(2, "book2 db"),
            Book(3, "book3 db")
        ))

        assertEquals(expected,actual)
    }
    @Test
    fun `connection exist no cache`() = runBlocking {
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val repository = BooksRepository.Base(
            cloudDataSource = testCloudDataSource,
            cacheDataSource = testCacheDataSource,
            cloudMapper = BooksCloudMapper.Base(TestBookCloudMapper()),
            cacheMapper = BooksCacheMapper.Base(TestBookDbMapper())
        )
        val actual = repository.fetchBooks()
        val expected = BooksData.Success( listOf(
            Book(1, "book1"),
            Book(2, "book2"),
            Book(3, "book3")
        ))

        assertEquals(expected, actual)
    }
    @Test fun `connection with cache`() = runBlocking {
        val testCacheDataSource = TestBooksCacheDataSource(true)
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val repository = BooksRepository.Base(
            cloudDataSource = testCloudDataSource,
            cacheDataSource = testCacheDataSource,
            cloudMapper = BooksCloudMapper.Base(TestBookCloudMapper()),
            cacheMapper = BooksCacheMapper.Base(TestBookDbMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(
            Book(1,"book1 db"),
            Book(2,"book2 db"),
            Book(3, "book3 db")
        ))

        assertEquals(expected,actual)
    }


    private inner class TestBooksCacheDataSource(private val success: Boolean) :
        BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> {
            return if (success) {
                listOf(
                    BookDb(1, "book1 db"),
                    BookDb(2, "book2 db"),
                    BookDb(3, "book3 db")
                )
            } else {
                emptyList()
            }
        }

        override suspend fun saveBooks(books: List<Book>) {

        }
    }

    private inner class TestBooksCloudDataSource(private val success: Boolean) :
        BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return if (success) {
                listOf(
                    BookCloud(1, "book1"),
                    BookCloud(2, "book2"),
                    BookCloud(3, "book3")
                )
            } else {
                throw exceptionCloud
            }
        }
    }

}

