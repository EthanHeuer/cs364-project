package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.data.FakeDatabase
import com.example.gottaeatemall.data.TeamSchema
import com.example.gottaeatemall.data.TeamUIState
import com.example.gottaeatemall.ui.theme.Gray
import com.example.gottaeatemall.ui.theme.LightBlue
import com.example.gottaeatemall.ui.theme.LightGray

/**
 * Drawer for the team screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreenDrawer(
    uiState: TeamUIState,
    onButtonNewTeam: () -> Unit,
    onButtonSetTeam: (Int) -> Unit
) {
    val teamsData = FakeDatabase.getInstance().querySelect<TeamSchema>(
        from = "teams"
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Title
        Row {
            Text(
                text = "Your Teams",
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // List of teams
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(teamsData) { team ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = team.name,
                            fontSize = 20.sp
                        )
                    },
                    selected = team.id == uiState.teamId,
                    onClick = { onButtonSetTeam(team.id) },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = LightBlue
                    )
                )
            }
        }

        // New team button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onButtonNewTeam() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray,
                    contentColor = LightGray
                )
            ) {
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