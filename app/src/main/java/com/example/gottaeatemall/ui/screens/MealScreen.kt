package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.data.DataSource
import com.example.gottaeatemall.data.PokemonUIState
import java.util.*

/**
 * Area where the user can search Pokemon stats,
 * and create a new meal
 *
 * @param onMealCreate On click event that takes the user
 * to the meal creation screen
 * @param mealUIState The meal UI state of the current order
 */
@Composable
fun MealScreen(
    onMealCreate: () -> Unit = {},
    mealUIState: PokemonUIState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Meal Maker Screen",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(15.dp))
        Button(
            onClick = onMealCreate
        ) {
            Text(text = "Create Meal Here")
        }
        Spacer(modifier = Modifier.padding(25.dp))
        PreviousMeals(mealUIState)
    }
}

/**
 * Lists the previous meals that the user has ordered
 *
 * @param mealUIState The UI state of the meal the user just ordered
 */

@Composable
fun PreviousMeals(mealUIState: PokemonUIState) {
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth(),
    ) {
        if (mealUIState.mealCalories != 0) {
            Column(
            ) {
                Text(text = "Previous Meals: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(7.dp))
                Spacer(modifier = Modifier.padding(7.dp))
                Text(text = "Complete Meal: ", fontWeight = FontWeight.Bold)
                Text(
                    text = "${mealUIState.ingredients[0]} + ${mealUIState.ingredients[1]} " +
                            "${mealUIState.completeMeal}"
                )
                Spacer(modifier = Modifier.padding(7.dp))
                Text("Calories: ", fontWeight = FontWeight.Bold)
                Text("${mealUIState.mealCalories}")
            }
        }
    }
}

