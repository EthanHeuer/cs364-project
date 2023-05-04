package com.example.gottaeatemall.data

data class PokemonUIState(
    val ingredients: List<String> = listOf(),
    val ingredient: String = "",
    val calories: Int = 0,
    val mealCalories: Int = 0,
    val isSelected: Boolean = false,
    val trainerName: String = "",
    val trainerBadgeCouzznt: Int = 0,
    val favoritePokemonUse: String = "",
    val favoritePokemonEat: String = ""
)
