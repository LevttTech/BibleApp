package com.levtttech.bibleapp.servicelocator.chapters

import com.levtttech.bibleapp.presentation.chapters.ChaptersViewModel
import com.levtttech.bibleapp.servicelocator.core.BaseFactory
import com.levtttech.bibleapp.servicelocator.core.BaseModule

class ChaptersViewModelFactory(module: BaseModule<ChaptersViewModel>) :
    BaseFactory<ChaptersViewModel>(
        module
    )