package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.gottaeatemall.ui.theme.DarkGray
import com.example.gottaeatemall.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamFormTopBar(
    title: String,
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
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