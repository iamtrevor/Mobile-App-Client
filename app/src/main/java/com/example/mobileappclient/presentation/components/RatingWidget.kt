package com.example.mobileappclient.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import com.example.mobileappclient.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mobileappclient.ui.theme.EXTRA_SMALL_PADDING
import com.example.mobileappclient.ui.theme.LightGray
import com.example.mobileappclient.ui.theme.StarColor
import kotlin.math.roundToInt

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating : Double,
    scaleFactor: Float = 3f,
    spaceBetween : Dp = EXTRA_SMALL_PADDING
){

    val result = calculatorStars(rating = rating)

    //val result = remember(rating) { calculatorStars(rating) }

    val starPathString = stringResource(id = R.string.star_path)

    //turn raw svg string data into a jetpack compose Path object
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }

    val starPathBounds = remember { starPath.getBounds() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {

        result["filledStars"]?.let {
            repeat(it){
                FilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }

        result["halfFilledStars"]?.let {
            repeat(it){
                HalfFilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }


        result["emptyStars"]?.let {
            repeat(it){
                EmptyStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }

    }

}


@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: androidx.compose.ui.geometry.Rect,
    scaleFactor: Float
){
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size
        scale(scale = scaleFactor){
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height

            val left = (canvasSize.width / 2f) - ( pathWidth / 1.7f )
            val top = (canvasSize.height / 2f) - ( pathHeight / 1.7f )

            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = StarColor
                )
            }

        }
    }
}




@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBounds: androidx.compose.ui.geometry.Rect,
    scaleFactor: Float
){
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size
        scale(scale = scaleFactor){
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height

            val left = (canvasSize.width / 2f) - ( pathWidth / 1.7f )
            val top = (canvasSize.height / 2f) - ( pathHeight / 1.7f )

            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(alpha = 0.5f)
                )

                clipPath(path = starPath){
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBounds.maxDimension / 1.7f,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }


            }

        }
    }
}



@Composable
fun EmptyStar(
    starPath: Path,
    starPathBounds: androidx.compose.ui.geometry.Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size
        scale(scale = scaleFactor){
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height

            val left = (canvasSize.width / 2f) - ( pathWidth / 1.7f )
            val top = (canvasSize.height / 2f) - ( pathHeight / 1.7f )

            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(alpha = 0.5f)
                )
            }

        }
    }
}





@Composable
fun calculatorStars(rating : Double) : Map<String, Int>{
    val maxStars by remember { mutableStateOf(5) }

    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }


    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating.toString().split(".").map { it.toInt() }

        if(firstNumber in 0..5 && lastNumber in 0..9){
            filledStars = firstNumber

            if(lastNumber in 1..5){
                halfFilledStars++
            }

            if(lastNumber in 6..9){
                filledStars++
            }

            if(firstNumber == 5 && lastNumber > 0){
                emptyStars = 5
                filledStars = 0
                halfFilledStars = 0
            }

        } else {
            Log.d("RatingWidget", "Invalid Rating Number")
        }
    }

    emptyStars = maxStars - (filledStars + halfFilledStars)

    return mapOf(
        "filledStars" to filledStars,
        "halfFilledStars" to halfFilledStars,
        "emptyStars" to emptyStars
    )

}






/*
fun calculatorStars(rating: Double, maxStars: Int = 5): Map<String, Int> {
    val tenths = (rating * 10).roundToInt()

    if (tenths !in 0..(maxStars * 10)) {
        Log.d("RatingWidget", "Invalid Rating Number")
        return mapOf("filledStars" to 0, "halfFilledStars" to 0, "emptyStars" to maxStars)
    }

    var filled = tenths / 10
    val decimal = tenths % 10
    val half = if (decimal in 1..5) 1 else 0
    if (decimal >= 6) filled++

    return mapOf(
        "filledStars" to filled,
        "halfFilledStars" to half,
        "emptyStars" to maxStars - filled - half
    )
}


 */











