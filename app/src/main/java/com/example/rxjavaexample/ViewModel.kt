package com.example.rxjavaexample

import com.example.usecases.GetCharacters

class ViewModel(private val getCharacters: GetCharacters) {
    fun getCharacters() = getCharacters.invoke()
}
