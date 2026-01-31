package com.levtttech.holybibleapp.domain.books

class BaseBookDataToDomainMapper : BookDataMapper<BookDomain> {
    override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
        BookDomain.Base(id, name, isFavorite)
}