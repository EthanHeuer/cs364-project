package com.example.gottaeatemall.data

/**
 * @param database: database being used in the app
 * Repository for the database
 */
class PokemonRepository(database: AppDatabase) {
    private val pokemonDao = database.pokemonDao()

    /**
     * @param query: the user entered string to search for
     * Function that searches the Pokemon table of the database
     */
    suspend fun searchPokemon(query: String): List<Pokemon> {
        return pokemonDao.searchPokemon("%$query%")
    }
}
