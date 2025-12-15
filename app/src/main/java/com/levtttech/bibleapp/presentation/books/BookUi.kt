package com.levtttech.bibleapp.presentation.books

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Comparing
import com.levtttech.bibleapp.core.TextMapper
import com.levtttech.bibleapp.presentation.books.BookUi.CollapseMapper
import com.levtttech.bibleapp.presentation.core.Collapse
import com.levtttech.bibleapp.presentation.core.ComparingMapper


sealed class BookUi : ComparingMapper<BookUi>, Collapse{

    open fun matches(arg: Int) = false
    override fun map(mapper: TextMapper) = Unit

    open fun changeState(): BookUi = Progress

    open fun open(clickListener: ClickListener) = Unit
    open fun saveId(cacheId: UiDataCache.CacheId) = Unit

    object Progress : BookUi()

    abstract class Info(
        protected open val id: Int,
        protected open val name: String,
    ) : BookUi() {
        override fun map(mapper: TextMapper) {
            mapper.map(name)
        }

        override fun matches(arg: Int): Boolean = arg == id
    }

    data class Base(override val id: Int, override val name: String) : Info(id, name) {
        override fun same(bookUi: BookUi) = bookUi is Base && bookUi.id == id

        override fun sameContent(bookUi: BookUi) = bookUi is Base && bookUi.name == name
        override fun map(mapper: TextMapper) {
            mapper.map(name)
        }

        override fun open(clickListener: ClickListener) = clickListener.click(id, name)
    }
    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false,
    ) : Info(id, name), Collapse {

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

    data class Fail(
        private val message: String,
    ) : BookUi() {
        override fun map(mapper: TextMapper) {
            mapper.map(message)
        }

        override fun same(bookUi: BookUi) = sameContent(bookUi)
        override fun sameContent(bookUi: BookUi) =  bookUi is Fail && bookUi.message == message
    }


    interface CollapseMapper {
        fun show(collapsed: Boolean)
    }
}