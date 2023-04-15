package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gottaeatemall.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TeamUIState(
    var teamId: Int = 0,
    var teamName: String = "",
    var pokemon: List<Int> = listOf(0, 0, 0, 0, 0, 0)
)

class TeamViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TeamUIState())
    val uiState: StateFlow<TeamUIState> = _uiState.asStateFlow()

    fun setTeamId(id: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                teamId = id
            )
        }
    }

    fun setTeamName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                teamName = name
            )
        }
    }

    fun setPokemon(pokemon: List<Int>) {
        _uiState.update { currentState ->
            currentState.copy(
                pokemon = pokemon
            )
        }
    }

    fun setTeam(team: Team) {
        _uiState.update { currentState ->
            currentState.copy(
                teamId = team.id,
                teamName = team.name,
                pokemon = team.pokemon
            )
        }
    }
}

class Pokemon(
    var id: Int,
    var name: String,
    var type1: String,
    var type2: String
)

class Team(
    var id: Int,
    var name: String,
    var pokemon: List<Int> = listOf()
)

val pokemon: List<Pokemon> = listOf(
    Pokemon(0, "", "", ""),
    Pokemon(1, "Bulbasaur", "Grass", "Poison"),
    Pokemon(2, "Ivysaur", "Grass", "Poison"),
    Pokemon(3, "Venusaur", "Grass", "Poison"),
    Pokemon(4, "Charmander", "Fire", ""),
    Pokemon(5, "Charmeleon", "Fire", ""),
    Pokemon(6, "Charizard", "Fire", "Flying"),
    Pokemon(7, "Squirtle", "Water", ""),
    Pokemon(8, "Wartortle", "Water", ""),
    Pokemon(9, "Blastoise", "Water", ""),
    Pokemon(10, "Caterpie", "Bug", ""),
    Pokemon(11, "Metapod", "Bug", ""),
    Pokemon(12, "Butterfree", "Bug", "Flying"),
    Pokemon(13, "Weedle", "Bug", "Poison"),
    Pokemon(14, "Kakuna", "Bug", "Poison"),
    Pokemon(15, "Beedrill", "Bug", "Poison"),
    Pokemon(16, "Pikachu", "Electric", ""),
    Pokemon(17, "Raichu", "Electric", ""),
    Pokemon(18, "Sandshrew", "Ground", ""),
    Pokemon(19, "Sandslash", "Ground", ""),
    Pokemon(20, "Nidoran♀", "Poison", ""),
    Pokemon(21, "Nidorina", "Poison", ""),
    Pokemon(22, "Nidoqueen", "Poison", "Ground"),
)

val teams: List<Team> = listOf(
    Team(0, "Team 1", listOf(1, 2, 3, 4, 5, 6)),
    Team(1, "Team 2", listOf(7, 8, 9, 10, 11, 12)),
    Team(2, "Team 3", listOf(13, 14, 15, 16, 17, 18))
)

/**
 * A screen that displays a list of teams.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamSlot(
    label: String,
    value: String
) {
    val options = pokemon
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(value) }

    val matches = options.filter { it.name.contains(selectedText, true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .width(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Container for the text field and the dropdown menu
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                // Text field for the selection
                OutlinedTextField(
                    value = selectedText,
                    modifier = Modifier.menuAnchor(),
                    onValueChange = { selectedText = it },
                    label = { Text(text = label) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }
                )

                // Dropdown menu for the selection
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    if (matches.isEmpty()) {
                        DropdownMenuItem(
                            onClick = { },
                            enabled = false,
                            text = { Text(text = "No matches found") }
                        )
                    } else {
                        matches.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedText = selectionOption.name
                                    expanded = false
                                },
                                text = { Text(text = selectionOption.name) }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "type 1")
                Text(text = "type 2")
            }
        }
    }
}

/**
 * Top app bar for the team screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreenAppBar(
    viewModel: TeamViewModel,
    uiState: TeamUIState,
    onActionDrawer: () -> Unit,
    onActionEdit: () -> Unit
) {
    TopAppBar(
        title = { Text(text = uiState.teamName) },
        navigationIcon = {
            IconButton(
                onClick = { onActionDrawer() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onActionEdit() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
            }
        }
    )
}

/**
 * Drawer for the team screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDrawer(
    viewModel: TeamViewModel,
    uiState: TeamUIState,
    onButtonNewTeam: () -> Unit,
    onButtonSetTeam: (Team) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Title
        Row {
            Text(
                text = "Your Teams",
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // List of teams
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(teams) { team ->
                NavigationDrawerItem(
                    label = { Text(text = team.name) },
                    selected = team.id == uiState.teamId,
                    onClick = { onButtonSetTeam(team) }
                )
            }
        }

        // New team button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(onClick = { onButtonNewTeam() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "New Team")
            }
        }
    }
}

/**
 * Dialog for editing the team name
 * @param openDialog mutable state for the dialog
 * @param value current value of the team name
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamEditDialog(
    openDialog: MutableState<Boolean>,
    value: String,
    onButtonSave: () -> Unit
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "New Team Name") },
            text = {
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        openDialog.value = false
                        onButtonSave()
                    },
                    label = { Text(text = "Team Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

/**
 * Screen for the team
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen() {
    val viewModel: TeamViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val openDialog = remember { mutableStateOf(false) }
    val editNameValue by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                TeamDrawer(
                    viewModel = viewModel,
                    uiState = uiState,
                    onButtonNewTeam = {
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
                    viewModel = viewModel,
                    uiState = uiState,
                    onActionDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onActionEdit = {
                        openDialog.value = true
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    uiState.pokemon.forEach { pokemonID ->
                        TeamSlot(
                            label = "Member",
                            value = pokemon[pokemonID].name,
                        )
                    }
                }
            }
        }
    }

    TeamEditDialog(
        openDialog = openDialog,
        value = editNameValue,
        onButtonSave = {
            viewModel.setTeamName(editNameValue)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TeamScreenPreview() {
    TeamScreen()
}