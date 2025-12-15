package com.levtttech.bibleapp.data.books.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    suspend fun fetchBooks(): List<BookDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBooks(books: List<BookDb>)
}