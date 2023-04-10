package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.gottaeatemall.R


@Composable
fun MealPopupBox(){
    Column(modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.choose_pokemon_meal),
            textAlign = TextAlign.Center
        )
        pokemonList(pokemonList = listOf("Bulbasaur", "Ivysaur", "Venusaur",
                "Charmander", "Charmeleon", "Charizard",
                "Squirtle", "Wartortle", "Blastoise",
                "Caterpie", "Metapod", "Butterfree",
                "Weedle", "Kakuna", "Beedrill",
                "Pikachu")
        )
    }
}


@Composable
fun pokemonList(pokemonList: List<String>, modifier: Modifier = Modifier){
    LazyColumn(){
        items(pokemonList){pokemon ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                elevation = 3.dp
            ){
                Column{
                    Text(pokemon, fontSize = 24.sp)
                }
            }
        }
    }
}
