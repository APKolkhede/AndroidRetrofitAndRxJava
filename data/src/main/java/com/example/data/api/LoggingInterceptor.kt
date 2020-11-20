package com.example.data.api

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {
    fun getInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
