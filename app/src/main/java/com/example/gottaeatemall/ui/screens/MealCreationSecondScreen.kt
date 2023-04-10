package com.example.gottaeatemall.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R


@Composable
fun MealSecondPopupBox(
    pokemonList: List<String>,
    onSecondPokemonSelected: (String) -> Unit = {},
    onSummaryButtonSelected: () -> Unit
){

    Column(modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Choose a second Pokemon for your meal",
            textAlign = TextAlign.Center
        )
    }
    Column(
    ){
        Spacer(modifier = Modifier.padding(15.dp))
        var selectedPokemon by rememberSaveable { mutableStateOf("") }
        var confirmSelection by remember { mutableStateOf(false) }

        LazyColumn(
        ) {
            items(pokemonList) { pokemon ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedPokemon == pokemon,
                        onClick = {
                            selectedPokemon = pokemon
                            confirmSelection = true
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = selectedPokemon == pokemon,
                        onClick = {
                            selectedPokemon = pokemon
                            confirmSelection = true
                            onSecondPokemonSelected(pokemon)
                        }
                    )
                    Text(pokemon)
                }
            }
        }
        if (confirmSelection) {
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
                            onClick = {
                                confirmSelection = true
                                onSummaryButtonSelected
                            }
                        ) {
                            Text("Confirm & Complete")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                confirmSelection = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    },
                )
            }
        }
    }
}



