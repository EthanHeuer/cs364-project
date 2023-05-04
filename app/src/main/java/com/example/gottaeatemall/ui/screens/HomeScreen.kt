package com.example.gottaeatemall.ui.screens

import android.graphics.Typeface.BOLD
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.data.PokemonUIState

/**
 * Home Screen
 * Displays at launch
 * @param pokemonUIState stores state information to display the card
 */
@Composable
fun HomeScreen(pokemonUIState: PokemonUIState) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth())
    {
        Text(
            text = "Gotta Eat 'Em All!",
            fontSize = 50.sp,
            modifier = Modifier.align(CenterHorizontally),
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "Become one of the Pokemon Trainer community! Join us, who enjoy battling and eating Pokemons!",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(50.dp))
        if (pokemonUIState.trainerName.equals("")) {
            Text(
                text = "Welcome unknown trainer! What do you want to do?",
                fontSize = 30.sp,
                modifier = Modifier.align(CenterHorizontally),
                fontStyle = FontStyle.Italic
            )
        } else {
            Text(
                text = "Welcome ${pokemonUIState.trainerName} ! What do you want to do?",
                fontSize = 30.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        if (!pokemonUIState.trainerName.equals("")) {
            TrainerInfo(
                gender = pokemonUIState.boy_or_girl,
                name = pokemonUIState.trainerName,
                favEat = pokemonUIState.favoritePokemonEat,
                favBattle = pokemonUIState.favoritePokemonUse,
                totalCaught = pokemonUIState.totalCaught,
                badges = pokemonUIState.badges,
                modifier = Modifier.align(CenterHorizontally),
                id = pokemonUIState.id
            )
        }
    }
}

/**
 * Preview of HomeScreen
 */
@Preview
@Composable
fun PreviewHome() {
    HomeScreen(pokemonUIState = PokemonUIState())
}