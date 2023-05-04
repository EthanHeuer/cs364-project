package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.AppDatabase
import com.example.gottaeatemall.data.PokemonRepository
import com.example.gottaeatemall.data.PokemonUIState
import com.example.gottaeatemall.data.SearchViewModel

/**
 * Creates the selecting ingredients screen with checkboxes
 * and a popup that will occur when the user selects a poison pokemon
 *
 * @param pokemonList The list of available Pokemon for a meal
 * @param onFirstPokemonSelected The Pokemon the user has selected
 * @param selectNext The navigation to the summary screen
 * @param reset Reset the page when the user cancels their meal
 * @param title Title of the meal page
 */
@Composable
fun MealPopupBox(
    pokemonList: List<String>,
    pokemonCals: List<Int>,
    onFirstPokemonSelected: (String) -> Unit = {},
    changeMealCalories: (Int) -> Unit = {},
    selectNext: () -> Unit,
    reset: () ->Unit = {},
    title: String
){
    Column(modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center
        )
    }
    Column{
        Spacer(modifier = Modifier.padding(15.dp))
        var selectedPokemon by rememberSaveable { mutableStateOf("") }
        var ingredientAmount by remember { mutableStateOf(0) }
        var cals by remember { mutableStateOf(0) }
        val listState = rememberLazyListState()

        var items by remember {
            mutableStateOf(
                (pokemonList.indices).map{
                PokemonUIState(
                    ingredient = "${pokemonList[it]}",
                    isSelected = false,
                    calories = pokemonCals[it]
                )
            }
            )
        }

        LazyColumn(modifier = Modifier
            .weight(1f, false),
            state = listState
        ) {
            items(items.size) { p ->
                Row(
                    modifier =
                    Modifier
                        .clickable(
                            enabled = (ingredientAmount < 2 || items[p].isSelected)
                        ) {
                            //Find the index in the list that matches
                            // and change it's is selected state
                            items = items.mapIndexed { j, item ->
                                if (p == j) {
                                    item.copy(isSelected = !item.isSelected)
                                } else {
                                    item
                                }
                            }
                            if (items[p].isSelected) {
                                ingredientAmount++
                                cals += items[p].calories
                            } else {
                                ingredientAmount--
                                cals -= items[p].calories
                            }
                            selectedPokemon = items[p].ingredient
                            onFirstPokemonSelected(items[p].ingredient)
                            changeMealCalories(cals)
                        }
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(items[p].ingredient)
                    if(items[p].isSelected){
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "${items[p].ingredient}",
                            tint = Color.Green,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
        }
        if (ingredientAmount > 2) {
            Column {
                AlertDialog(
                    title = {
                        Text(text = stringResource(id = R.string.confirm_selection))
                    },
                    text = {
                        Text("You selected $selectedPokemon")
                    },
                    onDismissRequest = {
                        false
                    },
                    confirmButton = {
                        Button(
                            onClick = selectNext
                        ) {
                            Text(stringResource(id = R.string.confirm))
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = reset
                        ) {
                            Text(stringResource(id = R.string.cancel))
                        }
                    },
                )
            }
        }

        Button(
            onClick = selectNext,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            enabled = (ingredientAmount == 2)
        ) {
            Text(text = "Next")
        }
    }
}