package com.levtttech.bibleapp.data.chapters.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChapterDao {


    @Insert
    fun saveChapters(chapters: List<ChapterDb>)

    @Query("SELECT * FROM chapters")
    fun fetchChapters(): List<ChapterDb>

}