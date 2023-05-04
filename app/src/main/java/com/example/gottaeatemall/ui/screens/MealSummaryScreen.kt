package com.example.gottaeatemall.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.gottaeatemall.data.DataSource.pokemonMealTypes
import com.example.gottaeatemall.data.PokemonUIState
import java.util.*
import kotlin.math.exp

/**
 * Displays all of the meal information
 *
 * @param mealUIState The UI state of the current meal that has the data to display
 * @param onBackButtonSelected When the user is done looking at the meal,
 * takes them to them to the home screen
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun mealSummary(
mealUIState: PokemonUIState,
onBackButtonSelected: () -> Unit,
onSelectionChanged: (String) -> Unit = {}
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
    )


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember {
            mutableStateOf(pokemonMealTypes[0])
        }
        val context = LocalContext.current
        //List out ingredients of the meal + meal
        items.forEach { item ->
            Text(item.first.uppercase(), fontWeight = FontWeight.Bold)
            Text(item.second)
            Divider(thickness = 1.dp)
            Spacer(modifier = Modifier.padding(10.dp))
        }
        Text(text = "Choose meal type:")
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.Center){
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)}
                )

                ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                    pokemonMealTypes.forEach{item ->
                        DropdownMenuItem(onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT)}) {
                            Text(text = item)
                            onSelectionChanged(selectedText)
                        }
                    }
                }
            }
        }
        //List out how many calories are in the meal
        Text("Calories", fontWeight = FontWeight.Bold)
        Text("${mealUIState.mealCalories}")
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = onBackButtonSelected) {
            Text(text = ("Complete Meal"))
        }
    }
}
