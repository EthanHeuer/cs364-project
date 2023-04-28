package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Slot for a pokemon in the team screen
 * @param slot the slot number
 * @param pokemonName the name of the pokemon
 * @param onPokemonSelected the callback when the pokemon is selected
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreenSlot(
    slot: Int,
    pokemonName: String,
    onPokemonSelected: (Int) -> Unit
) {
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 16.dp, 16.dp, 16.dp)
            ) {
                // Pokemon name
                Text(
                    text = pokemonName,
                    fontSize = 28.sp,
                )
            }
        }

        // Badge with the slot number
        Badge(
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Text(
                text = slot.toString(),
                fontSize = 20.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}