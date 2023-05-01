package com.example.gottaeatemall.data

/**
 * Data class for storing a team to be passed between screens.
 */
data class TeamTemplate(
    var name: String = "",
    val pokemon: List<String> = listOf("", "", "", "", "", "")
)