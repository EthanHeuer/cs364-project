package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.data.PokemonSchema
import com.example.gottaeatemall.ui.theme.ColorTypeMap
import com.example.gottaeatemall.ui.theme.ColorTypeMap700
import com.example.gottaeatemall.ui.theme.LightBlue

/**
 * Component for displaying a type chip.
 * @param type The type to display.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeChip(type: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(0.dp, 0.dp, 8.dp, 0.dp)
            .background(ColorTypeMap[type] ?: Color.Transparent, RoundedCornerShape(14.dp))
            .border(2.dp, ColorTypeMap700[type] ?: Color.Black, RoundedCornerShape(14.dp))
            .size(90.dp, 28.dp)
    ) {
        Text(
            text = type,
            fontSize = 14.sp
        )
    }
}

/**
 * Component for displaying a single slot in the team screen.
 * @param slot The slot number of the team.
 * @param pokemon The pokemon to display in the slot.
 * @param onPokemonSelected The callback to call when the user selects a pokemon.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreenSlot(
    slot: Int,
    pokemon: PokemonSchema,
    /* TODO */
    onPokemonSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = LightBlue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 8.dp, 8.dp)
                .clickable { onPokemonSelected(slot) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 8.dp, 8.dp, 8.dp)
            ) {
                // Row with the pokemon name
                Row(
                    modifier = Modifier
                ) {
                    // Pokemon name
                    Text(
                        text = pokemon.name,
                        fontSize = 24.sp
                    )
                }

                // Row with the pokemon types
                Row(
                    modifier = Modifier.padding(0.dp, 8.dp)
                ) {
                    TypeChip(type = pokemon.type1)

                    if (pokemon.type2 != "") {
                        TypeChip(type = pokemon.type2)
                    }
                }
            }
        }

        // Badge with the slot number
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                .size(width = 32.dp, height = 32.dp)
                .border(4.dp, LightBlue, RoundedCornerShape(16.dp))
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
        ) {
            Text(
                text = slot.toString(),
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}