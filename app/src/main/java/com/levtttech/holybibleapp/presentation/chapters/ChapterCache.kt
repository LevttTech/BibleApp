package com.levtttech.holybibleapp.presentation.chapters

import com.levtttech.holybibleapp.data.core.IdCache
import com.levtttech.holybibleapp.core.PreferencesProvider

interface ChapterCache : IdCache {

    class Base(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), ChapterCache {
        private companion object {
            const val FILENAME = "chapterIdFileName"
            const val KEY = "chapterIdKey"
        }
    }

    class Deeplink(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), ChapterCache {
        private companion object {
            const val FILENAME = "deeplinkIds"
            const val KEY = "deeplinkChapterIdKey"
        }
    }
}