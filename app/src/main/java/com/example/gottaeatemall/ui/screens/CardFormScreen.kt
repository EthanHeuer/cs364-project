package com.example.gottaeatemall.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.R

/**
 * Card Form Screen, appear when users want to make a new card
 * @param modifier modifier object to customize
 * @param onBadgeChanged event when a badge is checked/unchecked
 * @param onGenderChanged event when gender is changed
 * @param onSubmitButtonClicked event when clicked on submit
 * @param badge_options options for badges
 */
@Composable
fun CardFormScreen(
    modifier:Modifier = Modifier,
    onSubmitButtonClicked: (name:String, numCaught:String, favEat: String, favBattle:String, id:Int)
    -> Unit ,
    onBadgeChanged: (String) -> Unit = {},
    onGenderChanged: (Boolean) ->Unit ={},
    badge_options: List<String>
)
{
    val focusManager = LocalFocusManager.current
    var name_input by remember { mutableStateOf("") }
    var num_caught by remember { mutableStateOf("") }
    var fav_battle by remember { mutableStateOf("") }
    var fav_eat by remember { mutableStateOf("") }
    var badge by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf(true) }
    val id = (100000..999999).random()

    LazyColumn(
        modifier = modifier
            .padding(50.dp)
            .fillMaxWidth()
    ) {
        item{
            Text(text = "Make your Trainer Card", fontSize = 40.sp)
        }
        item {
            EditTextField(
                label = R.string.trainer_name,
                value = name_input,
                onValueChange = {
                    name_input = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }
        item {
            EditTextField(
                label = R.string.num_caught,
                value = num_caught,
                onValueChange = { num_caught = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }
        item {
            EditTextField(
                label = R.string.fav_battle,
                value = fav_battle,
                onValueChange = { fav_battle = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }
        item {
            EditTextField(
                label = R.string.fav_eat,
                value = fav_eat,
                onValueChange = { fav_eat = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                )
            )
        }
        item{
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Are you a boy or a girl?",
                fontSize = 25.sp,
                modifier = Modifier.padding(5.dp)
            )
        }
        item {
            Row(){
                RadioButton(
                    selected = gender == true,
                    onClick = {
                        gender = true
                        onGenderChanged(true)
                    }
                )
                Text(stringResource(id = R.string.boy), fontSize = 15.sp, modifier = Modifier.padding(10.dp))
                RadioButton(
                    selected = gender == false,
                    onClick = {
                        gender = false
                        onGenderChanged(false)
                    }
                )
                Text(stringResource(id = R.string.girl), fontSize = 15.sp, modifier = Modifier.padding(10.dp))
            }
        }
        item{
            Text(
                text = "Choose your badges",
                fontSize = 25.sp
            )
        }
        badge_options.forEach { item ->
            item {
                val checked = remember { mutableStateOf(false)}
                Row {
                    Checkbox(
                        checked = checked.value,
                        onCheckedChange = {
                            badge = item
                            checked.value = it
                            onBadgeChanged(badge)
                        }
                    )
                    Text(
                        item,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }
        item {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                onClick = { onSubmitButtonClicked(name_input, num_caught, fav_eat, fav_battle, id) },
                modifier = Modifier.fillMaxWidth(),
                enabled = name_input.isNotEmpty() && num_caught.isNotEmpty() && fav_battle.isNotEmpty() && fav_eat.isNotEmpty()
            ) {
                Text(
                    stringResource(id = R.string.submit),
                    color = Color.White
                )
            }
        }
    }

}

/**
 * TextField for user to input
 * @param label StringRes object
 * @param value input value
 * @param onValueChange event when value is changed
 * @param modifier modifier object
 * @param keyboardActions Actions when certains button is clicked
 * @param keyboardOptions type of keyboard
 */
@Composable
fun EditTextField(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

/**
 * Preview of Card Form Screen
 */
@Preview
@Composable
fun FormPreview() {
    CardFormScreen(
        onSubmitButtonClicked = {name:String, numCaught:String, favEat:String, favBattle:String, id:Int -> {}},
        modifier = Modifier,
        badge_options = listOf("Boulder","Cascade","Thunder","Rainbow","Marsh","Soul","Volcano","Earth")
    )
}
