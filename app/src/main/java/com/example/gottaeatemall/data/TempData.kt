package com.example.gottaeatemall.data

data class Pokemon(
    var id: Int,
    var name: String,
    var type1: String,
    var type2: String
)

data class Team(
    var id: Int,
    var name: String,
    var pokemon1: Int,
    var pokemon2: Int,
    var pokemon3: Int,
    var pokemon4: Int,
    var pokemon5: Int,
    var pokemon6: Int
)

val pokemonData: List<Pokemon> = listOf(
    Pokemon(1, "Bulbasaur", "Grass", "Poison"),
    Pokemon(2, "Ivysaur", "Grass", "Poison"),
    Pokemon(3, "Venusaur", "Grass", "Poison"),
    Pokemon(4, "Charmander", "Fire", ""),
    Pokemon(5, "Charmeleon", "Fire", ""),
    Pokemon(6, "Charizard", "Fire", "Flying"),
    Pokemon(7, "Squirtle", "Water", ""),
    Pokemon(8, "Wartortle", "Water", ""),
    Pokemon(9, "Blastoise", "Water", ""),
    Pokemon(10, "Caterpie", "Bug", ""),
    Pokemon(11, "Metapod", "Bug", ""),
    Pokemon(12, "Butterfree", "Bug", "Flying"),
    Pokemon(13, "Weedle", "Bug", "Poison"),
    Pokemon(14, "Kakuna", "Bug", "Poison"),
    Pokemon(15, "Beedrill", "Bug", "Poison"),
    Pokemon(16, "Pikachu", "Electric", ""),
    Pokemon(17, "Raichu", "Electric", ""),
    Pokemon(18, "Sandshrew", "Ground", ""),
    Pokemon(19, "Sandslash", "Ground", ""),
    Pokemon(20, "Nidoran♀", "Poison", ""),
    Pokemon(21, "Nidorina", "Poison", ""),
    Pokemon(22, "Nidoqueen", "Poison", "Ground"),
    Pokemon(23, "Nidoran♂", "Poison", ""),
    Pokemon(24, "Nidorino", "Poison", ""),
    Pokemon(25, "Nidoking", "Poison", "Ground"),
    Pokemon(26, "Clefairy", "Fairy", ""),
    Pokemon(27, "Clefable", "Fairy", ""),
    Pokemon(28, "Vulpix", "Fire", ""),
    Pokemon(29, "Ninetales", "Fire", ""),
    Pokemon(30, "Jigglypuff", "Normal", "Fairy"),
    Pokemon(31, "Wigglytuff", "Normal", "Fairy"),
    Pokemon(32, "Zubat", "Poison", "Flying"),
    Pokemon(33, "Golbat", "Poison", "Flying"),
    Pokemon(34, "Oddish", "Grass", "Poison"),
    Pokemon(35, "Gloom", "Grass", "Poison"),
    Pokemon(36, "Vileplume", "Grass", "Poison"),
    Pokemon(37, "Paras", "Bug", "Grass"),
    Pokemon(38, "Parasect", "Bug", "Grass"),
    Pokemon(39, "Venonat", "Bug", "Poison"),
    Pokemon(40, "Venomoth", "Bug", "Poison"),
    Pokemon(41, "Diglett", "Ground", ""),
    Pokemon(42, "Dugtrio", "Ground", ""),
    Pokemon(43, "Meowth", "Normal", ""),
    Pokemon(44, "Persian", "Normal", ""),
    Pokemon(45, "Psyduck", "Water", ""),
    Pokemon(46, "Golduck", "Water", ""),
    Pokemon(47, "Mankey", "Fighting", ""),
    Pokemon(48, "Primeape", "Fighting", ""),
    Pokemon(49, "Growlithe", "Fire", ""),
    Pokemon(50, "Arcanine", "Fire", "")
)

fun getPokemonId(pokemonName: String): Int {
    for (pokemon in pokemonData) {
        if (pokemon.name == pokemonName) {
            return pokemon.id
        }
    }

    return -1
}

fun getPokemonName(pokemonId: Int): String {
    for (pokemon in pokemonData) {
        if (pokemon.id == pokemonId) {
            return pokemon.name
        }
    }

    return ""
}

var teamsData: MutableList<Team> = mutableListOf(
    Team(0, "Team 1", 1, 2, 3, 4, 5, 6),
    Team(1, "Team 2", 7, 8, 9, 10, 11, 12),
    Team(2, "Team 3", 13, 14, 15, 16, 17, 18),
)