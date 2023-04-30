package com.example.gottaeatemall.NavigationTests.MealNavigationTests

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.gottaeatemall.App
import com.example.gottaeatemall.AppScreen
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.DataSource.PokemonList
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

    private fun NavController.assertCurrentRouteName(expectedRouteName: String){
        Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
    }

    private fun navigateToMealScreen(){
        composeTestRule.onNodeWithStringId(R.string.page_meal)
            .performClick()
    }

    private fun navigateToIngredientsScreen() {
        navigateToMealScreen()
        composeTestRule.onNodeWithText("Create Meal Here")
            .performClick()
    }

    private fun navigateToSummaryScreen(firstPokemon: String, secondPokemon: String){
        composeTestRule.onNodeWithContentDescription(firstPokemon)
            .performClick()
        composeTestRule.onNodeWithContentDescription(secondPokemon)
            .performClick()
        composeTestRule.onNodeWithText("Next")
            .performClick()
    }

    private fun navigateThroughApp(fP: String, sP: String){
        navigateToIngredientsScreen()
        navigateToSummaryScreen(fP, sP)
    }

    private fun testAllCombinations(){
        var swipeCounter = 0
        for (o in PokemonList){
            for (p in PokemonList){
                if(o != p){
                    navigateToIngredientsScreen()
                    composeTestRule.onNodeWithContentDescription(o)
                        .performClick()
                    if(swipeCounter > 8){
                        composeTestRule.onNodeWithContentDescription("Metapod")
                            .performTouchInput { swipeUp(1f, 50f) }
                    }
                    composeTestRule.onNodeWithContentDescription(p)
                        .performClick()
                    composeTestRule.onNodeWithText("Next")
                        .performClick()
                    composeTestRule.onNodeWithText("$o, $p").assertIsDisplayed()
                    composeTestRule.onNodeWithStringId(R.string.back)
                        .performClick()
                    swipeCounter++
                }
            }
        }
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

    @Test
    fun testPokemonCombinations(){
        testAllCombinations()
    }
}