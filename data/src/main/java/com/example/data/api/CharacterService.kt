package com.example.data.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterService {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(HttpClient.setupOkHttpClient(LoggingInterceptor.getInterceptor()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    fun getRemoteService() = retrofit.create(CharacterApi::class.java)
}
