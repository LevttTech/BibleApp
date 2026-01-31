package com.levtttech.holybibleapp.presentation.books

import com.levtttech.holybibleapp.core.PreferencesProvider
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.core.Save


interface CollapsedIdsCache : Save<Int>, Read<Set<Int>> {
    fun start()
    fun finish()

    abstract class Abstract(
        preferencesProvider: PreferencesProvider,
        fileName: String,
        private val key: String
    ) : CollapsedIdsCache {
        private val sharedPreferences = preferencesProvider.provideSharedPreferences(fileName)
        private val idSet = mutableSetOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(key, emptySet()) ?: emptySet()
            return set.mapTo(HashSet()) { it.toInt() }
        }

        override fun save(data: Int) {
            idSet.add(data)
        }

        override fun start() {
            idSet.clear()
        }

        override fun finish() {
            val set = idSet.mapTo(HashSet()) { it.toString() }
            sharedPreferences.edit().putStringSet(key, set).apply()
        }
    }

    class Base(preferencesProvider: PreferencesProvider) :
        Abstract(preferencesProvider, ID_LIST_NAME, IDS_KEY) {
        private companion object {
            const val ID_LIST_NAME = "collapsedItemsIdList"
            const val IDS_KEY = "collapsedItemsIdsKey"
        }
    }

    class Mock(preferencesProvider: PreferencesProvider) :
        Abstract(preferencesProvider, ID_LIST_NAME, IDS_KEY) {
        private companion object {
            const val ID_LIST_NAME = "MockCollapsedItemsIdList"
            const val IDS_KEY = "MockCollapsedItemsIdsKey"
        }
    }

    class Empty : CollapsedIdsCache {
        override fun read() = emptySet<Int>()
        override fun save(data: Int) = Unit
        override fun start() = Unit
        override fun finish() = Unit
    }
}