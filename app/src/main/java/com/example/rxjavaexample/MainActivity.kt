package com.example.rxjavaexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.CharacterRepository
import com.example.data.datasource.CharacterRemoteDataSource
import com.example.domain.Character
import com.example.rxjavaexample.databinding.ActivityMainBinding
import com.example.usecases.GetCharacters
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var d: Disposable

    private lateinit var binding: ActivityMainBinding

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val v = ViewModel(GetCharacters(CharacterRepository(CharacterRemoteDataSource())))

        d = v.getCharacters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response -> showCharacters(response) }
        linearLayoutManager = LinearLayoutManager(this)
        binding.characterListView.layoutManager = linearLayoutManager
    }

    private fun showCharacters(characters: List<Character>) {
        binding.characterListView.adapter = Adapter(characters)
    }

    override fun onDestroy() {
        if (!d.isDisposed) {
            d.dispose()
        }
        super.onDestroy()
    }
}
