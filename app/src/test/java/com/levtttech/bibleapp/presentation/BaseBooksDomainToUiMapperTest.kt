package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.data.BookData
import com.levtttech.bibleapp.data.BookDomainMapper
import com.levtttech.bibleapp.domain.BaseBooksDataToDomainMapper
import com.levtttech.bibleapp.domain.BookDomain
import com.levtttech.bibleapp.domain.BookDomain.Base
import com.levtttech.bibleapp.domain.BookDomain.Testament
import com.levtttech.bibleapp.domain.BooksDomain
import com.levtttech.bibleapp.domain.TestamentType
import com.levtttech.bibleapp.domain.TestamentTypeMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BaseBooksDomainToUiMapperTest {
    @Test
    fun `map should return Success with correct books domain when input is valid`() {
        val bookDomainMapperTest = BookDomainMapperTest()
        val testamentMapperTest = TestamentMapperTest()

        val mapper = BaseBooksDataToDomainMapper(
            bookDomainMapper = bookDomainMapperTest,
            testamentMapper = testamentMapperTest,
        )
        val list = listOf(
            BookData(1, "Genesis", "OT"),
            BookData(2, "Exodus", "OT"),
            BookData(40, "Matthew", "NT"),
            BookData(41, "Mark", "NT")
        )


        val actual = mapper.map(list)
        val expected = BooksDomain.Success(
            listOf(
                Testament(TestamentType.OLD),
                Base(1, "Genesis"),
                Base(2, "Exodus"),
                Testament(TestamentType.NEW),
                Base(40, "Matthew"),
                Base(41, "Mark")
            )
        )

        assertTrue(actual is BooksDomain.Success)
        assertEquals(expected, actual)

    }

    private class BookDomainMapperTest : BookDomainMapper {
        override fun map(
            id: Int,
            name: String,
        ): BookDomain = Base(id, name)
    }

    private class TestamentMapperTest : TestamentTypeMapper {
        override fun map(testamentType: TestamentType): Testament = Testament(testamentType)
    }
}