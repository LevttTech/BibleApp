package com.levtttech.holybibleapp.presentation.verses

import android.content.Intent
import com.levtttech.holybibleapp.core.Abstract
interface ShareVerse : Abstract.Mapper.Data<Share, Unit> {

    class Base(private val title: String, private val text: String) : ShareVerse {
        override fun map(data: Share) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
            data.share(Intent.createChooser(intent, title))
        }
    }
}

interface Share {
    fun share(data: Intent)
}