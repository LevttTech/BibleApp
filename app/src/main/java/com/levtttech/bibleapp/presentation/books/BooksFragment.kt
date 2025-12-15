package com.levtttech.bibleapp.presentation.books

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.BibleApp
import com.levtttech.bibleapp.presentation.core.BaseFragment

class BooksFragment : BaseFragment() {
    override fun getTitle() = getString(R.string.app_name)

    private lateinit var viewModel: BooksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ((requireActivity().application) as BibleApp).booksViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BibleAdapter(object : Retry {
            override fun clickButton() {
                viewModel.fetchBooks()
            }
        }, object : CollapseListener {
            override fun collapse(id: Int) {
                viewModel.collapseOrExpand(id)
            }
        }, object : ClickListener {
            override fun click(id: Int, name: String) {
                viewModel.show(id, name)
            }
        })
        recyclerView?.adapter = adapter
        viewModel.observer(this) { books ->
            adapter.update(books)
        }
        viewModel.init()
    }

    override fun onPause() {
        viewModel.saveCollapsedState()
        super.onPause()
    }
}