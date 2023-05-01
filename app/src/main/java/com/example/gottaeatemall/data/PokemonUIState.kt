package com.example.gottaeatemall.data

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap

data class PokemonUIState(
    val ingredients: List<String> = listOf(),
    val trainerName: String = "",
    //val trainerBadgeCount: Int = 0,
    val favoritePokemonUse: String = "",
    val favoritePokemonEat: String = "",
    //val playtime:String = "",
    val totalCaught:String = "",
    var badges: List<String> = listOf(),
    var boy_or_girl: Boolean = true
    )
