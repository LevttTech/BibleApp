package com.levtttech.bibleapp.presentation.books

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import java.io.BufferedReader

interface ResourceProvider {

    fun getString(@StringRes id: Int): String
    fun getRawResource(@RawRes id: Int): String

    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int): String {
            return context.getString(id)
        }

        override fun getRawResource(id: Int): String {
            return context.resources.openRawResource(id).bufferedReader().use(BufferedReader::readText)
        }
    }
}