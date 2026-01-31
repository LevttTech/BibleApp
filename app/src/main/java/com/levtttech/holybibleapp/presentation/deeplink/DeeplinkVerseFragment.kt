package com.levtttech.holybibleapp.presentation.deeplink

import com.levtttech.holybibleapp.presentation.verses.VersesFragment

class DeeplinkVerseFragment : VersesFragment<DeeplinkVersesViewModel>() {
    override fun viewModelClass() = DeeplinkVersesViewModel::class.java
    override fun showBack() = false
}