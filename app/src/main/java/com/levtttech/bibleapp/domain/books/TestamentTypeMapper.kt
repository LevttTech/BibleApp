package com.levtttech.bibleapp.domain.books

import com.levtttech.bibleapp.core.Abstract

interface TestamentTypeMapper : Abstract.Mapper {
    fun map(testamentType: TestamentType): BookDomain.Testament
    class Base : TestamentTypeMapper {
        override fun map(testamentType: TestamentType) = BookDomain.Testament(testamentType)
    }
}
