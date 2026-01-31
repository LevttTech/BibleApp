package com.levtttech.holybibleapp.presentation.books

import android.view.ViewGroup
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.core.BaseAdapter
import com.levtttech.holybibleapp.presentation.core.BaseViewHolder
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.presentation.core.DiffUtilCallback
import java.util.*

class BooksAdapter(
    private val retry: Retry,
    private val collapseListener: ClickListener<BookUi>,
    private val bookListener: ClickListener<BookUi>,
    private val favoriteListener: Show<Int>,
) : BaseAdapter<BookUi, BaseViewHolder<BookUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is BookUi.Base -> 0
        is BookUi.Fail -> 1
        is BookUi.Testament -> 2
        is BookUi.Progress -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> BooksViewHolder.Base(
            R.layout.text_layout.makeView(parent),
            bookListener,
            favoriteListener
        )
        1 -> BooksViewHolder.Error(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BooksViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
        3 -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    override fun diffUtilCallback(
        list: ArrayList<BookUi>,
        data: List<BookUi>,
    ) = BooksDiffUtilCallback(list, data, BookUiMapper.Compare.Base())

    inner class BooksDiffUtilCallback(
        oldList: List<BookUi>,
        newList: List<BookUi>,
        same: BookUiMapper.Compare,
    ) : DiffUtilCallback<BookUi, BookUiMapper.Compare>(oldList, newList, same) {
        override fun same(item: BookUi, same: BookUiMapper.Compare) = item.map(same)
    }
}