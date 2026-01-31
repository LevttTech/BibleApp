package com.levtttech.holybibleapp.domain.verses

import com.levtttech.holybibleapp.data.books.BookData
import com.levtttech.holybibleapp.domain.books.BookDataMapper
import com.levtttech.holybibleapp.data.verses.VerseData
import com.levtttech.holybibleapp.data.verses.VerseDataToDomainMapper
import com.levtttech.holybibleapp.data.verses.VersesDataToDomainMapper
import com.levtttech.holybibleapp.domain.books.BookDomain
import com.levtttech.holybibleapp.domain.core.BaseDataToDomainMapper

class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper<VerseDomain>,
    private val bookMapper: BookDataMapper<BookDomain>,
) : BaseDataToDomainMapper<Triple<List<VerseData>, BookData, Pair<Int, Boolean>>, VersesDomain>(),
    VersesDataToDomainMapper<VersesDomain> {

    override fun map(data: Triple<List<VerseData>, BookData, Pair<Int, Boolean>>): VersesDomain {
        val list = ArrayList(data.first.map { verseData -> verseData.map(mapper) })
        if (!data.third.second)
            list.add(VerseDomain.Next)
        return VersesDomain.Success(list, data.second.map(bookMapper), data.third.first)
    }

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}