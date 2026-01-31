package com.levtttech.holybibleapp.presentation.books

import android.view.View
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.core.BaseViewHolder
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.presentation.core.TextMapper
import com.levtttech.holybibleapp.presentation.core.view.CollapseView
import com.levtttech.holybibleapp.presentation.core.view.CustomFrameLayout
import com.levtttech.holybibleapp.presentation.core.view.CustomTextView
import com.levtttech.holybibleapp.presentation.core.view.FavoriteView


abstract class BooksViewHolder(view: View) : BaseViewHolder<BookUi>(view) {

    class Base(
        view: View, clickListener: ClickListener<BookUi>, private val favoriteListener: Show<Int>
    ) : BaseViewHolder.Base<BookUi>(view, clickListener) {

        override fun map(
            item: BookUi, background: CustomFrameLayout, button: FavoriteView, text: CustomTextView
        ) = with(item) {
            map(background)
            map(button)
            map(text)
        }

        override fun mapFavorite(item: BookUi) {
            item.map(BookUiMapper.Display(favoriteListener))
        }
    }

    class Testament(
        view: View,
        private val clickListener: ClickListener<BookUi>,
    ) : BooksViewHolder(view) {
        private val collapseView = itemView.findViewById<CollapseView>(R.id.collapseView)
        private val name: CustomTextView = itemView.findViewById(R.id.textView)

        override fun bind(item: BookUi) {
            item.map(name)
            itemView.setOnClickListener { clickListener.click(item) }
            item.map(collapseView)
        }
    }

    class Error(view: View, retry: Retry) : Fail<BookUi>(view, retry) {
        override fun mapErrorMessage(item: BookUi, textMapper: TextMapper) =
            item.map(textMapper)
    }
}