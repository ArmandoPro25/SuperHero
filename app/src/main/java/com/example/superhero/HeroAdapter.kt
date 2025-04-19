package com.example.superhero

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HeroAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    private var heroes = emptyList<Superhero>()

    inner class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(hero: Superhero) {
            with(itemView) {
                findViewById<TextView>(R.id.heroName).text = hero.name
                Glide.with(context)
                    .load(hero.image?.url)
                    .into(findViewById(R.id.heroImage))

                setOnClickListener { onClick(hero.id) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount() = heroes.size

    fun updateList(newHeroes: List<Superhero>) {
        heroes = newHeroes
        notifyDataSetChanged()
    }
}