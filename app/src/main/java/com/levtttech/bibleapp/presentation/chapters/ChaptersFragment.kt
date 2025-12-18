package com.levtttech.bibleapp.presentation.chapters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.BibleApp
import com.levtttech.bibleapp.presentation.books.Retry
import com.levtttech.bibleapp.presentation.core.BaseFragment

class ChaptersFragment : BaseFragment<ChaptersViewModel>() {
    override fun getViewModelClass() = ChaptersViewModel::class.java

    override fun getTitle() = viewModel.getBookName()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        val adapter = ChapterAdapter(object : Retry {
            override fun clickButton() {
                viewModel.fetchChapters()
            }
        })
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        recyclerView?.addItemDecoration(divider)
        recyclerView?.adapter = adapter
        viewModel.observeChapters(this) {
            Log.d("ChaptersFragment", it.toString())
            adapter.update(it)
        }
        viewModel.fetchChapters()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ViewModel", "onDestroyView() chapters")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ViewModel", "onDestroy() chapters")
    }
}