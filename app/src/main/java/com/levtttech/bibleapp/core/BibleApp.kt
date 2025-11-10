package com.levtttech.bibleapp.core

import android.app.Application
import com.levtttech.bibleapp.data.net.BookService
import retrofit2.Retrofit

class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).build()

        retrofit.create(BookService::class.java)
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}