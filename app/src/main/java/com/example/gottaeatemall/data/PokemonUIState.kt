package com.example.gottaeatemall.data

data class PokemonUIState(
    val ingredients: List<String> = listOf(),
    val trainerName: String = "",
    val trainerBadgeCouzznt: Int = 0,
    val favoritePokemonUse: String = "",
    val favoritePokemonEat: String = ""
)
