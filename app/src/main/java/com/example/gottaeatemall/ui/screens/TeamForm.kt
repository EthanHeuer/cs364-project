package com.example.gottaeatemall.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamFormBottomBar
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamFormSlot
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamFormTopBar

data class TeamTemplate(
    var name: String = "",
    val pokemon: List<String> = listOf("", "", "", "", "", "")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamForm(
    teamTemplate: TeamTemplate = TeamTemplate(),
    onSubmit: (TeamTemplate) -> Unit,
    onSave: (TeamTemplate) -> Unit,
    onCancel: () -> Unit,
    editMode: Boolean
) {
    var nameValue by remember { mutableStateOf(teamTemplate.name) }
    var slot1Value by remember { mutableStateOf(teamTemplate.pokemon[0]) }
    var slot2Value by remember { mutableStateOf(teamTemplate.pokemon[1]) }
    var slot3Value by remember { mutableStateOf(teamTemplate.pokemon[2]) }
    var slot4Value by remember { mutableStateOf(teamTemplate.pokemon[3]) }
    var slot5Value by remember { mutableStateOf(teamTemplate.pokemon[4]) }
    var slot6Value by remember { mutableStateOf(teamTemplate.pokemon[5]) }

    Scaffold(
        topBar = {
            val title = if (editMode) "Edit Team" else "Create New Team"
            TeamFormTopBar(title = title, onBack = onCancel)
        },
        bottomBar = {
            TeamFormBottomBar(
                teamTemplate = TeamTemplate(
                    nameValue,
                    listOf(
                        slot1Value,
                        slot2Value,
                        slot3Value,
                        slot4Value,
                        slot5Value,
                        slot6Value
                    )
                ),
                onSubmit = onSubmit,
                onSave = onSave,
                onCancel = onCancel,
                editMode = editMode
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = nameValue,
                    onValueChange = { nameValue = it },
                    label = { Text(text = "Team Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            TeamFormSlot(
                label = "Pokemon 1",
                value = slot1Value,
                onValueChange = {},
                onSubmit = { slot1Value = it })
            TeamFormSlot(
                label = "Pokemon 2",
                value = slot2Value,
                onValueChange = {},
                onSubmit = { slot2Value = it })
            TeamFormSlot(
                label = "Pokemon 3",
                value = slot3Value,
                onValueChange = {},
                onSubmit = { slot3Value = it })
            TeamFormSlot(
                label = "Pokemon 4",
                value = slot4Value,
                onValueChange = {},
                onSubmit = { slot4Value = it })
            TeamFormSlot(
                label = "Pokemon 5",
                value = slot5Value,
                onValueChange = {},
                onSubmit = { slot5Value = it })
            TeamFormSlot(
                label = "Pokemon 6",
                value = slot6Value,
                onValueChange = {},
                onSubmit = { slot6Value = it })
        }
    }
}