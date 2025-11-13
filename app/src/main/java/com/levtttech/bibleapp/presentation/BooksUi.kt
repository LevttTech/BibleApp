package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book
import com.levtttech.bibleapp.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, Abstract.Mapper.Empty>() {
    class Success(
        private val communication: BooksCommunication,
        private val books: List<Book>,
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) = communication.show(books)
    }

    class Fail(
        private val communication: BooksCommunication,
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider,
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val message = when (errorType) {
                ErrorType.NO_CONNECTION -> resourceProvider.getString(R.string.no_connection_message)
                ErrorType.SERVICE_UNAVAILABLE -> resourceProvider.getString(R.string.service_unavailable_message)
                ErrorType.GENERIC_ERROR -> resourceProvider.getString(R.string.generic_error_message)
            }
            communication.show(message)
        }
    }
}