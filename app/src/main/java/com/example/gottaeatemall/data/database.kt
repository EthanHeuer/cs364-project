package com.example.gottaeatemall.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tables for the Room database
 */
@Entity (tableName = "Meal")
data class Meal (
    @PrimaryKey val mealID: Int?,
    @ColumnInfo (name = "mealName") val mealName: String?
)
@Entity (tableName = "Pokemon")
data class Pokemon(
    @PrimaryKey val id: Int?,
    @ColumnInfo (name = "name") val name: String?,
    @ColumnInfo (name = "moves") val moves: String?,
    @ColumnInfo (name = "type_1") val type_1: String?,
    @ColumnInfo (name = "type_2") val type_2: String?,
    @ColumnInfo (name = "hp") val hp: Int?,
    @ColumnInfo (name = "atk") val atk: Int?,
    @ColumnInfo (name = "def") val def: Int?,
    @ColumnInfo (name = "spd") val spd: Int?,
    @ColumnInfo (name = "spe") val spe: Int?,
    @ColumnInfo (name = "image") val image: String?,
    @ColumnInfo (name = "calories") val calories: Int?,
    @ColumnInfo (name = "caution") val caution: String?
)



@Entity (tableName = "PokemonForMeal")
data class PokemonForMeal (
    @ColumnInfo (name = "mealID") val mealID: Int?,
    @ColumnInfo (name = "pokeID") val pokeID: Int?
)

@Entity (tableName = "PokemonForTeam")
data class PokemonForTeam (
    @ColumnInfo (name = "teamID") val teamID: Int?,
    @ColumnInfo (name = "pokeID") val pokeID: Int?
)
@Entity (tableName = "Team")
data class Team (
    @PrimaryKey val teamID: Int?,
    @ColumnInfo (name = "teamName") val teamName: String?
        )

