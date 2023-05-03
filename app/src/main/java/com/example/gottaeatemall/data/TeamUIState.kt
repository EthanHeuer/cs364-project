package com.example.gottaeatemall.data

data class TeamUIState(
    var activeTeamId: Int = 0,
    var name: String = "",
    var pokemon: List<PokemonSchema> = listOf()
)