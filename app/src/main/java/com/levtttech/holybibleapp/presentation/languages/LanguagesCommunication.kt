package com.levtttech.holybibleapp.presentation.languages

import com.levtttech.holybibleapp.presentation.core.Communication

interface LanguagesCommunication : Communication<LanguagesUi> {
    class Base : Communication.Base<LanguagesUi>(), LanguagesCommunication
}