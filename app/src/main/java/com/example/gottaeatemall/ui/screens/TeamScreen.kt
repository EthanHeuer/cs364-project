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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenAppBar
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenDrawer
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenSlot
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamViewModel
import kotlinx.coroutines.launch

/**
 * Screen for displaying a team
 * @param activeTeamId The id of the team to be displayed
 * @param onTeamCreate Callback for when a new team is created
 * @param onTeamEdit Callback for when a team is edited
 * @param onTeamDelete Callback for when a team is deleted
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    activeTeamId: Int,
    onTeamCreate: () -> Unit = {},
    onTeamEdit: (Int) -> Unit,
    onTeamDelete: (Int) -> Unit
) {
    val viewModel: TeamViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    viewModel.setTeam(activeTeamId)

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

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