package com.levtttech.bibleapp.presentation.books

import android.content.Context
import androidx.core.content.edit
import com.levtttech.bibleapp.core.Read
import com.levtttech.bibleapp.core.Save

interface BookCache : Save<Pair<Int, String>>, Read<Pair<Int, String>> {

    class Base(context: Context) : BookCache {
        private val sharedPreferences =
            context.getSharedPreferences(BOOK_ID_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: Pair<Int, String>) {
            sharedPreferences.edit {
                putInt(BOOK_ID_KEY, data.first)
                putString(BOOK_NAME_KEY, data.second)
            }
        }

        override fun read(): Pair<Int, String> {
            return Pair(
                sharedPreferences.getInt(BOOK_ID_KEY, DEFAULT_VALUE),
                sharedPreferences.getString(BOOK_NAME_KEY, "") ?: ""
            )
        }

        private companion object {
            const val BOOK_ID_KEY = "bookIdKey"
            const val BOOK_ID_FILENAME = "bookId"
            const val BOOK_NAME_KEY = "bookNameKey"
            const val DEFAULT_VALUE = 0
        }
    }
}