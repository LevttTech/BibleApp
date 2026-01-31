package com.levtttech.holybibleapp.presentation.books

import com.levtttech.holybibleapp.data.core.IdCache
import com.levtttech.holybibleapp.core.PreferencesProvider


interface BookCache : IdCache {

    class Base(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), BookCache {
        private companion object {
            const val FILENAME = "bookId"
            const val KEY = "bookIdKey"
        }
    }

    class Deeplink(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), BookCache {
        private companion object {
            const val FILENAME = "deeplinkIds"
            const val KEY = "deeplinkBookIdKey"
        }
    }
}