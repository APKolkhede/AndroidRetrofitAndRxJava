package com.example.domain

sealed class State<out T> {
    data class Success<out R>(val value: R) : State<R>()
    data class Failure(val error: Throwable) : State<Nothing>()
    object Loading : State<Nothing>()
}
