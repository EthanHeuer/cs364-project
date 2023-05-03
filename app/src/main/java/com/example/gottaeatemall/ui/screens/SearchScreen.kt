package com.example.gottaeatemall.ui.screens


import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gottaeatemall.data.AppDatabase
import com.example.gottaeatemall.data.Pokemon
import com.example.gottaeatemall.data.PokemonRepository
import com.example.gottaeatemall.data.SearchViewModel



@Composable
fun SearchScreen(database: AppDatabase) {
    val viewModel: SearchViewModel = viewModel()
    var query by rememberSaveable { mutableStateOf("") }
    val repository = PokemonRepository(database)

    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(8.dp)
                ),
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") }
        )
        Button(
            onClick = {
                viewModel.search(query, repository)
            }
        ) {
            Text("Search")
        }
        LazyColumn {
            items(viewModel.searchResults.value ?: emptyList()) { pokemon ->
                 PokemonListItem(pokemon)
            }
        }
    }
}

@Composable
fun PokemonListItem(pokemon: Pokemon) {
    Column {
        pokemon.name?.let { Text(it, fontWeight = FontWeight.Bold, fontSize = 20.sp) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text("HP: ${pokemon.hp}")
                Text("DEF: ${pokemon.def}")
                Text("SPD: ${pokemon.spd}")
                Text("SPE: ${pokemon.spe}")
                Text("TYPE 1: ${pokemon.type_1}")
                Text("TYPE 2: ${pokemon.type_2}")
                Text("MOVES: ${pokemon.moves}")
                Text("CALORIES: ${pokemon.calories}")
            }
        }
        Divider(color = Color.Black, thickness = 1.dp)
    }
}

