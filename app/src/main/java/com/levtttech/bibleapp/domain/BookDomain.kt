package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.presentation.BookUi

sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper>() {
    class Base(
        private val id: Int,
        private val name: String,
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi = mapper.map(id, name)
    }

    class Testament(private val type: TestamentType) : BookDomain(),
        TestamentMapper<Testament, TestamentTypeMapper> {
        override fun map(mapper: BookDomainToUiMapper): BookUi = mapper.map(type.getId(), type.name)
        override fun mapTestament(mapper: TestamentTypeMapper): Testament = mapper.map(type)

    }
}

interface TestamentMapper<T, M : Abstract.Mapper> {
    fun mapTestament(mapper: M): T
}