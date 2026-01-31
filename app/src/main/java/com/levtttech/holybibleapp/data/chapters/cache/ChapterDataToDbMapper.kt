package com.levtttech.holybibleapp.data.chapters.cache

import com.levtttech.holybibleapp.data.core.DbWrapper
import com.levtttech.holybibleapp.data.chapters.ChapterId
import io.realm.RealmObject

interface ChapterDataToDbMapper<T : RealmObject> {

    fun mapTo(chapterId: ChapterId, db: DbWrapper<T>): T

    class Base : ChapterDataToDbMapper<ChapterDb> {
        override fun mapTo(chapterId: ChapterId, db: DbWrapper<ChapterDb>) = chapterId.map(db)
    }
}