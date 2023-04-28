package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.ui.screens.TeamTemplate

@Composable
fun TeamFormBottomBar(
    name: String,
    slot1: String,
    slot2: String,
    slot3: String,
    slot4: String,
    slot5: String,
    slot6: String,
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
                .padding(10.dp)
        ) {
            Text(text = "Cancel")
        }

        if (editMode) {
            // Save button
            Button(
                onClick = {
                    if (
                        name.isNotEmpty() &&
                        slot1.isNotEmpty() &&
                        slot2.isNotEmpty() &&
                        slot3.isNotEmpty() &&
                        slot4.isNotEmpty() &&
                        slot5.isNotEmpty() &&
                        slot6.isNotEmpty()
                    ) {
                        onSave(
                            TeamTemplate(
                                name,
                                slot1,
                                slot2,
                                slot3,
                                slot4,
                                slot5,
                                slot6
                            )
                        )
                    }
                },
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(text = "Save")
            }
        } else {
            // Submit button
            Button(
                onClick = {
                    if (
                        name.isNotEmpty() &&
                        slot1.isNotEmpty() &&
                        slot2.isNotEmpty() &&
                        slot3.isNotEmpty() &&
                        slot4.isNotEmpty() &&
                        slot5.isNotEmpty() &&
                        slot6.isNotEmpty()
                    ) {
                        onSubmit(
                            TeamTemplate(
                                name,
                                slot1,
                                slot2,
                                slot3,
                                slot4,
                                slot5,
                                slot6
                            )
                        )
                    }
                },
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
}