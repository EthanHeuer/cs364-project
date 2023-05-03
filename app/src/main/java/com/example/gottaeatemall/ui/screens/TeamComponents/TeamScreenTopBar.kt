package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.TeamUIState
import com.example.gottaeatemall.ui.theme.DarkGray
import com.example.gottaeatemall.ui.theme.LightGray

/**
 * Component for displaying the app bar in the team screen.
 * @param uiState The state of the team screen.
 * @param onDrawer The callback to call when the user clicks the drawer button.
 * @param onEdit The callback to call when the user clicks the edit button.
 * @param onDelete The callback to call when the user clicks the delete button.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreenAppBar(
    uiState: TeamUIState,
    onDrawer: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = uiState.name)
        },
        navigationIcon = {
            // Drawer button
            IconButton(
                onClick = onDrawer
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu_toggle),
                    tint = LightGray
                )
            }
        },
        actions = {
            // Edit button
            IconButton(
                onClick = onEdit
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.edit_team),
                    tint = LightGray
                )
            }

            // Delete button
            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(R.string.delete_team),
                    tint = LightGray
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = DarkGray,
            titleContentColor = LightGray
        )
    )
}