package com.levtttech.holybibleapp.domain.verses

interface VerseDomainToUiMapper<T> {

    fun map(id: Int, visibleId: Int, text: String, isFavorite: Boolean = false): T
}