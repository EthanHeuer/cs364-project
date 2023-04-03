package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealScreen() {
    Column(modifier = Modifier
        .fillMaxWidth()){
        Spacer(modifier = Modifier.padding(15.dp))
        Text("Meal Maker Screen")
        Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally)) {
            Text(text = "Make A Meal")
        }
    }
}