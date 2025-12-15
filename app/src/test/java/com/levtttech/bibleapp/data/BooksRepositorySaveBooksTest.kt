package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.BooksCloudDataSource
import com.levtttech.bibleapp.data.books.BooksCloudMapper
import com.levtttech.bibleapp.data.books.BooksData
import com.levtttech.bibleapp.data.books.BooksRepository
import com.levtttech.bibleapp.data.books.cache.BookDb
import com.levtttech.bibleapp.data.books.cache.BooksCacheDataSource
import com.levtttech.bibleapp.data.books.cache.BooksDataMapper
import com.levtttech.bibleapp.data.books.cache.ToDbMapper
import com.levtttech.bibleapp.data.books.net.BookCloud
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
            cloudMapper = BooksCloudMapper.Base(ToBookDataMapperTest()),
            cacheMapper = BooksDataMapper.Base(ToBookDataMapperTest())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(
            BookData(1, "book1", "ot"),
            BookData(2, "book2", "ot"),
            BookData(3, "book3", "nt")
        ))

        assertEquals(expected,actual)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                BookData(1, "book1 db", "ot"),
                BookData(2, "book2 db", "ot"),
                BookData(3, "book3 db", "nt")
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

        override suspend fun saveBooks(books: List<BookData>) {
            books.map { book ->
                list.add(book.mapToDb(Mapper()))
            }
        }
    }

    private inner class TestBooksCloudDataSource :
        BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                    BookCloud(1, "book1","ot"),
                    BookCloud(2, "book2","ot"),
                    BookCloud(3, "book3","nt")
                )
        }
    }

    private inner class Mapper : ToDbMapper {
        override fun map(
            id: Int,
            name: String,
            testament: String,
        ): BookDb = BookDb(id, "$name db",testament)
    }

}

