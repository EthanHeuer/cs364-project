package com.example.gottaeatemall.ui.screens

import androidx.compose.ui.graphics.ImageBitmap
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
    var _badge = mutableListOf<String>()
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

    fun setBadge(badge:String) {
        if (_badge.contains(badge)){
            _badge.remove(badge)
            _uiState.update { currentState -> currentState.copy(badges = _badge) }
        } else {
            _badge.add(badge)
            _uiState.update { currentState -> currentState.copy(badges = _badge)}
        }
    }
    fun resetOrder(){
        //_uiState.value = PokemonUIState()
        _ingredientList = mutableListOf()
    }
    fun resetBadge() {
        _badge = mutableListOf()
    }
    fun setName(trainer:String) {
        _uiState.update { currentState -> currentState.copy(trainerName = trainer) }
    }
//    fun setPlayTime(playtime:String) {
//        _uiState.update { currentState -> currentState.copy(playtime = playtime) }
//    }
    fun setNumCaught(numCaught:String) {
        _uiState.update { currentState -> currentState.copy(totalCaught = numCaught) }
    }
    fun setFavBattle(favBattle:String) {
        _uiState.update { currentState -> currentState.copy(favoritePokemonUse = favBattle) }
    }
    fun setFavEat(favEat:String) {
        _uiState.update { currentState -> currentState.copy(favoritePokemonEat = favEat) }
    }
    fun setGender(gender:Boolean) {
        _uiState.update { currentState -> currentState.copy(boy_or_girl = gender) }
    }
}
