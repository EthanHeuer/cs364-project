package com.example.gottaeatemall.NavigationTests

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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

    @Test
    fun pokemonNavHost_verifyStart(){
        navController.assertCurrentRouteName(AppScreen.Home.name)
    }

    @Test
    fun pokemonNavHost_navigateToHomeScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_home)
            .performClick()
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

    /**
     * Test navigation to card making screen
     */
    @Test
    fun pokemonNavHost_navigateToTrainerCardScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_card)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Card.name)
    }

    /**
     * Test navigation to card form screen from card screen
     */
    @Test
    fun pokemonNavHost_navigateToCardFormScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_card)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.make_card).performClick()
        navController.assertCurrentRouteName(AppScreen.Form.name)
    }

    /**
     * test navigation from card form screen back to card screen
     */
    @Test
    fun pokemonNavHost_navigateFromFormToCardScreen() {
        composeTestRule.onNodeWithStringId(R.string.page_card)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.make_card).performClick()
        composeTestRule.onNodeWithStringId(R.string.trainer_name).performTextInput("An")
        composeTestRule.onNodeWithStringId(R.string.fav_eat).performTextInput("Pikachu")
        composeTestRule.onNodeWithStringId(R.string.fav_battle).performTextInput("Pikachu")
        composeTestRule.onNodeWithStringId(R.string.num_caught).performTextInput("123")
        composeTestRule.onNodeWithStringId(R.string.badge_boulder).performClick()
        composeTestRule.onNodeWithStringId(R.string.submit).performClick()

        navController.assertCurrentRouteName(AppScreen.Card.name)
    }
}