package com.levtttech.bibleapp.presentation.chapters

import com.levtttech.bibleapp.core.TextMapper
import com.levtttech.bibleapp.presentation.core.ComparingMapper

sealed class ChapterUi : ComparingMapper<ChapterUi> {


    object Progress : ChapterUi() {
        override fun map(mapper: TextMapper) {
        }
    }

    class Base(private val id: Int) : ChapterUi() {
        override fun same(data: ChapterUi) = data is Base && data.id == id

        override fun sameContent(data: ChapterUi) = same(data)

        override fun map(mapper: TextMapper) {
            mapper.map("Chapter $id")
        }
    }

    class Fail(private val message: String) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)

        override fun same(data: ChapterUi) = data is Fail && data.message == message

        override fun sameContent(data: ChapterUi) = same(data)
    }

}