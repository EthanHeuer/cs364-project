package com.example.gottaeatemall.ui.screens.TeamComponents

import androidx.lifecycle.ViewModel
import com.example.gottaeatemall.data.FakeDatabase
import com.example.gottaeatemall.data.PokemonSchema
import com.example.gottaeatemall.data.TeamPokemonSchema
import com.example.gottaeatemall.data.TeamSchema
import com.example.gottaeatemall.data.TeamUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the team screen
 */
class TeamViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(TeamUIState())
    val uiState: StateFlow<TeamUIState> = _uiState.asStateFlow()

    fun setActiveTeamId(teamId: Int){
        _uiState.update { uiState ->
            uiState.copy(activeTeamId = teamId)
        }
    }

    /**
     * Sets the team to be displayed
     * @param teamId The id of the team to be displayed
     */
    fun setTeam(teamId: Int) {
        println("setTeam $teamId")

        // get the team from the database
        val team = FakeDatabase.getInstance().querySelect<TeamSchema>(
            from = "teams",
            where = { it.id == teamId }
        ).first()

        // get the team pokemon from the database
        val teamPokemon = FakeDatabase.getInstance().querySelect<TeamPokemonSchema>(
            from = "team_pokemon",
            where = { it.teamId == team.id },
            orderBy = { it.slotId }
        )

        // get the pokemon from the database
        val pokemon = teamPokemon.map { slot ->
            FakeDatabase.getInstance().querySelect<PokemonSchema>(
                from = "pokemon",
                where = { it.id == slot.pokemonId }
            ).first()
        }

        // update the ui state
        _uiState.update { uiState ->
            uiState.copy(
                activeTeamId = team.id,
                name = team.name,
                pokemon = pokemon
            )
        }
    }
}