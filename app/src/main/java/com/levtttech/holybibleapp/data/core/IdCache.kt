package com.levtttech.holybibleapp.data.core

import com.levtttech.holybibleapp.core.PreferencesProvider
import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.core.Save


interface IdCache : Save<Int>, Read<Int> {

    abstract class Abstract(
        preferencesProvider: PreferencesProvider,
        fileName: String,
        private val key: String
    ) : IdCache {
        private val sharedPreferences = preferencesProvider.provideSharedPreferences(fileName)
        override fun read() = sharedPreferences.getInt(key, 0)
        override fun save(data: Int) {
            sharedPreferences.edit().putInt(key, data).apply()
        }
    }
}