package com.levtttech.holybibleapp.domain.books

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.core.Save
import com.levtttech.holybibleapp.data.books.TestamentTemp

interface BookDataMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String, testament: String, isFavorite: Boolean): T

    class Id(private val idContainer: Read<Int>) : BookDataMapper<Boolean> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            idContainer.read() == id
    }

    class CompareTestament(private val testamentTemp: TestamentTemp) : BookDataMapper<Boolean> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            testamentTemp.matches(testament)
    }

    class SaveTestament(private val testamentTemp: Save<String>) : BookDataMapper<Unit> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            testamentTemp.save(testament)
    }
}