package com.levtttech.holybibleapp.presentation.core

import com.levtttech.holybibleapp.presentation.main.NavigationCommunication
import com.github.johnnysc.R.presentation.main.Navigator
import com.levtttech.holybibleapp.sl.core.Feature

interface FeatureNavigation {
    fun init()
    fun showNextScreen()

    class Base(
        private val navigator: Navigator,
        private val navigationCommunication: NavigationCommunication,
        private val feature: Feature,
    ) : FeatureNavigation {
        override fun init() = navigator.save(feature)
        override fun showNextScreen() = navigator.nextScreen(navigationCommunication)
    }
}