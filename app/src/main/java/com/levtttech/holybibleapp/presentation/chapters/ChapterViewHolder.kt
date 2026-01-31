package com.levtttech.holybibleapp.presentation.chapters

import android.view.View
import com.levtttech.holybibleapp.core.Retry
import com.levtttech.holybibleapp.core.Show
import com.levtttech.holybibleapp.presentation.core.BaseViewHolder
import com.levtttech.holybibleapp.presentation.core.ClickListener
import com.levtttech.holybibleapp.presentation.core.TextMapper
import com.levtttech.holybibleapp.presentation.core.view.CustomFrameLayout
import com.levtttech.holybibleapp.presentation.core.view.CustomTextView
import com.levtttech.holybibleapp.presentation.core.view.FavoriteView

abstract class ChapterViewHolder(view: View) : BaseViewHolder<ChapterUi>(view) {

    class Base(
        view: View,
        clickListener: ClickListener<ChapterUi>,
        private val favoriteListener: Show<Pair<Int, Int>>,
    ) : BaseViewHolder.Base<ChapterUi>(view, clickListener) {

        override fun map(
            item: ChapterUi,
            background: CustomFrameLayout,
            button: FavoriteView,
            text: CustomTextView,
        ) = with(item) {
            map(background)
            map(button)
            map(text)
        }

        override fun mapFavorite(item: ChapterUi) {
            item.map(ChapterUiMapper.Display(favoriteListener))
        }
    }

    class Error(view: View, retry: Retry) : Fail<ChapterUi>(view, retry) {
        override fun mapErrorMessage(item: ChapterUi, textMapper: TextMapper) =
            item.map(textMapper)
    }
}