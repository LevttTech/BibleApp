package com.levtttech.holybibleapp.data.books.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.books.BookData
import com.levtttech.holybibleapp.data.books.ToBookMapper
import com.levtttech.holybibleapp.data.core.FavoritesList

interface BooksCloudMapper :
    Abstract.Mapper.Data<Pair<List<BookCloud>, FavoritesList>, List<BookData>> {

    class Base(private val bookMapper: ToBookMapper<BookData>) : BooksCloudMapper {
        override fun map(data: Pair<List<BookCloud>, FavoritesList>) = data.let { (books, ids) ->
            books.map { bookCloud -> bookCloud.map(bookMapper, ids.isFavorite(bookCloud)) }
        }
    }
}