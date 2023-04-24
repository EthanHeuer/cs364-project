package com.example.gottaeatemall.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey val id: Int,
    @ColumnInfo (name = "name") val name: String,
    @ColumnInfo (name = "moves") val moves: String,
    @ColumnInfo (name = "type_1") val type_1: String,
    @ColumnInfo (name = "type_2") val type_2: String,
    @ColumnInfo (name = "hp") val hp: Int,
    @ColumnInfo (name = "atk") val atk: Int,
    @ColumnInfo (name = "def") val def: Int,
    @ColumnInfo (name = "spd") val spd: Int,
    @ColumnInfo (name = "spe") val spe: Int,
    @ColumnInfo (name = "image") val image: String,
    @ColumnInfo (name = "calories") val calories: Int,
    @ColumnInfo (name = "caution") val caution: String
)

@Entity
data class Meal (
    @PrimaryKey val mealId: Int,
    @ColumnInfo (name = "mealName") val mealName: String
        )

@Entity
data class Team (
    @PrimaryKey val teamId: Int,
    @ColumnInfo (name = "mealName") val mealName:String
        )

@Entity
data class PokemonForMeal (
    @ColumnInfo (name = "mealId") val mealId: Int,
    @ColumnInfo (name = "pokeId") val pokeId: Int
        )

@Entity
data class PokemonForTeam (
    @ColumnInfo (name = "teamId") val teamId: Int,
    @ColumnInfo (name = "pokeId") val pokeId: Int
        )