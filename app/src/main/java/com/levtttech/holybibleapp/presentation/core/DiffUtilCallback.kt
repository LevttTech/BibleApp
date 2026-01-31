package com.levtttech.holybibleapp.presentation.core

import androidx.recyclerview.widget.DiffUtil
import com.levtttech.holybibleapp.core.Same

abstract class DiffUtilCallback<T, E : Same<T>>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val same: E,
) : DiffUtil.Callback() {

    abstract fun same(item: T, same: E): Boolean

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        same.itemToCompare(newList[newItemPosition])
        return same(oldList[oldItemPosition], same)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        newList[newItemPosition]?.equals(oldList[oldItemPosition]) == true
}