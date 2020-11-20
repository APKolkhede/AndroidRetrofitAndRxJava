package com.example.rxjavaexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.data.CharacterRepository
import com.example.data.datasource.CharacterRemoteDataSource
import com.example.domain.Character
import com.example.usecases.GetCharacters
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val v = ViewModel(GetCharacters(CharacterRepository(CharacterRemoteDataSource())))

        val d = v.getCharacters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response -> showCharacters(response) }
    }

    private fun showCharacters(characters: List<Character>) {
        Log.e("Response", "=============>" + characters[0].name)
    }
}
