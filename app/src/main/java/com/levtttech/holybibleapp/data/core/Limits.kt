package com.levtttech.holybibleapp.data.core

interface Limits : Min, Max

interface Min {
    fun min(): Int
}

interface Max {
    fun max(): Int
}