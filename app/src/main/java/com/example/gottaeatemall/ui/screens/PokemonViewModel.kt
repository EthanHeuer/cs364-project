package com.example.gottaeatemall.ui.screens

import androidx.lifecycle.ViewModel
import com.example.gottaeatemall.data.PokemonUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * The view model for the meal creation screen
 *
 * @property _uiState Current state flow for the meal
 * @property uiState State flow of the current state of the meal ordering screen
 * @property _ingredientList The list of the ingredients selected for the meal
 */
class PokemonViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUIState())
    val uiState: StateFlow<PokemonUIState> = _uiState.asStateFlow()
    var _ingredientList = mutableListOf<String>()

    /**
     * Adds ingredients to the list to display in the summary
     * Also removes the ingredient if it's selected again
     *
     * @param ingredient The ingredient the user has selected
     */
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

    /**
     * Adds the calories to the total
     *
     * @param cal Current calories of the selected Pokemon
     */
    fun addCalories(cal: Int) {
        _uiState.update{currentState ->
            currentState.copy(mealCalories = cal)
        }
    }

    /**
     * Update the ui state of the meal for the type
     *
     * @param meal The meal type the user ordered
     */
    fun getMeal(meal: String){
        _uiState.update{currentState ->
            currentState.copy(completeMeal = meal)
        }
    }

    /**
     * Resets the meal order
     */
    fun resetOrder(){
        //_uiState.value = PokemonUIState()
        _ingredientList = mutableListOf()
    }
}