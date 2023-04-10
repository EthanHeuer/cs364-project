package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.background
import androidx.compose.material.Card
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R

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
        previousMeals()
    }
}

@Composable
fun previousMeals(){
    Box(modifier = Modifier
        .background(Color.Gray)
        .fillMaxWidth(),
    ){
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Previous Meals")
        }
    }
}
