package com.levtttech.holybibleapp.presentation.core.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.levtttech.holybibleapp.presentation.core.TextMapper


class CustomButton : AppCompatButton, TextMapper {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion
    override fun map(data: String) {
        text = data
    }
}