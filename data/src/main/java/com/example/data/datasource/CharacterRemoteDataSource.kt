package com.example.data.datasource

import com.example.data.api.CharacterService

class CharacterRemoteDataSource : CharacterDataSource {
    override fun getCharacter() = CharacterService.getRemoteService().getCharacters()
}
