package com.levtttech.holybibleapp.presentation.verses

import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.presentation.core.Communication
import com.levtttech.holybibleapp.presentation.core.ListMapper
import com.levtttech.holybibleapp.presentation.core.TextMapper

interface VersesCommunication : Communication<VersesUi>, ChangeFavorite<Int> {
    fun title(textMapper: TextMapper)
    class Base : Communication.Base.Favorites<VersesUi>(), VersesCommunication {

        override fun title(textMapper: TextMapper) {
            liveData.value?.map(ListMapper.Empty(), textMapper)
        }
    }
}