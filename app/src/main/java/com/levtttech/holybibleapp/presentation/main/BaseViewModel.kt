package com.levtttech.holybibleapp.presentation.main

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.ChangeFavorite
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.presentation.core.Communication
import com.levtttech.holybibleapp.presentation.core.Observe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<E : Communication<T>, T>(
    private val resourceProvider: ResourceProvider,
    protected val communication: E,
) : ViewModel(), ScrollPositionUi, Observe<T> {

    abstract fun fetch()

    fun title(): String = resourceProvider.string(titleResId())

    @StringRes
    open fun titleResId(): Int = R.string.loading

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
        communication.observe(owner, observer)

    protected fun changeFavorite(
        id: Int, communication: ChangeFavorite<Int>, vararg changeFavorite: ChangeFavorite<Int>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            changeFavorite.forEach { it.changeFavorite(id) }
            withContext(Dispatchers.Main) { communication.changeFavorite(id) }
        }
    }
}

interface ScrollPositionUi {

    fun saveScrollPosition(position: Int) = Unit
    fun scrollPosition(): Int = 0
}