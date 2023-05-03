package com.example.gottaeatemall.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class SearchViewModel : ViewModel() {
    private val _searchResults = MutableLiveData<List<Pokemon>>()
    val searchResults: MutableLiveData<List<Pokemon>> = _searchResults

    fun search(query: String,repository: PokemonRepository) {
        viewModelScope.launch {
            val results = repository.searchPokemon(query)
            _searchResults.value = results
            Log.d("Results", searchResults.value.toString() )
        }
    }
}
