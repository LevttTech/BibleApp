package com.levtttech.holybibleapp.presentation.books

import android.os.Bundle
import android.view.View
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.main.BaseFragment

class BooksFragment : BaseFragment<BooksViewModel>() {

    override fun viewModelClass() = BooksViewModel::class.java
    override fun showBack() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BooksAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetch()
            },
            object : ClickListener<BookUi> {
                override fun click(item: BookUi) = viewModel.collapseOrExpand(item)
            },
            object : ClickListener<BookUi> {
                override fun click(item: BookUi) = item.map(BookUiMapper.Display(viewModel))
            },
            object : Show<Int> {
                override fun open(id: Int) = viewModel.changeFavorite(id)
            })
        setAdapter(adapter)
        viewModel.observe(this, {
            it.map(adapter)
            scrollTo()
        })
        viewModel.init()
    }

    override fun onPause() {
        viewModel.save(Unit)
        super.onPause()
    }
}