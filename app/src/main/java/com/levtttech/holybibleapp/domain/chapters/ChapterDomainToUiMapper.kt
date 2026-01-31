package com.levtttech.holybibleapp.domain.chapters

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.chapters.ChapterId

interface ChapterDomainToUiMapper<T> : Abstract.Mapper.Data<Pair<ChapterId, Boolean>, T>