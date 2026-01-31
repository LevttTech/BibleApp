package com.levtttech.holybibleapp.domain.books

import androidx.annotation.StringRes
import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.ResourceProvider

interface BookDomainToUiMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String, isFavorite: Boolean = false): T

    class Name(
        private val resourceProvider: ResourceProvider,
        @StringRes private val stringResId: Int,
        private val arg: Any
    ) : BookDomainToUiMapper<String> {
        override fun map(id: Int, name: String, isFavorite: Boolean) =
            resourceProvider.string(stringResId, name, arg)
    }
}