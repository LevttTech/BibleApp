package com.levtttech.holybibleapp.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.BibleApp
import com.levtttech.holybibleapp.presentation.core.TextMapper
import com.levtttech.holybibleapp.presentation.verses.Share

abstract class BaseActivity : AppCompatActivity(), TextMapper, Share {

    fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as BibleApp).viewModel(model, owner)

    override fun map(data: String) {
        title = data
    }

    override fun share(data: Intent) = startActivity(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}