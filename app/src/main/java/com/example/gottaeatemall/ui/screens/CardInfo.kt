package com.example.gottaeatemall.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gottaeatemall.R
import com.example.gottaeatemall.data.PokemonUIState

//class CardInfo(
//    pokemonUIState: PokemonUIState,
//    ctx: Context, onBitmapCreated: (bitmap: Bitmap) -> Unit
//) : LinearLayoutCompat(ctx)
//{
//    init {
//        val width = 600
//        val height = 670
//        val view = ComposeView(ctx)
//        view.visibility = View.GONE
//        view.layoutParams = LayoutParams(width, height)
//        this.addView(view)
//        view.setContent {
//            TrainerInfo(
//                gender = pokemonUIState.boy_or_girl ,
//                name = pokemonUIState.trainerName,
//                favEat = pokemonUIState.favoritePokemonEat,
//                favBattle = pokemonUIState.favoritePokemonUse,
//                totalCaught = pokemonUIState.totalCaught,
//                badges = pokemonUIState.badges
//            )
//        }
//        view.visibility = View.VISIBLE
//        viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                // val graphicUtils = GraphicUtils()
//                val bitmap = createBitmapFromView(view = view, width = width, height = height)
//                onBitmapCreated(bitmap)
//                viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })
//    }
//}

@Composable
fun TrainerInfo(
    gender:Boolean,
    name:String,
    favEat:String,
    favBattle:String,
    totalCaught:String,
    badges:List<String>
){
    Box(
        modifier = Modifier
    ){
        val id = (100000..999999).random()
        Image(
            painter = painterResource(id = R.drawable.card),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            text = "ID: ${id.toString()}",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 33.dp, top = 7.dp),
            fontSize = 5.sp
        )
        Text(
            text = name,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 12.dp, vertical = 18.dp),
            fontSize = 6.sp
        )
        Text(
            text = "Favorite Battle: $favBattle",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 12.dp)
                .padding(top = 40.dp),
            fontSize = 6.sp
        )
        Text(
            text = "Favorite Eat: $favEat",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 12.dp)
                .padding(top = 50.dp),
            fontSize = 6.sp
        )
        Text(
            text = "Total Caught: $totalCaught",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 12.dp)
                .padding(top = 36.dp),
            fontSize = 6.sp
        )
        if (gender){
            Image(
                painter = painterResource(id = R.drawable.boy),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(vertical = 25.dp)
                    .padding(end = 25.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.girl),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(vertical = 25.dp)
                    .padding(end= 25.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_boulder))) {
            Image(
                painter = painterResource(id = R.drawable.badge1),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 14.dp)
                    .padding(bottom = 5.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_cascade))){
            Image(
                painter = painterResource(id = R.drawable.badge2),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 5.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_thunder))){
            Image(
                painter = painterResource(id = R.drawable.badge3),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 25.dp)
                    .padding(bottom = 5.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_rainbow))){
            Image(
                painter = painterResource(id = R.drawable.badge4),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 28.dp)
                    .padding(bottom = 5.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_marsh))){
            Image(
                painter = painterResource(id = R.drawable.badge5),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 32.dp)
                    .padding(bottom = 5.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_soul))){
            Image(
                painter = painterResource(id = R.drawable.badge6),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 37.dp)
                    .padding(bottom = 5.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_volcano))){
            Image(
                painter = painterResource(id = R.drawable.badge7),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 42.dp)
                    .padding(bottom = 5.dp)
            )
        }
        if(badges.contains(stringResource(id = R.string.badge_earth))){
            Image(
                painter = painterResource(id = R.drawable.badge8),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 48.dp)
                    .padding(bottom = 5.dp)
            )
        }
    }
}

fun createBitmapFromView(view: View, width: Int, height: Int): Bitmap {
    view.layoutParams = LinearLayoutCompat.LayoutParams(
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT
    )

    view.measure(
        View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
    )

    view.layout(0, 0, width, height)

    val canvas = Canvas()
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    canvas.setBitmap(bitmap)
    view.draw(canvas)

    return bitmap
}

@Preview
@Composable
fun PreviewCard() {
    TrainerInfo(
        gender = true,
        name = "An",
        favEat = "Raichu",
        favBattle = "Pikachu",
        totalCaught = "365",
        badges=listOf("Boulder", "Thunder", "Rainbow", "Soul", "Volcano", "Earth")
    )
    //CardInfo(pokemonUIState = PokemonUIState(),LocalContext.current,{})
}