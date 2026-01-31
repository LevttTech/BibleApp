package com.levtttech.holybibleapp.data.chapters

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.levtttech.holybibleapp.data.core.DbWrapper
import io.realm.RealmObject


interface ChapterData : Abstract.DataObject {
    fun <T : RealmObject> map(mapper: ChapterDataToDbMapper<T>, db: DbWrapper<T>): T
    fun <T> map(mapper: ChapterDataToDomainMapper<T>): T

    data class Base(private val chapterId: ChapterId, private val isFavorite: Boolean) :
        ChapterData {
        override fun <T : RealmObject> map(mapper: ChapterDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapTo(chapterId, db)

        override fun <T> map(mapper: ChapterDataToDomainMapper<T>) =
            mapper.map(Pair(chapterId, isFavorite))
    }
}