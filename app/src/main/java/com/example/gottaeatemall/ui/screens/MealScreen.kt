package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.gottaeatemall.R

@Composable
fun MealScreen() {
    var popupControl by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(15.dp))
        Text("Meal Maker Screen")
        Button(
            onClick = { popupControl = true },
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.create_meal))
        }
    }

    if (popupControl) {
        Box {
            val popupWidth = 300.dp
            val popupHeight = 100.dp
                Popup(
                    alignment = Alignment.CenterStart,
                    offset = IntOffset(0,500),
                    onDismissRequest = {popupControl = false},
                ) {
                    Box(modifier = Modifier
                        .size(popupWidth, popupHeight)
                        .padding(3.dp)
                        .border(2.dp, color = Color.Black, RoundedCornerShape(7.dp))
                    ){
                        Column(modifier = Modifier
                            .padding(horizontal = 15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                            Text(
                                text = "This is where you create a meal"
                            )
                        }
                    }
                }
            }
        }
    }

@Composable
fun createButton(text: String, onClick: ()-> Unit) {
    Button(
        onClick = { onClick },
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Text(text = text)
    }
}

@Composable
fun mealPopup(isOpen: Boolean){
}