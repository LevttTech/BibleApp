package com.levtttech.bibleapp.data.chapters.net

import retrofit2.http.GET
import retrofit2.http.Path

interface ChapterService {

    @GET("books/{id}/chapters")
    suspend fun fetchChapters(
        @Path("id") id: Int
    ) : List<ChapterCloud>
}