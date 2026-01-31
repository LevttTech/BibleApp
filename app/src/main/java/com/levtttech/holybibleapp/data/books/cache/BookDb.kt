package com.levtttech.holybibleapp.data.books.cache

import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.books.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb : RealmObject(), BookRealm, Matcher<Int> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean) =
        mapper.map(id, name, testament, isFavorite)

    override fun matches(arg: Int) = arg == id
}

interface BookRealm {
    fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean): T
}