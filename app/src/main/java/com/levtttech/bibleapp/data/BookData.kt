package com.levtttech.bibleapp.data

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.cache.BookDb
import com.levtttech.bibleapp.data.cache.ToDbMapper
import com.levtttech.bibleapp.domain.BookDomain

data class BookData(private val id: Int, private val name: String) :
    Abstract.Object<BookDomain, BookDomainMapper>(), DbMapper<BookDb, ToDbMapper> {
    override fun map(mapper: BookDomainMapper): BookDomain = mapper.map(id, name)
    override fun mapToDb(mapper: ToDbMapper): BookDb = mapper.map(id, name)
}

interface DbMapper<T, M : Abstract.Mapper> {
    fun mapToDb(mapper: M): T
}

