package com.levtttech.holybibleapp.data.verse

import com.levtttech.holybibleapp.data.core.IdCache
import com.levtttech.holybibleapp.core.PreferencesProvider


interface VerseIdCache : IdCache {

    class Deeplink(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILE_NAME, KEY), VerseIdCache {
        private companion object {
            const val FILE_NAME = "deeplinkIds"
            const val KEY = "verseIdKey"
        }
    }
}