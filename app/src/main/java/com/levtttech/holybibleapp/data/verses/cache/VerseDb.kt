package com.levtttech.holybibleapp.data.verses.cache

import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.verses.ToVerseMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class VerseDb : RealmObject(), VerseRealm, Matcher<Int> {

    @PrimaryKey
    var id: Int = -1
    var verseId: Int = -1
    var text: String = ""

    override fun <T> map(mapper: ToVerseMapper<T>, isFavorite: Boolean) =
        mapper.map(id, verseId, text, isFavorite)

    override fun matches(arg: Int) = arg == id
}

interface VerseRealm {
    fun <T> map(mapper: ToVerseMapper<T>, isFavorite: Boolean): T
}