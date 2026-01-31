package com.levtttech.holybibleapp.presentation.deeplink

import android.os.Bundle
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.presentation.main.BaseActivity
class DeeplinkActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = viewModel(DeeplinkViewModel::class.java, this)
        viewModel.observe(this, {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DeeplinkVerseFragment())
                .commit()
        })
        viewModel.init(intent)
    }
}