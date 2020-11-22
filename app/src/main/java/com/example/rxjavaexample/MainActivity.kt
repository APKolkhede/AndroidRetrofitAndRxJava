package com.example.rxjavaexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.CharacterRepository
import com.example.data.datasource.CharacterRemoteDataSource
import com.example.domain.Character
import com.example.domain.State
import com.example.rxjavaexample.databinding.ActivityMainBinding
import com.example.usecases.GetCharacters

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val v = ViewModelProviders.of(
            this,
            ViewModelFactory {
                ViewModel(
                    GetCharacters(
                        CharacterRepository(CharacterRemoteDataSource())
                    )
                )
            }
        ).get(ViewModel::class.java)
        v.getResponse().observe(
            this, { updateUIState(it) }
        )
        v.getCharacters()
        linearLayoutManager = LinearLayoutManager(this)
        binding.characterListView.layoutManager = linearLayoutManager
    }

    private fun updateUIState(state: State<List<Character>>) {
        when (state) {
            is State.Success -> {
                binding.characterListView.adapter = Adapter(state.value)
                binding.progressBar.visibility = View.GONE
            }
            is State.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is State.Failure -> {
                binding.errorMessage.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
