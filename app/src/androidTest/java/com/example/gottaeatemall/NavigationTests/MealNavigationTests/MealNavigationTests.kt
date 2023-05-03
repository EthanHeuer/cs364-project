package com.example.gottaeatemall.NavigationTests.MealNavigationTests

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.action.ViewActions
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

    private fun testAllCombinations(){

        for (o in PokemonList){
            for (p in PokemonList){
                if(o != p){
                    navigateToIngredientsScreen()
                    composeTestRule.onNodeWithContentDescription(o)
                        .performClick()
                    /*
                    composeTestRule.onNodeWithContentDescription("Metapod")
                        .performTouchInput { swipeUp(150f, 10f) }
                        */
                    composeTestRule.onNodeWithContentDescription(p)
                        .performClick()
                    composeTestRule.onNodeWithText("Next")
                        .performClick()
                    composeTestRule.onNodeWithText("$o, $p").assertIsDisplayed()
                    composeTestRule.onNodeWithStringId(R.string.back)
                        .performClick()
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
        navigateToSummaryScreen("Caterpie", "Charmander")
        composeTestRule.onNodeWithStringId(R.string.back)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Meal.name)
    }

    @Test
    fun testPokemonCombinations(){
        testAllCombinations()
    }
}