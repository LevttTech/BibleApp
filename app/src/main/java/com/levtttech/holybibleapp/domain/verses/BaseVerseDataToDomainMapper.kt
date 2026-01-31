package com.levtttech.holybibleapp.domain.verses

import com.levtttech.holybibleapp.data.verses.VerseDataToDomainMapper
class BaseVerseDataToDomainMapper : VerseDataToDomainMapper<VerseDomain> {
    override fun map(id: Int, verseId: Int, text: String, isFavorite: Boolean) =
        VerseDomain.Base(id, verseId, text, isFavorite)
}