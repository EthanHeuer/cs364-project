package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R
import androidx.compose.material.Divider
import com.example.gottaeatemall.data.PokemonUIState

@Composable
fun mealSummary(
mealUIState: PokemonUIState,
onBackButtonSelected: () -> Unit
){
    val items = listOf(
    Pair(stringResource(id = R.string.first_ingredient), mealUIState.firstIngredient),
        Pair(stringResource(id = R.string.second_ingredient), mealUIState.secondIngredient)
    )


    Column(
    modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        items.forEach{item ->
            Text(item.first.uppercase())
            Text(item.second, fontWeight = FontWeight.Bold)
            Divider(thickness = 1.dp)
        }


        Button(onClick = onBackButtonSelected) {
            Text(text = stringResource(id = R.string.back))
        }
    }
}
