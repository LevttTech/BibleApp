package com.levtttech.bibleapp.core

interface Fetch<T> {
    suspend fun fetch(): T
}