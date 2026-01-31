package com.levtttech.holybibleapp.presentation.verses

import com.levtttech.holybibleapp.core.Multiply
import com.levtttech.holybibleapp.core.Same
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.deeplink.DeeplinkData

interface VerseUiMapper<T> {

    fun map(id: Int, text: String, isFavorite: Boolean): T

    class Display(private val show: Show<Int>) : VerseUiMapper<Unit> {
        override fun map(id: Int, text: String, isFavorite: Boolean) = show.open(id)
    }

    class ChangeState : VerseUiMapper<VerseUi> {
        override fun map(id: Int, text: String, isFavorite: Boolean) =
            VerseUi.Base(id, text, !isFavorite)
    }

    interface Compare : VerseUiMapper<Boolean>, Same<VerseUi> {

        class Base : Compare {
            private var itemToCompare: VerseUi = VerseUi.Empty

            override fun map(id: Int, text: String, isFavorite: Boolean) =
                itemToCompare.map(Id(id))

            override fun itemToCompare(item: VerseUi) {
                itemToCompare = item
            }
        }
    }

    class Id(private val id: Int) : VerseUiMapper<Boolean> {
        override fun map(id: Int, text: String, isFavorite: Boolean) = this.id == id
    }

    class Share(
        private val deeplinkData: DeeplinkData,
        private val bookNameAndChapterNumber: String,
        private val multiply: Multiply
    ) : VerseUiMapper<String> {

        override fun map(id: Int, text: String, isFavorite: Boolean): String {
            val bookId = multiply.divide(multiply.divide(id))
            val chapterId = multiply.rest(multiply.divide(id))
            val verseId = multiply.rest(multiply.rest(id))
            return deeplinkData.shareText(
                "$bookNameAndChapterNumber\n$text\n",
                "${bookId}_${chapterId}_$verseId"
            )
        }
    }
}