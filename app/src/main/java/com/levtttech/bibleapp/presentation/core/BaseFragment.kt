package com.levtttech.bibleapp.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.BibleApp

abstract class BaseFragment<T : ViewModel> : Fragment() {
    protected var recyclerView: RecyclerView? = null //todo viewbinding
    protected lateinit var viewModel: T

    protected abstract fun getViewModelClass(): Class<T>
    protected abstract fun getTitle(): String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            (requireActivity().application as BibleApp).getViewModel(getViewModelClass(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerView)
        requireActivity().title = getTitle()
    }

}