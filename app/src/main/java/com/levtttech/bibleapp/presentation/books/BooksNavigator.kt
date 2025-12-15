package com.levtttech.bibleapp.presentation.books

import com.levtttech.bibleapp.presentation.chapters.NavigationCommunication
import com.levtttech.bibleapp.presentation.core.NavigationForward

interface BooksNavigator : NavigationForward  {
    fun saveBooksScreen()
}