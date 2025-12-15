package com.levtttech.bibleapp.core

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.levtttech.bibleapp.R
import com.levtttech.bibleapp.presentation.books.BookUi

class CustomTextView : AppCompatTextView, TextMapper {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(data: String) {
        text = data
    }

}


class CustomCollapseView : AppCompatImageView, BookUi.CollapseMapper {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun show(collapsed: Boolean) {
        val iconId = if (collapsed) {
            R.drawable.arrow_drop_down_24px
        } else {
            R.drawable.arrow_drop_up_24px

        }
        setImageResource(iconId)
    }

}

class CustomProgressBar : FrameLayout, TextMapper {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(data: String) {

    }
}

interface TextMapper : Abstract.Mapper.Data<String, Unit> {

}

