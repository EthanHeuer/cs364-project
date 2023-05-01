package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.data.PokemonUIState
import com.example.gottaeatemall.R

//Research on Box layout and see if we can add pictures into it

@Composable
fun CardScreen(
    onCreateButtonClicked: () -> Unit ={},
    onShareButtonClicked: () -> Unit ={},
    modifier: Modifier = Modifier,
    pokemonUIState: PokemonUIState
) {
    Column {
        Column(
            modifier = Modifier
                .padding(50.dp)
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            Button(
                onClick = onCreateButtonClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(id = R.string.make_card),
                    color = Color.Black
                )
            }
            Button(
                onClick = onShareButtonClicked,
                modifier = Modifier.fillMaxWidth(),
                enabled = !pokemonUIState.trainerName.equals("")
            ) {
                Text(
                    "Share your trainer Card",
                    color = Color.Black
                )
            }
            if (!pokemonUIState.trainerName.equals("")) {
                TrainerInfo(
                    gender = pokemonUIState.boy_or_girl,
                    name = pokemonUIState.trainerName,
                    favEat = pokemonUIState.favoritePokemonEat,
                    favBattle = pokemonUIState.favoritePokemonUse,
                    totalCaught = pokemonUIState.totalCaught,
                    badges = pokemonUIState.badges
                )
            }
        }
    }

}

@Preview
@Composable
fun Preview(){
    CardScreen(
        onShareButtonClicked = {},
        onCreateButtonClicked = {},
        modifier = Modifier,
        pokemonUIState = PokemonUIState()
    )
}
