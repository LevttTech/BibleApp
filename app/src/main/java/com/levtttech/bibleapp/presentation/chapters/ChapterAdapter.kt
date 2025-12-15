package com.levtttech.bibleapp.presentation.chapters

import android.view.View
import android.view.ViewGroup
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.CustomTextView
import com.levtttech.bibleapp.presentation.books.Retry
import com.levtttech.bibleapp.presentation.core.BaseAdapter
import com.levtttech.bibleapp.presentation.core.BaseViewHolder

class ChapterAdapter(private val retry: Retry) :
    BaseAdapter<BaseViewHolder<ChapterUi>, ChapterUi>() {
    override fun getItemViewType(position: Int): Int {
        val viewType = when (list[position]) {
            is ChapterUi.Base -> 0
            is ChapterUi.Fail -> 1
            else -> 2
        }
        return viewType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ChapterUi> {
        val viewHodler = when (viewType) {
            0 -> ChapterViewHolder.Base(R.layout.chapter.makeView(parent))
            1 -> BaseViewHolder.Fail<ChapterUi>(R.layout.fail_fullscrean.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress<ChapterUi>(
                R.layout.progress_fullscreen.makeView(
                    parent
                )
            )
        }
        return viewHodler
    }


    abstract class ChapterViewHolder(view: View) : BaseViewHolder<ChapterUi>(view) {

        class Base(view: View) : ChapterViewHolder(view) {
            private val tv = itemView.findViewById<CustomTextView>(R.id.chapterTextView)
            override fun bind(data: ChapterUi) {
                data.map(tv)
            }
        }
    }
}