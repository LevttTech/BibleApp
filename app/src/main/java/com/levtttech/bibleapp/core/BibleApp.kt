package com.levtttech.bibleapp.core

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.levtttech.bibleapp.servicelocator.books.BooksModule
import com.levtttech.bibleapp.servicelocator.chapters.ChaptersModule
import com.levtttech.bibleapp.servicelocator.core.CoreModule
import com.levtttech.bibleapp.servicelocator.core.ViewModelsFactory

class BibleApp : Application() {

    var useMocks = true
    lateinit var coreModule: CoreModule

    override fun onCreate() {
        super.onCreate()
        coreModule = CoreModule(applicationContext)
        coreModule.init()
    }

    private val factory by lazy {
        ViewModelsFactory(
            coreModule, BooksModule(useMocks, coreModule), ChaptersModule(useMocks,coreModule)
        )
    }

    fun <T: ViewModel> getViewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T {
        return ViewModelProvider(owner,factory).get(modelClass) as T
    }
}
