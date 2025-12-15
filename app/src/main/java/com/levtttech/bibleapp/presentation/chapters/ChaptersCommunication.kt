package com.levtttech.bibleapp.presentation.chapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.levtttech.bibleapp.core.Communication

interface ChaptersCommunication : Communication<List<ChapterUi>> {
    class Base : Communication.Base<List<ChapterUi>>(), ChaptersCommunication
}


