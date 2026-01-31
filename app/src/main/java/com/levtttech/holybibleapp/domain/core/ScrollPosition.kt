package com.levtttech.holybibleapp.domain.core

import com.levtttech.holybibleapp.sl.core.Feature

interface ScrollPosition {
    fun saveScrollPosition(feature: Feature, position: Int)
    fun scrollPosition(feature: Feature): Int
}