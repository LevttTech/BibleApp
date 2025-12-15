package com.levtttech.bibleapp.data.books.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.ToBookDataMapper

@Entity(tableName = "books")
data class BookDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "testament") val testament: String
) : Abstract.Object<BookData, ToBookDataMapper> {
    override fun map(mapper: ToBookDataMapper): BookData = mapper.map(id, name, testament)
}
