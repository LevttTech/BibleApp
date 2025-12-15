package com.levtttech.bibleapp.presentation.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Comparing
import com.levtttech.bibleapp.core.CustomTextView
import com.levtttech.bibleapp.core.TextMapper
import com.levtttech.bibleapp.presentation.books.DiffUtilCallback
import com.levtttech.bibleapp.presentation.books.Retry

abstract class BaseAdapter<VH : BaseViewHolder<T>, T : ComparingMapper<T>> :
    RecyclerView.Adapter<VH>() {

    protected val list = mutableListOf<T>()


    fun update(newList: List<T>) {
        val callback = DiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(callback)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
    protected fun Int.makeView(parent: ViewGroup) = LayoutInflater.from(parent.context).inflate(
        this, parent, false
    )
}

interface ComparingMapper<T : ComparingMapper<T>> : Comparing<T>, Abstract.Object<Unit, TextMapper>

abstract class BaseViewHolder<T : ComparingMapper<T>>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)


    class Fail<T : ComparingMapper<T>>(view: View, private val retry: Retry) :
        BaseViewHolder<T>(view) {
        private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
        private val textView = itemView.findViewById<CustomTextView>(R.id.failScreenTextView)
        override fun bind(data: T) {
            data.map(textView)
            button.setOnClickListener {
                retry.clickButton()
            }
        }
    }

    class FullScreenProgress<T : ComparingMapper<T>>(view: View) : BaseViewHolder<T>(view) {
        override fun bind(data: T) {}
    }
}

