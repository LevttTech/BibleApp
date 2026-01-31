package com.levtttech.holybibleapp.data.verses

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.data.books.BookData


interface VersesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain<Triple<List<VerseData>, BookData, Pair<Int, Boolean>>, T>