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

    for (i in 1..3) {
        db.queryInsert("teams", TeamSchema(i, "Team $i"))

        for (j in 1..6) {
            val randomPokemon = (Math.random() * 20).toInt() + 1

            db.queryInsert("team_pokemon", TeamPokemonSchema((i - 1) * 6 + j, i, j, randomPokemon))
        }
    }
}