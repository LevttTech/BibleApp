package com.levtttech.holybibleapp.presentation.chapters

import android.os.Bundle
import android.view.View
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.main.BaseFragment

class ChaptersFragment : BaseFragment<ChaptersViewModel>() {

    override fun viewModelClass() = ChaptersViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetch()
            },
            object : ClickListener<ChapterUi> {
                override fun click(item: ChapterUi) = item.map(ChapterUiMapper.Display(viewModel))
            },
            object : Show<Pair<Int, Int>> {
                override fun open(id: Pair<Int, Int>) = viewModel.changeFavorite(id)
            }
        )
        viewModel.observe(this, { ui ->
            ui.map(adapter, title())
            scrollTo()
        })
        setAdapter(adapter)

        viewModel.init()
    }
}