package com.levtttech.holybibleapp.core

import androidx.annotation.RawRes

interface RawResourceReader {

    fun readText(@RawRes id: Int): String
}