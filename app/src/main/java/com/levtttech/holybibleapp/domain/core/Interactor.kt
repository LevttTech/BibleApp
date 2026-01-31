package com.levtttech.holybibleapp.domain.core

import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.sl.core.Feature

interface Interactor : ScrollPosition, ChangeFavorite<Int> {

    abstract class Abstract(
        private val repository: ChangeFavorite<Int>,
        private val scrollPositionCache: ScrollPosition
    ) : Interactor {

        override fun changeFavorite(id: Int) = repository.changeFavorite(id)
        override fun scrollPosition(feature: Feature) = scrollPositionCache.scrollPosition(feature)
        override fun saveScrollPosition(feature: Feature, position: Int) =
            scrollPositionCache.saveScrollPosition(feature, position)
    }
}