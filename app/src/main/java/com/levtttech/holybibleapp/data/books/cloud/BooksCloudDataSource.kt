package com.levtttech.holybibleapp.data.books.cloud

import com.levtttech.holybibleapp.core.ChosenLanguage
import com.levtttech.holybibleapp.core.RawResourceReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.levtttech.bibleapp.R


interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    abstract class Abstract(private val gson: Gson, private val typeToken: TypeToken<*>) :
        BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud.Base> =
            gson.fromJson(getDataAsString(), typeToken.type)

        protected abstract suspend fun getDataAsString(): String
    }

    class Base(
        private val languages: ChosenLanguage,
        private val englishCloudDataSource: BooksCloudDataSource,
        private val russianDataSource: BooksCloudDataSource,
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks() = (
                if (languages.isChosenRussian())
                    russianDataSource
                else
                    englishCloudDataSource
                ).fetchBooks()
    }

    class Russian(private val booksRu: () -> List<BookRu>) : BooksCloudDataSource {
        override suspend fun fetchBooks() = booksRu()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    class English(private val service: BooksService, gson: Gson, typeToken: TypeToken<*>) :
        Abstract(gson, typeToken) {
        override suspend fun getDataAsString() = service.fetchBooks().string()
    }

    class Mock(
        private val rawResourceReader: RawResourceReader, gson: Gson, typeToken: TypeToken<*>
    ) : Abstract(gson, typeToken) {

        override suspend fun getDataAsString() =
            rawResourceReader.readText(R.raw.books_successful_response)
    }
}

class BooksTypeToken : TypeToken<List<BookCloud.Base>>()