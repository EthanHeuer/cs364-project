package com.example.gottaeatemall.data

open class FakeDatabase(
    private var tables : List<FakeTable> = listOf()
) {
    fun appendTable(table: FakeTable) {
        tables += table
    }

    private fun getTable(name: String): FakeTable? {
        for (table in tables) {
            if (table.name == name) {
                return table
            }
        }

        return null
    }

    fun <T: FakeRow> queryInsert(
        into: String,
        values: T
    ) {
        val table = getTable(into)

        if (table != null) {
            table.insert(values as FakeRow)
        } else {
            throw Exception("Table $into does not exist")
        }
    }

    fun <T: FakeRow> querySelect(
        from: String,
        where: (T) -> Boolean = { true },
        orderBy: (T) -> Int = { 0 }
    ): List<T> {
        val result = mutableListOf<T>()
        val table = getTable(from)

        if (table != null) {
            for (row in table.rows) {
                if (where(row as T)) {
                    result.add(row)
                }
            }
        } else {
            throw Exception("Table $from does not exist")
        }

        return result.sortedBy(orderBy)
    }

    fun <T: FakeRow> queryUpdate(
        tableName: String,
        values: T,
        where: (T) -> Boolean = { true }
    ) {
        val table = getTable(tableName)

        if (table != null) {
            for (row in table.rows) {
                if (where(row as T)) {
                    row.update(values)
                }
            }
        } else {
            throw Exception("Table $tableName does not exist")
        }
    }

    fun <T: FakeRow> queryDelete(
        from: String,
        where: (T) -> Boolean = { true }
    ) {
        val table = getTable(from)

        if (table != null) {
            table.rows = table.rows.filter { !where(it as T) }
        } else {
            throw Exception("Table $from does not exist")
        }
    }

    companion object {
        fun getInstance(): FakeDatabase {
            return instance
        }

        private val instance = FakeDatabase()
    }
}

open class FakeTable(
    val id: Int,
    val name: String,
    var rows: List<FakeRow> = listOf()
) {
    fun insert(row: FakeRow) {
        rows += row
    }
}

open class FakeRow(
    open val id: Int
) {
    open fun update(row: FakeRow) {}
}

class PokemonSchema(
    id: Int,
    var name: String,
    var type1: String,
    var type2: String
) : FakeRow(id) {
    override fun update(row: FakeRow) {
        val other = row as PokemonSchema

        this.name = other.name
        this.type1 = other.type1
        this.type2 = other.type2
    }
}

class TeamSchema(
    id: Int,
    var name: String
) : FakeRow(id) {
    override fun update(row: FakeRow) {
        val other = row as TeamSchema

        this.name = other.name
    }
}

class TeamPokemonSchema(
    id: Int,
    var teamId: Int,
    var slotId: Int,
    var pokemonId: Int
) : FakeRow(id) {
    override fun update(row: FakeRow) {
        val other = row as TeamPokemonSchema

        this.teamId = other.teamId
        this.slotId = other.slotId
        this.pokemonId = other.pokemonId
    }
}

