package com.levtttech.bibleapp.data.cache

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    suspend fun fetchBooks(): List<BookDb>
}