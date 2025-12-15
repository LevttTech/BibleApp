package com.levtttech.bibleapp.data.books.net

import retrofit2.http.GET

interface BookService {
    @GET("books")
    suspend fun fetchBooks(): List<BookCloud>
}

//https://bible-go-api.rkeplin.com/v1/ base url