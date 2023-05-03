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
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenAppBar
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenDeleteDialog
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenDrawer
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenSlot
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamViewModel
import kotlinx.coroutines.launch

/**
 * Component for displaying the team screen.
 * @param viewModel The view model for the team screen.
 * @param onTeamCreate The callback to call when the user clicks the new team button.
 * @param onTeamEdit The callback to call when the user clicks the edit team button.
 * @param onTeamDelete The callback to call when the user clicks the delete team button.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    viewModel: TeamViewModel = TeamViewModel(),
    onTeamCreate: () -> Unit = {},
    onTeamEdit: (Int) -> Unit = {},
    onTeamDelete: (Int) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.activeTeamId == 0) {
        viewModel.setTeam(1)
    }

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val deleteTeamDialog = remember { mutableStateOf(false) }

    // Team Drawer and Scaffold
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
        // Team Scaffold
        Scaffold(
            topBar = {
                TeamScreenAppBar(
                    uiState = uiState,
                    onDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onEdit = { onTeamEdit(uiState.activeTeamId) },
                    onDelete = { deleteTeamDialog.value = true }
                )
            }
        ) { innerPadding ->
            // Team Slots
            Box(modifier = Modifier.padding(innerPadding)) {
                LazyColumn(
                    modifier = Modifier.padding(8.dp)
                ) {
                    item {
                        if (uiState.pokemon.isNotEmpty()) {
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

    // Delete team dialog
    if (deleteTeamDialog.value) {
        TeamScreenDeleteDialog(
            state = deleteTeamDialog,
            uiState = uiState,
            onTeamDelete = onTeamDelete
        )
    }
}