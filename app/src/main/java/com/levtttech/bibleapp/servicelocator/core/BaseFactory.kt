package com.levtttech.bibleapp.servicelocator.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFactory<VM : ViewModel>(private val module: BaseModule<VM>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = module.viewModel() as T
}