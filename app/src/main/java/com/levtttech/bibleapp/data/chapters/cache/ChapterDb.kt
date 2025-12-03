package com.levtttech.bibleapp.data.chapters.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters")
data class ChapterDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("book_id") val bookId: Int,
    val chapter: Int
)
