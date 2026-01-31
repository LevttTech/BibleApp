package com.levtttech.holybibleapp.data.verses

interface ToVerseMapper<T> {

    fun map(id: Int, verseId: Int, text: String, isFavorite: Boolean): T

    class Base : ToVerseMapper<VerseData> {
        override fun map(id: Int, verseId: Int, text: String, isFavorite: Boolean) =
            VerseData.Base(id, verseId, text, isFavorite)
    }
}