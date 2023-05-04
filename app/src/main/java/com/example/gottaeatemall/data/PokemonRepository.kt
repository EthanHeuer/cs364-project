package com.example.gottaeatemall.data

class PokemonRepository(database: AppDatabase) {
    private val pokemonDao = database.pokemonDao()

    suspend fun searchPokemon(query: String): List<Pokemon> {
        return pokemonDao.searchPokemon("%$query%")
    }
}
