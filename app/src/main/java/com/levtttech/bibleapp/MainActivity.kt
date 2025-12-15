package com.levtttech.bibleapp

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.levtttech.bibleapp.core.BibleApp

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = (application as BibleApp).mainViewModel
        setContentView(R.layout.activity_main)
        viewModel.init()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.observeScreen(this) { screen ->
            val fragment = viewModel.getFragment(screen)

            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        onBackPressedDispatcher.addCallback(this) {
            if (!viewModel.navigateBack()) finish()
        }

        return super.getOnBackInvokedDispatcher()
    }
}