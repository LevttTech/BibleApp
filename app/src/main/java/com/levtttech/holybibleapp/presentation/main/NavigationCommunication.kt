package com.levtttech.holybibleapp.presentation.main

import com.levtttech.holybibleapp.presentation.core.Communication


interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}