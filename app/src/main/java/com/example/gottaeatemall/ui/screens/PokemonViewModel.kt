package com.example.gottaeatemall.ui.screens

import androidx.lifecycle.ViewModel
import com.example.gottaeatemall.data.PokemonUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PokemonViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUIState())
    val uiState: StateFlow<PokemonUIState> = _uiState.asStateFlow()

    fun setFirstIngredient(fI: String){
        _uiState.update { currentState ->
            currentState.copy(firstIngredient = fI)
        }
    }

    fun setSecondIngredient(sI: String){
        _uiState.update { currentState ->
            currentState.copy(secondIngredient = sI)
        }
    }

    fun resetOrder(){
        _uiState.value = PokemonUIState()
    }
}