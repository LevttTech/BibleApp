package com.levtttech.holybibleapp.presentation.verses

import android.view.ViewGroup
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.core.BaseAdapter
import com.levtttech.holybibleapp.presentation.core.BaseViewHolder
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.presentation.core.DiffUtilCallback


class VersesAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<VerseUi>,
    private val favoriteListener: Show<Int>,
    private val shareClickListener: ClickListener<VerseUi>
) : BaseAdapter<VerseUi, BaseViewHolder<VerseUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is VerseUi.Base -> 0
        is VerseUi.Fail -> 1
        is VerseUi.Progress -> 2
        is VerseUi.Next -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> VerseViewHolder.Base(
            (if (itemCount == 1) R.layout.single_verse_layout else R.layout.verse_layout)
                .makeView(parent),
            favoriteListener,
            shareClickListener
        )
        1 -> VerseViewHolder.Error(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        3 -> VerseViewHolder.Next(R.layout.next_layout.makeView(parent), clickListener)
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    override fun diffUtilCallback(
        list: ArrayList<VerseUi>,
        data: List<VerseUi>
    ) = VersesDiffUtilCallback(list, data, VerseUiMapper.Compare.Base())

    inner class VersesDiffUtilCallback(
        oldList: List<VerseUi>,
        newList: List<VerseUi>,
        same: VerseUiMapper.Compare
    ) : DiffUtilCallback<VerseUi, VerseUiMapper.Compare>(oldList, newList, same) {
        override fun same(item: VerseUi, same: VerseUiMapper.Compare) = item.map(same)
    }
}