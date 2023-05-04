package com.example.gottaeatemall.data

import android.content.Context
import android.util.Log
import androidx.room.*


@Database(entities = [Pokemon::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    //Companion object for the database, needed to use it in the app
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        //Called in App.kt to instantiate the database
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //Used to instantiate the database within the getInstance function
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "gottaEatEmAllDB.sqlite"
            ).createFromAsset("gottaEatEmAllDB.sqlite").fallbackToDestructiveMigration()
                .build()
        }
    }

    //Room DAO that searches through the Pokemon table
    @Dao
    interface PokemonDao {

        @Query("SELECT * FROM Pokemon WHERE name LIKE '%' || :query || '%'")
        suspend fun searchPokemon(query: String): List<Pokemon>
    }



}
