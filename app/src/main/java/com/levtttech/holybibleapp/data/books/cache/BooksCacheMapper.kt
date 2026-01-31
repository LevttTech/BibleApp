package com.levtttech.holybibleapp.data.books.cache

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.books.BookData
import com.levtttech.holybibleapp.data.books.ToBookMapper
import com.levtttech.holybibleapp.data.core.FavoritesList


interface BooksCacheMapper : Abstract.Mapper.Data<Pair<List<BookDb>, FavoritesList>, List<BookData>> {

    class Base(private val mapper: ToBookMapper<BookData>) : BooksCacheMapper {
        override fun map(data: Pair<List<BookDb>, FavoritesList>) = data.let { (books, ids) ->
            books.map { bookDb -> bookDb.map(mapper, ids.isFavorite(bookDb)) }
        }
    }
}