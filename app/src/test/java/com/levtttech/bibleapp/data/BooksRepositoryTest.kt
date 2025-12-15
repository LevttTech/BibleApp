
package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.BooksCloudDataSource
import com.levtttech.bibleapp.data.books.BooksCloudMapper
import com.levtttech.bibleapp.data.books.BooksData
import com.levtttech.bibleapp.data.books.BooksRepository
import com.levtttech.bibleapp.data.books.cache.BookDb
import com.levtttech.bibleapp.data.books.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.books.cache.BooksDataMapper
import com.levtttech.bibleapp.data.books.net.BookCloud
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
            cloudMapper = BooksCloudMapper.Base(ToBookDataMapperTest()),
            cacheMapper = BooksDataMapper.Base(ToBookDataMapperTest())
        )
        val actual = repository.fetchBooks()
        val expected = BooksData.Fail( exceptionCloud)

        assertEquals(expected,actual)
    }
    @Test
    fun `connection exist no cache`() = runBlocking {
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val repository = BooksRepository.Base(
            cloudDataSource = testCloudDataSource,
            cacheDataSource = testCacheDataSource,
            cloudMapper = BooksCloudMapper.Base(ToBookDataMapperTest()),
            cacheMapper = BooksDataMapper.Base(ToBookDataMapperTest())
        )
        val actual = repository.fetchBooks()
        val expected = BooksData.Success( listOf(
            BookData(1, "book1", "ot"),
            BookData(2, "book2", "ot"),
            BookData(3, "book3", "nt")
        ))

        assertEquals(expected, actual)
    }
    @Test fun `connection with cache`() = runBlocking {
        val testCacheDataSource = TestBooksCacheDataSource(true)
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val repository = BooksRepository.Base(
            cloudDataSource = testCloudDataSource,
            cacheDataSource = testCacheDataSource,
            cloudMapper = BooksCloudMapper.Base(ToBookDataMapperTest()),
            cacheMapper = BooksDataMapper.Base(ToBookDataMapperTest())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(
            BookData(1, "book1 db", "ot"),
            BookData(2, "book2 db", "ot"),
            BookData(3, "book3 db", "nt")
        ))

        assertEquals(expected,actual)
    }


    private inner class TestBooksCacheDataSource(private val success: Boolean) :
        BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> {
            return if (success) {
                listOf(
                    BookDb(1, "book1 db","ot"),
                    BookDb(2, "book2 db","ot"),
                    BookDb(3, "book3 db","nt")
                )
            } else {
                emptyList()
            }
        }

        override suspend fun saveBooks(books: List<BookData>) {

        }

    }

    private inner class TestBooksCloudDataSource(private val success: Boolean) :
        BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return if (success) {
                listOf(
                    BookCloud(1, "book1","ot"),
                    BookCloud(2, "book2","ot"),
                    BookCloud(3, "book3","nt")
                )
            } else {
                throw exceptionCloud
            }
        }
    }

}


