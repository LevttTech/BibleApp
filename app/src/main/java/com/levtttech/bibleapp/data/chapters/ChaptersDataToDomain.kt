package com.levtttech.bibleapp.data.chapters

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.chapters.ChaptersDomain

abstract class ChaptersDataToDomain<T> : Abstract.Mapper.DataToDomain.Base<List<ChapterData>, T>()