fun INITIALIZE_DATA() {
    val db = FakeDatabase.getInstance()

    db.appendTable(FakeTable(1, "pokemon"))
    db.appendTable(FakeTable(2, "teams"))
    db.appendTable(FakeTable(3, "team_pokemon"))

    db.queryInsert("pokemon", PokemonSchema(1, "Bulbasaur", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(2, "Ivysaur", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(3, "Venusaur", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(4, "Charmander", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(5, "Charmeleon", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(6, "Charizard", "Fire", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(7, "Squirtle", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(8, "Wartortle", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(9, "Blastoise", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(10, "Caterpie", "Bug", ""))
    db.queryInsert("pokemon", PokemonSchema(11, "Metapod", "Bug", ""))
    db.queryInsert("pokemon", PokemonSchema(12, "Butterfree", "Bug", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(13, "Weedle", "Bug", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(14, "Kakuna", "Bug", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(15, "Beedrill", "Bug", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(16, "Pidgey", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(17, "Pidgeotto", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(18, "Pidgeot", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(19, "Rattata", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(20, "Raticate", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(21, "Spearow", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(22, "Fearow", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(23, "Ekans", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(24, "Arbok", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(25, "Pikachu", "Electric", ""))
    db.queryInsert("pokemon", PokemonSchema(26, "Raichu", "Electric", ""))
    db.queryInsert("pokemon", PokemonSchema(27, "Sandshrew", "Ground", ""))
    db.queryInsert("pokemon", PokemonSchema(28, "Sandslash", "Ground", ""))
    db.queryInsert("pokemon", PokemonSchema(29, "NidoranF", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(30, "Nidorina", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(31, "Nidoqueen", "Poison", "Ground"))
    db.queryInsert("pokemon", PokemonSchema(32, "NidoranM", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(33, "Nidorino", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(34, "Nidoking", "Poison", "Ground"))
    db.queryInsert("pokemon", PokemonSchema(35, "Clefairy", "Fairy", ""))
    db.queryInsert("pokemon", PokemonSchema(36, "Clefable", "Fairy", ""))
    db.queryInsert("pokemon", PokemonSchema(37, "Vulpix", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(38, "Ninetales", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(39, "Jigglypuff", "Normal", "Fairy"))
    db.queryInsert("pokemon", PokemonSchema(40, "Wigglytuff", "Normal", "Fairy"))
    db.queryInsert("pokemon", PokemonSchema(41, "Zubat", "Poison", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(42, "Golbat", "Poison", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(43, "Oddish", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(44, "Gloom", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(45, "Vileplume", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(46, "Paras", "Bug", "Grass"))
    db.queryInsert("pokemon", PokemonSchema(47, "Parasect", "Bug", "Grass"))
    db.queryInsert("pokemon", PokemonSchema(48, "Venonat", "Bug", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(49, "Venomoth", "Bug", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(50, "Diglett", "Ground", ""))
    db.queryInsert("pokemon", PokemonSchema(51, "Dugtrio", "Ground", ""))
    db.queryInsert("pokemon", PokemonSchema(52, "Meowth", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(53, "Persian", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(54, "Psyduck", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(55, "Golduck", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(56, "Mankey", "Fighting", ""))
    db.queryInsert("pokemon", PokemonSchema(57, "Primeape", "Fighting", ""))
    db.queryInsert("pokemon", PokemonSchema(58, "Growlithe", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(59, "Arcanine", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(60, "Poliwag", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(61, "Poliwhirl", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(62, "Poliwrath", "Water", "Fighting"))
    db.queryInsert("pokemon", PokemonSchema(63, "Abra", "Psychic", ""))
    db.queryInsert("pokemon", PokemonSchema(64, "Kadabra", "Psychic", ""))
    db.queryInsert("pokemon", PokemonSchema(65, "Alakazam", "Psychic", ""))
    db.queryInsert("pokemon", PokemonSchema(66, "Machop", "Fighting", ""))
    db.queryInsert("pokemon", PokemonSchema(67, "Machoke", "Fighting", ""))
    db.queryInsert("pokemon", PokemonSchema(68, "Machamp", "Fighting", ""))
    db.queryInsert("pokemon", PokemonSchema(69, "Bellsprout", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(70, "Weepinbell", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(71, "Victreebel", "Grass", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(72, "Tentacool", "Water", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(73, "Tentacruel", "Water", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(74, "Geodude", "Rock", "Ground"))
    db.queryInsert("pokemon", PokemonSchema(75, "Graveler", "Rock", "Ground"))
    db.queryInsert("pokemon", PokemonSchema(76, "Golem", "Rock", "Ground"))
    db.queryInsert("pokemon", PokemonSchema(77, "Ponyta", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(78, "Rapidash", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(79, "Slowpoke", "Water", "Psychic"))
    db.queryInsert("pokemon", PokemonSchema(80, "Slowbro", "Water", "Psychic"))
    db.queryInsert("pokemon", PokemonSchema(81, "Magnemite", "Electric", "Steel"))
    db.queryInsert("pokemon", PokemonSchema(82, "Magneton", "Electric", "Steel"))
    db.queryInsert("pokemon", PokemonSchema(83, "Farfetchd", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(84, "Doduo", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(85, "Dodrio", "Normal", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(86, "Seel", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(87, "Dewgong", "Water", "Ice"))
    db.queryInsert("pokemon", PokemonSchema(88, "Grimer", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(89, "Muk", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(90, "Shellder", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(91, "Cloyster", "Water", "Ice"))
    db.queryInsert("pokemon", PokemonSchema(92, "Gastly", "Ghost", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(93, "Haunter", "Ghost", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(94, "Gengar", "Ghost", "Poison"))
    db.queryInsert("pokemon", PokemonSchema(95, "Onix", "Rock", "Ground"))
    db.queryInsert("pokemon", PokemonSchema(96, "Drowzee", "Psychic", ""))
    db.queryInsert("pokemon", PokemonSchema(97, "Hypno", "Psychic", ""))
    db.queryInsert("pokemon", PokemonSchema(98, "Krabby", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(99, "Kingler", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(100, "Voltorb", "Electric", ""))
    db.queryInsert("pokemon", PokemonSchema(101, "Electrode", "Electric", ""))
    db.queryInsert("pokemon", PokemonSchema(102, "Exeggcute", "Grass", "Psychic"))
    db.queryInsert("pokemon", PokemonSchema(103, "Exeggutor", "Grass", "Psychic"))
    db.queryInsert("pokemon", PokemonSchema(104, "Cubone", "Ground", ""))
    db.queryInsert("pokemon", PokemonSchema(105, "Marowak", "Ground", ""))
    db.queryInsert("pokemon", PokemonSchema(106, "Hitmonlee", "Fighting", ""))
    db.queryInsert("pokemon", PokemonSchema(107, "Hitmonchan", "Fighting", ""))
    db.queryInsert("pokemon", PokemonSchema(108, "Lickitung", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(109, "Koffing", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(110, "Weezing", "Poison", ""))
    db.queryInsert("pokemon", PokemonSchema(111, "Rhyhorn", "Ground", "Rock"))
    db.queryInsert("pokemon", PokemonSchema(112, "Rhydon", "Ground", "Rock"))
    db.queryInsert("pokemon", PokemonSchema(113, "Chansey", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(114, "Tangela", "Grass", ""))
    db.queryInsert("pokemon", PokemonSchema(115, "Kangaskhan", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(116, "Horsea", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(117, "Seadra", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(118, "Goldeen", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(119, "Seaking", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(120, "Staryu", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(121, "Starmie", "Water", "Psychic"))
    db.queryInsert("pokemon", PokemonSchema(122, "MrMime", "Psychic", "Fairy"))
    db.queryInsert("pokemon", PokemonSchema(123, "Scyther", "Bug", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(124, "Jynx", "Ice", "Psychic"))
    db.queryInsert("pokemon", PokemonSchema(125, "Electabuzz", "Electric", ""))
    db.queryInsert("pokemon", PokemonSchema(126, "Magmar", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(127, "Pinsir", "Bug", ""))
    db.queryInsert("pokemon", PokemonSchema(128, "Tauros", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(129, "Magikarp", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(130, "Gyarados", "Water", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(131, "Lapras", "Water", "Ice"))
    db.queryInsert("pokemon", PokemonSchema(132, "Ditto", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(133, "Eevee", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(134, "Vaporeon", "Water", ""))
    db.queryInsert("pokemon", PokemonSchema(135, "Jolteon", "Electric", ""))
    db.queryInsert("pokemon", PokemonSchema(136, "Flareon", "Fire", ""))
    db.queryInsert("pokemon", PokemonSchema(137, "Porygon", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(138, "Omanyte", "Rock", "Water"))
    db.queryInsert("pokemon", PokemonSchema(139, "Omastar", "Rock", "Water"))
    db.queryInsert("pokemon", PokemonSchema(140, "Kabuto", "Rock", "Water"))
    db.queryInsert("pokemon", PokemonSchema(141, "Kabutops", "Rock", "Water"))
    db.queryInsert("pokemon", PokemonSchema(142, "Aerodactyl", "Rock", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(143, "Snorlax", "Normal", ""))
    db.queryInsert("pokemon", PokemonSchema(144, "Articuno", "Ice", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(145, "Zapdos", "Electric", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(146, "Moltres", "Fire", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(147, "Dratini", "Dragon", ""))
    db.queryInsert("pokemon", PokemonSchema(148, "Dragonair", "Dragon", ""))
    db.queryInsert("pokemon", PokemonSchema(149, "Dragonite", "Dragon", "Flying"))
    db.queryInsert("pokemon", PokemonSchema(150, "Mewtwo", "Psychic", ""))
    db.queryInsert("pokemon", PokemonSchema(151, "Mew", "Psychic", ""))

    for (i in 1..3) {
        db.queryInsert("teams", TeamSchema(i, "Team $i"))

        for (j in 1..6) {
            val randomPokemon = (Math.random() * 151).toInt() + 1

            db.queryInsert("team_pokemon", TeamPokemonSchema((i - 1) * 6 + j, i, j, randomPokemon))
        }
    }
}