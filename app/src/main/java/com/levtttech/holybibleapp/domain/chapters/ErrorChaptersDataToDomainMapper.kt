package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.data.books.BookData
import com.levtttech.holybibleapp.data.chapters.ChapterData
import com.levtttech.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.levtttech.holybibleapp.domain.core.BaseDataToDomainMapper

class ErrorChaptersDataToDomainMapper :
    BaseDataToDomainMapper<Pair<List<ChapterData>, BookData>, ErrorType>(),
    ChaptersDataToDomainMapper<ErrorType> {
    override fun map(data: Pair<List<ChapterData>, BookData>) = ErrorType.GENERIC_ERROR
    override fun map(e: Exception) = errorType(e)
}