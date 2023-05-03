package com.example.gottaeatemall

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.gottaeatemall.data.FakeDatabase
import com.example.gottaeatemall.data.INITIALIZE_DATA
import com.example.gottaeatemall.data.TeamPokemonSchema
import com.example.gottaeatemall.data.TeamSchema
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}

class TeamBuilderTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }

            INITIALIZE_DATA()

            FakeDatabase.getInstance().queryDelete<TeamSchema>(
                from = "teams"
            )

            FakeDatabase.getInstance().queryDelete<TeamPokemonSchema>(
                from = "team_pokemon"
            )

            FakeDatabase.getInstance().queryInsert(
                into = "teams",
                values = TeamSchema(
                    id = 1,
                    name = "Team 1"
                )
            )

            FakeDatabase.getInstance().queryInsert(
                into = "teams",
                values = TeamSchema(
                    id = 2,
                    name = "Team 2"
                )
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 1, teamId = 1, slotId = 1, pokemonId = 1)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 2, teamId = 1, slotId = 2, pokemonId = 2)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 3, teamId = 1, slotId = 3, pokemonId = 3)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 4, teamId = 1, slotId = 4, pokemonId = 4)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 5, teamId = 1, slotId = 5, pokemonId = 5)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 6, teamId = 1, slotId = 6, pokemonId = 6)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 7, teamId = 2, slotId = 1, pokemonId = 7)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 8, teamId = 2, slotId = 2, pokemonId = 8)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 9, teamId = 2, slotId = 3, pokemonId = 9)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 10, teamId = 2, slotId = 4, pokemonId = 10)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 11, teamId = 2, slotId = 5, pokemonId = 11)
            )

            FakeDatabase.getInstance().queryInsert(
                into = "team_pokemon",
                values = TeamPokemonSchema(id = 12, teamId = 2, slotId = 6, pokemonId = 12)
            )

            App(navController = navController)
        }
    }

    /**
     * Navigate to the Team Builder
     */
    @Test
    fun appNavHost_navigateToTeamBuilder() {
        // click the team button
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // check that the team builder is open
        navController.assertCurrentRouteName(AppScreen.Team.name)
    }

    /**
     * Navigate to the Create Team Form
     */
    @Test
    fun appNavHost_navigateCreateTeamForm() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the menu toggle button to open the teams drawer
        composeTestRule.onNodeWithContentDescription("Menu Toggle").performClick()
        // click the new team button
        composeTestRule.onNodeWithStringId(R.string.new_team).performClick()
        // check that the create team form is open
        navController.assertCurrentRouteName(AppScreen.TeamForm.name)
    }

    /**
     * Navigate to the Edit Team Form
     */
    @Test
    fun appNavHost_navigateTeamEditForm() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the edit team button
        composeTestRule.onNodeWithContentDescription("Edit Team").performClick()
        // check that the edit team form is open
        navController.assertCurrentRouteName(AppScreen.TeamFormEdit.name)
    }

    /**
     * Navigate to Team Edit Form then cancel
     */
    @Test
    fun appNavHost_navigateTeamEditForm_cancel() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the edit team button
        composeTestRule.onNodeWithContentDescription("Edit Team").performClick()
        // click the cancel button
        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()
        // check that the team builder is open
        navController.assertCurrentRouteName(AppScreen.Team.name)
    }

    /**
     * Navigate to the Team Edit Form then save
     */
    @Test
    fun appNavHost_navigateTeamEditForm_save() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the edit team button
        composeTestRule.onNodeWithContentDescription("Edit Team").performClick()
        // click the save button
        composeTestRule.onNodeWithStringId(R.string.save).performClick()
        // check that the team builder is open
        navController.assertCurrentRouteName(AppScreen.Team.name)
    }


    /**
     * Test clicking the Delete team and opening the dialog
     */
    @Test
    fun appNavHost_navigateTeamDeleteDialog() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the delete team button
        composeTestRule.onNodeWithContentDescription("Delete Team").performClick()
        // check that the delete team dialog is open
        composeTestRule.onNodeWithStringId(R.string.delete_dialog_title).assertExists()
    }

    /**
     * Switch the current team
     */
    @Test
    fun appNavHost_navigateSwitchTeam() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the menu toggle button to open the teams drawer
        composeTestRule.onNodeWithContentDescription("Menu Toggle").performClick()
        // click Team 3
        composeTestRule.onNodeWithText("Team 2").performClick()
        // check that pokemon 7 (Squirtle) of Team 2 exists
        composeTestRule.onNodeWithText("Squirtle").assertExists()
    }

    /**
     * Test deleting a team
     */
    @Test
    fun appNavHost_actionDeleteTeam() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the delete team button
        composeTestRule.onNodeWithContentDescription("Delete Team").performClick()
        // click the delete button
        composeTestRule.onNodeWithStringId(R.string.confirm).performClick()
        // check that the team was deleted
        composeTestRule.onNodeWithText("Team 1").assertDoesNotExist()
    }

    /**
     * Test editing a team
     */
    @Test
    fun appNavHost_actionEditTeam() {
        // navigate to team builder
        composeTestRule.onNodeWithStringId(R.string.team).performClick()
        // click the edit team button
        composeTestRule.onNodeWithContentDescription("Edit Team").performClick()
        // change the team name
        composeTestRule.onNodeWithText("Team Name").performTextInput(" Edited")
        // click the save button
        composeTestRule.onNodeWithStringId(R.string.save).performClick()
        // check that the team name was changed
        composeTestRule.onNodeWithText("Team 1").assertDoesNotExist()
    }
}