package com.levtttech.holybibleapp.presentation.deeplink

import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.ErrorType
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.domain.books.BookDomain
import com.levtttech.holybibleapp.domain.books.BookDomainToUiMapper
import com.levtttech.holybibleapp.domain.verses.VerseDomain
import com.levtttech.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.levtttech.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.levtttech.holybibleapp.presentation.core.BaseDomainToUiMapper
import com.levtttech.holybibleapp.presentation.verses.VerseUi
import com.levtttech.holybibleapp.presentation.verses.VersesUi

class DeeplinkVersesDomainToUiMapper(
    private val verseIdContainer: Read<Int>,
    private val mapper: VerseDomainToUiMapper<VerseUi>,
    private val resourceProvider: ResourceProvider
) : BaseDomainToUiMapper<Triple<List<VerseDomain>, BookDomain, Int>, VersesUi>(resourceProvider),
    VersesDomainToUiMapper<VersesUi> {

    override fun map(data: Triple<List<VerseDomain>, BookDomain, Int>): VersesUi {
        val list = mutableListOf<VerseUi>()
        data.first.find { it.matches(verseIdContainer.read()) }?.let { list.add(it.map(mapper)) }
        return VersesUi.Base(
            list,
            data.second.map(
                BookDomainToUiMapper.Name(resourceProvider, R.string.book_and_chapter, data.third)
            )
        )
    }

    override fun map(errorType: ErrorType) = errorMessage(errorType).let {
        VersesUi.Base(mutableListOf(VerseUi.Fail(it)), it)
    }
}
