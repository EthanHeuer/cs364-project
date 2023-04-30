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
import com.example.gottaeatemall.data.TeamUIState
import com.example.gottaeatemall.ui.theme.DarkGray
import com.example.gottaeatemall.ui.theme.LightGray

/**
 * Top app bar for the team screen
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
            IconButton(
                onClick = { onDrawer() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                    tint = LightGray
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onEdit() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = LightGray
                )
            }
            IconButton(
                onClick = { onDelete() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
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