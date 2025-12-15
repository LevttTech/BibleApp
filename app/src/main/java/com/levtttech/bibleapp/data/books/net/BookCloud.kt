package com.levtttech.bibleapp.data.books.net

import com.google.gson.annotations.SerializedName
import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.data.books.BookData
import com.levtttech.bibleapp.data.books.ToBookDataMapper

//{"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}
data class BookCloud(
    @SerializedName("id") private val id: Int,
    @SerializedName("name") private val name: String,
    @SerializedName("testament") private val testament: String
) : Abstract.Object<BookData, ToBookDataMapper> {
    override fun map(mapper: ToBookDataMapper): BookData = mapper.map(id, name, testament)
}