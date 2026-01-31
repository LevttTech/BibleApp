package com.levtttech.holybibleapp.presentation.chapters

import com.levtttech.holybibleapp.core.Same
import com.levtttech.holybibleapp.core.Show

interface ChapterUiMapper<T> {

    fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean): T

    class Display(private val show: Show<Pair<Int, Int>>) : ChapterUiMapper<Unit> {
        override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) =
            show.open(Pair(visibleId, id))
    }

    class Id(private val id: Int) : ChapterUiMapper<Boolean> {
        override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) = id == this.id
    }

    interface Compare : ChapterUiMapper<Boolean>, Same<ChapterUi> {

        class Base : Compare {
            private var itemToCompare: ChapterUi = ChapterUi.Empty

            override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) =
                itemToCompare.map(Id(id))

            override fun itemToCompare(item: ChapterUi) {
                itemToCompare = item
            }
        }
    }

    class ChangeState : ChapterUiMapper<ChapterUi> {
        override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) =
            ChapterUi.Base(visibleId, id, text, !isFavorite)
    }
}