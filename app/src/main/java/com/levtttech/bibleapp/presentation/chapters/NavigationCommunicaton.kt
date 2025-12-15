package com.levtttech.bibleapp.presentation.chapters

import com.levtttech.bibleapp.core.Communication

interface NavigationCommunication : Communication<Int>{
    class Base : Communication.Base<Int>(), NavigationCommunication
}