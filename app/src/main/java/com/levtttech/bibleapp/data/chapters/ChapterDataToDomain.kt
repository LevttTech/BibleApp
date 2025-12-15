package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.chapters.ChapterDomain

interface ChapterDataToDomain : Abstract.Mapper {
    fun map(id: Int): ChapterDomain
}