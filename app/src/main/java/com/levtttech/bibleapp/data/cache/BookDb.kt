package com.levtttech.bibleapp.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.core.Book

@Entity(tableName = "books")
data class BookDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
) : Abstract.Object<Book, BookDbMapper>() {
    override fun map(mapper: BookDbMapper): Book = mapper.map(id, name)
}
