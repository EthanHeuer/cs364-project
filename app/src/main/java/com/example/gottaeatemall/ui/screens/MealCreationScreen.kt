package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R


@Composable
fun MealPopupBox(
    pokemonList: List<String>,
    onFirstPokemonSelected: (String) -> Unit = {},
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
    Column(
    ){
        Spacer(modifier = Modifier.padding(15.dp))
        var selectedPokemon by rememberSaveable { mutableStateOf("") }
        var ingredientAmount by remember { mutableStateOf(0) }
        var confirmSelection by remember { mutableStateOf(false)}

        LazyColumn() {
            items(pokemonList) { pokemon ->
                Row(
                    modifier =
                    Modifier.clickable {
                        selectedPokemon = pokemon
                        onFirstPokemonSelected(pokemon)
                    }
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    val checked = remember { mutableStateOf(false)}
                    Checkbox(
                        checked = checked.value,
                        onCheckedChange ={
                            selectedPokemon = pokemon
                            checked.value = it
                            onFirstPokemonSelected(pokemon)
                            if(checked.value) {
                                ingredientAmount++
                            }
                            else {
                                ingredientAmount--
                            }
                        }
                    )
                    Text(pokemon)
                }
            }
        }

        if (ingredientAmount == 2) {
            Column() {
                AlertDialog(
                    title = {
                        Text(text = stringResource(id = R.string.confirm_selection))
                    },
                    text = {
                        Text("You selected $selectedPokemon")
                    },
                    onDismissRequest = {
                        confirmSelection = false
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
    }
}



