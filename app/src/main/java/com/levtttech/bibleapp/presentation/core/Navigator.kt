package com.levtttech.bibleapp.presentation.core

import android.content.Context
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.levtttech.bibleapp.core.Read
import com.levtttech.bibleapp.core.Save
import com.levtttech.bibleapp.presentation.books.BooksFragment
import com.levtttech.bibleapp.presentation.books.BooksNavigator
import com.levtttech.bibleapp.presentation.chapters.ChaptersFragment
import com.levtttech.bibleapp.presentation.chapters.ChaptersNavigator
import com.levtttech.bibleapp.presentation.chapters.NavigationCommunication
import java.util.concurrent.Callable

interface Navigator : Save<Int>, Read<Int>, BooksNavigator, ChaptersNavigator,  FragmentProvider{

    class Base(context: Context) : Navigator {
        private val fragments =
            listOf(Callable { BooksFragment() }, Callable { ChaptersFragment() })

        override fun getFragment(id: Int): Fragment {
            return fragments[id].call()
        }

        private var sharedPreferences =
            context.getSharedPreferences(NAVIGATOR_FILE_NAME, Context.MODE_PRIVATE)

        override fun save(data: Int) = sharedPreferences.edit { putInt(ID_KEY, data) }
        override fun saveBooksScreen() {
            save(BOOKS_SCREEN)
        }

        override fun saveChaptersScreen() {
            save(CHAPTERS_SCREEN)
        }


        override fun nextScreen(navigationCommunication: NavigationCommunication) {
            navigationCommunication.map(read() + 1)
        }

        override fun read() = sharedPreferences.getInt(ID_KEY, DEFAULT_VALUE)

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigator"
            const val ID_KEY = "navigatorId"
            const val DEFAULT_VALUE = 0
            const val BOOKS_SCREEN = 0
            const val CHAPTERS_SCREEN = 1
        }
    }
}

interface FragmentProvider {
    fun getFragment(id: Int): Fragment
}