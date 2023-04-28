package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.*
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
import com.example.gottaeatemall.data.Team
import com.example.gottaeatemall.data.TeamUIState
import com.example.gottaeatemall.data.getPokemonName
import com.example.gottaeatemall.data.teamsData
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

    fun setTeam(team: Team) {
        _uiState.update { uiState ->
            uiState.copy(
                teamId = team.id,
                teamName = team.name,
                pokemon1 = getPokemonName(team.pokemon1),
                pokemon2 = getPokemonName(team.pokemon2),
                pokemon3 = getPokemonName(team.pokemon3),
                pokemon4 = getPokemonName(team.pokemon4),
                pokemon5 = getPokemonName(team.pokemon5),
                pokemon6 = getPokemonName(team.pokemon6)
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
    onTeamCreate: () -> Unit = {},
    onTeamEdit: (Int) -> Unit,
    onTeamDelete: (Int) -> Unit
) {
    val viewModel: TeamViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    viewModel.setTeam(teamsData[0])

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
                    onButtonSetTeam = { team ->
                        viewModel.setTeam(team)

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
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    TeamScreenSlot(slot = 1, pokemonName = uiState.pokemon1, onPokemonSelected = {})
                    TeamScreenSlot(slot = 2, pokemonName = uiState.pokemon2, onPokemonSelected = {})
                    TeamScreenSlot(slot = 3, pokemonName = uiState.pokemon3, onPokemonSelected = {})
                    TeamScreenSlot(slot = 4, pokemonName = uiState.pokemon4, onPokemonSelected = {})
                    TeamScreenSlot(slot = 5, pokemonName = uiState.pokemon5, onPokemonSelected = {})
                    TeamScreenSlot(slot = 6, pokemonName = uiState.pokemon6, onPokemonSelected = {})
                }
            }
        }
    }
}