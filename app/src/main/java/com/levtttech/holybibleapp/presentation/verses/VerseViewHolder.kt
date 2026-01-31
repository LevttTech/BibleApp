package com.levtttech.holybibleapp.presentation.verses

import android.view.View
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.core.BaseViewHolder
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.presentation.core.TextMapper
import com.levtttech.holybibleapp.presentation.core.view.CustomButton
import com.levtttech.holybibleapp.presentation.core.view.CustomFrameLayout
import com.levtttech.holybibleapp.presentation.core.view.CustomTextView
import com.levtttech.holybibleapp.presentation.core.view.FavoriteView


abstract class VerseViewHolder(view: View) : BaseViewHolder<VerseUi>(view) {

    class Base(
        view: View,
        private val favoriteListener: Show<Int>,
        shareClickListener: ClickListener<VerseUi>,
    ) : BaseViewHolder.Base<VerseUi>(view, shareClickListener) {
        private val shareView = itemView.findViewById<View>(R.id.shareLayout)

        override fun clickableView(): View = shareView

        override fun map(
            item: VerseUi, background: CustomFrameLayout, button: FavoriteView, text: CustomTextView
        ) = with(item) {
            map(background)
            map(button)
            map(text)
        }

        override fun mapFavorite(item: VerseUi) {
            item.map(VerseUiMapper.Display(favoriteListener))
        }
    }

    class Next(view: View, private val clickListener: ClickListener<VerseUi>) :
        VerseViewHolder(view) {
        private val nextButton = itemView.findViewById<CustomButton>(R.id.nextButton)
        override fun bind(item: VerseUi) {
            item.map(nextButton)
            nextButton.setOnClickListener { clickListener.click(item) }
        }
    }

    class Error(view: View, retry: Retry) : Fail<VerseUi>(view, retry) {
        override fun mapErrorMessage(item: VerseUi, textMapper: TextMapper) =
            item.map(textMapper)
    }
}