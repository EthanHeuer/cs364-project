package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Area where the user can search Pokemon stats,
 * and create a new meal
 *
 * @param onMealCreate On click event that takes the user
 * to the meal creation screen
 */
@Composable
fun MealScreen(
    onMealCreate: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Search Bar goes here")
        Spacer(modifier = Modifier.padding(15.dp))
        Text("Meal Maker Screen")
        Button(
            onClick = onMealCreate
        ) {
            Text(text = "Create Meal Here")
        }
        Spacer(modifier = Modifier.padding(25.dp))
        PreviousMeals()
    }
}

/**
 * Lists the previous meals that the user has ordered
 */
@Composable
fun PreviousMeals(){
    Box(modifier = Modifier
        .background(Color.Gray)
        .fillMaxWidth(),
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Previous Meals")
        }
    }
}
