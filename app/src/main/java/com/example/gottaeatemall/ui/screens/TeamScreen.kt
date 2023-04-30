package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gottaeatemall.data.FakeDatabase
import com.example.gottaeatemall.data.PokemonSchema
import com.example.gottaeatemall.data.TeamPokemonSchema
import com.example.gottaeatemall.data.TeamSchema
import com.example.gottaeatemall.data.TeamUIState
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenAppBar
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenDrawer
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenSlot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TeamUIState())
    val uiState: StateFlow<TeamUIState> = _uiState.asStateFlow()

    fun setTeam(teamId: Int) {
        val team = FakeDatabase.getInstance().querySelect<TeamSchema>(
            from = "teams",
            where = { it.id == teamId }
        ).first()

        val teamPokemon = FakeDatabase.getInstance().querySelect<TeamPokemonSchema>(
            from = "team_pokemon",
            where = { it.teamId == team.id },
            orderBy = { it.slotId }
        )

        val pokemon = teamPokemon.map { slot ->
            FakeDatabase.getInstance().querySelect<PokemonSchema>(
                from = "pokemon",
                where = { it.id == slot.pokemonId }
            ).first()
        }

        _uiState.update { uiState ->
            uiState.copy(
                teamId = team.id,
                name = team.name,
                pokemon = pokemon
            )
        }
    }
}

/**
 * Screen for the team
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    activeTeamId: Int,
    onTeamCreate: () -> Unit = {},
    onTeamEdit: (Int) -> Unit,
    onTeamDelete: (Int) -> Unit
) {
    val td = FakeDatabase.getInstance().querySelect<TeamPokemonSchema>(
        from = "team_pokemon",
        where = { it.teamId == activeTeamId },
        orderBy = { it.slotId }
    )

    for (slot in td) {
        val pokemon = FakeDatabase.getInstance().querySelect<PokemonSchema>(
            from = "pokemon",
            where = { it.id == slot.pokemonId }
        ).first()

        println("${slot.slotId} ${slot.pokemonId} -> ${pokemon.name} ${pokemon.id}")
    }

    val viewModel: TeamViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    viewModel.setTeam(activeTeamId)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                TeamScreenDrawer(
                    uiState = uiState,
                    onButtonNewTeam = {
                        onTeamCreate()

                        scope.launch {
                            drawerState.close()
                        }
                    },
                    onButtonSetTeam = { teamId ->
                        viewModel.setTeam(teamId)

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

            }
        }
    ) {
        Scaffold(
            topBar = {
                TeamScreenAppBar(
                    uiState = uiState,
                    onDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onEdit = { onTeamEdit(uiState.teamId) },
                    onDelete = { onTeamDelete(uiState.teamId) }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                LazyColumn(
                    modifier = Modifier.padding(8.dp)
                ) {
                    item {
                        for (i in 0..5) {
                            TeamScreenSlot(
                                slot = i + 1,
                                pokemon = uiState.pokemon[i],
                                onPokemonSelected = {}
                            )
                        }
                    }
                }
            }
        }
    }
}