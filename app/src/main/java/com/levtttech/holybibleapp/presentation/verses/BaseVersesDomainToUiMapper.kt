package com.levtttech.holybibleapp.presentation.verses

import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.domain.books.BookDomain
import com.levtttech.holybibleapp.domain.books.BookDomainToUiMapper
import com.levtttech.holybibleapp.domain.verses.VerseDomain
import com.levtttech.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.levtttech.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.levtttech.holybibleapp.presentation.core.BaseDomainToUiMapper
class BaseVersesDomainToUiMapper(
    private val mapper: VerseDomainToUiMapper<VerseUi>,
    private val resourceProvider: ResourceProvider,
) : BaseDomainToUiMapper<Triple<List<VerseDomain>, BookDomain, Int>, VersesUi>(resourceProvider),
    VersesDomainToUiMapper<VersesUi> {

    override fun map(data: Triple<List<VerseDomain>, BookDomain, Int>) = VersesUi.Base(
        ArrayList(data.first.map { verse -> verse.map(mapper) }),
        data.second.map(BookDomainToUiMapper.Name(resourceProvider, R.string.book_and_chapter, data.third))
    )

    override fun map(errorType: ErrorType) = errorMessage(errorType).let {
        VersesUi.Base(mutableListOf(VerseUi.Fail(it)), it)
    }
}