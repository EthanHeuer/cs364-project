package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.data.PokemonUIState
import com.example.gottaeatemall.R

/**
 * Display the Card making screen
 * @param onCreateButtonClicked event when make a new card
 * @param onShareButtonClicked event when click on share button
 * @param modifier Modifier object
 * @param pokemonUIState uiState to get information from
 */
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
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(
                    stringResource(id = R.string.make_card),
                    color = Color.Black,
                    fontSize = 35.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = onShareButtonClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                enabled = !pokemonUIState.trainerName.equals(""),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(
                    "Share your trainer Card",
                    color = Color.Black,
                    fontSize = 35.sp
                )
            }
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

}

/**
 * Preview of CardScreen
 */
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