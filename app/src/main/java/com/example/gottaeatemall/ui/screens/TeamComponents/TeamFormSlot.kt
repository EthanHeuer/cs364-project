package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import com.example.gottaeatemall.data.FakeDatabase
import com.example.gottaeatemall.data.PokemonSchema

/**
 * Component for displaying a slot in the team form.
 * @param label The label for the slot.
 * @param value The value of the slot.
 * @param onValueChange The callback to call when the value of the slot changes.
 * @param onSubmit The callback to call when the user submits the slot.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamFormSlot(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    val options = FakeDatabase.getInstance().querySelect<PokemonSchema>(
        from = "pokemon"
    )
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    val matches = options.filter { it.name.contains(searchText, true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp)
    ) {
        // Container for the text field and the dropdown menu
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.weight(1f)
        ) {
            // Text field for the selection
            OutlinedTextField(
                value = value,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                onValueChange = { onValueChange(it) },
                readOnly = true,
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
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth()
                )

                if (matches.isEmpty()) {
                    DropdownMenuItem(
                        onClick = { },
                        enabled = false,
                        text = { Text(text = stringResource(R.string.no_matches_found)) }
                    )
                } else {
                    matches.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                onValueChange(selectionOption.name)
                                onSubmit(selectionOption.name)
                                searchText = ""
                                expanded = false
                            },
                            text = { Text(text = selectionOption.name) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}