package com.levtttech.holybibleapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.levtttech.bibleapp.BuildConfig.USE_MOCKS

import com.levtttech.holybibleapp.sl.core.CoreModule
import com.levtttech.holybibleapp.sl.core.DependencyContainer
import com.levtttech.holybibleapp.sl.core.ViewModelsFactory


class BibleApp : Application() {

    private val coreModule = CoreModule(USE_MOCKS)

    private val factory by lazy {
        ViewModelsFactory(DependencyContainer.Base(coreModule, USE_MOCKS))
    }

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}