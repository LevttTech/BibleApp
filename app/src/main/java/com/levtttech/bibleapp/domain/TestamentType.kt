package com.levtttech.bibleapp.domain

enum class TestamentType(private val id: Int) {
    OLD(Int.MIN_VALUE), NEW(Int.MAX_VALUE);

    fun getId() = id
}