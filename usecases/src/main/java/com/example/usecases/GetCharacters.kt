package com.example.usecases

import com.example.data.CharacterRepository
import com.example.domain.Character
import com.example.domain.Response
import io.reactivex.rxjava3.core.Single

class GetCharacters(private val characterRepository: CharacterRepository) {

    fun invoke(): Single<List<Character>> =
        characterRepository.getCharacter().flatMap { response: Response ->
            Single.just(response.results)
        }
}
