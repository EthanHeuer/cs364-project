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

    var _ingredientList = mutableListOf<String>()
    fun addIngredient(ingredient: String){
        if (_ingredientList.contains(ingredient)){
            _ingredientList.remove(ingredient)
            _uiState.update {currentState ->
                currentState.copy(ingredients = _ingredientList)
            }
        }
        else{
            _ingredientList.add(ingredient)
            _uiState.update {currentState ->
                currentState.copy(ingredients = _ingredientList)
            }
        }
    }

    fun resetOrder(){
        //_uiState.value = PokemonUIState()
        _ingredientList = mutableListOf()
    }
}