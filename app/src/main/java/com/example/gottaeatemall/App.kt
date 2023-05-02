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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gottaeatemall.data.AppDatabase
import com.example.gottaeatemall.data.DataSource.PokemonList
import com.example.gottaeatemall.data.FakeDatabase
import com.example.gottaeatemall.data.PokemonSchema
import com.example.gottaeatemall.data.TeamPokemonSchema
import com.example.gottaeatemall.data.TeamSchema
import com.example.gottaeatemall.data.TeamTemplate
import com.example.gottaeatemall.ui.screens.CardScreen
import com.example.gottaeatemall.ui.screens.DetailScreen
import com.example.gottaeatemall.ui.screens.HomeScreen
import com.example.gottaeatemall.ui.screens.MealPopupBox
import com.example.gottaeatemall.ui.screens.MealScreen
import com.example.gottaeatemall.ui.screens.PokemonViewModel
import com.example.gottaeatemall.ui.screens.SearchScreen
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamViewModel
import com.example.gottaeatemall.ui.screens.TeamForm
import com.example.gottaeatemall.ui.screens.TeamScreen
import com.example.gottaeatemall.ui.screens.mealSummary
import com.example.gottaeatemall.ui.theme.Red
import java.util.UUID

enum class AppScreen(@StringRes val title: Int) {
    Home(title = R.string.page_home),
    Search(title = R.string.page_search),
    Team(title = R.string.page_team),
    Meal(title = R.string.page_meal),
    SelectIngredients(title = R.string.ingredient_list),
    MealSummary(title = R.string.meal_summary),
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
    navPageCard: () -> Unit = {}
) {
    BottomAppBar(
        contentPadding = PaddingValues(0.dp),
        backgroundColor = Red
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
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
    viewModel: PokemonViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    viewModelTeam: TeamViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current
    val database = AppDatabase.getInstance(context)
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Home.name
    )

    val uiState by viewModel.uiState.collectAsState()
    val uiStateTeam by viewModelTeam.uiState.collectAsState()

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
                SearchScreen(database)
            }

            composable(route = AppScreen.Team.name) {
                TeamScreen(
                    viewModel = viewModelTeam,
                    onTeamCreate = { navController.navigate(AppScreen.TeamForm.name) },
                    onTeamEdit = { teamId ->
                        viewModelTeam.setActiveTeamId(teamId)
                        navController.navigate(AppScreen.TeamFormEdit.name)
                    },
                    onTeamDelete = { teamId ->
                        var teams = FakeDatabase.getInstance().querySelect<TeamSchema>(
                            from = "teams"
                        )

                        val teamIndex = teams.indexOfFirst { it.id == teamId }

                        FakeDatabase.getInstance().queryDelete<TeamSchema>(
                            from = "teams",
                            where = { it.id == teamId }
                        )

                        FakeDatabase.getInstance().queryDelete<TeamPokemonSchema>(
                            from = "team_pokemon",
                            where = { it.teamId == teamId }
                        )

                        teams = FakeDatabase.getInstance().querySelect<TeamSchema>(
                            from = "teams"
                        )

                        if (teamIndex > teams.size - 1) {
                            viewModelTeam.setTeam(teams[teams.size - 1].id)
                        } else {
                            viewModelTeam.setTeam(teams[teamIndex].id)
                        }
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
                val team = FakeDatabase.getInstance().querySelect<TeamSchema>(
                    from = "teams",
                    where = { it.id == uiStateTeam.activeTeamId }
                ).first()

                val teamPokemon = FakeDatabase.getInstance().querySelect<TeamPokemonSchema>(
                    from = "team_pokemon",
                    where = { it.teamId == uiStateTeam.activeTeamId },
                    orderBy = { it.slotId }
                )

                val teamPokemonMap = teamPokemon.map { teamMember ->
                    val pokemon = FakeDatabase.getInstance().querySelect<PokemonSchema>(
                        from = "pokemon",
                        where = { it.id == teamMember.pokemonId }
                    ).first()

                    pokemon.name
                }

                val teamTemplate = TeamTemplate(
                    name = team.name,
                    pokemon = teamPokemonMap
                )

                TeamForm(
                    teamTemplate = teamTemplate,
                    onSubmit = {},
                    onSave = { updatedTeam ->
                        // Update team name
                        FakeDatabase.getInstance().queryUpdate(
                            tableName = "teams",
                            values = TeamSchema(
                                id = uiStateTeam.activeTeamId,
                                name = updatedTeam.name
                            ),
                            where = { it.id == uiStateTeam.activeTeamId }
                        )

                        // Update team pokemon
                        for (i in 0..5) {
                            val pokemon = FakeDatabase.getInstance().querySelect<PokemonSchema>(
                                from = "pokemon",
                                where = { it.name == updatedTeam.pokemon[i] }
                            ).first()

                            FakeDatabase.getInstance().queryUpdate(
                                tableName = "team_pokemon",
                                values = TeamPokemonSchema(
                                    id = UUID.randomUUID().hashCode(),
                                    teamId = uiStateTeam.activeTeamId,
                                    slotId = i + 1,
                                    pokemonId = pokemon.id
                                ),
                                where = { it.teamId == uiStateTeam.activeTeamId && it.slotId == i + 1 }
                            )
                        }

                        viewModelTeam.setTeam(uiStateTeam.activeTeamId)

                        navController.navigate(AppScreen.Team.name)
                    },
                    onCancel = { navController.navigate(AppScreen.Team.name) },
                    editMode = true
                )
            }

            composable(route = AppScreen.Meal.name) {
                MealScreen(
                    onMealCreate =
                    { navController.navigate(AppScreen.SelectIngredients.name) },
                )
            }

            composable(route = AppScreen.SelectIngredients.name) {
                MealPopupBox(
                    pokemonList = PokemonList,
                    onFirstPokemonSelected = { viewModel.addIngredient(it) },
                    selectNext = { navController.navigate(AppScreen.MealSummary.name) },
                    title = stringResource(id = R.string.choose_pokemon_meal),
                    reset = { resetMeal(viewModel, navController) }
                )
            }

            composable(route = AppScreen.MealSummary.name) {
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
) {
    viewModel.resetOrder()
    navController.popBackStack(AppScreen.Meal.name, false)
}

fun resetMeal(
    viewModel: PokemonViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.navigate(AppScreen.SelectIngredients.name)
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}