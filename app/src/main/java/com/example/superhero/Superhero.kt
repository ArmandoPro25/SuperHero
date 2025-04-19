package com.example.superhero

import com.google.gson.annotations.SerializedName

data class Superhero(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperheroImage?,
    @SerializedName("powerstats") val powerstats: PowerStats?,
    @SerializedName("biography") val biography: Biography?
)

data class SuperheroImage(
    @SerializedName("url") val url: String?
)

data class PowerStats(
    @SerializedName("intelligence") val intelligence: String?,
    @SerializedName("strength") val strength: String?,
    @SerializedName("speed") val speed: String?,
    @SerializedName("durability") val durability: String?,
    @SerializedName("power") val power: String?,
    @SerializedName("combat") val combat: String?
)

data class Biography(
    @SerializedName("full-name") val fullName: String?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("alignment") val alignment: String?
)