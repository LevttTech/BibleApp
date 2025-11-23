package com.levtttech.bibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.bibleapp.R

class BibleAdapter(private val retry: Retry) : RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    fun update(newBooks: List<BookUi>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (books[position]) {
            is BookUi.Base -> 0
            is BookUi.Fail -> 1
            is BookUi.Progress -> 2
        }
    }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BibleViewHolder {
        val viewHolder = when (viewType) {
            0 -> BibleViewHolder.Base(R.layout.book_layout.makeView(parent))
            1 -> BibleViewHolder.Fail(R.layout.fail_fullscrean.makeView(parent), retry)
            else -> BibleViewHolder.FullscreenProgress(
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


        class Base(view: View) : BibleViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)
            override fun bind(book: BookUi) {
                book.map(object : BookUi.Mapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }

        }

        class FullscreenProgress(view: View) : BibleViewHolder(view) {
            override fun bind(book: BookUi) {}
        }

        class Fail(view: View, private val retry: Retry) : BibleViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.failScreenTextView)
            private val buttonRetry = itemView.findViewById<Button>(R.id.tryAgainButton).apply {
                setOnClickListener { retry.clickButton() }
            }
            override fun bind(book: BookUi) {
                book.map(object : BookUi.Mapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                })
            }
        }
    }
}

interface Retry {
    fun clickButton()
}

private fun Int.makeView(parent: ViewGroup) = LayoutInflater.from(parent.context).inflate(
    this, parent, false
)