package com.example.gottaeatemall

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gottaeatemall.data.Team
import com.example.gottaeatemall.data.getPokemonId
import com.example.gottaeatemall.data.getPokemonName
import com.example.gottaeatemall.data.teamsData
import com.example.gottaeatemall.ui.screens.CardScreen
import com.example.gottaeatemall.ui.screens.DetailScreen
import com.example.gottaeatemall.ui.screens.HomeScreen
import com.example.gottaeatemall.ui.screens.MealPopupBox
import com.example.gottaeatemall.ui.screens.MealScreen
import com.example.gottaeatemall.ui.screens.MealSecondPopupBox
import com.example.gottaeatemall.ui.screens.PokemonViewModel
import com.example.gottaeatemall.ui.screens.SearchScreen
import com.example.gottaeatemall.ui.screens.TeamForm
import com.example.gottaeatemall.ui.screens.TeamScreen
import java.util.UUID

enum class AppScreen(@StringRes val title: Int) {
    Home(title = R.string.page_home),
    Search(title = R.string.page_search),
    Team(title = R.string.page_team),
    Meal(title = R.string.page_meal),
    CreateMeal(title = R.string.create_meal),
    CreateMealSecond(title = R.string.favorite_meal),
    Card(title = R.string.page_card),
    Detail(title = R.string.page_detail),
    TeamForm(title = R.string.page_team_form),
    TeamFormEdit(title = R.string.page_team_form_edit)
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
                icon = { Icon(imageVector = Icons.Default.Search, "") },
                label = { Text("Search") },
                selected = (currentScreen.name == AppScreen.Search.name),
                onClick = navPageSearch
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Lock, "") },
                label = { Text("Team") },
                selected = (currentScreen.name == AppScreen.Team.name),
                onClick = navPageTeam
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Lock, "") },
                label = { Text("Meal") },
                selected = (currentScreen.name == AppScreen.Meal.name),
                onClick = navPageMeal
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Lock, "") },
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

    var editTeamId by remember { mutableStateOf(-1) }

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
                TeamScreen(
                    onTeamCreate = { navController.navigate(AppScreen.TeamForm.name) },
                    onTeamEdit = { teamId ->
                        editTeamId = teamId
                        navController.navigate(AppScreen.TeamFormEdit.name)
                    },
                    onTeamDelete = { teamId ->

                    }
                )
            }

            composable(route = AppScreen.TeamForm.name) {
                TeamForm(
                    name = "",
                    slot1 = "",
                    slot2 = "",
                    slot3 = "",
                    slot4 = "",
                    slot5 = "",
                    slot6 = "",
                    onSubmit = { newTeam ->
                        val team = Team(
                            id = UUID.randomUUID().hashCode(),
                            name = newTeam.name,
                            pokemon1 = getPokemonId(newTeam.pokemon1),
                            pokemon2 = getPokemonId(newTeam.pokemon2),
                            pokemon3 = getPokemonId(newTeam.pokemon3),
                            pokemon4 = getPokemonId(newTeam.pokemon4),
                            pokemon5 = getPokemonId(newTeam.pokemon5),
                            pokemon6 = getPokemonId(newTeam.pokemon6)
                        )
                        println(team)
                        teamsData.add(team)
                        navController.navigate(AppScreen.Team.name)
                    },
                    onSave = { },
                    onCancel = { navController.navigate(AppScreen.Team.name) },
                    onBack = { navController.navigate(AppScreen.Team.name) },
                    editMode = false
                )
            }

            composable(route = AppScreen.TeamFormEdit.name) {
                val teamData = teamsData.find { it.id == editTeamId }
                TeamForm(
                    name = teamData?.name ?: "",
                    slot1 = getPokemonName(teamData?.pokemon1 ?: 0),
                    slot2 = getPokemonName(teamData?.pokemon2 ?: 0),
                    slot3 = getPokemonName(teamData?.pokemon3 ?: 0),
                    slot4 = getPokemonName(teamData?.pokemon4 ?: 0),
                    slot5 = getPokemonName(teamData?.pokemon5 ?: 0),
                    slot6 = getPokemonName(teamData?.pokemon6 ?: 0),
                    onSubmit = { newTeam ->
                        val team = Team(
                            id = UUID.randomUUID().hashCode(),
                            name = newTeam.name,
                            pokemon1 = getPokemonId(newTeam.pokemon1),
                            pokemon2 = getPokemonId(newTeam.pokemon2),
                            pokemon3 = getPokemonId(newTeam.pokemon3),
                            pokemon4 = getPokemonId(newTeam.pokemon4),
                            pokemon5 = getPokemonId(newTeam.pokemon5),
                            pokemon6 = getPokemonId(newTeam.pokemon6)
                        )
                        teamsData.add(team)
                        navController.navigate(AppScreen.Team.name)
                    },
                    onSave = { team ->
                        teamsData[editTeamId] = Team(
                            id = teamData?.id ?: 0,
                            name = team.name,
                            pokemon1 = getPokemonId(team.pokemon1),
                            pokemon2 = getPokemonId(team.pokemon2),
                            pokemon3 = getPokemonId(team.pokemon3),
                            pokemon4 = getPokemonId(team.pokemon4),
                            pokemon5 = getPokemonId(team.pokemon5),
                            pokemon6 = getPokemonId(team.pokemon6)
                        )
                        navController.navigate(AppScreen.Team.name)
                    },
                    onCancel = { navController.navigate(AppScreen.Team.name) },
                    onBack = { navController.navigate(AppScreen.Team.name) },
                    editMode = true
                )
            }

            composable(route = AppScreen.Meal.name) {
                MealScreen(
                    onMealCreate =
                    { navController.navigate(AppScreen.CreateMeal.name) },
                )
            }

            composable(route = AppScreen.CreateMeal.name) {
                MealPopupBox(
                    pokemonList = listOf(
                        "Bulbasaur", "Ivysaur", "Venusaur",
                        "Charmander", "Charmeleon", "Charizard",
                        "Squirtle", "Wartortle", "Blastoise",
                        "Caterpie", "Metapod", "Butterfree",
                        "Weedle", "Kakuna", "Beedrill",
                        "Pikachu"
                    ),
                    onFirstPokemonSelected = { viewModel.setFirstIngredient(it) },
                    selectNext = { navController.navigate(AppScreen.CreateMealSecond.name) }
                )
            }

            composable(route = AppScreen.CreateMealSecond.name) {
                MealSecondPopupBox(
                    pokemonList = listOf(
                        "Bulbasaur", "Ivysaur", "Venusaur",
                        "Charmander", "Charmeleon", "Charizard",
                        "Squirtle", "Wartortle", "Blastoise",
                        "Caterpie", "Metapod", "Butterfree",
                        "Weedle", "Kakuna", "Beedrill",
                        "Pikachu"
                    ),
                    onSecondPokemonSelected = { viewModel.setFirstIngredient(it) },
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