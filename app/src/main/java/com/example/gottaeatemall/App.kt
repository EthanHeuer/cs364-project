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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gottaeatemall.data.FakeDatabase
import com.example.gottaeatemall.data.PokemonSchema
import com.example.gottaeatemall.data.TeamPokemonSchema
import com.example.gottaeatemall.data.TeamSchema
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
import com.example.gottaeatemall.ui.theme.Red
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
        backgroundColor = Red
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
                icon = { Icon(imageVector = Icons.Default.Menu, "") },
                label = { Text("Team") },
                selected = (currentScreen.name == AppScreen.Team.name),
                onClick = navPageTeam
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.Favorite, "") },
                label = { Text("Meal") },
                selected = (currentScreen.name == AppScreen.Meal.name),
                onClick = navPageMeal
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Default.AccountBox, "") },
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

    var activeTeamId by remember { mutableStateOf(1) }
    var deleteTeamDialog = remember { mutableStateOf(false) }

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
            startDestination = AppScreen.Team.name,
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
                    activeTeamId = activeTeamId,
                    onTeamCreate = { navController.navigate(AppScreen.TeamForm.name) },
                    onTeamEdit = { teamId ->
                        activeTeamId = teamId
                        navController.navigate(AppScreen.TeamFormEdit.name)
                    },
                    onTeamDelete = { teamId ->
                        deleteTeamDialog.value = true
                        /*
                        FakeDatabase.getInstance().queryDelete<TeamSchema>(
                            from = "teams",
                            where = { it.id == teamId }
                        )

                        FakeDatabase.getInstance().queryDelete<TeamPokemonSchema>(
                            from = "team_pokemon",
                            where = { it.teamId == teamId }
                        )
                        */
                    }
                )
            }

            composable(route = AppScreen.TeamForm.name) {
                TeamForm(
                    onSubmit = { newTeam ->
                        val teamId = UUID.randomUUID().hashCode()

                        FakeDatabase.getInstance().queryInsert(
                            into = "teams",
                            values = TeamSchema(
                                id = teamId,
                                name = newTeam.name
                            )
                        )

                        for (i in 0..5) {
                            val pokemon = FakeDatabase.getInstance().querySelect<PokemonSchema>(
                                from = "pokemon",
                                where = { it.name == newTeam.pokemon[i] }
                            ).first()

                            FakeDatabase.getInstance().queryInsert(
                                into = "team_pokemon",
                                values = TeamPokemonSchema(
                                    id = UUID.randomUUID().hashCode(),
                                    teamId = teamId,
                                    slotId = i + 1,
                                    pokemonId = pokemon.id
                                )
                            )
                        }

                        navController.navigate(AppScreen.Team.name)
                    },
                    onSave = { },
                    onCancel = { navController.navigate(AppScreen.Team.name) },
                    editMode = false
                )
            }

            composable(route = AppScreen.TeamFormEdit.name) {
                TeamForm(
                    onSubmit = {},
                    onSave = { team ->
                        println("SAVE TEAM")
                        println(team.name)

                        for (i in 0..5) {
                            println(team.pokemon[i])
                        }

                        // Update team name
                        FakeDatabase.getInstance().queryUpdate(
                            tableName = "teams",
                            values = TeamSchema(
                                id = activeTeamId,
                                name = team.name
                            ),
                            where = { it.id == activeTeamId }
                        )

                        // Update team pokemon
                        for (i in 0..5) {
                            val pokemon = FakeDatabase.getInstance().querySelect<PokemonSchema>(
                                from = "pokemon",
                                where = { it.name == team.pokemon[i] }
                            ).first()

                            FakeDatabase.getInstance().queryUpdate(
                                tableName = "team_pokemon",
                                values = TeamPokemonSchema(
                                    id = UUID.randomUUID().hashCode(),
                                    teamId = activeTeamId,
                                    slotId = i + 1,
                                    pokemonId = pokemon.id
                                ),
                                where = { it.teamId == activeTeamId && it.slotId == i + 1 }
                            )
                        }

                        navController.navigate(AppScreen.Team.name)
                    },
                    onCancel = { navController.navigate(AppScreen.Team.name) },
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

    if (deleteTeamDialog.value) {
        AlertDialog(
            title = { androidx.compose.material3.Text("Delete Team") },
            text = {
                androidx.compose.material3.Text(
                    text = "Are you sure you want to delete this team? This action cannot be undone!",
                    softWrap = true
                )
            },
            onDismissRequest = {
            },
            dismissButton = {
                deleteTeamDialog.value = false
                androidx.compose.material3.Text("Cancel")
            },
            confirmButton = {
                androidx.compose.material3.Text("Confirm")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}