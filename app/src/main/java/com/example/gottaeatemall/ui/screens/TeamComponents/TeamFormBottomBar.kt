package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.ui.screens.TeamTemplate
import com.example.gottaeatemall.ui.theme.Gray
import com.example.gottaeatemall.ui.theme.LightGray

@Composable
fun SecondaryButton(
    label: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Gray,
            contentColor = LightGray
        )
    ) {
        Text(text = label)
    }
}

@Composable
fun TeamFormBottomBar(
    teamTemplate: TeamTemplate = TeamTemplate(),
    onSubmit: (TeamTemplate) -> Unit,
    onSave: (TeamTemplate) -> Unit,
    onCancel: () -> Unit,
    editMode: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Cancel button
        OutlinedButton(
            onClick = onCancel,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = "Cancel")
        }

        if (editMode) {
            // Save button
            SecondaryButton(
                label = "Save",
                onClick = {
                    if (
                        teamTemplate.name.isNotEmpty() &&
                        teamTemplate.pokemon.all { it.isNotEmpty() }
                    ) {
                        onSave(
                            TeamTemplate(
                                teamTemplate.name,
                                teamTemplate.pokemon
                            )
                        )
                    }
                }
            )
        } else {
            // Submit button
            SecondaryButton(
                label = "Submit",
                onClick = {
                    if (
                        teamTemplate.name.isNotEmpty() &&
                        teamTemplate.pokemon.all { it.isNotEmpty() }
                    ) {
                        onSubmit(
                            TeamTemplate(
                                teamTemplate.name,
                                teamTemplate.pokemon
                            )
                        )
                    }
                }
            )
        }
    }
}