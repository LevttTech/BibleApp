package com.levtttech.holybibleapp.sl.chapters

import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository

interface ChaptersRepositoryProvider {

    fun chaptersRepository(): ChaptersRepository
}