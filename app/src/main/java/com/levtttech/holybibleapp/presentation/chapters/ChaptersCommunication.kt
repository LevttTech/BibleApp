package com.levtttech.holybibleapp.presentation.chapters

import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.presentation.core.Communication


interface ChaptersCommunication : Communication<ChaptersUi>, ChangeFavorite<Int> {
    class Base : Communication.Base.Favorites<ChaptersUi>(), ChaptersCommunication
}