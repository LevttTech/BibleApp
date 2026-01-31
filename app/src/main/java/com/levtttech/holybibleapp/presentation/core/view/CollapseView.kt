package com.levtttech.holybibleapp.presentation.core.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.presentation.core.CollapseMapper

class CollapseView : AppCompatImageView, CollapseMapper {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion
    override fun map(data: Boolean) {
        val iconId: Int = if (data) R.drawable.ic_expand_more_24 else R.drawable.ic_expand_less_24
        setImageResource(iconId)
    }
}