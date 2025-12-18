package com.levtttech.bibleapp.servicelocator.books

import androidx.lifecycle.ViewModel
import com.levtttech.bibleapp.presentation.books.BooksViewModel
import com.levtttech.bibleapp.servicelocator.core.BaseFactory
import com.levtttech.bibleapp.servicelocator.core.BaseModule

class BooksViewModelFactory(module: BaseModule<BooksViewModel>) :
    BaseFactory<BooksViewModel>(module)
