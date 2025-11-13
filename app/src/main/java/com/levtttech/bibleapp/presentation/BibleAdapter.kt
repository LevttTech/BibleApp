package com.levtttech.bibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.Book

class BibleAdapter : RecyclerView.Adapter<BibleAdapter.BibbleViewHolder>() {

    private val books = ArrayList<Book>()

    fun update(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BibbleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.book_layout,
            parent,
            false
        )
        return BibbleViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BibbleViewHolder,
        position: Int,
    ) {
        holder.bind(books[position])
    }

    inner class BibbleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(book: Book) {

        }
    }
}