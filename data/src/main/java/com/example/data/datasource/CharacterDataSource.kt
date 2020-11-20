package com.example.data.datasource

import com.example.domain.Response
import io.reactivex.rxjava3.core.Single

interface CharacterDataSource {
    fun getCharacter(): Single<Response>
}
