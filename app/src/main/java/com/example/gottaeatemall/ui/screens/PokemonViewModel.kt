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
    var _badge = mutableListOf<String>()

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
     * Resets the meal order
     */
    fun resetOrder(){
        //_uiState.value = PokemonUIState()
        _ingredientList = mutableListOf()
    }

    /**
     * set a badge into the list of badges
     * @param badge to set
     */
    fun setBadge(badge:String) {
        if (_badge.contains(badge)){
            _badge.remove(badge)
            _uiState.update { currentState -> currentState.copy(badges = _badge) }
        } else {
            _badge.add(badge)
            _uiState.update { currentState -> currentState.copy(badges = _badge)}
        }
    }

    /**
     * reset list of badges
     */
    fun resetBadge() {
        _badge = mutableListOf()
    }

    /**
     * set name of trainer
     * @param trainer name to set
     */
    fun setName(trainer:String) {
        _uiState.update { currentState -> currentState.copy(trainerName = trainer) }
    }

    /**
     * set id
     * @param id Id to set
     */
    fun setID(id:Int) {
        _uiState.update { currentState -> currentState.copy(id = id) }
    }

    /**
     * set total number of pokemon caught
     * @param numCaught number to set
     */
    fun setNumCaught(numCaught:String) {
        _uiState.update { currentState -> currentState.copy(totalCaught = numCaught) }
    }

    /**
     * set favorite pokemon for battle
     * @param favBattle string to set
     */
    fun setFavBattle(favBattle:String) {
        _uiState.update { currentState -> currentState.copy(favoritePokemonUse = favBattle) }
    }

    /**
     * set favorite pokemon to eat
     * @param favEat string to set
     */
    fun setFavEat(favEat:String) {
        _uiState.update { currentState -> currentState.copy(favoritePokemonEat = favEat) }
    }

    /**
     * set gender of trainer
     * @param gender boolean to set
     */
    fun setGender(gender:Boolean) {
        _uiState.update { currentState -> currentState.copy(boy_or_girl = gender) }
    }
}