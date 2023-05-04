package com.example.gottaeatemall.data

/**
 * state of the App
 */
data class PokemonUIState(
    val ingredients: List<String> = listOf(),
    val ingredient: String = "",
    val calories: Int = 0,
    val mealCalories: Int = 0,
    val isSelected: Boolean = false,
    val trainerName: String = "",
    val favoritePokemonUse: String = "",
    val favoritePokemonEat: String = "",
    val totalCaught:String = "",
    var badges: List<String> = listOf(),
    var boy_or_girl: Boolean = true,
    var id: Int = 999999
)
