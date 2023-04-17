package com.example.gottaeatemall.NavigationTests.MealNavigationTests

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.gottaeatemall.App
import com.example.gottaeatemall.AppScreen
import com.example.gottaeatemall.R
import com.example.gottaeatemall.onNodeWithStringId
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealNavigationTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupPokemonNavHost(){
        composeTestRule.setContent {
            navController =
                TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            App(navController = navController)
        }
    }

    fun NavController.assertCurrentRouteName(expectedRouteName: String){
        Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
    }

    fun navigateToMealScreen(){
        composeTestRule.onNodeWithStringId(R.string.page_meal)
            .performClick()
    }

    fun navigateToIngredientsScreen() {
        navigateToMealScreen()
        composeTestRule.onNodeWithText("Create Meal Here")
            .performClick()
    }

    fun navigateToSummaryScreen(firstPokemon: String, secondPokemon: String){
        composeTestRule.onNodeWithContentDescription(firstPokemon)
            .performClick()
        composeTestRule.onNodeWithContentDescription(secondPokemon)
            .performClick()
        composeTestRule.onNodeWithText("Next")
            .performClick()
    }

    @Test
    fun pokemonNavHost_navigateToMealScreen() {
        navigateToMealScreen()
        navController.assertCurrentRouteName(AppScreen.Meal.name)
    }

    @Test
    fun pokemonNavHost_mealCreation(){
        navigateToIngredientsScreen()
        navController.assertCurrentRouteName(AppScreen.SelectIngredients.name)
    }

    @Test
    fun pokemonNavHost_orderSummary(){
        navigateToIngredientsScreen()
        navigateToSummaryScreen("Bulbasaur", "Ivysaur")
        navController.assertCurrentRouteName(AppScreen.MealSummary.name)
    }

    @Test
    fun pokemonNavHost_BackToMainMealScreen(){
        navigateToIngredientsScreen()
        navigateToSummaryScreen("Bulbasaur", "Ivysaur")
        composeTestRule.onNodeWithStringId(R.string.back)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Meal.name)
    }
}