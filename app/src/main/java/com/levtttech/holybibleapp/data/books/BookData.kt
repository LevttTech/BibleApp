package com.levtttech.holybibleapp.data.books

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.books.cache.BookDataToDbMapper
import com.levtttech.holybibleapp.data.core.DbWrapper
import com.levtttech.holybibleapp.domain.books.BookDataMapper
import io.realm.RealmObject

interface BookData : Abstract.DataObject {

    fun <T> map(mapper: BookDataMapper<T>): T
    fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>): T

    data class Base(
        private val id: Int,
        private val name: String,
        private val testament: String,
        private val isFavorite: Boolean = false,
    ) : BookData {
        override fun <T> map(mapper: BookDataMapper<T>) =
            mapper.map(id, name, testament, isFavorite)

        override fun <E : RealmObject> map(mapper: BookDataToDbMapper<E>, db: DbWrapper<E>) =
            mapper.mapToDb(id, name, testament, db)
    }

    class Empty : BookData {
        override fun <T> map(mapper: BookDataMapper<T>) = mapper.map(0, "", "", false)
        override fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(0, "", "", db)
    }
}