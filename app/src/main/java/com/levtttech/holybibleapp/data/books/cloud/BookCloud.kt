package com.levtttech.holybibleapp.data.books.cloud

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.data.books.ToBookMapper
import com.google.gson.annotations.SerializedName

interface BookCloud : Abstract.CloudObject, Matcher<Int> {
    fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("name")
        private val name: String,
        @SerializedName("testament")
        private val testament: String
    ) : BookCloud {
        override fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean) =
            mapper.map(id, name, testament, isFavorite)

        override fun matches(arg: Int) = arg == id
    }
}