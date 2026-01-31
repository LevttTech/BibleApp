package com.levtttech.holybibleapp.data.chapters

import com.levtttech.holybibleapp.core.Abstract


interface ChapterDataToDomainMapper<T> : Abstract.Mapper.Data<Pair<ChapterId, Boolean>, T>