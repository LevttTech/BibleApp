package com.levtttech.holybibleapp.data.chapters.cache

import com.levtttech.holybibleapp.data.chapters.ChapterData
import com.levtttech.holybibleapp.data.core.CacheDataSource
import com.levtttech.holybibleapp.data.core.DbWrapper
import com.levtttech.holybibleapp.data.core.Limits
import com.levtttech.holybibleapp.data.core.RealmProvider
import io.realm.Realm

interface ChaptersCacheDataSource : CacheDataSource<ChapterData> {

    fun fetchChapters(limits: Limits): List<ChapterDb>

    class Base(realmProvider: RealmProvider, private val mapper: ChapterDataToDbMapper<ChapterDb>) :
        CacheDataSource.Abstract<ChapterData>(realmProvider), ChaptersCacheDataSource {

        override fun fetchChapters(limits: Limits): List<ChapterDb> =
            realmProvider.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java)
                    .between("id", limits.min(), limits.max())
                    .findAll()
                return realm.copyFromRealm(chapters)
            }

        override fun save(data: List<ChapterData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction { val dbWrapper = ChapterDbWrapper(it)
                data.forEach { chapter -> chapter.map(mapper, dbWrapper) }
            }
        }

        private inner class ChapterDbWrapper(realm: Realm) : DbWrapper.Base<ChapterDb>(realm) {
            override fun dbClass() = ChapterDb::class.java
        }
    }
}