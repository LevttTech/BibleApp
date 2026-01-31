package com.levtttech.holybibleapp.data.chapters

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.books.BookData

interface ChaptersDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain<Pair<List<ChapterData>, BookData>, T>