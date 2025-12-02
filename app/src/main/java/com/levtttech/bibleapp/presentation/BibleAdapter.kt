package com.levtttech.bibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.bibleapp.R

class BibleAdapter(private val retry: Retry, private val collapse: CollapseListener) :
    RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    fun update(newBooks: List<BookUi>) {
        val callback = DiffUtilCallback(books, newBooks)
        val diff = DiffUtil.calculateDiff(callback)
        books.clear()
        books.addAll(newBooks)
        diff.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (books[position]) {
            is BookUi.Base -> 0
            is BookUi.Fail -> 1
            is BookUi.Testament -> 2
            is BookUi.Progress -> 3
            else -> -1
        }
    }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BibleViewHolder {
        val viewHolder = when (viewType) {
            0 -> Base(R.layout.book_layout.makeView(parent))
            1 -> Fail(R.layout.fail_fullscrean.makeView(parent), retry)
            2 -> Testament(R.layout.testament.makeView(parent), collapse)
            else -> FullscreenProgress(
                R.layout.progress_fullscreen.makeView(parent)
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(
        holder: BibleViewHolder,
        position: Int,
    ) {
        holder.bind(books[position])
    }

    abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(book: BookUi)
        abstract class Info(view: View) : BibleViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)
            override fun bind(book: BookUi) {
                book.map(object : BookUi.Mapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }
    }

    class Base(view: View) : BibleViewHolder.Info(view)

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
        private val image = itemView.findViewById<ImageView>(R.id.imageView)
        override fun bind(book: BookUi) {
            super.bind(book)
            itemView.setOnClickListener {
                book.collapseOrExpand(collapse)
            }

            book.showCollapsed(object : BookUi.CollapseMapper {
                override fun show(collapsed: Boolean) {
                    val iconId = if (collapsed) {
                        R.drawable.arrow_drop_down_24px
                    } else {
                        R.drawable.arrow_drop_up_24px

                    }
                    image.setImageResource(iconId)
                }
            })
        }
    }
}


interface Retry {
    fun clickButton()
}

interface CollapseListener {
    fun collapse(id: Int)
}

private fun Int.makeView(parent: ViewGroup) = LayoutInflater.from(parent.context).inflate(
    this, parent, false
)