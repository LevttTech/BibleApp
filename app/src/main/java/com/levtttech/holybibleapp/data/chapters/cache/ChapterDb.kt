package com.levtttech.holybibleapp.data.chapters.cache

import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChapterDb : RealmObject(), ChapterRealm, Matcher<Int> {

    /**
     * BookId * 1000 + chapterId
     */
    @PrimaryKey
    var id: Int = -1

    override fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean) =
        mapper.map(id, isFavorite)

    override fun matches(arg: Int) = arg == id
}

interface ChapterRealm {
    fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean): T
}