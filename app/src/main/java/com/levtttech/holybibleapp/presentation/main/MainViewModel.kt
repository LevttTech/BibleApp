package com.levtttech.holybibleapp.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.levtttech.holybibleapp.presentation.core.Observe

class MainViewModel(
    private val navigator: MainNavigator,
    private val communication: NavigationCommunication
) : ViewModel() , Observe<Int> {

    fun init(firstOpening: Boolean) {
        if (firstOpening) communication.map(navigator.read())
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val canNavigateBack = navigator.canGoBack()
        if (canNavigateBack) navigator.navigateBack(communication)
        return !canNavigateBack
    }

    fun fragment(id: Int) = navigator.fragment(id)
    fun showLanguagesScreen() = navigator.showLanguagesFragment(communication)
}