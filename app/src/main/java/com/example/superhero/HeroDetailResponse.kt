package com.example.superhero

import com.google.gson.annotations.SerializedName

data class HeroDetailResponse(
    @SerializedName("response") val status: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStats?,
    @SerializedName("biography") val biography: Biography?,
    @SerializedName("image") val image: SuperheroImage?
)