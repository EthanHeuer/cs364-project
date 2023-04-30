package com.example.gottaeatemall.data

val tempData = FakeDatabase.getInstance().querySelect<PokemonSchema>(
    from = "pokemon",
    where = { it.id == 1 }
).first()

data class TeamUIState(
    var teamId: Int = 0,
    var name: String = "",
    var pokemon: List<PokemonSchema> = listOf(
        tempData,
        tempData,
        tempData,
        tempData,
        tempData,
        tempData
    )
)