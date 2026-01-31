package com.levtttech.holybibleapp.sl.core

import androidx.lifecycle.ViewModel

interface BaseModule<T : ViewModel> {

    fun viewModel(): T
}