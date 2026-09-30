package com.example.mobileappclient.utils

import ads_mobile_sdk.nu
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult





object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl : String,
        context: Context
    ) : Bitmap? {
        val loader = ImageLoader(context = context)

        val request = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        val imageResult = loader.execute(request = request)

        return if(imageResult is SuccessResult){
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }


    /*
    fun extractColorFromBitmap(bitmap: Bitmap) : Map<String, String> {
        return mapOf(
            "vibrant" to parseColorSwatch(color = Palette.from(bitmap).generate().vibrantSwatch),
            "darkVibrant" to parseColorSwatch(color = Palette.from(bitmap).generate().vibrantSwatch),
            "onDarkVibrant" to parseBodyColor(color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor)
        )
    }
     */


    fun extractColorFromBitmap(bitmap: Bitmap): Map<String, String> {
        val palette = Palette.from(bitmap).generate()
        return mapOf(
            "vibrant" to parseColorSwatch(color = palette.vibrantSwatch),
            "darkVibrant" to parseColorSwatch(color = palette.darkVibrantSwatch),
            "onDarkVibrant" to parseBodyColor(color = palette.darkVibrantSwatch?.bodyTextColor)
        )
    }



    private fun parseColorSwatch(color : Palette.Swatch?) : String{
        return if(color != null){
            val parsedColor = Integer.toHexString(color.rgb)
            return "#$parsedColor"
        } else {
            "#000000"
        }
    }


    private fun parseBodyColor(color : Int?) : String {
        return if(color != null){
            val parsedColor = Integer.toHexString(color)
            "#$parsedColor"
        } else {
            "#FFFFFF"
        }
    }



}