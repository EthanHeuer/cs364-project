package com.example.gottaeatemall.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
    var searchResults by remember { mutableStateOf(emptyList<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search") }
            )
        }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchBar(onSearch = { query ->
                // Perform search operation and update searchResults
                searchResults = listOf(query)
            })

            LazyColumn {
                items(searchResults) { result ->
                    // Display search result
                    Text(text = result)
                }
            }
        }
    }
}




@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        placeholder = { Text(text = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchText)
            }
        )
    )
}

