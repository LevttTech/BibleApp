package com.levtttech.bibleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.bibleapp.core.BibleApp
import com.levtttech.bibleapp.presentation.BibleAdapter
import com.levtttech.bibleapp.presentation.Retry

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = (application as BibleApp).mainViewModel
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter(object : Retry {
            override fun clickButton() {
                viewModel.fetchBooks()
            }
        })
        recyclerView.adapter = adapter
        viewModel.observer(this) { books ->
            adapter.update(books)
        }

        viewModel.fetchBooks()
    }
}