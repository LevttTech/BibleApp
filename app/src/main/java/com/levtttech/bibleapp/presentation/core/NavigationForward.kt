package com.levtttech.bibleapp.presentation.core

import com.levtttech.bibleapp.presentation.chapters.NavigationCommunication

interface NavigationForward {
    fun nextScreen(navigationCommunication: NavigationCommunication)
}