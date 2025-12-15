package com.levtttech.bibleapp.data.chapters.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChapterDao {
    @Insert
    suspend fun saveChapters(chapters: List<ChapterDb>)

    @Query("SELECT * FROM chapters WHERE book_id = :bookId")
    suspend fun fetchChapters(bookId: Int): List<ChapterDb>

}