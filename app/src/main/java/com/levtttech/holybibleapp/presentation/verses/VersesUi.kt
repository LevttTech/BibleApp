package com.levtttech.holybibleapp.presentation.verses

import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.presentation.core.ListMapper
import com.levtttech.holybibleapp.presentation.core.TextMapper
sealed class VersesUi : ChangeFavorite<Int> {

    abstract fun map(list: ListMapper<VerseUi>, text: TextMapper)

    data class Base(private val data: MutableList<VerseUi>, private val title: String) :
        VersesUi() {
        override fun map(list: ListMapper<VerseUi>, text: TextMapper) {
            list.map(data)
            text.map(title)
        }

        override fun changeFavorite(id: Int) {
            val itemToChange = data.find { it.map(VerseUiMapper.Id(id)) } ?: VerseUi.Empty
            val newItem = itemToChange.map(VerseUiMapper.ChangeState())
            data[data.indexOf(itemToChange)] = newItem
        }
    }
}