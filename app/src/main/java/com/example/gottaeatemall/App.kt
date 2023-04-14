package com.example.gottaeatemall

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gottaeatemall.data.DataSource.PokemonList
import com.example.gottaeatemall.ui.screens.*

enum class AppScreen(@StringRes val title: Int) {
    Home(title = R.string.page_home),
    Search(title = R.string.page_search),
    Team(title = R.string.page_team),
    Meal(title = R.string.page_meal),
    SelectFirstIngredient(title = R.string.first_ingredient),
    SelectSecondIngredient(title = R.string.second_ingredient),
    MealSummary(title = R.string.meal_summary),
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

    val uiState by viewModel.uiState.collectAsState()

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
                        { navController.navigate(AppScreen.SelectFirstIngredient.name) },
                )
            }

            composable(route = AppScreen.SelectFirstIngredient.name) {
                MealPopupBox(
                    pokemonList = PokemonList,
                    onFirstPokemonSelected = {viewModel.addIngredient(it)},
                    selectNext = {navController.navigate(AppScreen.MealSummary.name)},
                    title = stringResource(id = R.string.choose_pokemon_meal)
                )
            }

            composable(route = AppScreen.MealSummary.name){
                mealSummary(mealUIState = uiState,
                onBackButtonSelected = {
                    finishMeal(viewModel, navController)
                })
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

    fun finishMeal(
    viewModel: PokemonViewModel,
    navController: NavHostController
    ){
        viewModel.resetOrder()
        navController.popBackStack(AppScreen.Meal.name, false)
    }

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}