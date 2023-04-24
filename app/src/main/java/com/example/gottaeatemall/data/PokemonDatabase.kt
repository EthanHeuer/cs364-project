package com.example.gottaeatemall.data

import android.content.Context
import android.util.Log
import androidx.room.*

@Database(entities = [Pokemon::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "gottaEatEmAllDB.sqlite"
            ).createFromAsset("gottaEatEmAllDB.sqlite").build()
        }
    }

    @Dao
    interface PokemonDao {

        @Query("SELECT * FROM Pokemon WHERE name LIKE :query")
        suspend fun searchPokemon(query: String): List<Pokemon>
    }


}
