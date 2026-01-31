package com.levtttech.holybibleapp.data.verses.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Multiply

interface VerseToWrapperMapper : Abstract.Mapper {

    fun map(text: String): VerseCloud

    class Base(
        private val bookId: Int,
        private val chapterId: Int,
        private val id: Int,
        private val multiplyTwice: Multiply,
        private val multiply: Multiply,
    ) : VerseToWrapperMapper {
        override fun map(text: String): VerseCloud {
            val finalId = multiplyTwice.map(bookId) + multiply.map(chapterId) + id
            return VerseRuWrapper(finalId, id, text)
        }
    }
}