package com.levtttech.holybibleapp.presentation.main

import com.levtttech.holybibleapp.core.Read

interface MainNavigator : Read<Int>, NavigateBack {

    fun fragment(id: Int): BaseFragment<*>
    fun showLanguagesFragment(communication: NavigationCommunication)
}

interface NavigateBack {
    fun canGoBack(): Boolean
    fun navigateBack(navigationCommunication: NavigationCommunication)
}