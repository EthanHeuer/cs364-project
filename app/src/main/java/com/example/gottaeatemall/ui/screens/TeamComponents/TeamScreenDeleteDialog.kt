package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.TeamUIState

/**
 * Component for displaying the delete dialog in the team screen.
 * @param state The state of the dialog.
 * @param uiState The UI state of the team.
 * @param onTeamDelete The callback to call when the user confirms the deletion.
 */
@Composable
fun TeamScreenDeleteDialog(
    state: MutableState<Boolean>,
    uiState: TeamUIState,
    onTeamDelete: (Int) -> Unit
) {
    AlertDialog(
        title = { Text(stringResource(R.string.delete_dialog_title)) },
        text = {
            Text(
                text = stringResource(R.string.delete_dialog_text),
                softWrap = true
            )
        },
        onDismissRequest = {
            state.value = false
        },
        dismissButton = {
            OutlinedButton(
                onClick = { state.value = false }
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onTeamDelete(uiState.activeTeamId)

                    state.value = false
                }
            ) {
                Text(text = stringResource(R.string.confirm))
            }
        }
    )
}