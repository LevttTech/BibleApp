package com.levtttech.holybibleapp.sl.core

import com.levtttech.holybibleapp.data.books.cloud.BookRu
import com.levtttech.holybibleapp.data.books.cloud.RussianTranslation
import com.google.gson.reflect.TypeToken
import com.levtttech.bibleapp.R

class RussianBooksContainer(
    private val coreModule: CoreModule, private val typeToken: TypeToken<*>,
) : BooksRuProvider {
    override fun booksRu(): List<BookRu> {
        val text = coreModule.resourceProvider.readText(R.raw.synodal)
        val response = coreModule.gson.fromJson<RussianTranslation>(text, typeToken.type)
        return response.contentAsList();
    }
}

class RussianBooksTypeToken : TypeToken<RussianTranslation>()

interface BooksRuProvider {
    fun booksRu(): List<BookRu>
}

interface ClearRussianBooks {
    fun clearBooksRu()
}