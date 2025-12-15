package com.levtttech.bibleapp.presentation.core

import com.levtttech.bibleapp.presentation.books.BookUi.CollapseMapper
import com.levtttech.bibleapp.presentation.books.CollapseListener

interface Collapse {
    fun collapseOrExpand(listener: CollapseListener) = Unit
    fun showCollapsed(mapper: CollapseMapper) = Unit
    fun isCollapsed(): Boolean = false
}
