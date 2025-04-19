package com.example.superhero

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()
        viewModel.searchHeroes("a") // Búsqueda inicial
    }

    private fun setupRecyclerView() {
        adapter = HeroAdapter { id ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("HERO_ID", id)
            }
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.rvHeroes).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        viewModel.heroes.observe(this) { heroes ->
            adapter.updateList(heroes)
        }
    }
}