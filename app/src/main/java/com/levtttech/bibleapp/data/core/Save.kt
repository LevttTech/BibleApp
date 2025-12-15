package com.levtttech.bibleapp.data.core

interface Save<T> {
    suspend fun save(data: T)
}