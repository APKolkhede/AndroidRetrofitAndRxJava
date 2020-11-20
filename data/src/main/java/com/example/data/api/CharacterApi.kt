package com.example.data.api

import com.example.domain.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CharacterApi {

    @GET("character")
    fun getCharacters(): Single<Response>
}
