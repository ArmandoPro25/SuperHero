package com.example.superhero

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val heroId = intent.getStringExtra("HERO_ID") ?: return
        loadHeroDetails(heroId)
    }

    private fun loadHeroDetails(id: String) {
        lifecycleScope.launch {
            try {
                Log.d("DetailActivity", "Buscando héroe con ID: $id")
                val response: Response<HeroDetailResponse> = RetrofitClient.instance.getHeroById(id)

                Log.d("DetailActivity", "Respuesta recibida: ${response.code()}")

                if(response.isSuccessful) {
                    Log.d("DetailActivity", "Cuerpo de respuesta: ${response.body()}")

                    if(response.body()?.status == "success") {
                        response.body()?.let { heroResponse ->
                            bindHeroData(heroResponse)
                        }
                    } else {
                        Log.e("DetailActivity", "Respuesta API no exitosa: ${response.body()?.status}")
                        Toast.makeText(this@DetailActivity, "Error en datos del héroe", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("DetailActivity", "Respuesta fallida: ${response.errorBody()?.string()}")
                    Toast.makeText(this@DetailActivity, "Error de servidor", Toast.LENGTH_SHORT).show()
                }
            } catch(e: Exception) {
                Log.e("DetailActivity", "Error completo: ", e)
                Toast.makeText(this@DetailActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindHeroData(hero: HeroDetailResponse) {
        findViewById<TextView>(R.id.tvName).text = hero.name ?: "Sin nombre"

        hero.biography?.let { bio ->
            findViewById<TextView>(R.id.tvFullName).text = bio.fullName ?: "No disponible"
            findViewById<TextView>(R.id.tvPublisher).text = bio.publisher ?: "No disponible"
            findViewById<TextView>(R.id.tvAlignment).text = bio.alignment ?: "No disponible"
        } ?: run {
            findViewById<TextView>(R.id.tvFullName).text = "No disponible"
            findViewById<TextView>(R.id.tvPublisher).text = "No disponible"
            findViewById<TextView>(R.id.tvAlignment).text = "No disponible"
        }

        hero.image?.url?.let { url ->
            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_placeholder) // Añade un placeholder
                .error(R.drawable.ic_error) // Añade un ícono de error
                .into(findViewById(R.id.ivHero))
        } ?: run {
            findViewById<ImageView>(R.id.ivHero).setImageResource(R.drawable.ic_placeholder)
        }
    }
}