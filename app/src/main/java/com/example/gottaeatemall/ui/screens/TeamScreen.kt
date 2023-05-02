package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenAppBar
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenDrawer
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamScreenSlot
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamViewModel
import kotlinx.coroutines.launch

/**
 * Screen for displaying a team
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    viewModel: TeamViewModel,
    onTeamCreate: () -> Unit = {},
    onTeamEdit: (Int) -> Unit,
    onTeamDelete: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val deleteTeamDialog = remember { mutableStateOf(false) }

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
                    onEdit = { onTeamEdit(uiState.activeTeamId) },
                    onDelete = { deleteTeamDialog.value = true }
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

    if (deleteTeamDialog.value) {
        AlertDialog(
            title = { Text(stringResource(R.string.delete_dialog_title)) },
            text = {
                Text(
                    text = stringResource(R.string.delete_dialog_text),
                    softWrap = true
                )
            },
            onDismissRequest = {
                deleteTeamDialog.value = false
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { deleteTeamDialog.value = false }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onTeamDelete(uiState.activeTeamId)

                        deleteTeamDialog.value = false
                    }
                ) {
                    Text(text = stringResource(R.string.confirm))
                }
            }
        )
    }
}