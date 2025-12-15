package com.levtttech.bibleapp.core

interface Comparing<T> {
    fun same(data: T): Boolean = false
    fun sameContent(data: T): Boolean = false
}