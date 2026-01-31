package com.levtttech.holybibleapp.presentation.verses

import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.domain.verses.VerseDomain
import com.levtttech.holybibleapp.domain.verses.VerseDomainToUiMapper

class BaseVerseDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    VerseDomainToUiMapper<VerseUi> {
    override fun map(id: Int, visibleId: Int, text: String, isFavorite: Boolean) =
        if (VerseDomain.Next.matches(id))
            VerseUi.Next(resourceProvider.string(R.string.next_chapter))
        else
            VerseUi.Base(id, "$visibleId $text", isFavorite)
}