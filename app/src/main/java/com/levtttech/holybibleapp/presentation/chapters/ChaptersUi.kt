package com.levtttech.holybibleapp.presentation.chapters

import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.presentation.books.BookUi
import com.levtttech.holybibleapp.presentation.core.ListMapper
import com.levtttech.holybibleapp.presentation.core.TextMapper

sealed class ChaptersUi : ChangeFavorite<Int> {

    abstract fun map(list: ListMapper<ChapterUi>, text: TextMapper)

    class Base(private val chapters: MutableList<ChapterUi>, private val bookName: BookUi) :
        ChaptersUi() {

        override fun map(list: ListMapper<ChapterUi>, text: TextMapper) {
            list.map(chapters)
            bookName.map(text)
        }

        override fun changeFavorite(id: Int) {
            val itemToChange = chapters.find { it.map(ChapterUiMapper.Id(id)) } ?: ChapterUi.Empty
            val newItem = itemToChange.map(ChapterUiMapper.ChangeState())
            chapters[chapters.indexOf(itemToChange)] = newItem
        }
    }
}