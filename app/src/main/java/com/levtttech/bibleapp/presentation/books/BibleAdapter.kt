package com.levtttech.bibleapp.presentation.books

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.CustomCollapseView
import com.levtttech.bibleapp.core.CustomTextView
import com.levtttech.bibleapp.presentation.core.BaseAdapter
import com.levtttech.bibleapp.presentation.core.BaseViewHolder

class BibleAdapter(private val retry: Retry, private val collapse: CollapseListener,
    private val click: ClickListener) :
    BaseAdapter<BibleAdapter.BibleViewHolder, BookUi>() {

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is BookUi.Base -> 0
            is BookUi.Fail -> 1
            is BookUi.Testament -> 2
            is BookUi.Progress -> 3
            else -> -1
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BibleViewHolder {
        val viewHolder = when (viewType) {
            0 -> Base(R.layout.book_layout.makeView(parent), click)
            1 -> Fail(R.layout.fail_fullscrean.makeView(parent), retry)
            2 -> Testament(R.layout.testament.makeView(parent), collapse)
            else -> FullscreenProgress(
                R.layout.progress_fullscreen.makeView(parent)
            )
        }
        return viewHolder
    }

    abstract class BibleViewHolder(view: View) : BaseViewHolder<BookUi>(view) {
        abstract class Info(view: View) : BibleViewHolder(view) {
            private val name = itemView.findViewById<CustomTextView>(R.id.textView)
            override fun bind(book: BookUi) {
                book.map(name)
            }
        }
    }

    class Base(view: View, private val click: ClickListener) : BibleViewHolder.Info(view) {
        private val button = itemView.findViewById<CustomTextView>(R.id.textView)

        override fun bind(book: BookUi) {
            super.bind(book)
            button.setOnClickListener { book.open(click) }
        }
    }

    class Fail(view: View, private val retry: Retry) : BibleViewHolder.Info(view) {
        private val buttonRetry = itemView.findViewById<Button>(R.id.tryAgainButton).apply {
            setOnClickListener { retry.clickButton() }
        }
    }

    class FullscreenProgress(view: View) : BibleViewHolder(view) {
        override fun bind(book: BookUi) {}
    }

    class Testament(view: View, private val collapse: CollapseListener) :
        BibleViewHolder.Info(view) {
        private val image = itemView.findViewById<CustomCollapseView>(R.id.imageView)
        override fun bind(book: BookUi) {
            super.bind(book)
            itemView.setOnClickListener {
                book.collapseOrExpand(collapse)
            }

            book.showCollapsed(image)
        }
    }
}


interface Retry {
    fun clickButton()
}

interface CollapseListener {
    fun collapse(id: Int)
}

interface ClickListener {
    fun click(id: Int, name: String)
}