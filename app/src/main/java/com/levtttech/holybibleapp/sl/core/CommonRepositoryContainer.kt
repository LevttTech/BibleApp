package com.levtttech.holybibleapp.sl.core

import com.levtttech.holybibleapp.data.books.cloud.BookRu
import com.levtttech.holybibleapp.domain.books.BooksRepository
import com.levtttech.holybibleapp.domain.chapters.ChaptersRepository
import com.levtttech.holybibleapp.sl.books.BooksRepositoryProvider
import com.levtttech.holybibleapp.sl.books.ClearBooks
import com.levtttech.holybibleapp.sl.chapters.ChaptersRepositoryProvider
import com.levtttech.holybibleapp.sl.chapters.ClearChapters

class CommonRepositoryContainer(
    private val books: (() -> List<BookRu>) -> BooksRepository,
    private val chapters: (() -> List<BookRu>) -> ChaptersRepository,
    private val russianBooks: () -> List<BookRu>
) : BooksRepositoryProvider, ChaptersRepositoryProvider, ClearBooks, ClearChapters,
    BooksRuProvider, ClearRussianBooks {

    private var booksRepository: BooksRepository? = null
    private var chaptersRepository: ChaptersRepository? = null
    private val booksRussian: ArrayList<BookRu> = ArrayList()

    override fun booksRepository(): BooksRepository {
        if (booksRepository == null)
            booksRepository = books { booksRu() }
        return booksRepository!!
    }

    override fun clearBooksRepository() {
        booksRepository = null
    }

    override fun chaptersRepository(): ChaptersRepository {
        if (chaptersRepository == null)
            chaptersRepository = chapters { booksRu() }
        return chaptersRepository!!
    }

    override fun clearChaptersRepository() {
        chaptersRepository = null
    }

    override fun booksRu(): List<BookRu> {
        if (booksRussian.isEmpty())
            booksRussian.addAll(russianBooks())
        return booksRussian
    }

    override fun clearBooksRu() {
        booksRussian.clear()
    }
}