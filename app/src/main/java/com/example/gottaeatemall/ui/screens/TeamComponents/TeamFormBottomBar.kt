package com.example.gottaeatemall.ui.screens.TeamComponents

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.TeamTemplate
import com.example.gottaeatemall.ui.theme.DarkGray
import com.example.gottaeatemall.ui.theme.Gray
import com.example.gottaeatemall.ui.theme.LightBlue

/**
 * Component for displaying a secondary button.
 * @param label The label to display on the button.
 * @param onClick The callback to call when the user clicks the button.
 */
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
            contentColor = LightBlue
        )
    ) {
        Text(text = label)
    }
}

/**
 * Validates the team form and calls the onClick callback if the form is valid.
 * @param teamTemplate The team template to validate.
 * @param context The context to use for displaying the toast.
 * @param onClick The callback to call if the form is valid.
 */
fun validateForm(
    teamTemplate: TeamTemplate,
    context: Context,
    onClick: (TeamTemplate) -> Unit,
) {
    if (
        teamTemplate.name.isNotEmpty() &&
        teamTemplate.pokemon.all { it.isNotEmpty() }
    ) {
        onClick(teamTemplate)
    } else {
        Toast.makeText(
            context,
            "Please fill out all fields",
            Toast.LENGTH_SHORT
        ).show()
    }
}

/**
 * Component for displaying the bottom bar in the team form.
 * @param teamTemplate The team template to display.
 * @param onSubmit The callback to call when the user clicks the submit button.
 * @param onSave The callback to call when the user clicks the save button.
 * @param onCancel The callback to call when the user clicks the cancel button.
 * @param editMode Whether the team is in edit mode or not.
 */
@Composable
fun TeamFormBottomBar(
    teamTemplate: TeamTemplate = TeamTemplate(),
    onSubmit: (TeamTemplate) -> Unit,
    onSave: (TeamTemplate) -> Unit,
    onCancel: () -> Unit,
    editMode: Boolean
) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Cancel button
        OutlinedButton(
            onClick = onCancel,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = DarkGray
            ),
            border = BorderStroke(2.dp, DarkGray),
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = stringResource(R.string.cancel))
        }

        // Save or Submit button
        SecondaryButton(
            label = if (editMode) stringResource(R.string.save) else stringResource(R.string.submit),
            onClick = {
                validateForm(
                    teamTemplate = teamTemplate,
                    context = context,
                    onClick = {
                        if (editMode) onSave(TeamTemplate(it.name, it.pokemon)) else
                            onSubmit(TeamTemplate(it.name, it.pokemon))
                    }
                )
            }
        )
    }
}