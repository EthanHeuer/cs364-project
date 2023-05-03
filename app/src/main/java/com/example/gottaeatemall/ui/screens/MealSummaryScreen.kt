package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.*
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
import com.example.gottaeatemall.data.DataSource.pokemonMealTypes
import com.example.gottaeatemall.data.PokemonUIState
import java.util.*

@Composable
fun mealSummary(
mealUIState: PokemonUIState,
onBackButtonSelected: () -> Unit
) {
    var ingredients = ""
    for (o in mealUIState.ingredients){
        if (o == mealUIState.ingredients[mealUIState.ingredients.size-1]){
            ingredients += o
        }
        else{
            ingredients += "${o}, "
        }
    }
    val items = listOf(
        Pair(stringResource(id = R.string.ingredient_list), ingredients),
        Pair(
            "Complete Meal: ",
            "${mealUIState.ingredients[0]} + ${mealUIState.ingredients[1]} " +
                    "${pokemonMealTypes[Random().nextInt(pokemonMealTypes.size)]}"
        )
    )


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //List out ingredients of the meal + meal
        items.forEach { item ->
            Text(item.first.uppercase(), fontWeight = FontWeight.Bold)
            Text(item.second)
            Divider(thickness = 1.dp)
            Spacer(modifier = Modifier.padding(10.dp))
        }
        //List out how many calories are in the meal
        Text("Calories", fontWeight = FontWeight.Bold)
        Text("1000 calories")
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = onBackButtonSelected) {
            Text(text = stringResource(id = R.string.back))
        }
    }
}
