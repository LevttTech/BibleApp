package com.levtttech.bibleapp.presentation

import android.content.Context
import androidx.core.content.edit

interface UiDataCache {
    fun saveState()
    fun getList(id: Int): List<BookUi>
    fun cache(books: List<BookUi>): BooksUi

    interface CacheId {
        fun read(): List<Int>
        fun save(id: Int)
        fun finish()
        fun clear()

        class Base(context: Context) : CacheId {
            override fun read(): List<Int> =
                sharedPreferences.getStringSet(ID_LIST_KEY, emptySet<String>())?.map { it.toInt() }
                    ?.toList() ?: emptyList()


            private var sharedPreferences =
                context.getSharedPreferences(ID_LIST_NAME, Context.MODE_PRIVATE)
            private var idList = mutableListOf<Int>()
            override fun save(id: Int) {
                idList.add(id)
            }

            override fun finish() {
                val set = idList.map { it.toString() }.toSet()

                sharedPreferences.edit { putStringSet(ID_LIST_KEY, set) }
            }

            override fun clear() = idList.clear()
        }

        private companion object {
            const val ID_LIST_NAME = "collapsedItemsIdList"
            const val ID_LIST_KEY = "collapsedItemsIdKey"
        }
    }

    class Base(private val cacheId: CacheId) : UiDataCache {
        override fun saveState() {
            cacheId.clear()
            cachedList.filter { it.isCollapsed() }.forEach {
                it.saveId(cacheId)
            }
            cacheId.finish()
        }

        private val cachedList = mutableListOf<BookUi>()
        override fun cache(books: List<BookUi>): BooksUi {
            cachedList.clear()
            cachedList.addAll(books)
            var list: List<BookUi> = ArrayList(books)
            val collapsedList = cacheId.read()
            collapsedList.forEach { collapsed ->
                list = getList(collapsed)
            }
            return BooksUi.Base(list)
        }

        override fun getList(id: Int): List<BookUi> {
            val newList = mutableListOf<BookUi>()
            val item = cachedList.find {
                it.matches(id)
            }
            var flag = false

            cachedList.forEachIndexed { index, book ->
                if (book == item) {
                    val newItem = item.changeState()
                    newList.add(newItem)
                    cachedList[index] = newItem
                    flag = !newItem.isCollapsed()
                } else if (book is BookUi.Testament) {
                    newList.add(book)
                    flag = !book.isCollapsed()
                } else if (flag) {
                    newList.add(book)
                }
            }
            return newList
        }
    }
}