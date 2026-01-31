package com.levtttech.holybibleapp.data.verses

interface VerseDataToDomainMapper<T> {
    fun map(id: Int, verseId: Int, text: String, isFavorite: Boolean): T
}