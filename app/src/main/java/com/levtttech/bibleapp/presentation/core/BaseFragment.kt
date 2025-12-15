package com.levtttech.bibleapp.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.bibleapp.R

abstract class BaseFragment : Fragment() {
    protected var recyclerView: RecyclerView? = null //todo viewbinding

    protected abstract fun getTitle(): String
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