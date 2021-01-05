package com.example.dicodingsub2.util

import android.net.Uri

class Constants {
    companion object{
        const val BASE_URL = "https://api.github.com"
        const val SEARCH_NEWS_TIME_DELAY = 500L
        const val SCHEME = "content"
        const val AUTHORITY = "com.example.dicodingsub2"
        const val TABLE_NAME = "items"

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()
    }
}