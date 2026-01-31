package com.levtttech.holybibleapp.presentation.core.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.presentation.core.FavoriteMapper


class CustomFrameLayout : FrameLayout, FavoriteMapper {
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
        val colorId = if (data) R.color.gold else R.color.white
        val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            resources.getColor(colorId, null)
        else
            resources.getColor(colorId)
        setBackgroundColor(color)
    }
}