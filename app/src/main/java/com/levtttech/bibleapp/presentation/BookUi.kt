package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.core.Abstract

sealed class BookUi : Abstract.Object<Unit, BookUi.Mapper>() {

    object Progress : BookUi() {
        override fun map(mapper: Mapper) {

        }
    }

    class Base(
        private val id: Int,
        private val name: String,
    ) : BookUi() {
        override fun map(mapper: Mapper) = mapper.map(name)
    }

    class Fail(
        private val message: String,
    ) : BookUi() {
        override fun map(mapper: Mapper) = mapper.map(message)
    }

    interface Mapper : Abstract.Mapper {
        fun map(text: String)

    }
}