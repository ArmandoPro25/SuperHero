package com.example.superhero

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiService {
    @GET("api/f4e3ac478a30df188b39974bddc44355/search/{name}")
    suspend fun searchHeroes(@Path("name") name: String): Response<ApiResponse>

    @GET("api/f4e3ac478a30df188b39974bddc44355/{id}")
    suspend fun getHeroById(@Path("id") id: String): Response<HeroDetailResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://superheroapi.com/"

    val instance: SuperheroApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SuperheroApiService::class.java)
    }
}