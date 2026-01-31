package com.levtttech.holybibleapp.domain.core

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.ChangeFavorite

interface Repository<E : Abstract.DataObject> : ChangeFavorite<Int> {
    suspend fun fetchData(): E
}