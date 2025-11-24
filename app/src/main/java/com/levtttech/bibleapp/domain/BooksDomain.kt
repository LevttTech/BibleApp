package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.BookData
import com.levtttech.bibleapp.data.BookDomainMapper
import com.levtttech.bibleapp.data.TestamentWrapper
import com.levtttech.bibleapp.presentation.BooksUi
import java.net.HttpRetryException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {

    class Success(
        private val books: List<BookData>,
        private val mapperToBookDomain: BookDomainMapper,
        private val testamentMapper: TestamentTypeMapper,
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            val testament = TestamentWrapper.Base()
            val list = mutableListOf<BookDomain>()
            val (oldTestament, newTestament) = books.partition {
                it.compareTestament(
                    testament
                )
            }

            if (oldTestament.isNotEmpty()) updateList(list, oldTestament, TestamentType.OLD)
            if (newTestament.isNotEmpty()) updateList(list, newTestament, TestamentType.NEW)

            return mapper.map(list)
        }

        fun updateList(
            list: MutableList<BookDomain>,
            testamentList: List<BookData>,
            testamentType: TestamentType,
        ) {
            list.add(testamentMapper.map(testamentType))
            list.addAll(testamentList.map { it.map(mapperToBookDomain) })
        }
    }

    class Fail(private val e: Exception) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpRetryException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}