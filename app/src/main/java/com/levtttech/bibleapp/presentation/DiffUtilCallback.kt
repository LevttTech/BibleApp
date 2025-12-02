package com.levtttech.bibleapp.presentation

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val old: List<BookUi>,
    private val new: List<BookUi>
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