package com.levtttech.bibleapp

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.levtttech.bibleapp.presentation.chapters.NavigationCommunication
import com.levtttech.bibleapp.presentation.core.Navigator

class MainViewModel(
    private val navigator: Navigator,
    private val navigationCommunication: NavigationCommunication,
) : ViewModel() {

    fun init() {
        val screen = navigator.read()
        navigationCommunication.map(screen)
    }

    fun observeScreen(lifecycleOwner: LifecycleOwner, observer: Observer<Int>) {
        navigationCommunication.observe(lifecycleOwner, observer)
    }

    fun navigateBack(): Boolean {
        val id = navigator.read() - 1
        return if (id >= 0) {
            navigator.save(id)
            navigationCommunication.map(id)
            true
        } else {
            false
        }
    }

    fun getFragment(id: Int): Fragment {
        return navigator.getFragment(id)
    }
}

