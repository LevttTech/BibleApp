package com.levtttech.bibleapp.data.core

interface Fetch<T> {

    suspend fun fetch(): T
}