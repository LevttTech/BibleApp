package com.levtttech.holybibleapp.presentation.core

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.presentation.books.BookUiMapper
import com.levtttech.holybibleapp.presentation.chapters.ChapterUiMapper
import com.levtttech.holybibleapp.presentation.verses.VerseUiMapper

interface TextMapper : Abstract.Mapper.Data<String, Unit>, BookUiMapper<Unit>,
    ChapterUiMapper<Unit>, VerseUiMapper<Unit> {
    override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) = map(name)
    override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) = map(text)
}