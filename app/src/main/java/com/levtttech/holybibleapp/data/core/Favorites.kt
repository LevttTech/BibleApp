package com.levtttech.holybibleapp.data.core

interface Favorites {
    fun favorites(limits: Limits): List<Int>
}