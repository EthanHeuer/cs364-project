package com.example.gottaeatemall

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gottaeatemall.ui.screens.*

enum class AppScreen(@StringRes val title: Int) {
    Home(title = R.string.page_home),
    Search(title = R.string.page_search),
    Team(title = R.string.page_team),
    Meal(title = R.string.page_meal),
    CreateMeal(title = R.string.create_meal),
    CreateMealSecond(title = R.string.favorite_meal),
    Card(title = R.string.page_card),
    Detail(title = R.string.page_detail)
}

@Composable
fun AppBottomBar(
    currentScreen: AppScreen,
    navPageSearch: () -> Unit = {},
    navPageTeam: () -> Unit = {},
    navPageMeal: () -> Unit = {},
    navPageCard: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        contentPadding = PaddingValues(0.dp),
        backgroundColor = Color.Red
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Search,"") },
                label = { Text("Search") },
                selected = (currentScreen.name == AppScreen.Search.name),
                onClick = navPageSearch
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Lock,"") },
                label = { Text("Team") },
                selected = (currentScreen.name == AppScreen.Team.name),
                onClick = navPageTeam
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Lock,"") },
                label = { Text("Meal") },
                selected = (currentScreen.name == AppScreen.Meal.name),
                onClick = navPageMeal
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Lock,"") },
                label = { Text("Card") },
                selected = (currentScreen.name == AppScreen.Card.name),
                onClick = navPageCard
            )
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: PokemonViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Home.name
    )

    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentScreen = currentScreen,
                navPageSearch = { navController.navigate(AppScreen.Search.name) },
                navPageTeam = { navController.navigate(AppScreen.Team.name) },
                navPageMeal = { navController.navigate(AppScreen.Meal.name) },
                navPageCard = { navController.navigate(AppScreen.Card.name) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = AppScreen.Home.name) {
                HomeScreen()
            }

            composable(route = AppScreen.Search.name) {
                SearchScreen()
            }

            composable(route = AppScreen.Team.name) {
                TeamScreen()
            }

            composable(route = AppScreen.Meal.name) {
                MealScreen(
                    onMealCreate =
                        { navController.navigate(AppScreen.CreateMeal.name) },
                )
            }

            composable(route = AppScreen.CreateMeal.name) {
                MealPopupBox(
                    pokemonList = listOf("Bulbasaur", "Ivysaur", "Venusaur",
                        "Charmander", "Charmeleon", "Charizard",
                        "Squirtle", "Wartortle", "Blastoise",
                        "Caterpie", "Metapod", "Butterfree",
                        "Weedle", "Kakuna", "Beedrill",
                        "Pikachu"
                ),
                    onFirstPokemonSelected = {viewModel.setFirstIngredient(it)},
                    selectNext = {navController.navigate(AppScreen.CreateMealSecond.name)}
                )
            }

            composable(route = AppScreen.CreateMealSecond.name) {
                MealSecondPopupBox(
                    pokemonList = listOf("Bulbasaur", "Ivysaur", "Venusaur",
                        "Charmander", "Charmeleon", "Charizard",
                        "Squirtle", "Wartortle", "Blastoise",
                        "Caterpie", "Metapod", "Butterfree",
                        "Weedle", "Kakuna", "Beedrill",
                        "Pikachu"
                    ),
                    onSecondPokemonSelected = {viewModel.setFirstIngredient(it)},
                    onSummaryButtonSelected = {}
                )
            }



            composable(route = AppScreen.Card.name) {
                CardScreen()
            }

            composable(route = AppScreen.Detail.name) {
                DetailScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}