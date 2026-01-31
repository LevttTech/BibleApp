package com.levtttech.holybibleapp.presentation.chapters

import android.view.ViewGroup
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.core.BaseAdapter
import com.levtttech.holybibleapp.presentation.core.BaseViewHolder
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.presentation.core.DiffUtilCallback
class ChaptersAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<ChapterUi>,
    private val favoriteListener: Show<Pair<Int, Int>>,
) : BaseAdapter<ChapterUi, BaseViewHolder<ChapterUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is ChapterUi.Base -> 0
        is ChapterUi.Fail -> 1
        is ChapterUi.Progress -> 2
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> ChapterViewHolder.Base(
            R.layout.text_layout.makeView(parent),
            clickListener,
            favoriteListener
        )
        1 -> ChapterViewHolder.Error(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    override fun diffUtilCallback(
        list: ArrayList<ChapterUi>,
        data: List<ChapterUi>,
    ) = ChapterDiffUtilCallback(list, data, ChapterUiMapper.Compare.Base())

    inner class ChapterDiffUtilCallback(
        oldList: List<ChapterUi>,
        newList: List<ChapterUi>,
        same: ChapterUiMapper.Compare,
    ) : DiffUtilCallback<ChapterUi, ChapterUiMapper.Compare>(oldList, newList, same) {
        override fun same(item: ChapterUi, same: ChapterUiMapper.Compare) = item.map(same)
    }
}