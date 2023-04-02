package com.example.gottaeatemall

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    Card(title = R.string.page_card),
    Viewer(title = R.string.page_viewer)
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
    Text(stringResource(id = currentScreen.title))
    Row {
        Button(onClick = navPageSearch) {
            Text("List")
        }
        Button(onClick = navPageTeam) {
            Text("Team")
        }
        Button(onClick = navPageMeal) {
            Text("Meal")
        }
        Button(onClick = navPageCard) {
            Text("Card")
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Home.name
    )

    Scaffold(
        bottomBar = {AppBottomBar(
            currentScreen = currentScreen,
            navPageSearch = { navController.navigate(AppScreen.Search.name) },
            navPageTeam = { navController.navigate(AppScreen.Team.name) },
            navPageMeal = { navController.navigate(AppScreen.Meal.name) },
            navPageCard = { navController.navigate(AppScreen.Card.name) }
        )}
    ) {innerPadding ->
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
                MealScreen()
            }

            composable(route = AppScreen.Card.name) {
                CardScreen()
            }
        }
    }
}