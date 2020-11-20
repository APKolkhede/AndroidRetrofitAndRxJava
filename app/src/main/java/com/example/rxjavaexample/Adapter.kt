package com.example.rxjavaexample

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.domain.Character

class Adapter(private val characters: List<Character>) :
    RecyclerView.Adapter<Adapter.CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflatedView = parent.inflate(R.layout.character_view, false)
        return CharacterViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindCharacter(characters[position])
    }

    override fun getItemCount() = characters.size

    class CharacterViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var characterName: TextView = v.findViewById(R.id.characterName)
        private var characterImage: ImageView = v.findViewById(R.id.characterImage)
        private var characterStatus: TextView = v.findViewById(R.id.characterStatus)
        private var view = v

        fun bindCharacter(character: Character) {
            characterName.text = character.name
            characterStatus.text = character.status
            Glide.with(view).load(character.image).apply(RequestOptions.circleCropTransform())
                .into(characterImage)
        }
    }
}
