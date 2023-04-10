package com.example.gottaeatemall.NavigationTests

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.gottaeatemall.App
import com.example.gottaeatemall.AppScreen
import com.example.gottaeatemall.onNodeWithStringId
import com.example.gottaeatemall.R
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonAppNavigationTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost(){
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

    @Test
    fun pokemonNavHost_verifyStart(){
        navController.assertCurrentRouteName(AppScreen.Home.name)
    }

    @Test
    fun pokemonNavHost_navigateToSearchScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_search)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Search.name)
    }

    @Test
    fun pokemonNavHost_navigateToTeamScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_team)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Team.name)
    }

    @Test
    fun pokemonNavHost_navigateToMealScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_meal)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Meal.name)
    }

    @Test
    fun pokemonNavHost_navigateToTrainerCardScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_card)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Card.name)
    }
}