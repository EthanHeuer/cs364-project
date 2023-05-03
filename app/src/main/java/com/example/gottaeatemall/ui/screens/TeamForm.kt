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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.TeamTemplate
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamFormBottomBar
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamFormSlot
import com.example.gottaeatemall.ui.screens.TeamComponents.TeamFormTopBar

/**
 * Component for displaying a form for creating or editing a team.
 * @param teamTemplate The team template to use for the form.
 * @param onSubmit The callback to call when the user submits the form.
 * @param onSave The callback to call when the user saves the form.
 * @param onCancel The callback to call when the user cancels the form.
 * @param editMode Whether the team is in edit mode or not.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamForm(
    teamTemplate: TeamTemplate = TeamTemplate(),
    onSubmit: (TeamTemplate) -> Unit = {},
    onSave: (TeamTemplate) -> Unit = {},
    onCancel: () -> Unit = {},
    editMode: Boolean = false
) {
    var nameValue by remember { mutableStateOf(teamTemplate.name) }
    var slotValues by remember { mutableStateOf(teamTemplate.pokemon) }

    Scaffold(
        topBar = {
            val title =
                if (editMode) stringResource(R.string.edit_team) else stringResource(R.string.create_new_team)
            TeamFormTopBar(title = title, onBack = onCancel)
        },
        bottomBar = {
            TeamFormBottomBar(
                teamTemplate = TeamTemplate(
                    nameValue,
                    slotValues
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
                    label = { Text(text = stringResource(R.string.team_name)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            for (i in 0..5) {
                TeamFormSlot(
                    label = "Pokemon ${i + 1}",
                    value = slotValues[i],
                    onValueChange = {},
                    onSubmit = {
                        slotValues =
                            slotValues.mapIndexed { index, value -> if (index == i) it else value }
                    }
                )
            }
        }
    }
}