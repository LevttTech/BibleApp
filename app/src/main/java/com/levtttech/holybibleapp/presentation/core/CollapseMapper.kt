package com.levtttech.holybibleapp.presentation.core

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.presentation.books.BookUiMapper

interface CollapseMapper : Abstract.Mapper.Data<Boolean, Unit>, BookUiMapper<Unit> {
    override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
        map(isFavoriteOrCollapsed)
}