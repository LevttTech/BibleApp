package com.levtttech.holybibleapp.data.verses.cache

import com.levtttech.holybibleapp.core.Multiply
import com.levtttech.holybibleapp.data.core.Limits

class VersesLimits(
    private val bookId: Int,
    private val chapterId: Int,
    private val million: Multiply,
    private val thousand: Multiply,
) : Limits {

    override fun min() = million.map(bookId) + thousand.map(chapterId)
    override fun max() = million.map(bookId) + thousand.map(chapterId + 1)
}