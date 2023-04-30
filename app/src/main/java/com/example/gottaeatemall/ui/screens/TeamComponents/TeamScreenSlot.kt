package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.data.PokemonSchema
import com.example.gottaeatemall.ui.theme.DarkGray
import com.example.gottaeatemall.ui.theme.LightBlue

val ColorMap = mapOf(
    "Normal" to Color(0xFFA8A878),
    "Fire" to Color(0xFFF08030),
    "Water" to Color(0xFF6890F0),
    "Electric" to Color(0xFFF8D030),
    "Grass" to Color(0xFF78C850),
    "Ice" to Color(0xFF98D8D8),
    "Fighting" to Color(0xFFC03028),
    "Poison" to Color(0xFFA040A0),
    "Ground" to Color(0xFFE0C068),
    "Flying" to Color(0xFFA890F0),
    "Psychic" to Color(0xFFF85888),
    "Bug" to Color(0xFFA8B820),
    "Rock" to Color(0xFFB8A038),
    "Ghost" to Color(0xFF705898),
    "Dragon" to Color(0xFF7038F8),
    "Dark" to Color(0xFF705848),
    "Steel" to Color(0xFFB8B8D0),
    "Fairy" to Color(0xFFEE99AC)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeChip(type: String) {
    AssistChip(
        label = { Text(text = type) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = ColorMap[type] ?: Color.Transparent,
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = Color.Transparent,
            borderWidth = 0.dp
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
        onClick = {}
    )
}

/**
 * Slot for a pokemon in the team screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreenSlot(
    slot: Int,
    pokemon: PokemonSchema,
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
                    .padding(16.dp, 8.dp, 8.dp, 8.dp)
            ) {
                Row(
                    modifier = Modifier
                ) {
                    // Pokemon name
                    Text(
                        text = pokemon.name,
                        fontSize = 24.sp
                    )
                }
                Row(
                    modifier = Modifier
                ) {
                    TypeChip(type = pokemon.type1)

                    if (pokemon.type2 != "") {
                        TypeChip(type = pokemon.type2)
                    }
                }
            }
        }

        // Badge with the slot number
        Badge(
            modifier = Modifier.padding(0.dp, 40.dp, 0.dp, 0.dp),
            containerColor = LightBlue,
            contentColor = DarkGray
        ) {
            Text(
                text = slot.toString(),
                fontSize = 20.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}