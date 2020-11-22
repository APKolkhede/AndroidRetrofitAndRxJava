package com.example.rxjavaexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Character
import com.example.domain.State
import com.example.domain.State.Success
import com.example.usecases.GetCharacters
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModel(private val getCharacters: GetCharacters) : ViewModel() {
    private val response = MutableLiveData<State<List<Character>>>()
    private lateinit var d: Disposable

    fun getCharacters() {
        response.value = State.Loading
        d = getCharacters.invoke().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    updateResponse(characters = response)
                },
                { errorResponse -> response.value = State.Failure(errorResponse) }
            )
    }

    private fun updateResponse(characters: List<Character>) {
        response.value = Success(characters)
    }

    fun getResponse() = response

    override fun onCleared() {
        if (!d.isDisposed) {
            d.dispose()
        }
        super.onCleared()
    }
}
