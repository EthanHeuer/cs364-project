package com.example.gottaeatemall.ui.screens

import androidx.compose.material.Card
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.gottaeatemall.R

@Composable
fun MealScreen() {
    var popupControl by remember { mutableStateOf(false) }
    var popupText by remember { mutableStateOf("Create a Meal")}

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(15.dp))
        Text("Meal Maker Screen")
        Button(
            onClick = { popupControl = !popupControl
                      if(!popupControl) popupText = "Create a Meal"},
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(text = popupText)
        }
    }

    //If the user selected create a meal, show the popup window
    if (popupControl) {
        popupText = "Cancel Meal"
        mealPopupBox(isOpen = popupControl)
        }
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealScreen()
}

@Composable
fun mealPopupBox(isOpen: Boolean){
    var popupControl by remember { mutableStateOf(isOpen) }

    Box {
        Popup(
            alignment = Alignment.CenterStart,
            offset = IntOffset(0,500),
            onDismissRequest = {popupControl = false},
        ) {
            Box(modifier = Modifier
                .padding(3.dp)
                .border(2.dp, color = Color.Black, RoundedCornerShape(7.dp))
                .align(Alignment.CenterStart)
                .fillMaxWidth(),
            ){
                Column(modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.choose_pokemon_meal),
                        textAlign = TextAlign.Center
                    )
                    pokemonList(pokemonList = listOf("Pikachu", "Bulbasaur"))
                    TextButton(onClick = { popupControl = !popupControl })
                    {
                        Text(
                            text = stringResource(id = R.string.back),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun pokemonList(pokemonList: List<String>, modifier: Modifier = Modifier){
    LazyColumn(){
        items(pokemonList){pokemon ->
            Card(modifier = Modifier.padding(5.dp), elevation = 3.dp){
                Column{
                    Text(
                        text = pokemon
                    )
                }
            }
        }
    }
}
