package com.levtttech.holybibleapp.data.books

import com.levtttech.holybibleapp.core.Abstract


interface ToBookMapper<T> : Abstract.Mapper {

    fun map(id: Int, name: String, testament: String, isFavorite: Boolean = false): T

    class Base : ToBookMapper<BookData> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            BookData.Base(id, name, testament, isFavorite)
    }
}