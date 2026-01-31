package com.levtttech.holybibleapp.domain.books

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.domain.core.BaseDataToDomainMapper
import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.data.books.BookData

interface BooksDataMapper<T> : Abstract.Mapper.DataToDomain<List<BookData>, T> {

    class Id(private val id: Read<Int>) : BooksDataMapper<BookData> {
        override fun map(data: List<BookData>) =
            data.find { it.map(BookDataMapper.Id(id)) } ?: BookData.Empty()

        override fun map(e: Exception) = BookData.Empty()
    }

    class Error : BaseDataToDomainMapper<List<BookData>, ErrorType>(), BooksDataMapper<ErrorType> {
        override fun map(data: List<BookData>) = ErrorType.GENERIC_ERROR
        override fun map(e: Exception) = errorType(e)
    }
}