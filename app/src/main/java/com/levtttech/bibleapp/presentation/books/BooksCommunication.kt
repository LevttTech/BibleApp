package com.levtttech.bibleapp.presentation.books

import com.levtttech.bibleapp.core.Communication

interface
BooksCommunication : Communication<List<BookUi>> {
    class Base : Communication.Base<List<BookUi>>(), BooksCommunication
}



