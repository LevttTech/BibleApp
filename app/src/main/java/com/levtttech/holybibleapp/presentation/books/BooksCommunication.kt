package com.levtttech.holybibleapp.presentation.books

import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.presentation.core.Communication

interface BooksCommunication : Communication<BooksUi>, ChangeFavorite<Int> {
    class Base : Communication.Base.Favorites<BooksUi>(), BooksCommunication
}