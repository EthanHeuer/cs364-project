package com.example.gottaeatemall.ui.screens


import android.util.Log
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
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.gottaeatemall.data.AppDatabase
import com.example.gottaeatemall.data.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SearchScreen(database: AppDatabase) {
    var query by remember { mutableStateOf("") }
    val lifecycleOwner = LocalLifecycleOwner.current

    suspend fun searchDatabase(query: String): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            database.pokemonDao().searchPokemon("%$query%")
        }
    }


    Column {
        TextField(modifier = Modifier.fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Red,
                shape = RoundedCornerShape(8.dp)
            ),
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") }
        )
        Button (
            onClick = {
                lifecycleOwner.lifecycleScope.launch {
                    val results = searchDatabase("$query")
                    Log.d("SearchScreen", "Results: $results")
                }
            }
        ) {
            Text ("Search")
        }
    }
}
