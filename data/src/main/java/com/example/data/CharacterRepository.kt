package com.example.data

import com.example.data.datasource.CharacterDataSource
import com.example.domain.Response
import io.reactivex.rxjava3.core.Single

class CharacterRepository(private val dataSource: CharacterDataSource) : CharacterDataSource {
    override fun getCharacter(): Single<Response> = dataSource.getCharacter()
}
