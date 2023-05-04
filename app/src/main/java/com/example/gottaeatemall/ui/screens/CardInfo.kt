package com.example.gottaeatemall.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.PokemonUIState

/**
 * Custom View
 * @param context context
 * @param attrs stores AttributeSet
 * @param defStyleAttr style of attributes
 */
class CardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {
    /**
     * Override the Content function
     */
    @Composable
    override fun Content() {
        // This is a ComposableUI function
    }

    /**
     * custom Content function
     */
    @Composable
    fun ContentPokemon(ui:PokemonUIState) {
        TrainerInfo(
            gender = ui.boy_or_girl,
            name = ui.trainerName,
            favEat = ui.favoritePokemonEat,
            favBattle = ui.favoritePokemonUse,
            totalCaught = ui.totalCaught,
            badges = ui.badges,
            id = ui.id
        )
    }
}

/**
 * class to generate bitmap
 */
class ImageUtils {
    companion object{
        fun generateBitmap(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(
                600,
                670,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            view.layout(
                view.left,
                view.top,
                view.right,
                view.bottom
            )
            val bgDrawable = view.background
            if (bgDrawable != null) {//has background drawable, then draw it on the canvas
                bgDrawable.draw(canvas) }
            else {  //does not have background drawable, then draw white background on the canvas
                canvas.drawColor(Color.WHITE)
            }// draw the view on the canvas
            view.draw(canvas)
            return bitmap
        }
    }
}


/**
 * generate a Trainer Card
 * @param modifier modifier object
 * @param gender gender of user
 * @param name user name
 * @param favBattle favorite pokemon for battle
 * @param favEat favorite pokemon to eat
 * @param totalCaught total caught Pokemon
 * @param badges list of badges
 * @param id Id of trainer
 */
@Composable
fun TrainerInfo(
    modifier: Modifier = Modifier,
    gender:Boolean,
    name:String,
    favEat:String,
    favBattle:String,
    totalCaught:String,
    badges:List<String>,
    id:Int
){
    Box(
        modifier = modifier
    ){
        //
        Image(
            painter = painterResource(id = R.drawable.card),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(500.dp)
        )
        Text(
            text = "ID: $id",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 75.dp, top = 110.dp),
            fontSize = 25.sp
        )
        Text(
            text = name,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 155.dp, start = 42.dp),
            fontSize = 25.sp
        )
        Text(
            text = "Favorite Battle: $favBattle",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 42.dp)
                .padding(top = 215.dp),
            fontSize = 25.sp
        )
        Text(
            text = "Favorite Eat: $favEat",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 42.dp)
                .padding(top = 250.dp),
            fontSize = 25.sp
        )
        Text(
            text = "Total Caught: $totalCaught",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 42.dp)
                .padding(bottom = 16.dp),
            fontSize = 25.sp
        )
        if (gender){
            Image(
                painter = painterResource(id = R.drawable.boy),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 175.dp)
                    .padding(end = 25.dp)
                    .size(150.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.girl),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 175.dp)
                    .padding(end = 25.dp)
                    .size(150.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_boulder))) {
            Image(
                painter = painterResource(id = R.drawable.badge1),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=38.dp)
                    .padding(top = 175.dp)
                    .padding(bottom = 30.dp)
                    .size(400.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_cascade))){
            Image(
                painter = painterResource(id = R.drawable.badge2),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=50.dp)
                    .padding(bottom = 30.dp)
                    .size(400.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_thunder))){
            Image(
                painter = painterResource(id = R.drawable.badge3),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=62.dp)
                    .padding(bottom = 30.dp)
                    .size(400.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_rainbow))){
            Image(
                painter = painterResource(id = R.drawable.badge4),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=68.dp)
                    .padding(bottom = 30.dp)
                    .size(400.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_marsh))){
            Image(
                painter = painterResource(id = R.drawable.badge5),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=72.dp)
                    .padding(bottom = 30.dp).size(400.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_soul))){
            Image(
                painter = painterResource(id = R.drawable.badge6),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=82.dp)
                    .padding(bottom = 30.dp).size(400.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_volcano))){
            Image(
                painter = painterResource(id = R.drawable.badge7),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=90.dp)
                    .padding(bottom = 30.dp).size(400.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_earth))){
            Image(
                painter = painterResource(id = R.drawable.badge8),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start=99.dp)
                    .padding(bottom = 30.dp).size(400.dp)
            )
        }
    }
}

/**
 * preview of TrainerInfo function
 */
@Preview
@Composable
fun PreviewCard() {
    TrainerInfo(
        id = 999999,
        gender = true,
        name = "An",
        favEat = "Raichu",
        favBattle = "Pikachu",
        totalCaught = "365",
        badges=listOf("Boulder", "Thunder", "Rainbow", "Soul", "Volcano", "Earth")
    )
}