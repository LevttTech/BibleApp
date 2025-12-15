package com.levtttech.bibleapp.presentation.books

import androidx.recyclerview.widget.DiffUtil
import com.levtttech.bibleapp.core.Comparing

class DiffUtilCallback<T : Comparing<T>>(
    private val old: List<T>,
    private val new: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ) = old[oldItemPosition].same(new[newItemPosition])

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ) = old[oldItemPosition].sameContent(new[newItemPosition])
}