package com.levtttech.bibleapp.presentation

import com.levtttech.bibleapp.core.Abstract

sealed class BookUi : Abstract.Object<Unit, BookUi.Mapper>() {
    open fun collapseOrExpand(listener: CollapseListener) {}
    open fun showCollapsed(mapper: CollapseMapper) {}
    open fun matches(arg: Int) = false
    open fun changeState(): BookUi = Progress

    open fun isCollapsed() = false
    open fun same(bookUi: BookUi) = false
    open fun sameContent(bookUi: BookUi) = false
    open fun saveId(cacheId: UiDataCache.CacheId) = Unit


    object Empty : BookUi() {
        override fun map(mapper: Mapper) {
        }

    }
    object Progress : BookUi() {
        override fun map(mapper: Mapper) {

        }
    }

    abstract class Info(
        protected open val id: Int,
        protected open val name: String,
    ) : BookUi() {
        override fun map(mapper: Mapper) {
            mapper.map(name)
        }
        override fun matches(arg: Int): Boolean = arg == id
    }

    class Base(id: Int, name: String) : Info(id, name) {
        override fun same(bookUi: BookUi) = bookUi is Base && bookUi.id == id

        override fun sameContent(bookUi: BookUi) = bookUi is Base && bookUi.name == name
    }
    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false,
    ) : Info(id, name) {
        override fun saveId(cacheId: UiDataCache.CacheId) =
            cacheId.save(id)

        override fun collapseOrExpand(listener: CollapseListener) = listener.collapse(id)

        override fun showCollapsed(mapper: CollapseMapper) = mapper.show(collapsed)

        override fun changeState() = Testament(id, name, !collapsed)
        override fun isCollapsed() = collapsed
        override fun same(bookUi: BookUi) = bookUi is Testament && bookUi.id == id
        override fun sameContent(bookUi: BookUi) =
            bookUi is Testament && bookUi.name == name && bookUi.collapsed == collapsed
    }

    class Fail(
        private val message: String,
    ) : BookUi() {
        override fun map(mapper: Mapper) = mapper.map(message)
        override fun same(bookUi: BookUi) = sameContent(bookUi)
        override fun sameContent(bookUi: BookUi) =  bookUi is Fail && bookUi.message == message
    }

    interface Mapper : Abstract.Mapper {
        fun map(text: String)

    }

    interface CollapseMapper {
        fun show(collapsed: Boolean)
    }
}