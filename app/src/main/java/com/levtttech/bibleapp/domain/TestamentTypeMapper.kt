package com.levtttech.bibleapp.domain

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.BookDomain.Testament

interface TestamentTypeMapper : Abstract.Mapper {
    fun map(testamentType: TestamentType): Testament
    class Base : TestamentTypeMapper {
        override fun map(testamentType: TestamentType) = Testament(testamentType)
    }
}
