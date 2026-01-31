package com.levtttech.holybibleapp.presentation.languages

import com.levtttech.holybibleapp.core.Read
import com.levtttech.holybibleapp.presentation.core.SaveScreen
import com.levtttech.holybibleapp.presentation.main.NavigateBack
import com.levtttech.holybibleapp.presentation.main.NavigateForward

interface LanguagesNavigator : NavigateForward, Read<Int>, SaveScreen, NavigateBack {

    fun nextFromLanguages(): Int
}